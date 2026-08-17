package com.p1nero.dpr.skill;

import yesman.epicfight.api.event.EntityEventListener;
import yesman.epicfight.api.event.EpicFightEventHooks;
import yesman.epicfight.skill.SkillContainer;

public class ParryAndDodgeRewardSkill extends RewardSkill {
    public ParryAndDodgeRewardSkill(Builder builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container, EntityEventListener eventListener) {
        super.onInitiate(container, eventListener);
        // MUST be a context-aware subscription, not registerEvent(). TAKE_DAMAGE_INCOME is a
        // CancelableEventHook, and CancelableEventHook#processSub skips DefaultEventSubscriptions
        // once the event is canceled. A successful parry cancels it: ParryingSkill#guard sets
        // isParried(true) and then calls dealEvent() -> event.cancel(). So with registerEvent the
        // listener is excluded at exactly the moment the flag it cares about gets set, and can
        // only ever observe isParried=false. Context-aware subscriptions fire regardless of
        // cancel state. (Dodge needs no such treatment - DodgeEvent isn't cancelable at all.)
        eventListener.registerContextAwareEvent(EpicFightEventHooks.Entity.TAKE_DAMAGE_INCOME, (event, context) -> {
            if(event.isParried()) {
                apply(container);
            }
        }, this);
        eventListener.registerEvent(EpicFightEventHooks.Entity.ON_DODGE, (event) -> apply(container), this);
    }

    // A single removeListenersBelongTo(this) call (done for us by Skill's default onRemoved)
    // takes care of both listeners registered above, since they share the same identifier.
}
