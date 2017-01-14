package com.TonyTiger.simplecoins.world;

import java.util.Random;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class genCoinsInWorld {
	private void init(){
		
	}
	@SubscribeEvent
	public void lootLoad(LootTableLoadEvent event){
		event.getTable().generateLootForPools(new Random(), 
				new LootContext(0, null, null, null, null, null));
	}
}
