package com.p1nero.dpr.gameassets;

import com.p1nero.dpr.DodgeParryRewardMod;
import com.p1nero.dpr.skill.RewardSkill;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.api.utils.PacketBufferCodec;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.skill.SkillDataKey;

public class DPRDatakeys {
    public static final DeferredRegister<SkillDataKey<?>> DATA_KEYS = DeferredRegister.create(ResourceLocation.fromNamespaceAndPath(EpicFightMod.MODID, "skill_data_keys"), DodgeParryRewardMod.MOD_ID);

    public static final RegistryObject<SkillDataKey<Integer>> DELAY_TIMER = DATA_KEYS.register("delay_timer", () ->
            SkillDataKey.createSkillDataKey(PacketBufferCodec.INTEGER, 0, false, RewardSkill.class));
}
