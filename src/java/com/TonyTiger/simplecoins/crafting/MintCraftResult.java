package com.TonyTiger.simplecoins.crafting;

import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.item.ItemStack;

public class MintCraftResult extends InventoryCraftResult {
	public MintCraftResult(){
		super();
	}
	
	@Override
	public boolean isItemValidForSlot(int slotId, ItemStack is){
		return false;
	}
	
}
