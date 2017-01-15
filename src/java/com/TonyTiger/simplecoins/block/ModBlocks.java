package com.TonyTiger.simplecoins.block;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
	public static MintBlock mint = new MintBlock("mint_block");
	public static ItemBlock mintItem = new ItemBlock(mint);
	
	public static final void createBlocks(){
		mintItem.setRegistryName("mint_block");
		mintItem.setCreativeTab(CreativeTabs.MISC);
		GameRegistry.register(mint);
		GameRegistry.register(mintItem);
	}
}
