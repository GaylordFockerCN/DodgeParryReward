package com.p1nero.dpr.skill;

import yesman.epicfight.api.event.EntityEventListener;
import yesman.epicfight.api.event.EpicFightEventHooks;
import yesman.epicfight.skill.SkillContainer;

public class ParryRewardSkill extends RewardSkill {

    public ParryRewardSkill(Builder builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container, EntityEventListener eventListener) {
        super.onInitiate(container, eventListener);
        // Context-aware (not registerEvent) is required here - see the detailed note in
        // ParryAndDodgeRewardSkill: a successful parry cancels TAKE_DAMAGE_INCOME, and canceled
        // events skip DefaultEventSubscriptions entirely.
        eventListener.registerContextAwareEvent(EpicFightEventHooks.Entity.TAKE_DAMAGE_INCOME, (event, context) -> {
            if(event.isParried()) {
                apply(container);
            }
        }, this);
    }

    // No onRemoved override needed - see DodgeRewardSkill.

}
