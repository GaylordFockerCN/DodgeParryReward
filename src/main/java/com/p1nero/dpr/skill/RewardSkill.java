package com.p1nero.dpr.skill;

import com.p1nero.dpr.gameassets.DPRDatakeys;
import com.p1nero.dpr.mixin.MobEffectInstanceAccessor;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import yesman.epicfight.api.utils.side.ClientOnly;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class RewardSkill extends Skill {

    protected int duration;
    protected int amplifier;
    protected boolean ambient;
    protected boolean visible;
    protected boolean showIcon = true;
    @Nullable
    protected Holder<MobEffect> effectOverrideCache;

    @Nullable
    protected Supplier<Holder<MobEffect>> mobEffectSupplier;
    protected int delay;
    @Nullable
    protected Consumer<PlayerPatch<?>> playerPatchConsumer;
    protected final ResourceLocation sKillTexture;
    protected ResourceLocation effectTexture;

    public static Builder createParryRewardSkill(Function<Builder, ? extends RewardSkill> constructor) {
        return new Builder(constructor).setCategory(SkillCategories.PASSIVE).setResource(Resource.NONE);
    }

    public RewardSkill(Builder builder) {
        super(builder);
        duration = builder.effectDuration;
        amplifier = builder.effectAmplifier;
        mobEffectSupplier = builder.mobEffectSupplier;
        delay = builder.delay;
        playerPatchConsumer = builder.playerPatchConsumer;
        sKillTexture = builder.sKillTexture;
    }

    @Override
    public void loadDatapackParameters(CompoundTag parameters) {
        super.loadDatapackParameters(parameters);
        if (parameters.contains("duration")) {
            duration = parameters.getInt("duration");
        }
        if (parameters.contains("amplifier")) {
            amplifier = parameters.getInt("amplifier");
        }
        if (parameters.contains("ambient")) {
            ambient = parameters.getBoolean("ambient");
        }
        if (parameters.contains("visible")) {
            visible = parameters.getBoolean("visible");
        }
        if (parameters.contains("showIcon")) {
            showIcon = parameters.getBoolean("showIcon");
        }
        if(parameters.contains("effect_override")) {
            mobEffectSupplier = () -> {
                if(effectOverrideCache == null) {
                    ResourceLocation id = ResourceLocation.parse(parameters.getString("effect_override"));
                    effectOverrideCache = BuiltInRegistries.MOB_EFFECT.getHolder(ResourceKey.create(Registries.MOB_EFFECT, id)).orElseThrow();
                }
                return effectOverrideCache;
            };
        }
    }

    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);
        int delayTimer = container.getDataManager().getDataValue(DPRDatakeys.DELAY_TIMER);
        if(delayTimer > 0) {
            if(!container.getExecutor().isLogicalClient()) {
                container.getDataManager().setDataSync(DPRDatakeys.DELAY_TIMER, delayTimer - 1);
            }
            if (delayTimer == 1 && playerPatchConsumer != null) {
                playerPatchConsumer.accept(container.getExecutor());
            }
        }
    }

    public void apply(SkillContainer container) {
        Player player = container.getExecutor().getOriginal();
        if(delay > 0) {
            if(player instanceof ServerPlayer) {
                container.getDataManager().setDataSync(DPRDatakeys.DELAY_TIMER, delay);
            }
        } else {
            if (playerPatchConsumer != null) {
                playerPatchConsumer.accept(container.getExecutor());
            }
        }
        if (mobEffectSupplier != null && player instanceof ServerPlayer serverPlayer) {
            Holder<MobEffect> mobEffect = mobEffectSupplier.get();
            MobEffectInstance instance = player.getEffect(mobEffect);
            if(instance != null) {
                ((MobEffectInstanceAccessor) instance).setDuration(duration);
                // 3rd arg is the 1.21+ "blend" flag (smooth visual transition between effect
                // states) - false matches the old 1.20.1 behavior, which predates blending.
                serverPlayer.connection.send(new ClientboundUpdateMobEffectPacket(serverPlayer.getId(), instance, false));
            }
            player.addEffect(new MobEffectInstance(mobEffect, duration, amplifier, ambient, visible, showIcon));

        }
    }

    /**
     * 有效果直接读效果贴图
     */
    @ClientOnly
    @Override
    public ResourceLocation getSkillTexture() {
        if (mobEffectSupplier != null) {
            if (effectTexture == null) {
                Holder<MobEffect> mobEffect = mobEffectSupplier.get();
                ResourceLocation effectId = BuiltInRegistries.MOB_EFFECT.getKey(mobEffect.value());
                if(effectId != null) {
                    effectTexture = ResourceLocation.fromNamespaceAndPath(effectId.getNamespace(), "textures/mob_effect/" + effectId.getPath() + ".png");
                    return effectTexture;
                }
            } else {
                return effectTexture;
            }
        }
        if (sKillTexture != null) {
            return sKillTexture;
        }
        return super.getSkillTexture();
    }

    @ClientOnly
    @Override
    public List<Object> getTooltipArgsOfScreen(List<Object> list) {
        list.add(amplifier);
        if (mobEffectSupplier != null) {
            MobEffect mobEffect = mobEffectSupplier.get().value();
            list.add(mobEffect.getDisplayName().copy().withStyle(Style.EMPTY.withColor(mobEffect.getColor()).withBold(true)));
        }
        list.add(duration);
        return list;
    }

    public static class Builder extends SkillBuilder<Builder> {
        private int effectDuration;
        protected int effectAmplifier;
        private Supplier<Holder<MobEffect>> mobEffectSupplier;
        private int delay;
        private Consumer<PlayerPatch<?>> playerPatchConsumer;
        protected ResourceLocation sKillTexture;

        public Builder(Function<Builder, ? extends RewardSkill> constructor) {
            super(constructor);
        }

        public Builder setEffectDuration(int effectDuration) {
            this.effectDuration = effectDuration;
            return this;
        }

        public Builder setEffectAmplifier(int effectAmplifier) {
            this.effectAmplifier = effectAmplifier;
            return this;
        }

        public Builder setsKillTexture(ResourceLocation sKillTexture) {
            this.sKillTexture = sKillTexture;
            return this;
        }

        public Builder setMobEffectSupplier(Supplier<Holder<MobEffect>> mobEffectSupplier) {
            this.mobEffectSupplier = mobEffectSupplier;
            return this;
        }

        public Builder setWhenExecute(Consumer<PlayerPatch<?>> playerPatchConsumer) {
            this.playerPatchConsumer = playerPatchConsumer;
            return this;
        }

        public Builder setWhenExecute(Consumer<PlayerPatch<?>> playerPatchConsumer, int delay) {
            this.playerPatchConsumer = playerPatchConsumer;
            this.delay = delay;
            return this;
        }
    }

}
