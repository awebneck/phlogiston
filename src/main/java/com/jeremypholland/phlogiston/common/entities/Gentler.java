package com.jeremypholland.phlogiston.common.entities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.*;
import net.minecraft.world.World;

/**
 * Created by jeremy on 12/10/15.
 */
public class Gentler {
    public Gentler() {
    }

    public static void gentleEntity(EntityLiving entity, World worldIn) {
        EntityLiving gentled = getGentledEntity(entity, worldIn);
        if (gentled != null) {
            System.out.println("GENTLED ENTITY: " + gentled.toString());
            gentled.setPositionAndRotation(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
            gentled.setRotationYawHead(entity.getRotationYawHead());
            worldIn.removeEntity(entity);
            worldIn.spawnEntityInWorld(gentled);
        }
    }

    private static EntityLiving getGentledEntity(EntityLiving entity, World worldIn) {
        if (entity instanceof EntityCow) {
            System.out.println("GENTLING COW");
            return new EntityGentledCow(worldIn);
        } else if (entity instanceof EntityPig)
            return new EntityGentledPig(worldIn);
        else if (entity instanceof EntitySheep)
            return new EntityGentledSheep(worldIn);
        else if (entity instanceof EntityWolf)
            return new EntityGentledWolf(worldIn);
        else if (entity instanceof EntityOcelot)
            return new EntityGentledOcelot(worldIn);
        else if (entity instanceof EntityChicken)
            return new EntityGentledChicken(worldIn);
        else if (entity instanceof EntityHorse)
            return new EntityGentledHorse(worldIn);
        else if (entity instanceof EntityBat) {
            System.out.println("GENTLING BAT");
            return new EntityGentledBat(worldIn);
        } else if (entity instanceof EntityVillager)
            return new EntityGentledVillager(worldIn);
        else
            return null;
    }
}
