package com.TonyTiger.simplecoins;

import com.TonyTiger.simplecoins.TileEntity.ModTileEntities;
import com.TonyTiger.simplecoins.block.ModBlocks;
import com.TonyTiger.simplecoins.client.render.blocks.BlockRenderRegister;
import com.TonyTiger.simplecoins.client.render.items.ItemRenderRegister;
import com.TonyTiger.simplecoins.crafting.ModCrafting;
import com.TonyTiger.simplecoins.items.ModItems;
import com.TonyTiger.simplecoins.network.ModGuiHandler;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy {
	public void preInit(FMLPreInitializationEvent e){
		ModItems.createItems();
		ModTileEntities.init();
		ModBlocks.createBlocks();
	}
	public void init(FMLInitializationEvent e){
		ItemRenderRegister.registerItemRenderer();
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new ModGuiHandler());
		BlockRenderRegister.registerBlockRenderer();
		ModCrafting.initCrafting();
	}
	public void postInit(FMLPostInitializationEvent e){
		
	}
}
