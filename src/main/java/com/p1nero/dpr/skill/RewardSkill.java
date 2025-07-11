package com.p1nero.dpr.skill;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
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

    protected int effectDuration;
    protected int effectAmplifier;
    @Nullable
    protected Supplier<MobEffect> mobEffectSupplier;
    @Nullable
    protected Consumer<PlayerPatch<?>> playerPatchConsumer;
    protected final UUID EVENT_UUID;
    protected final ResourceLocation sKillTexture;

    public static Builder createParryRewardSkill() {
        return new Builder().setCategory(SkillCategories.PASSIVE).setResource(Resource.NONE);
    }

    public RewardSkill(Builder builder) {
        super(builder);
        effectDuration = builder.effectDuration;
        effectAmplifier = builder.effectAmplifier;
        mobEffectSupplier = builder.mobEffectSupplier;
        playerPatchConsumer = builder.playerPatchConsumer;
        sKillTexture = builder.sKillTexture;
        EVENT_UUID = builder.uuid;
    }

    @Override
    public void setParams(CompoundTag parameters) {
        super.setParams(parameters);
        if(parameters.contains("effect_duration")) {
            effectDuration = parameters.getInt("effect_duration");
        }
        if(parameters.contains("effect_amplifier")) {
            effectDuration = parameters.getInt("effect_amplifier");
        }
    }

    public void apply(SkillContainer container) {
        if(mobEffectSupplier != null && !container.getExecutor().isLogicalClient()) {
            container.getExecutor().getOriginal().addEffect(new MobEffectInstance(mobEffectSupplier.get(), effectDuration, effectAmplifier));
        }
        if(playerPatchConsumer != null) {
            playerPatchConsumer.accept(container.getExecutor());
        }
    }

    /**
     * 有效果直接读效果贴图
     */
    @OnlyIn(Dist.CLIENT)
    @Override
    public ResourceLocation getSkillTexture() {
        if(mobEffectSupplier != null) {
            MobEffect mobEffect = mobEffectSupplier.get();
            ResourceLocation resourceLocation = ForgeRegistries.MOB_EFFECTS.getKey(mobEffect);
            if(resourceLocation != null) {
                return ResourceLocation.fromNamespaceAndPath(resourceLocation.getNamespace(), "textures/mob_effect/" + resourceLocation.getPath() + ".png");
            }
        }
        if(sKillTexture != null) {
            return sKillTexture;
        }
        return super.getSkillTexture();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public List<Object> getTooltipArgsOfScreen(List<Object> list) {
        list.add(effectAmplifier);
        if(mobEffectSupplier != null) {
            list.add(mobEffectSupplier.get().getDisplayName());
        }
        list.add(effectDuration);
        return list;
    }

    public static class Builder extends SkillBuilder<RewardSkill>{
        private int effectDuration;
        protected int effectAmplifier;
        private Supplier<MobEffect> mobEffectSupplier;
        private Consumer<PlayerPatch<?>> playerPatchConsumer;
        private UUID uuid = UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114514");
        protected ResourceLocation sKillTexture;

        public Builder setEffectDuration(int effectDuration) {
            this.effectDuration = effectDuration;
            return this;
        }

        public Builder setsKillTexture(ResourceLocation sKillTexture) {
            this.sKillTexture = sKillTexture;
            return this;
        }

        public Builder setEffectAmplifier(int effectAmplifier) {
            this.effectAmplifier = effectAmplifier;
            return this;
        }

        public Builder setMobEffectSupplier(Supplier<MobEffect> mobEffectSupplier) {
            this.mobEffectSupplier = mobEffectSupplier;
            return this;
        }

        public Builder setPlayerPatchConsumer(Consumer<PlayerPatch<?>> playerPatchConsumer) {
            this.playerPatchConsumer = playerPatchConsumer;
            return this;
        }

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }
    }

}
