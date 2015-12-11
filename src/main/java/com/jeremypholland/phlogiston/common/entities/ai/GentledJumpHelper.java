package com.jeremypholland.phlogiston.common.entities.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityJumpHelper;

/**
 * Created by jeremy on 12/9/15.
 */
public class GentledJumpHelper extends EntityJumpHelper {
    public GentledJumpHelper(EntityLiving entity) {
        super(entity);
    }
    @Override
    public void doJump() {
    }
}
