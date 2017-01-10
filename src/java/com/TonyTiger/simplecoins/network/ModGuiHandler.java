package com.TonyTiger.simplecoins.network;

import com.TonyTiger.simplecoins.TileEntity.MintTileEntity;
import com.TonyTiger.simplecoins.client.gui.GuiMintTileEntity;
import com.TonyTiger.simplecoins.guicontainer.ContainerMintTileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuiHandler implements IGuiHandler {
	
	public static final int MOD_TILE_ENTITY_GUI = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	    if (ID == MOD_TILE_ENTITY_GUI)
	        return new ContainerMintTileEntity(player.inventory, (MintTileEntity) world.getTileEntity(new BlockPos(x, y, z)));

	    return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	    if (ID == MOD_TILE_ENTITY_GUI)
	        return new GuiMintTileEntity(player.inventory, (MintTileEntity) world.getTileEntity(new BlockPos(x, y, z)));

	    return null;
	}

}
