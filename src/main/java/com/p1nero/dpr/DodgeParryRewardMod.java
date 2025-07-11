package com.p1nero.dpr;

import com.p1nero.dpr.mob_effect.DPRMobEffects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DodgeParryRewardMod.MOD_ID)
public class DodgeParryRewardMod {

    public static final String MOD_ID = "dodge_parry_reward";

    public DodgeParryRewardMod(FMLJavaModLoadingContext context) {
        IEventBus eventBus = context.getModEventBus();
        DPRMobEffects.REGISTRY.register(eventBus);
    }

}
