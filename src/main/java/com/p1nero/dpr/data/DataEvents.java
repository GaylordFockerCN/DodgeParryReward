package com.p1nero.dpr.data;

import com.p1nero.dpr.DodgeParryRewardMod;
import com.yesman.epicskills.common.data.SkillTreeProvider;
import net.minecraft.data.DataProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = DodgeParryRewardMod.MOD_ID, bus = Bus.MOD)
public final class DataEvents {

    @SubscribeEvent
    public static void sword_soaring$gatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(true, (DataProvider.Factory<SkillTreeProvider>) DPRSkillTreeProvider::new);
    }
}
