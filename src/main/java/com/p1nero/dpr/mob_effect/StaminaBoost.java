package com.p1nero.dpr.mob_effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

/**
 * The +max-stamina buff granted by the stamina2/3/4 rewards.
 *
 * <p>The original 1.20.1 version overrode {@code removeAttributeModifiers(LivingEntity,
 * AttributeMap, int)} to immediately clamp the player's current stamina down if it was sitting
 * above the reverted (lower) max once the buff expired. That override point no longer exists in
 * 1.21.1 - {@code MobEffect#removeAttributeModifiers} now only receives the {@link
 * net.minecraft.world.entity.ai.attributes.AttributeMap}, with no way back to the owning entity.
 *
 * <p>It is not needed anymore either: Epic Fight 1.21.1's own
 * {@code PlayerPatch#preTickServer()} already does the equivalent clamp unconditionally every
 * server tick ({@code if (maxStamina < stamina) this.setStamina(maxStamina);}), so any transient
 * overflow self-corrects within a single tick regardless. Verified by decompiling
 * epic-fight-21.17.3.1-mc1.21.1-neoforge.jar.
 */
public class StaminaBoost extends MobEffect {
    protected StaminaBoost(int amp) {
        super(MobEffectCategory.BENEFICIAL, amp);
    }
}
