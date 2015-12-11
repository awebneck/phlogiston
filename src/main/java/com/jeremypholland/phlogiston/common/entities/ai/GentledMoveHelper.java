package com.jeremypholland.phlogiston.common.entities.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityMoveHelper;

/**
 * Created by jeremy on 12/9/15.
 */
public class GentledMoveHelper extends EntityMoveHelper {
    public GentledMoveHelper(EntityLiving entity) {
        super(entity);
    }

    @Override
    public void onUpdateMoveHelper() {
    }
}
