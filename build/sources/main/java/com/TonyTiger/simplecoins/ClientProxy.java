package com.TonyTiger.simplecoins;

import com.TonyTiger.simplecoins.block.ModBlocks;
import com.TonyTiger.simplecoins.client.render.blocks.BlockRenderRegister;
import com.TonyTiger.simplecoins.client.render.items.ItemRenderRegister;
import com.TonyTiger.simplecoins.crafting.ModCrafting;
import com.TonyTiger.simplecoins.items.ModItems;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy {
	public void preInit(FMLPreInitializationEvent e){
		ModItems.createItems();
		ModBlocks.createBlocks();
	}
	public void init(FMLInitializationEvent e){
		ItemRenderRegister.registerItemRenderer();
		BlockRenderRegister.registerBlockRenderer();
		ModCrafting.initCrafting();
	}
	public void postInit(FMLPostInitializationEvent e){
		
	}
}
