package com.TonyTiger.simplecoins.TileEntity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class MintTileEntity extends TileEntity implements ITickable{

	@Override
	public void update() {
		System.out.println("I'm an entity! Yay!");
	}

}
