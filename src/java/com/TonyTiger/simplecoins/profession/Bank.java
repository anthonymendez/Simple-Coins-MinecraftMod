package com.TonyTiger.simplecoins.profession;

import java.util.List;
import java.util.Random;

import com.TonyTiger.simplecoins.block.ModBlocks;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class Bank extends StructureVillagePieces.Village{
	
	public Bank(){}
	
	public Bank(StructureVillagePieces.Start start, int type, Random rand, StructureBoundingBox box, EnumFacing facing){
		super(start,type);
		this.setCoordBaseMode(facing);
		this.boundingBox = box;
	}
	
	protected void writeStructureToNBT(NBTTagCompound tagCompound)
    {
        super.writeStructureToNBT(tagCompound);
    }

    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager TempManager)
    {
        super.readStructureFromNBT(tagCompound, TempManager);
    }
	
	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
		if (this.averageGroundLvl < 0){
            this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);
            if (this.averageGroundLvl < 0){
                return true;
            }
            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 6 - 1, 0);
        }
		
		IBlockState brick = Blocks.BRICK_BLOCK.getDefaultState(),
			stairs = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH)),
			wood = this.getBiomeSpecificBlockState(Blocks.PLANKS.getDefaultState()),
			glass = Blocks.GLASS.getDefaultState(), air = Blocks.AIR.getDefaultState(),
			glow = Blocks.GLOWSTONE.getDefaultState(), bar = Blocks.IRON_BARS.getDefaultState(),
			table = Blocks.CRAFTING_TABLE.getDefaultState(),
			book = Blocks.BOOKSHELF.getDefaultState(),
			mint = ModBlocks.mint.getDefaultState(),
			BstairsE = this.getBiomeSpecificBlockState(Blocks.BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST)),
			BstairsN = this.getBiomeSpecificBlockState(Blocks.BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH)),
			BstairsW = this.getBiomeSpecificBlockState(Blocks.BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST)),
			BstairsS = this.getBiomeSpecificBlockState(Blocks.BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
		this.fillWithAir(worldIn, structureBoundingBoxIn, 0, 0, 0, 9, 9, 9);
		//Foundation World               Box    (Min/Max)    x  y  z  x  y  z  blockstates, existing only
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 2, 0, 1, air, air, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 0, 1, 6, 0, 1, stairs, stairs, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 0, 1, 8, 0, 1, air, air, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 2, 8, 0, 3, wood, wood, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 5, 7, 0, 7, wood, wood, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 4, 8, 0, 4, brick, brick, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 5, 8, 0, 8, brick, brick, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 8, 8, 0, 8, brick, brick, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 0, 6, 8, 0, 8, brick, brick, false);
		
		//Walls 
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 4, 1, 3, 8, brick, brick, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 1, 4, 8, 3, 8, brick, brick, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 8, 7, 3, 8, brick, brick, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 4, 7, 3, 4, brick, brick, false);
		
		//Roof
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 4, 1, 4, 8, BstairsE, BstairsE, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 4, 4, 8, 4, 8, BstairsW, BstairsW, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 4, 8, 7, 4, 8, BstairsS, BstairsS, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 4, 4, 7, 4, 4, BstairsN, BstairsN, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 4, 5, 7, 4, 7, glass, glass, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 4, 6, 7, 4, 6, glow, glow, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 4, 6, 3, 4, 6, glow, glow, false);
		
		//Front
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 2, 1, 1, 3, bar, bar, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 1, 2, 8, 1, 3, bar, bar, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 2, 2, 3, 2, bar, bar, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 1, 2, 7, 3, 2, bar, bar, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 3, 2, 7, 3, 2, bar, bar, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 2, 4, 4, 2, 4, bar, bar, false);
		this.placeTorch(worldIn, EnumFacing.SOUTH, 2, 3, 3, structureBoundingBoxIn);
		this.placeTorch(worldIn, EnumFacing.SOUTH, 7, 3, 3, structureBoundingBoxIn);
		this.createVillageDoor(worldIn, structureBoundingBoxIn, new Random(), 6, 1, 4, EnumFacing.EAST);
		
		//Inside
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 5, 2, 1, 5, table, table, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 1, 5, 7, 1, 5, table, table, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 1, 6, 7, 1, 7, book, book, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 7, 2, 1, 7, mint, mint, false);
		
		for (int k = 0; k < 10; ++k)
        {
            for (int j = 0; j < 10; ++j)
            {
                this.replaceAirAndLiquidDownwards(worldIn, air, j, 6, k, structureBoundingBoxIn);
            }
        }
		
		
		this.spawnVillagers(worldIn, structureBoundingBoxIn, 6, 3, 4, 1);
		return true;
	}
	
	@SuppressWarnings("deprecation")
	protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession)
    {
        return VillagerRegistry.instance().getId(BankerProfessionAndCareer.bankerProf);
    }

	public static Bank createPiece(StructureVillagePieces.Start start, List<StructureComponent> p_175855_1_, Random rand, int p_175855_3_, int p_175855_4_, int p_175855_5_, EnumFacing facing, int p_175855_7_){
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175855_3_, p_175855_4_, p_175855_5_, 0, 0, 0, 10, 6, 7, facing);
        return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175855_1_, structureboundingbox) == null ? new Bank(start, p_175855_7_, rand, structureboundingbox, facing) : null;
    }

	public static Village buildComponent(PieceWeight villagePiece, Start startPiece, List<StructureComponent> pieces,
			Random random, int p1, int p2, int p3, EnumFacing facing, int p5) {
		return createPiece(startPiece, pieces, random, p1, p2, p3, facing, p5);
	}
	
}
