package com.p1nero.dpr.gameassets;

import com.p1nero.dpr.DodgeParryRewardMod;
import com.p1nero.dpr.mob_effect.DPRMobEffects;
import com.p1nero.dpr.skill.HealSkill;
import com.p1nero.dpr.skill.ParryAndDodgeRewardSkill;
import com.p1nero.dpr.skill.RewardSkill;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import yesman.epicfight.registry.EpicFightRegistries;
import yesman.epicfight.registry.entries.EpicFightMobEffects;
import yesman.epicfight.skill.Skill;

/**
 * The 32 skills. Registered through the standard NeoForge {@link DeferredRegister} for
 * {@link Skill} (see {@code yesman.epicfight.registry.entries.EpicFightSkills} for Epic Fight's
 * own skills, which follow the exact same pattern) - this replaces the old Forge 1.20.1
 * {@code SkillBuildEvent}/{@code ModRegistryWorker} callback, which no longer exists in
 * Epic Fight 1.21.1.
 *
 * <p>Note there is no per-skill {@code setUuid(...)} call anymore: in the old API a skill needed
 * a separate UUID to identify its own event listeners. Skill itself now implements
 * {@code IdentifierProvider} (via its registry name), so each skill instance IS its own
 * listener identifier - see {@code RewardSkill}/{@code DodgeRewardSkill} etc.
 */
public class DPRSkills {

