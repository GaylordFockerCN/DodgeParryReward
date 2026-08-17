package com.p1nero.dpr.mixin;

import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Lets {@link com.p1nero.dpr.skill.RewardSkill} refresh the duration of an already-applied
 * effect instance in place (so re-triggering a reward while the previous one is still running
 * resets the timer instead of stacking a second, separate instance).
 */
@Mixin(MobEffectInstance.class)
public interface MobEffectInstanceAccessor {
    @Accessor("duration")
    void setDuration(int duration);
}
