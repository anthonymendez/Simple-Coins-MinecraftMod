package com.TonyTiger.simplecoins.block;

import com.TonyTiger.simplecoins.Main;
import com.TonyTiger.simplecoins.TileEntity.MintTileEntity;
import com.TonyTiger.simplecoins.network.ModGuiHandler;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MintBlock extends Block{
	
	public final static String modid = Main.MODID;
	
	public MintBlock(String unlocalizedName, Material material, float hardness, float resistance){
		super(material);
		
		this.setSoundType(SoundType.METAL);
		this.setUnlocalizedName(unlocalizedName);
		this.setHarvestLevel("pickaxe",0);
		this.setCreativeTab(CreativeTabs.MISC);
		this.setResistance(resistance);
		this.setHardness(hardness);
		this.setRegistryName(modid, unlocalizedName);
		
	}
	public MintBlock(String unlocalizedName, float hardness, float resistance) {
        this(unlocalizedName, Material.IRON, hardness, resistance);
    }

    public MintBlock(String unlocalizedName) {
        this(unlocalizedName, 2.0f, 10.0f);   
    }
    @Override
    public boolean hasTileEntity(IBlockState state){
    	return true;
    }
	@Override
	public TileEntity createTileEntity(World worldIn, IBlockState state) {
		return new MintTileEntity();
	}
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, 
			EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!world.isRemote)
	        player.openGui(Main.instance, ModGuiHandler.MOD_TILE_ENTITY_GUI, world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState blockstate){
	    MintTileEntity te = (MintTileEntity) world.getTileEntity(pos);
	    InventoryHelper.dropInventoryItems(world, pos, te);
	    super.breakBlock(world, pos, blockstate);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
	    if (stack.hasDisplayName()) {
	        ((MintTileEntity) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
	    }
	}
	
}
