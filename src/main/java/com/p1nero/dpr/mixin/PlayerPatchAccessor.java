package com.p1nero.dpr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

@Mixin(value = PlayerPatch.class, remap = false)
public interface PlayerPatchAccessor {
    @Accessor("tickSinceLastAction")
    void setTickSinceLastAction(int tickSinceLastAction);
}
