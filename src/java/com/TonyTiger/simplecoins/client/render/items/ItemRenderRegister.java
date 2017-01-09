package com.TonyTiger.simplecoins.client.render.items;

import com.TonyTiger.simplecoins.Main;
import com.TonyTiger.simplecoins.block.ModBlocks;
import com.TonyTiger.simplecoins.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ItemRenderRegister {
	
	public final static String modid = Main.MODID;

	public static void registerItemRenderer(){
		Item[] items = {
			ModItems.GOLDCOIN,ModItems.IRONCOIN	
		};
		regItem(items);
	}
	
	public static void regItem(Item[] item){
		for(Item i : item){
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register
			(i, 0,new ModelResourceLocation(modid + ":" + i.getUnlocalizedName().substring(i.getUnlocalizedName().indexOf(".")+1),"inventory"));
		}
	}
}
