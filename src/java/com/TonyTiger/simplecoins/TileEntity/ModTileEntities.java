package com.TonyTiger.simplecoins.TileEntity;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {
	public static void init(){
		GameRegistry.registerTileEntity(MintTileEntity.class, "simplecoins_tile_entity");
	}
}
