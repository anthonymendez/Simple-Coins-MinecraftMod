package com.TonyTiger.simplecoins.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class IronCoin extends Item {
	public IronCoin(String unlocalizedName){
		super();
		
		this.setMaxStackSize(64);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.MISC);
	}
}
