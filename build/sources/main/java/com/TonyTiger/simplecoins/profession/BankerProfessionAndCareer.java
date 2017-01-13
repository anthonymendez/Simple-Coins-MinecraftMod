package com.TonyTiger.simplecoins.profession;

import com.TonyTiger.simplecoins.items.ModItems;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.EmeraldForItems;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

public class BankerProfessionAndCareer {
	
	protected static final VillagerProfession bankerProf = new VillagerProfession("simplecoins_banker_prof",
			"simplecoins:trading/banker.png","minecraft:textures/entity/zombie_villager/zombie_villager.png");
	protected static final VillagerCareer bankerCar = new VillagerCareer(bankerProf,"simplecoins_banker_car");
	
	public static ITradeList[] trades = {
			new EmeraldForItems(ModItems.IRONCOIN, new PriceInfo(21,27)),
			new EmeraldForItems(ModItems.GOLDCOIN, new PriceInfo(24,30)),
//			new EntityVillager.ListItemForEmeralds(new ItemStack(ModItems.IRONCOIN,21), new PriceInfo(1,1)),
//			new EntityVillager.ListItemForEmeralds(new ItemStack(ModItems.GOLDCOIN,24), new PriceInfo(1,1)),
	};
	public static ITradeList[] tradesl2 = {
			new EmeraldForItems(ModItems.IRONCOIN, new PriceInfo(19,25)),
			new EmeraldForItems(ModItems.GOLDCOIN, new PriceInfo(22,28)),
//			new EntityVillager.ListItemForEmeralds(new ItemStack(ModItems.IRONCOIN,RandNum(38,50)), new PriceInfo(2,2)),
//			new EntityVillager.ListItemForEmeralds(new ItemStack(ModItems.GOLDCOIN,RandNum(44,56)), new PriceInfo(2,2))
	};
	
	public static void initilize(){
		BankerProfessionAndCareer.bankerCar.addTrade(1, BankerProfessionAndCareer.trades);
//		BankerProfessionAndCareer.bankerCar.addTrade(2, BankerProfessionAndCareer.tradesl2);
		VillagerRegistry.instance().register(BankerProfessionAndCareer.bankerProf);
	}
	
	private static int RandNum(int from, int to){
		double rand = (to-from)*Math.random();
		from += Math.round(rand);
		return from;
	}
}