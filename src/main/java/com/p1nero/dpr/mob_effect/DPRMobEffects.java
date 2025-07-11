package com.p1nero.dpr.mob_effect;

import com.p1nero.dpr.DodgeParryRewardMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

public class DPRMobEffects {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DodgeParryRewardMod.MOD_ID);
    public static final RegistryObject<MobEffect> STAMINA_BOOST = REGISTRY.register("stamina_boost",() -> new StaminaBoost(0X6c6a5c)
            .addAttributeModifier(EpicFightAttributes.MAX_STAMINA.get(), "5D6F0BA2-1145-14AC-B896-C61C5CEE99CC", 3.0F, AttributeModifier.Operation.ADDITION));
}
