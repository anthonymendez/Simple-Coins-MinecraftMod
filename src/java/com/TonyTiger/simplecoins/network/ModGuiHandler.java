package com.TonyTiger.simplecoins.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuiHandler implements IGuiHandler {
	
	public static final int MOD_TILE_ENTITY_GUI = 0;

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer arg1, World arg2, int arg3, int arg4, int arg5) {
		if(ID == MOD_TILE_ENTITY_GUI)
			return new ContainerModTileEntity();
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer arg1, World arg2, int arg3, int arg4, int arg5) {
		if (ID == MOD_TILE_ENTITY_GUI)
            return new GuiModTileEntity();
		return null;
	}

}
