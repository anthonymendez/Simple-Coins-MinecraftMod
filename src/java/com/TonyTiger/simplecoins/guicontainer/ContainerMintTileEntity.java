package com.TonyTiger.simplecoins.guicontainer;

import com.TonyTiger.simplecoins.TileEntity.MintTileEntity;
import com.TonyTiger.simplecoins.items.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerMintTileEntity extends Container {
	
	private MintTileEntity te;
	protected World worldObj;
	protected BlockPos pos;
	
	/*
	 * SLOTS:
	 * 
	 * Input 0 ........ 0
	 * Output 1 ........ 1
	 * Player Inventory 9-35 .. 9  - 35
	 * Player Inventory 0-8 ... 29 - 37
	 */
	
	public MintTileEntity getMintTileEntity(){
		return te;
	}
	
	public ContainerMintTileEntity(IInventory playerInv, MintTileEntity inte){
		te = inte;
		worldObj = inte.getWorld();
		pos = inte.getPos();
		
		//Tile slot input
		this.addSlotToContainer(new Slot(te,0,53,7));
		
		//Tile slot output
		this.addSlotToContainer(new Slot(te,1,106,7));
		
		// Player Inventory, Slot 9-35, Slot IDs 9  - 35
	    for (int y = 0; y < 3; ++y) {
	        for (int x = 0; x < 9; ++x) {
	            if(y == 0)
	        	this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 34 + y));
	            else if(y == 1)
	            this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 51 + y));
	            else if(y == 2)
		        this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 68 + y));
	        }
	    }

	    // Player Inventory, Slot 0-8, Slot IDs 29 - 37
	    for (int x = 0; x < 9; ++x) {
	        this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 92));
	    }
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return te.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
	    ItemStack previous = ItemStack.EMPTY;
	    Slot slot = (Slot) this.inventorySlots.get(fromSlot);
	    
	    if (slot != null && slot.getHasStack()) {
	        ItemStack current = slot.getStack();
	        previous = current.copy();

	        if (fromSlot < 2) {
	            // From TE Inventory to Player Inventory
	            if (!this.mergeItemStack(current, 9, 37, true))
	                return ItemStack.EMPTY;
	        }else{
	            // From Player Inventory to TE Inventory
	            if (!this.mergeItemStack(current, 0, 1, false))
	                return ItemStack.EMPTY;
	        }
	        
	        if (current.getCount() == 0)
	            slot.putStack(ItemStack.EMPTY);
	        else
	            slot.onSlotChanged();

	        if (current.getCount() == previous.getCount())
	            return ItemStack.EMPTY;
	        slot.onTake(playerIn, current);
	    }
	    te.markDirty();
	    return previous;
	}
	
	@Override
	public ItemStack slotClick(int slotId, int dragType, 
			ClickType clickTypeIn,EntityPlayer player){			
		ItemStack clickItemStack = super.slotClick(slotId, dragType, 
	              clickTypeIn, player);
		System.out.println(clickItemStack.getDisplayName());
	    te.markDirty();
	    return clickItemStack;
	}
	
	@Override
    public void onContainerClosed(EntityPlayer parPlayer)
    {
        if(!ItemStack.areItemStacksEqual(parPlayer.inventory.getItemStack(),ItemStack.EMPTY)){
            parPlayer.entityDropItem(parPlayer.inventory.getItemStack(), 0.5f);
        }
        if(!worldObj.isRemote){
            ItemStack itemStack = inventoryItemStacks.get(0);
            if(!ItemStack.areItemStacksEqual(ItemStack.EMPTY, itemStack)){
                parPlayer.entityDropItem(itemStack, 0.5f);
            }
            itemStack = inventoryItemStacks.get(1);
            if(!ItemStack.areItemStacksEqual(ItemStack.EMPTY, itemStack)){
                parPlayer.entityDropItem(itemStack, 0.5f);
            }
        }
    }

	
	@Override
	public void putStackInSlot(int slotID, ItemStack stack) {
		super.putStackInSlot(slotID, stack);
	}
}