    public static final DeferredRegister<Skill> SKILLS = DeferredRegister.create(EpicFightRegistries.Keys.SKILL, DodgeParryRewardMod.MOD_ID);

    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> HEALTH_BOOST1 = SKILLS.register("health_boost1", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.HEALTH_BOOST)
            .setEffectAmplifier(1)
            .setEffectDuration(200)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> HEALTH_BOOST2 = SKILLS.register("health_boost2", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.HEALTH_BOOST)
            .setEffectAmplifier(3)
            .setEffectDuration(200)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> HEALTH_BOOST3 = SKILLS.register("health_boost3", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.HEALTH_BOOST)
            .setEffectAmplifier(5)
            .setEffectDuration(400)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> HEALTH_BOOST4 = SKILLS.register("health_boost4", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.HEALTH_BOOST)
            .setEffectAmplifier(7)
            .setEffectDuration(400)
            .build(key));

    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> ABSORB1 = SKILLS.register("absorb1", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.ABSORPTION)
            .setEffectAmplifier(0)
            .setEffectDuration(60)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> ABSORB2 = SKILLS.register("absorb2", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.ABSORPTION)
            .setEffectAmplifier(1)
            .setEffectDuration(60)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> ABSORB3 = SKILLS.register("absorb3", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.ABSORPTION)
            .setEffectAmplifier(2)
            .setEffectDuration(100)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> ABSORB4 = SKILLS.register("absorb4", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.ABSORPTION)
            .setEffectAmplifier(3)
            .setEffectDuration(100)
            .build(key));

    public static final DeferredHolder<Skill, HealSkill> HEAL1 = SKILLS.register("heal1", key -> RewardSkill.createParryRewardSkill(HealSkill::new)
            .setsKillTexture(ResourceLocation.withDefaultNamespace("textures/mob_effect/regeneration.png"))
            .build(key));
    public static final DeferredHolder<Skill, HealSkill> HEAL2 = SKILLS.register("heal2", key -> RewardSkill.createParryRewardSkill(HealSkill::new)
            .setsKillTexture(ResourceLocation.withDefaultNamespace("textures/mob_effect/regeneration.png"))
            .build(key));
    public static final DeferredHolder<Skill, HealSkill> HEAL3 = SKILLS.register("heal3", key -> RewardSkill.createParryRewardSkill(HealSkill::new)
            .setsKillTexture(ResourceLocation.withDefaultNamespace("textures/mob_effect/regeneration.png"))
            .build(key));
    public static final DeferredHolder<Skill, HealSkill> HEAL4 = SKILLS.register("heal4", key -> RewardSkill.createParryRewardSkill(HealSkill::new)
            .setsKillTexture(ResourceLocation.withDefaultNamespace("textures/mob_effect/regeneration.png"))
            .build(key));

    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> RESIST1 = SKILLS.register("resist1", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.DAMAGE_RESISTANCE)
            .setEffectAmplifier(0)
            .setEffectDuration(60)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> RESIST2 = SKILLS.register("resist2", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.DAMAGE_RESISTANCE)
            .setEffectAmplifier(1)
            .setEffectDuration(60)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> RESIST3 = SKILLS.register("resist3", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.DAMAGE_RESISTANCE)
            .setEffectAmplifier(1)
            .setEffectDuration(120)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> RESIST4 = SKILLS.register("resist4", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.DAMAGE_RESISTANCE)
            .setEffectAmplifier(2)
            .setEffectDuration(160)
            .build(key));

    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> STUN_IMM1 = SKILLS.register("stun_imm1", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> EpicFightMobEffects.STUN_IMMUNITY)
            .setEffectAmplifier(0)
            .setEffectDuration(40)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> STUN_IMM2 = SKILLS.register("stun_imm2", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> EpicFightMobEffects.STUN_IMMUNITY)
            .setEffectAmplifier(0)
            .setEffectDuration(100)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> STUN_IMM3 = SKILLS.register("stun_imm3", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> EpicFightMobEffects.STUN_IMMUNITY)
            .setEffectAmplifier(0)
            .setEffectDuration(160)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> STUN_IMM4 = SKILLS.register("stun_imm4", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> EpicFightMobEffects.STUN_IMMUNITY)
            .setEffectAmplifier(0)
            .setEffectDuration(200)
            .build(key));

    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> DAMAGE_BOOST1 = SKILLS.register("damage_boost1", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.DAMAGE_BOOST)
            .setEffectAmplifier(0)
            .setEffectDuration(40)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> DAMAGE_BOOST2 = SKILLS.register("damage_boost2", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.DAMAGE_BOOST)
            .setEffectAmplifier(1)
            .setEffectDuration(40)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> DAMAGE_BOOST3 = SKILLS.register("damage_boost3", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.DAMAGE_BOOST)
            .setEffectAmplifier(1)
            .setEffectDuration(100)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> DAMAGE_BOOST4 = SKILLS.register("damage_boost4", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.DAMAGE_BOOST)
            .setEffectAmplifier(2)
            .setEffectDuration(100)
            .build(key));

    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> SPEED1 = SKILLS.register("speed1", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.MOVEMENT_SPEED)
            .setEffectAmplifier(0)
            .setEffectDuration(60)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> SPEED2 = SKILLS.register("speed2", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.MOVEMENT_SPEED)
            .setEffectAmplifier(1)
            .setEffectDuration(60)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> SPEED3 = SKILLS.register("speed3", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.MOVEMENT_SPEED)
            .setEffectAmplifier(2)
            .setEffectDuration(60)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> SPEED4 = SKILLS.register("speed4", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setMobEffectSupplier(() -> MobEffects.MOVEMENT_SPEED)
            .setEffectAmplifier(3)
            .setEffectDuration(60)
            .build(key));

    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> STAMINA1 = SKILLS.register("stamina1", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setsKillTexture(ResourceLocation.fromNamespaceAndPath(DodgeParryRewardMod.MOD_ID, "textures/mob_effect/stamina_boost.png"))
            .setWhenExecute(playerPatch -> playerPatch.setStamina(playerPatch.getStamina() + 2), 2)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> STAMINA2 = SKILLS.register("stamina2", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setWhenExecute(playerPatch -> playerPatch.setStamina(playerPatch.getStamina() + 2), 2)
            .setMobEffectSupplier(() -> DPRMobEffects.STAMINA_BOOST)
            .setEffectAmplifier(0)
            .setEffectDuration(200)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> STAMINA3 = SKILLS.register("stamina3", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setWhenExecute(playerPatch -> playerPatch.setStamina(playerPatch.getStamina() + 3), 2)
            .setMobEffectSupplier(() -> DPRMobEffects.STAMINA_BOOST)
            .setEffectAmplifier(1)
            .setEffectDuration(200)
            .build(key));
    public static final DeferredHolder<Skill, ParryAndDodgeRewardSkill> STAMINA4 = SKILLS.register("stamina4", key -> RewardSkill.createParryRewardSkill(ParryAndDodgeRewardSkill::new)
            .setWhenExecute(playerPatch -> playerPatch.setStamina(playerPatch.getStamina() + 3), 2)
            .setMobEffectSupplier(() -> DPRMobEffects.STAMINA_BOOST)
            .setEffectAmplifier(2)
            .setEffectDuration(400)
            .build(key));

}
