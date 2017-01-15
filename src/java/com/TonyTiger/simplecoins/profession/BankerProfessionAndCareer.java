package com.TonyTiger.simplecoins.profession;

import com.TonyTiger.simplecoins.items.ModItems;

import net.minecraft.entity.passive.EntityVillager.EmeraldForItems;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.entity.passive.EntityVillager.ListItemForEmeralds;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

public class BankerProfessionAndCareer {
	
	protected static final VillagerProfession bankerProf = new VillagerProfession("simplecoins_banker_prof",
			"simplecoins:trading/banker.png","minecraft:textures/entity/zombie_villager/zombie_villager.png");
	protected static final VillagerCareer bankerCar = new VillagerCareer(bankerProf,"simplecoins_banker_car");
	
	// Initialized in ConfigHandler
	public static int tier1IronMin = 21,tier1GoldMin = 24,tier1IronMax = 27,tier1GoldMax = 30,
	tier2IronMin = 19,tier2GoldMin = 22,tier2IronMax = 25,tier2GoldMax = 28;
	public static boolean enableTier1trades = true,enableTier2trades = false;
	
	public static ITradeList[] trades = {
			// 21-27/24-30 coins for 1 emerald.
			new EmeraldForItems(ModItems.IRONCOIN, new PriceInfo(tier1IronMin,tier1IronMax)),
			new EmeraldForItems(ModItems.GOLDCOIN, new PriceInfo(tier1GoldMin,tier1GoldMax)),
			// 1 emerald for 21-27 coins.
			// Price info must be negative so that forge understands that
			// 1 emerald is being spent for multiple items.
			// ... but not just use an ItemStack...? I dunno.
			new ListItemForEmeralds(new ItemStack(ModItems.IRONCOIN,1), new PriceInfo(-1*tier1IronMin,-1*tier1IronMax)),
			new ListItemForEmeralds(new ItemStack(ModItems.GOLDCOIN,1), new PriceInfo(-1*tier1GoldMin,-1*tier1GoldMax)),
	};
	public static ITradeList[] tradesl2 = {
			new EmeraldForItems(ModItems.IRONCOIN, new PriceInfo(tier2IronMin,tier2IronMax)),
			new EmeraldForItems(ModItems.GOLDCOIN, new PriceInfo(tier2IronMin,tier2GoldMax)),
			new ListItemForEmeralds(new ItemStack(ModItems.IRONCOIN,1), new PriceInfo(-1*tier2IronMin*2,-1*tier2IronMax*2)),
			new ListItemForEmeralds(new ItemStack(ModItems.GOLDCOIN,1), new PriceInfo(-1*tier2GoldMin*2,-1*tier2GoldMax*2))
	};
	
	public static void initialize(){
		if(enableTier1trades)
			BankerProfessionAndCareer.bankerCar.addTrade(1, BankerProfessionAndCareer.trades);
		if(enableTier2trades)
			BankerProfessionAndCareer.bankerCar.addTrade(2, BankerProfessionAndCareer.tradesl2);
		VillagerRegistry.instance().register(BankerProfessionAndCareer.bankerProf);
		MapGenStructureIO.registerStructureComponent(Bank.class, "ViB");
		BankVillageHandler bvh = new BankVillageHandler();
		VillagerRegistry.instance().registerVillageCreationHandler(bvh);
	}
}