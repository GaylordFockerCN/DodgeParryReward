package com.p1nero.dpr.mob_effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import org.jetbrains.annotations.NotNull;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

public class StaminaBoost extends MobEffect {
    protected StaminaBoost(int amp) {
        super(MobEffectCategory.BENEFICIAL, amp);
    }

    public void removeAttributeModifiers(@NotNull LivingEntity livingEntity, @NotNull AttributeMap attributeMap, int amp) {
        super.removeAttributeModifiers(livingEntity, attributeMap, amp);
        PlayerPatch<?> playerPatch = EpicFightCapabilities.getEntityPatch(livingEntity, PlayerPatch.class);
        if(playerPatch != null) {
            float maxStamina = (float) livingEntity.getAttribute(EpicFightAttributes.MAX_STAMINA.get()).getValue();
            if (playerPatch.getStamina() > maxStamina) {
                playerPatch.setStamina(maxStamina);
            }

        }
    }

}
