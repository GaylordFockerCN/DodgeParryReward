package com.p1nero.dpr.skill;

import com.p1nero.dpr.gameassets.DPRDatakeys;
import com.p1nero.dpr.mixin.MobEffectInstanceAccessor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class RewardSkill extends Skill {

    protected int duration;
    protected int amplifier;
    protected boolean ambient;
    protected boolean visible;
    protected boolean showIcon = true;
    protected MobEffect cache;

    @Nullable
    protected Supplier<MobEffect> mobEffectSupplier;
    protected int delay;
    @Nullable
    protected Consumer<PlayerPatch<?>> playerPatchConsumer;
    protected final UUID eventUuid;
    protected final ResourceLocation sKillTexture;
    protected ResourceLocation effectTexture;

    public static Builder createParryRewardSkill() {
        return new Builder().setCategory(SkillCategories.PASSIVE).setResource(Resource.NONE);
    }

    public RewardSkill(Builder builder) {
        super(builder);
        duration = builder.effectDuration;
        amplifier = builder.effectAmplifier;
        mobEffectSupplier = builder.mobEffectSupplier;
        delay = builder.delay;
        playerPatchConsumer = builder.playerPatchConsumer;
        sKillTexture = builder.sKillTexture;
        eventUuid = builder.uuid;
    }

    @Override
    public void setParams(CompoundTag parameters) {
        super.setParams(parameters);
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
                if(cache == null) {
                    cache = ForgeRegistries.MOB_EFFECTS.getValue(ResourceLocation.parse(parameters.getString("effect_override")));
                }
                return cache;
            };
        }
    }

    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);
        int delayTimer = container.getDataManager().getDataValue(DPRDatakeys.DELAY_TIMER.get());
        if(delayTimer > 0) {
            if(!container.getExecutor().isLogicalClient()) {
                container.getDataManager().setDataSync(DPRDatakeys.DELAY_TIMER.get(), delayTimer - 1);
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
                container.getDataManager().setDataSync(DPRDatakeys.DELAY_TIMER.get(), delay);
            }
        } else {
            if (playerPatchConsumer != null) {
                playerPatchConsumer.accept(container.getExecutor());
            }
        }
        if (mobEffectSupplier != null && player instanceof ServerPlayer serverPlayer) {
            MobEffect mobEffect = mobEffectSupplier.get();
            MobEffectInstance instance = player.getEffect(mobEffect);
            if(instance != null) {
                ((MobEffectInstanceAccessor) instance).setDuration(duration);
                serverPlayer.connection.send(new ClientboundUpdateMobEffectPacket(serverPlayer.getId(), instance));
            }
            player.addEffect(new MobEffectInstance(mobEffect, duration, amplifier, ambient, visible, showIcon));

        }
    }

    /**
     * 有效果直接读效果贴图
     */
    @OnlyIn(Dist.CLIENT)
    @Override
    public ResourceLocation getSkillTexture() {
        if (mobEffectSupplier != null) {
            if (effectTexture != null) {
                return ResourceLocation.fromNamespaceAndPath(effectTexture.getNamespace(), "textures/mob_effect/" + effectTexture.getPath() + ".png");
            }
            MobEffect mobEffect = mobEffectSupplier.get();
            effectTexture = ForgeRegistries.MOB_EFFECTS.getKey(mobEffect);
        }
        if (sKillTexture != null) {
            return sKillTexture;
        }
        return super.getSkillTexture();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public List<Object> getTooltipArgsOfScreen(List<Object> list) {
        list.add(amplifier);
        if (mobEffectSupplier != null) {
            list.add(mobEffectSupplier.get().getDisplayName().copy().withStyle(Style.EMPTY.withColor(mobEffectSupplier.get().getColor()).withBold(true)));
        }
        list.add(duration);
        return list;
    }

    public static class Builder extends SkillBuilder<RewardSkill> {
        private int effectDuration;
        protected int effectAmplifier;
        private Supplier<MobEffect> mobEffectSupplier;
        private int delay;
        private Consumer<PlayerPatch<?>> playerPatchConsumer;
        private UUID uuid = UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114514");
        protected ResourceLocation sKillTexture;

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

        public Builder setMobEffectSupplier(Supplier<MobEffect> mobEffectSupplier) {
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

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }
    }

}
