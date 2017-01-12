package com.TonyTiger.simplecoins.event;

import com.TonyTiger.simplecoins.items.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerCommon {
	
	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event) {
		Entity entity = event.getEntity();
		Entity base = event.getEntityLiving();
		if(entity instanceof EntityCow){
			base.dropItem(ModItems.GOLDCOIN, 10);
		}
    }
}
