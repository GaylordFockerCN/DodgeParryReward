package com.p1nero.dpr.skill;

import yesman.epicfight.api.event.EntityEventListener;
import yesman.epicfight.api.event.EpicFightEventHooks;
import yesman.epicfight.skill.SkillContainer;

public class DodgeRewardSkill extends RewardSkill {

    public DodgeRewardSkill(Builder builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container, EntityEventListener eventListener) {
        super.onInitiate(container, eventListener);
        eventListener.registerEvent(EpicFightEventHooks.Entity.ON_DODGE, (event) -> apply(container), this);
    }

    // No onRemoved override needed: Skill's default implementation already calls
    // eventListener.removeListenersBelongTo(this) for us (this class IS its own
    // IdentifierProvider via Skill#getId(), which returns its registry name).
}
