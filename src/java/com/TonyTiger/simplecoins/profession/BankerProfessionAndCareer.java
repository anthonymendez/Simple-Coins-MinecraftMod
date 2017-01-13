package com.TonyTiger.simplecoins.profession;

import com.TonyTiger.simplecoins.items.ModItems;

import net.minecraft.entity.passive.EntityVillager.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

public class BankerProfessionAndCareer {
	
	protected static final VillagerProfession bankerProf = new VillagerProfession("simplecoins_banker_prof",
			"simplecoins:trading/banker.png","minecraft:textures/entity/zombie_villager/zombie_villager.png");
	protected static final VillagerCareer bankerCar = new VillagerCareer(bankerProf,"simplecoins_banker_car");
	
	public static ITradeList[] trades = {
			// 21-27/24-30 coins for 1 emerald.
			new EmeraldForItems(ModItems.IRONCOIN, new PriceInfo(21,27)),
			new EmeraldForItems(ModItems.GOLDCOIN, new PriceInfo(24,30)),
			// 1 emerald for 21-27 coins.
			// Price info must be negative so that forge understands that
			// 1 emerald is being spent for multiple items.
			// ... but not just use an ItemStack...? I dunno.
			new ListItemForEmeralds(new ItemStack(ModItems.IRONCOIN,1), new PriceInfo(-21,-27)),
			new ListItemForEmeralds(new ItemStack(ModItems.GOLDCOIN,1), new PriceInfo(-24,-30)),
	};
	public static ITradeList[] tradesl2 = {
			new EmeraldForItems(ModItems.IRONCOIN, new PriceInfo(19,25)),
			new EmeraldForItems(ModItems.GOLDCOIN, new PriceInfo(22,28)),
			new ListItemForEmeralds(new ItemStack(ModItems.IRONCOIN,1), new PriceInfo(-38,-50)),
			new ListItemForEmeralds(new ItemStack(ModItems.GOLDCOIN,1), new PriceInfo(-44,-56))
	};
	
	public static void initialize(){
		BankerProfessionAndCareer.bankerCar.addTrade(1, BankerProfessionAndCareer.trades);
		BankerProfessionAndCareer.bankerCar.addTrade(2, BankerProfessionAndCareer.tradesl2);
		VillagerRegistry.instance().register(BankerProfessionAndCareer.bankerProf);
	}
}