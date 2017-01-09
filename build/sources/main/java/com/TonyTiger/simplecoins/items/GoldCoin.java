package com.TonyTiger.simplecoins.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class GoldCoin extends Item {
	public GoldCoin(String unlocalizedName){
		super();
		
		this.setMaxStackSize(64);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.MISC);
	}
}
