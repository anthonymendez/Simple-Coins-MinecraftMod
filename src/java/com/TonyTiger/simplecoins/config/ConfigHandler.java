package com.TonyTiger.simplecoins.config;

import java.io.File;

import com.TonyTiger.simplecoins.ClientProxy;
import com.TonyTiger.simplecoins.event.EventHandlerCommon;
import com.TonyTiger.simplecoins.guicontainer.ContainerMintTileEntity;
import com.TonyTiger.simplecoins.profession.BankerProfessionAndCareer;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {
	public static Configuration configMain = 
			new Configuration(new File("config/simplecoins.cfg"));
	public static void syncConfig(){
		init();
		if(configMain.hasChanged())
			configMain.save();
	}
	
	public static void init(){
		String category = "";
		
		category = "Villagers";
		configMain.addCustomCategoryComment(category, "Villager Settings");
		ClientProxy.enableBanker = configMain.getBoolean("Enable Banker", category, true, "Villager that allows trading to and from emeralds with coins.");
		
		category = "Trading";
		configMain.addCustomCategoryComment(category, "Trading Settings");
		BankerProfessionAndCareer.enableTier1trades = configMain.getBoolean("Enable Tier 1 Trades", category, true, "Enable/Disable first tier of trades. I recommend you turn them on");
		BankerProfessionAndCareer.tier1IronMin = configMain.getInt("T1: Iron Min", category, 21, 1, 64, "Lowest amount of iron coins that can be trade for 1 emerald. Must be lower or the same to its max.| (1,64) | 1st Tier");
		BankerProfessionAndCareer.tier1IronMax = configMain.getInt("T1: Iron Max", category, 27, 1, 64, "Highest amount of iron coins that can be trade for 1 emerald. Must be more or the same to its min.| (1,64) | 1st Tier");
		BankerProfessionAndCareer.tier1GoldMin = configMain.getInt("T1: Gold Min", category, 24, 1, 64, "Lowest amount of gold coins that can be trade for 1 emerald. Must be lower or the same to its max.| (1,64) | 1st Tier");
		BankerProfessionAndCareer.tier1GoldMax = configMain.getInt("T1: Gold Max", category, 30, 1, 64, "Highest amount of gold coins that can be trade for 1 emerald. Must be more or the same to its min.| (1,64) | 1st Tier");
		BankerProfessionAndCareer.enableTier2trades = configMain.getBoolean("Enable Tier 2 trades", category, false, "Enable/Disable second tier of trades. Default is false. No recommendation, up to you if you want tier 2 trades.");
		BankerProfessionAndCareer.tier2IronMin = configMain.getInt("T2: Iron Min", category, 19, 1, 32, "Lowest amount of iron coins that can be trade for 1 emerald. Must be lower or the same to its max.| (1,32) | 2nd Tier");
		BankerProfessionAndCareer.tier2IronMax = configMain.getInt("T2: Iron Max", category, 25, 1, 32, "Highest amount of iron coins that can be trade for 1 emerald. Must be more or the same to its min.| (1,32) | 2nd Tier");
		BankerProfessionAndCareer.tier2GoldMin = configMain.getInt("T2: Gold Min", category, 22, 1, 32, "Lowest amount of gold coins that can be trade for 1 emerald. Must be lower or the same to its max.| (1,32) | 2nd Tier");
		BankerProfessionAndCareer.tier2GoldMax = configMain.getInt("T2: Gold Max", category, 28, 1, 32, "Highest amount of gold coins that can be trade for 1 emerald. Must be more or the same to its min.| (1,32) | 2nd Tier");
		
		category = "Crafting";
		configMain.addCustomCategoryComment(category, "Mint Crafting Settings");
		ContainerMintTileEntity.numCoinsCrafted = configMain.getInt("Number of Coins Crafted", category, 1, 1, 64, "How many coins are crafted in the Mint.");
		ContainerMintTileEntity.numNuggetsNeedForCraft = configMain.getInt("Nuggets need to craft Coins", category, 3, 1, 64, "How many nuggest are needed in the Mint.");
		
		
		category = "Mob Drops";
		configMain.addCustomCategoryComment(category, "Mob Drop Settings");
		EventHandlerCommon.ironcoinDroprate = (float)(configMain.getInt("Iron Coins Drop Rate", category, 5, 0, 100, "Chance of Iron Coins dropping in the mobs below.")/100.0);
		EventHandlerCommon.goldcoinDroprate = (float)(configMain.getInt("Gold Coins Drop Rate", category, 10, 0, 100, "Chance of Gold Coins dropping in the mobs below.")/100.0);
		EventHandlerCommon.goldcoinMinDropAmt = configMain.getInt("Minimum Amount of Gold Drop", category, 0, 0, 64, "If higher than maximum, the max will be set to this value.");
		EventHandlerCommon.goldcoinMaxDropAmt = configMain.getInt("Maximum Amount of Gold Drop", category, 4, 0, 64, "If lower than minimum, it will be equal to the min value set.");
		EventHandlerCommon.ironcoinMinDropAmt = configMain.getInt("Minimum Amount of Iron Drop", category, 0, 0, 64, "If higher than maximum, the max will be set to this value.");
		EventHandlerCommon.ironcoinMaxDropAmt = configMain.getInt("Maximum Amount of Iron Drop", category, 4, 0, 64, "If lower than minimum, it will be equal to the min value set.");
		EventHandlerCommon.enableEvokerdrops = configMain.getBoolean("Enable Evoker Drops", category, true, "");
		EventHandlerCommon.enableHuskdrops = configMain.getBoolean("Enable Husk Drops", category, true, "");
		EventHandlerCommon.enablePigZomdrops = configMain.getBoolean("Enable Zombie Pigman Drops", category, true, "");
		EventHandlerCommon.enableVillagerdrops = configMain.getBoolean("Enable Villager Drops", category, true, "");
		EventHandlerCommon.enableVindicatordrops = configMain.getBoolean("Enable Vindicator Drops", category, true, "");
		EventHandlerCommon.enableZombiedrops = configMain.getBoolean("Enable Zombie Drops", category, true, "");
		EventHandlerCommon.enableZomVildrops = configMain.getBoolean("Enable Zombie Villager Drops", category, true, "");
		if(EventHandlerCommon.goldcoinMaxDropAmt < EventHandlerCommon.goldcoinMinDropAmt)
			EventHandlerCommon.goldcoinMaxDropAmt = EventHandlerCommon.goldcoinMinDropAmt;
		if(EventHandlerCommon.ironcoinMaxDropAmt < EventHandlerCommon.ironcoinMinDropAmt)
			EventHandlerCommon.ironcoinMaxDropAmt = EventHandlerCommon.ironcoinMinDropAmt;	
		
	}
}
