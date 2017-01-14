package com.TonyTiger.simplecoins.crafting;

import com.TonyTiger.simplecoins.block.ModBlocks;
import com.TonyTiger.simplecoins.guicontainer.ContainerMintTileEntity;
import com.TonyTiger.simplecoins.items.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModCrafting {
	public static void initCrafting(){
		// new Object[]{} arrays prevent a casting error (Integer to Char)
//		GameRegistry.addRecipe(
//			new ItemStack(ModItems.IRONCOIN,8), new Object[]{" I", "I ", 'I',Items.IRON_INGOT}		
//		);
//		GameRegistry.addRecipe(
//			new ItemStack(ModItems.GOLDCOIN,8), new Object[]{" I", "I ", 'I',Items.GOLD_INGOT}		
//		);
		GameRegistry.addRecipe(
			new ItemStack(Item.getByNameOrId("iron_nugget"),ContainerMintTileEntity.numNuggetsNeedForCraft), new Object[]{"I", 'I',new ItemStack(ModItems.IRONCOIN,ContainerMintTileEntity.numCoinsCrafted)}
		);
		GameRegistry.addRecipe(
			new ItemStack(Items.GOLD_NUGGET,ContainerMintTileEntity.numNuggetsNeedForCraft), new Object[]{"I", 'I',new ItemStack(ModItems.GOLDCOIN,ContainerMintTileEntity.numCoinsCrafted)}		
		);
		GameRegistry.addRecipe(
			new ItemStack(ModBlocks.mint), new Object[]{"SPS","S S","SSS", 'S',Blocks.STONE,
			'P',Blocks.PISTON}
		);
	}
}
