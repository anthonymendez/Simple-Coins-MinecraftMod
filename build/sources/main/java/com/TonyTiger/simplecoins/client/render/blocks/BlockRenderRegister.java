package com.TonyTiger.simplecoins.client.render.blocks;

import com.TonyTiger.simplecoins.Main;
import com.TonyTiger.simplecoins.block.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

public class BlockRenderRegister {
	public static String modid = Main.MODID;
	
	public static void registerBlockRenderer(){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
			ModBlocks.mintItem, 0, new ModelResourceLocation(modid +":"+ 
		ModBlocks.mint.getUnlocalizedName().substring(5), "inventory")
		);
	}

}
