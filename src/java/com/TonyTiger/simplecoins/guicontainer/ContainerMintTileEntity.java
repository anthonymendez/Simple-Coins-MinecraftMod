package com.TonyTiger.simplecoins.guicontainer;

import com.TonyTiger.simplecoins.TileEntity.MintTileEntity;
import com.TonyTiger.simplecoins.crafting.MintCraftResult;
import com.TonyTiger.simplecoins.items.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerMintTileEntity extends Container {
	
	private MintTileEntity te;
	private IInventory CraftResult;
	private InventoryCrafting inputInventory = new InventoryCrafting(this,1,1);
	protected World worldObj;
	protected BlockPos pos;
	
	public static int numNuggetsNeedForCraft = 3,numCoinsCrafted = 1;
	
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
	
	public ContainerMintTileEntity(InventoryPlayer playerInv, MintTileEntity inte){
		te = inte;
		worldObj = inte.getWorld();
		pos = inte.getPos();
		CraftResult = new MintCraftResult();
		inputInventory.setInventorySlotContents(0, ItemStack.EMPTY);
		this.addSlotToContainer(new Slot(inputInventory,0,53,7));
		this.addSlotToContainer(new SlotCrafting(playerInv.player,inputInventory,CraftResult,1,106,7));
		
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
		Slot slot = (Slot) inventorySlots.get(fromSlot);
        // If there is something in the stack to pick up
        if (slot != null && slot.getHasStack())
        {
            // If the slot is the input
            if (slot.inventory.equals(inputInventory)){
                // try to move to player inventory
                if (!playerIn.inventory.addItemStackToInventory(slot.getStack())){
                    return ItemStack.EMPTY;
                }
                slot.putStack(ItemStack.EMPTY);
                slot.onSlotChanged();
            }
            //if the slot is the output
            else if(slot.inventory.equals(CraftResult)){
            	int inputAmount = inputInventory.getStackInSlot(0).getCount();
            	int remain = inputAmount%numNuggetsNeedForCraft;
            	ItemStack is = slot.getStack();
            	is.setCount(inputAmount/numNuggetsNeedForCraft);
            	if (!playerIn.inventory.addItemStackToInventory(is)){
            		return ItemStack.EMPTY;
                }
            	is = inputInventory.getStackInSlot(0);
            	is.setCount(remain);
            	inputInventory.setInventorySlotContents(0,is);;
            	this.inventorySlots.get(0).putStack(is);;
            	this.inventorySlots.get(0).onSlotChanged();;
            	slot.putStack(ItemStack.EMPTY);
            	slot.onSlotChanged();
            }
            // if the slot is a player inventory slot
            else if(slot.inventory.equals(playerIn.inventory))
            {
                // DEBUG
                System.out.println("Shift-clicked on player inventory slot");
                // Try to transfer to input slot
                if (!((Slot)inventorySlots.get(0)).getHasStack())
                {
                    ((Slot)inventorySlots.get(0)).putStack(slot.getStack());
                    slot.putStack(ItemStack.EMPTY);
                    slot.onSlotChanged();
                }
                else
                {
                    // DEBUG
                    System.out.println("There is already something in the input slot");
                }
            }
        }
        return ItemStack.EMPTY;
	}
	
	@Override
	public ItemStack slotClick(int slotId, int dragType, 
			ClickType clickTypeIn,EntityPlayer player){
		int getInCount = 0;
		int getOutCount = 0;
		int getHandCount = 0;
		if(slotId == 1 && !CraftResult.getStackInSlot(0).isEmpty()
			&& !clickTypeIn.equals(ClickType.QUICK_MOVE)){
			getInCount = inputInventory.getStackInSlot(0).getCount();
			getOutCount = CraftResult.getStackInSlot(0).getCount();
			getHandCount = player.inventory.getItemStack().getCount();
		}
		ItemStack clickStack = super.slotClick(slotId, dragType, clickTypeIn, player);
		if(slotId == 1 && !clickTypeIn.equals(ClickType.QUICK_MOVE)){
			getInCount -= numNuggetsNeedForCraft;
			getOutCount -= numCoinsCrafted;
			getHandCount += numCoinsCrafted;			
			inputInventory.getStackInSlot(0).setCount(getInCount);
			CraftResult.getStackInSlot(0).setCount(getOutCount);
			player.inventory.getItemStack().setCount(getHandCount);
		}
		onCraftMatrixChanged(inputInventory);
		return clickStack;
	}
	
	@Override
    protected void retrySlotClick(int slotId, int dragType, boolean bin, EntityPlayer player) {
		if(slotId == 1){
			this.slotClick(slotId, dragType, ClickType.PICKUP, player);
		}
		else
			super.retrySlotClick(slotId, dragType, bin, player);
    }
	
	@Override
	public void onCraftMatrixChanged(IInventory parInventory){
		if(inputInventory != null){
			ItemStack in = inputInventory.getStackInSlot(0);
			ItemStack ironOut = new ItemStack(ModItems.IRONCOIN,1);
			ItemStack goldOut = new ItemStack(ModItems.GOLDCOIN,1);
			if(in.isItemEqual(new ItemStack(Items.GOLD_NUGGET,1)) && in.getCount() >= 3){
				CraftResult.setInventorySlotContents(0, goldOut);
			}else if(in.isItemEqual(new ItemStack(Item.getByNameOrId("iron_nugget"),1)) && in.getCount() >= 3){
				CraftResult.setInventorySlotContents(0, ironOut);
			}else if(in.isEmpty())
				CraftResult.setInventorySlotContents(0, ItemStack.EMPTY);
		}
	}		

	
	@Override
    public void onContainerClosed(EntityPlayer parPlayer){
        if(!ItemStack.areItemStacksEqual(parPlayer.inventory.getItemStack(),ItemStack.EMPTY)){
            parPlayer.entityDropItem(parPlayer.inventory.getItemStack(), 0.5f);
        }
        if(!worldObj.isRemote){
            ItemStack itemStack = inventoryItemStacks.get(0);
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
