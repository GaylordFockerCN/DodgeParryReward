package com.p1nero.dpr.gameassets;

import com.p1nero.dpr.DodgeParryRewardMod;
import com.p1nero.dpr.skill.RewardSkill;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import yesman.epicfight.registry.EpicFightRegistries;
import yesman.epicfight.skill.SkillDataKey;

public class DPRDatakeys {
    public static final DeferredRegister<SkillDataKey<?>> DATA_KEYS = DeferredRegister.create(EpicFightRegistries.SKILL_DATA_KEY, DodgeParryRewardMod.MOD_ID);

    public static final DeferredHolder<SkillDataKey<?>, SkillDataKey<Integer>> DELAY_TIMER = DATA_KEYS.register("delay_timer", () ->
            SkillDataKey.createSkillDataKey(ByteBufCodecs.INT, 0, false, RewardSkill.class));
}
