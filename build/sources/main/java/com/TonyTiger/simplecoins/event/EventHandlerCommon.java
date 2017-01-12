package com.TonyTiger.simplecoins.event;

import com.TonyTiger.simplecoins.items.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEvoker;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityVindicator;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerCommon {
	
	private float ironDroprate = 0.2f;
	private float goldDroprate = 0.1f;
	
	//Adding Coins to Mob drops
	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event) {
		Entity entity = event.getEntity();
		Entity base = event.getEntityLiving();
		
		// Chance to drop 4 Ironcoins and 4 Goldcoins
		if( (entity instanceof EntityZombie || entity instanceof EntityZombieVillager
			|| entity instanceof EntityHusk || entity instanceof EntityPigZombie) ){
			//4 Iron coins have a 20% chance of dropping 
			if(Math.random() < ironDroprate){
				base.dropItem(ModItems.IRONCOIN, 4);
				
			//4 Gold coins have a 10% chance of dropping
			}else if(Math.random() < goldDroprate){
				base.dropItem(ModItems.GOLDCOIN, 4);
			}
			
			//Drops 0-3 Goldcoins and 0-3 Ironcoins
		}else if( (entity instanceof EntityEvoker || entity instanceof EntityVindicator 
				|| entity instanceof EntityVillager)){
			int amount;
			amount = (int)Math.round(4*Math.random());
			if(amount >= 4) amount = 3;
			base.dropItem(ModItems.GOLDCOIN, amount);
			amount = (int)Math.round(4*Math.random());
			if(amount >= 4) amount = 3;
			base.dropItem(ModItems.IRONCOIN, amount);
		}
    }
}
