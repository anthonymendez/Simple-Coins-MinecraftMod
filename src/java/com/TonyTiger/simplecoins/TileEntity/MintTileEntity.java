package com.TonyTiger.simplecoins.TileEntity;

import com.TonyTiger.simplecoins.guicontainer.ContainerMintTileEntity;
import com.TonyTiger.simplecoins.items.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class MintTileEntity extends TileEntity implements ITickable, IInventory {
    
    private NonNullList<ItemStack> inventory = NonNullList.create();
    private String customName = "";

    public MintTileEntity(){
    	inventory.add(ItemStack.EMPTY);
    	inventory.add(ItemStack.EMPTY);
    	this.markDirty();
    }
    
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt){
    	super.writeToNBT(nbt);

        NBTTagList list = new NBTTagList();
        for (int i = 0; i < this.getSizeInventory(); ++i) {
            if (this.getStackInSlot(i) != ItemStack.EMPTY) {
                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte("Slot", (byte) i);
                this.getStackInSlot(i).writeToNBT(stackTag);
                list.appendTag(stackTag);
            }
        }
        nbt.setTag("Items", list);

        if (this.hasCustomName()) {
            nbt.setString("CustomName", this.getCustomName());
        }
        this.markDirty();
        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
    	super.readFromNBT(nbt);

        NBTTagList list = nbt.getTagList("Items", 10);
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound stackTag = list.getCompoundTagAt(i);
            int slot = stackTag.getByte("Slot") & 255;
            // Was formerly ItemStack.loadItemStackFromNBT(stackTag)
            this.setInventorySlotContents(slot, new ItemStack(stackTag));
        }

        if (nbt.hasKey(this.getCustomName(), 8)) {
            this.setCustomName(nbt.getString(this.getCustomName()));
        }
    }
    
    @Override
    public NBTTagCompound getUpdateTag(){
        return writeToNBT(new NBTTagCompound());
    }
	
	@Override
	public void update() {
		if(this.getStackInSlot(0).getItem().equals(Items.IRON_INGOT)){
			this.setInventorySlotContents
				(1, new ItemStack(ModItems.IRONCOIN,4));
		}else if(this.getStackInSlot(0).getItem().equals(Items.GOLD_INGOT)){
			this.setInventorySlotContents
				(1, new ItemStack(ModItems.GOLDCOIN,4));
		}
		
	}
	
	public String getCustomName() {
        return this.customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container.simplecoins_tile_entity";
	}

	@Override
	public boolean hasCustomName() {
		return this.customName.equals(null) &&
				!this.customName.equals("");
	}
	
	@Override
	public ITextComponent getDisplayName() {
	    return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.getSizeInventory(); i++)
	        this.setInventorySlotContents(i, ItemStack.EMPTY);
	}

	@Override
	public void closeInventory(EntityPlayer player){
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (ItemStack.areItemStacksEqual(this.getStackInSlot(index),ItemStack.EMPTY)) {
	        ItemStack itemstack = ItemStack.EMPTY;

	        if (this.getStackInSlot(index).getCount() <= count) {
	            itemstack = this.getStackInSlot(index);
	            this.setInventorySlotContents(index, ItemStack.EMPTY);
	            this.markDirty();
	            return itemstack;
	        } else {
	            itemstack = this.getStackInSlot(index).splitStack(count);

	            if (this.getStackInSlot(index).getCount() <= 0) {
	                this.setInventorySlotContents(index, ItemStack.EMPTY);
	            } else {
	                //Just to show that changes happened
	                this.setInventorySlotContents(index, this.getStackInSlot(index));
	            }

	            this.markDirty();
	            return itemstack;
	        }
	    } else {
	        return ItemStack.EMPTY;
	    }
	}

	@Override
	public int getField(int arg0) {
		return 0;
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public int getSizeInventory() {
		return inventory.size();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.inventory.get(index);
	}

	@Override
	public boolean isEmpty() {
		for(ItemStack is : inventory){
			if(is.isEmpty() && is.getCount() == 0)
				return true;
		}
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int inint, ItemStack is) {
		if(((ItemStack.areItemsEqual(new ItemStack(Items.IRON_INGOT,1), is) 
		|| ItemStack.areItemsEqual(new ItemStack(Items.GOLD_INGOT,1), is))
				&& inint == 0) || inint != 1)
			return true;
		return false;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.getWorld().getTileEntity(this.getPos()) == this 
		&& player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
	}

	@Override
	public void openInventory(EntityPlayer arg0){}
	
	//Was formerly getStackInSlotOnClosing(int index)
	@Override
	public ItemStack removeStackFromSlot(int index){
		ItemStack stack = this.getStackInSlot(index);
	    this.setInventorySlotContents(index, ItemStack.EMPTY);
	    System.out.println(stack.getDisplayName());
	    return stack;
	}

	@Override
	public void setField(int arg0, int arg1) {	
	}
	
	//CALL THIS! DONT CALL INVENTORY.SET SINCE THIS ALREADY DOES IT!
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index < 0 || index >= this.getSizeInventory())			
	        return;
	    if (stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit())
	        stack.setCount(this.getInventoryStackLimit());
	        
	    if (stack.isEmpty() && stack.getCount() == 0)
	        stack = ItemStack.EMPTY;

	    this.inventory.set(index, stack);
	    this.markDirty();
	}

}
