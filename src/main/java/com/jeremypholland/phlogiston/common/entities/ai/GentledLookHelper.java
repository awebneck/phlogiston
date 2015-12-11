package com.jeremypholland.phlogiston.common.entities.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityLookHelper;

/**
 * Created by jeremy on 12/9/15.
 */
public class GentledLookHelper extends EntityLookHelper {
    public GentledLookHelper(EntityLiving entity) {
        super(entity);
    }

    @Override
    public void onUpdateLook() {
    }
}
