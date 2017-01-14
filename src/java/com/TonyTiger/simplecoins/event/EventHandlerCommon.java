package com.TonyTiger.simplecoins.event;

import com.TonyTiger.simplecoins.config.ConfigHandler;
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
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerCommon {
	
	public static float ironcoinDroprate = 0.05f;
	public static float goldcoinDroprate = 0.1f;
	public static int goldcoinMinDropAmt = 0, goldcoinMaxDropAmt = 4, ironcoinMinDropAmt = 0, ironcoinMaxDropAmt = 3;
	public static boolean enableEvokerdrops = true, enableVindicatordrops = true,
			enableVillagerdrops = true, enableZombiedrops = true, 
					enableZomVildrops = true, enableHuskdrops = true,
							enablePigZomdrops = true;
	
	//Adding Coins to Mob drops
	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event) {
		Entity entity = event.getEntity();
		Entity base = event.getEntityLiving();
		if( (entity instanceof EntityEvoker && enableEvokerdrops ) 
		 || (entity instanceof EntityVindicator && enableVindicatordrops) 
		 || (entity instanceof EntityVillager && enableVillagerdrops)
		 || (entity instanceof EntityZombie && enableZombiedrops)
		 || (entity instanceof EntityZombieVillager && enableZomVildrops)
		 || (entity instanceof EntityHusk && enableHuskdrops)
		 || (entity instanceof EntityPigZombie && enablePigZomdrops) ){
			int amount;
			if(Math.random() < goldcoinDroprate){
				amount = (int)Math.round((goldcoinMaxDropAmt-goldcoinMinDropAmt)*Math.random() + goldcoinMinDropAmt);
				base.dropItem(ModItems.GOLDCOIN, amount);
			}if(Math.random() < ironcoinDroprate){
				amount = (int)Math.round((ironcoinMaxDropAmt-ironcoinMinDropAmt)*Math.random() + ironcoinMinDropAmt);
				base.dropItem(ModItems.IRONCOIN, amount);
			}
		}
    }
	
	@SubscribeEvent
	  public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
	    if(eventArgs.getModID().equals("simplecoins"))
	      ConfigHandler.syncConfig();
	  }
}
