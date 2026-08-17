package com.p1nero.dpr;

import com.p1nero.dpr.gameassets.DPRDatakeys;
import com.p1nero.dpr.gameassets.DPRSkills;
import com.p1nero.dpr.mob_effect.DPRMobEffects;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DodgeParryRewardMod.MOD_ID)
public class DodgeParryRewardMod {

    public static final String MOD_ID = "dodge_parry_reward";

    public DodgeParryRewardMod(IEventBus modEventBus) {
        DPRMobEffects.REGISTRY.register(modEventBus);
        DPRDatakeys.DATA_KEYS.register(modEventBus);
        DPRSkills.SKILLS.register(modEventBus);
    }

}
