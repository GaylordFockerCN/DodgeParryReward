package com.p1nero.dpr.mob_effect;

import com.p1nero.dpr.DodgeParryRewardMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import yesman.epicfight.registry.entries.EpicFightAttributes;

public class DPRMobEffects {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, DodgeParryRewardMod.MOD_ID);
    public static final DeferredHolder<MobEffect, StaminaBoost> STAMINA_BOOST = REGISTRY.register("stamina_boost", () -> {
        // addAttributeModifier() returns the MobEffect supertype (not a self-type), so it can't
        // be chained here without widening the inferred Supplier<StaminaBoost> to Supplier<MobEffect>.
        StaminaBoost effect = new StaminaBoost(0X6c6a5c);
        effect.addAttributeModifier(EpicFightAttributes.MAX_STAMINA, ResourceLocation.fromNamespaceAndPath(DodgeParryRewardMod.MOD_ID, "stamina_boost"), 3.0, AttributeModifier.Operation.ADD_VALUE);
        return effect;
    });
}
