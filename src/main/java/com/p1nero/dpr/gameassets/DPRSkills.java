package com.p1nero.dpr.gameassets;

import com.p1nero.dpr.DodgeParryRewardMod;
import com.p1nero.dpr.mixin.PlayerPatchAccessor;
import com.p1nero.dpr.mob_effect.DPRMobEffects;
import com.p1nero.dpr.skill.ParryAndDodgeRewardSkill;
import com.p1nero.dpr.skill.RewardSkill;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.world.effect.EpicFightMobEffects;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = DodgeParryRewardMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DPRSkills {

    public static Skill HEALTH_BOOST1;
    public static Skill HEALTH_BOOST2;
    public static Skill HEALTH_BOOST3;
    public static Skill HEALTH_BOOST4;
    public static Skill ABSORB1;
    public static Skill ABSORB2;
    public static Skill ABSORB3;
    public static Skill ABSORB4;
    public static Skill HEAL1;
    public static Skill HEAL2;
    public static Skill HEAL3;
    public static Skill HEAL4;
    public static Skill RESIST1;
    public static Skill RESIST2;
    public static Skill RESIST3;
    public static Skill RESIST4;
    public static Skill STUN_IMM1;
    public static Skill STUN_IMM2;
    public static Skill STUN_IMM3;
    public static Skill STUN_IMM4;
    public static Skill DAMAGE_BOOST1;
    public static Skill DAMAGE_BOOST2;
    public static Skill DAMAGE_BOOST3;
    public static Skill DAMAGE_BOOST4;
    public static Skill SPEED1;
    public static Skill SPEED2;
    public static Skill SPEED3;
    public static Skill SPEED4;
    public static Skill STAMINA1;
    public static Skill STAMINA2;
    public static Skill STAMINA3;
    public static Skill STAMINA4;

    @SubscribeEvent
    public static void buildSkills(SkillBuildEvent event) {
        SkillBuildEvent.ModRegistryWorker registryWorker = event.createRegistryWorker(DodgeParryRewardMod.MOD_ID);
        HEALTH_BOOST1 = registryWorker.build("health_boost1", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114500"))
                .setMobEffectSupplier(() -> MobEffects.HEALTH_BOOST)
                .setEffectAmplifier(1)
                .setEffectDuration(200));
        HEALTH_BOOST2 = registryWorker.build("health_boost2", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114501"))
                .setMobEffectSupplier(() -> MobEffects.HEALTH_BOOST)
                .setEffectAmplifier(3)
                .setEffectDuration(200));
        HEALTH_BOOST3 = registryWorker.build("health_boost3", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114502"))
                .setMobEffectSupplier(() -> MobEffects.HEALTH_BOOST)
                .setEffectAmplifier(5)
                .setEffectDuration(400));
        HEALTH_BOOST4 = registryWorker.build("health_boost4", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114503"))
                .setMobEffectSupplier(() -> MobEffects.HEALTH_BOOST)
                .setEffectAmplifier(7)
                .setEffectDuration(400));

        ABSORB1 = registryWorker.build("absorb1", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114504"))
                .setMobEffectSupplier(() -> MobEffects.ABSORPTION)
                .setEffectAmplifier(0)
                .setEffectDuration(60));
        ABSORB2 = registryWorker.build("absorb2", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114505"))
                .setMobEffectSupplier(() -> MobEffects.ABSORPTION)
                .setEffectAmplifier(1)
                .setEffectDuration(60));
        ABSORB3 = registryWorker.build("absorb3", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114506"))
                .setMobEffectSupplier(() -> MobEffects.ABSORPTION)
                .setEffectAmplifier(2)
                .setEffectDuration(100));
        ABSORB4 = registryWorker.build("absorb4", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114507"))
                .setMobEffectSupplier(() -> MobEffects.ABSORPTION)
                .setEffectAmplifier(3)
                .setEffectDuration(100));
        HEAL1 = registryWorker.build("heal1", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114508"))
                .setsKillTexture(ResourceLocation.withDefaultNamespace("textures/mob_effect/regeneration.png"))
                .setPlayerPatchConsumer((playerPatch -> {
                    playerPatch.getOriginal().setHealth(playerPatch.getOriginal().getHealth() + 2);
                })));
        HEAL2 = registryWorker.build("heal2", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114509"))
                .setsKillTexture(ResourceLocation.withDefaultNamespace("textures/mob_effect/regeneration.png"))
                .setPlayerPatchConsumer((playerPatch -> {
                    playerPatch.getOriginal().setHealth(playerPatch.getOriginal().getHealth() + 4);
                })));
        HEAL3 = registryWorker.build("heal3", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114510"))
                .setsKillTexture(ResourceLocation.withDefaultNamespace("textures/mob_effect/regeneration.png"))
                .setPlayerPatchConsumer((playerPatch -> {
                    playerPatch.getOriginal().setHealth(playerPatch.getOriginal().getHealth() + 6);
                })));
        HEAL4 = registryWorker.build("heal4", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114511"))
                .setsKillTexture(ResourceLocation.withDefaultNamespace("textures/mob_effect/regeneration.png"))
                .setPlayerPatchConsumer((playerPatch -> {
                    playerPatch.getOriginal().setHealth(playerPatch.getOriginal().getHealth() + 8);
                })));
        RESIST1 = registryWorker.build("resist1", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114512"))
                .setMobEffectSupplier(() -> MobEffects.DAMAGE_RESISTANCE)
                .setEffectAmplifier(0)
                .setEffectDuration(60));
        RESIST2 = registryWorker.build("resist2", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114513"))
                .setMobEffectSupplier(() -> MobEffects.DAMAGE_RESISTANCE)
                .setEffectAmplifier(1)
                .setEffectDuration(60));
        RESIST3 = registryWorker.build("resist3", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114514"))
                .setMobEffectSupplier(() -> MobEffects.DAMAGE_RESISTANCE)
                .setEffectAmplifier(1)
                .setEffectDuration(120));
        RESIST4 = registryWorker.build("resist4", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114515"))
                .setMobEffectSupplier(() -> MobEffects.DAMAGE_RESISTANCE)
                .setEffectAmplifier(2)
                .setEffectDuration(160));
        STUN_IMM1 = registryWorker.build("stun_imm1", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114516"))
                .setMobEffectSupplier(EpicFightMobEffects.STUN_IMMUNITY)
                .setEffectAmplifier(0)
                .setEffectDuration(40));
        STUN_IMM2 = registryWorker.build("stun_imm2", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114517"))
                .setMobEffectSupplier(EpicFightMobEffects.STUN_IMMUNITY)
                .setEffectAmplifier(0)
                .setEffectDuration(100));
        STUN_IMM3 = registryWorker.build("stun_imm3", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114518"))
                .setMobEffectSupplier(EpicFightMobEffects.STUN_IMMUNITY)
                .setEffectAmplifier(0)
                .setEffectDuration(160));
        STUN_IMM4 = registryWorker.build("stun_imm4", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114519"))
                .setMobEffectSupplier(EpicFightMobEffects.STUN_IMMUNITY)
                .setEffectAmplifier(0)
                .setEffectDuration(200));
        DAMAGE_BOOST1 = registryWorker.build("damage_boost1", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114520"))
                .setMobEffectSupplier(() -> MobEffects.DAMAGE_BOOST)
                .setEffectAmplifier(0)
                .setEffectDuration(40));
        DAMAGE_BOOST2 = registryWorker.build("damage_boost2", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114521"))
                .setMobEffectSupplier(() -> MobEffects.DAMAGE_BOOST)
                .setEffectAmplifier(1)
                .setEffectDuration(40));
        DAMAGE_BOOST3 = registryWorker.build("damage_boost3", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114522"))
                .setMobEffectSupplier(() -> MobEffects.DAMAGE_BOOST)
                .setEffectAmplifier(1)
                .setEffectDuration(100));
        DAMAGE_BOOST4 = registryWorker.build("damage_boost4", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114523"))
                .setMobEffectSupplier(() -> MobEffects.DAMAGE_BOOST)
                .setEffectAmplifier(2)
                .setEffectDuration(100));
        SPEED1 = registryWorker.build("speed1", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114524"))
                .setMobEffectSupplier(() -> MobEffects.MOVEMENT_SPEED)
                .setEffectAmplifier(0)
                .setEffectDuration(60));
        SPEED2 = registryWorker.build("speed2", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114525"))
                .setMobEffectSupplier(() -> MobEffects.MOVEMENT_SPEED)
                .setEffectAmplifier(1)
                .setEffectDuration(60));
        SPEED3 = registryWorker.build("speed3", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114526"))
                .setMobEffectSupplier(() -> MobEffects.MOVEMENT_SPEED)
                .setEffectAmplifier(2)
                .setEffectDuration(60));
        SPEED4 = registryWorker.build("speed4", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114527"))
                .setMobEffectSupplier(() -> MobEffects.MOVEMENT_SPEED)
                .setEffectAmplifier(3)
                .setEffectDuration(60));
        STAMINA1 = registryWorker.build("stamina1", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                        .setsKillTexture(ResourceLocation.fromNamespaceAndPath(DodgeParryRewardMod.MOD_ID, "textures/mob_effect/stamina_boost.png"))
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114528"))
                .setPlayerPatchConsumer(playerPatch -> ((PlayerPatchAccessor)playerPatch).setTickSinceLastAction(1145)));
        STAMINA2 = registryWorker.build("stamina2", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114529"))
                .setPlayerPatchConsumer(playerPatch -> ((PlayerPatchAccessor)playerPatch).setTickSinceLastAction(1145))
                .setMobEffectSupplier(DPRMobEffects.STAMINA_BOOST)
                .setEffectAmplifier(0)
                .setEffectDuration(200));
        STAMINA3 = registryWorker.build("stamina3", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114530"))
                .setPlayerPatchConsumer(playerPatch -> ((PlayerPatchAccessor)playerPatch).setTickSinceLastAction(1145))
                .setMobEffectSupplier(DPRMobEffects.STAMINA_BOOST)
                .setEffectAmplifier(1)
                .setEffectDuration(200));
        STAMINA4 = registryWorker.build("stamina4", ParryAndDodgeRewardSkill::new, RewardSkill.createParryRewardSkill()
                .setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac114531"))
                .setPlayerPatchConsumer(playerPatch -> ((PlayerPatchAccessor)playerPatch).setTickSinceLastAction(1145))
                .setMobEffectSupplier(DPRMobEffects.STAMINA_BOOST)
                .setEffectAmplifier(2)
                .setEffectDuration(400));
    }

}
