package com.TonyTiger.simplecoins.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModItems {
	public static Item IRONCOIN = new IronCoin("ironcoin_Item").setRegistryName("ironcoin_Item");
	public static Item GOLDCOIN = new GoldCoin("goldcoin_Item").setRegistryName("goldcoin_Item");
	public static void createItems(){
		GameRegistry.register(IRONCOIN);
		GameRegistry.register(GOLDCOIN);
	}
}
