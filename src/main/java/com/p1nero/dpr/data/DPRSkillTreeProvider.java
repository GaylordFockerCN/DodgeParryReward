package com.p1nero.dpr.data;

import com.p1nero.dpr.DodgeParryRewardMod;
import com.yesman.epicskills.common.data.SkillTreeProvider;
import net.minecraft.data.PackOutput;
import yesman.epicfight.skill.Skill;

import java.util.function.Consumer;

import static com.p1nero.dpr.gameassets.DPRSkills.*;

public class DPRSkillTreeProvider extends SkillTreeProvider {
    public DPRSkillTreeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    protected void buildSkillTreePages(Consumer<SkillTreePageBuilder> writer) {
        SkillTreePageBuilder builder = newPage(DodgeParryRewardMod.MOD_ID, "passive")
                .menuBarColor(37, 27, 18);
        buildSkillTree(builder);
        writer.accept(builder);
    }

    // 配置常量（可调整）
    private static final int CENTER_X = 250;  // 整体中心X坐标
    private static final int CENTER_Y = 250;  // 整体中心Y坐标
    private static final int BASE_RADIUS = 80; // 1级节点到中心的距离
    private static final int LEVEL_SPACING = 50; // 每级之间的间距


    private void buildSkillTree(SkillTreePageBuilder builder) {

        final Skill[][] BRANCHES = {
                {HEALTH_BOOST1, HEALTH_BOOST2, HEALTH_BOOST3, HEALTH_BOOST4},
                {ABSORB1, ABSORB2, ABSORB3, ABSORB4},
                {HEAL1, HEAL2, HEAL3, HEAL4},
                {RESIST1, RESIST2, RESIST3, RESIST4},
                {STUN_IMM1, STUN_IMM2, STUN_IMM3, STUN_IMM4},
                {DAMAGE_BOOST1, DAMAGE_BOOST2, DAMAGE_BOOST3, DAMAGE_BOOST4},
                {SPEED1, SPEED2, SPEED3, SPEED4},
                {STAMINA1, STAMINA2, STAMINA3, STAMINA4}
        };

        final double[] angles = new double[8];
        for (int i = 0; i < 8; i++) {
            angles[i] = Math.toRadians(i * 45);
        }

        // 构建所有分支
        for (int branchIdx = 0; branchIdx < BRANCHES.length; branchIdx++) {
            Skill[] branch = BRANCHES[branchIdx];
            double angle = angles[branchIdx];

            // 计算各级节点位置
            for (int level = 0; level < branch.length; level++) {
                int radius = BASE_RADIUS + (level * LEVEL_SPACING);
                int x = CENTER_X + (int)(radius * Math.cos(angle));
                int y = CENTER_Y + (int)(radius * Math.sin(angle));

                // 构建节点
                if (level == 0) {
                    // 1级节点（无父节点）
                    builder.newNode(branch[level])
                            .position(x, y)
                            .abilityPointsRequirement(1)
                            .done();
                } else {
                    // 2-4级节点（连接上级节点）
                    builder.newNode(branch[level])
                            .position(x, y)
                            .addParent(branch[level-1])
                            .abilityPointsRequirement(level + 1)
                            .done();
                }
            }
        }
    }

}
