package com.TonyTiger.simplecoins.profession;

import java.util.List;
import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class BankVillageHandler implements IVillageCreationHandler {
	
	public static int limit = 1;
	
	@Override
	public PieceWeight getVillagePieceWeight(Random random, int i) {
		return new PieceWeight(Bank.class, 5, limit);
	}

	@Override
	public Class<?> getComponentClass() {
		return Bank.class;
	}

	@Override
	public Village buildComponent(PieceWeight villagePiece, Start startPiece, List<StructureComponent> pieces,
			Random random, int p1, int p2, int p3, EnumFacing facing, int p5) {
		return Bank.buildComponent(villagePiece, startPiece, pieces, random, p1, p2, p3, facing, p5);
	}
	
}
