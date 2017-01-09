package com.TonyTiger.simplecoins.block;

import com.TonyTiger.simplecoins.Main;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
		this.isBlockContainer = true;
		this.setRegistryName(modid, unlocalizedName);
	}
	public MintBlock(String unlocalizedName, float hardness, float resistance) {
        this(unlocalizedName, Material.IRON, hardness, resistance);
    }

    public MintBlock(String unlocalizedName) {
        this(unlocalizedName, 2.0f, 10.0f);
        
    }
	
}
