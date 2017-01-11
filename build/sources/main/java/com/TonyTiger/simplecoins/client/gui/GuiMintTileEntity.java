package com.TonyTiger.simplecoins.client.gui;

import com.TonyTiger.simplecoins.TileEntity.MintTileEntity;
import com.TonyTiger.simplecoins.guicontainer.ContainerMintTileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiMintTileEntity extends GuiContainer{
	
	private InventoryPlayer playerInv;
	private MintTileEntity te;
	public static double decreaseBy = 2;
	
	public GuiMintTileEntity(InventoryPlayer playerInv, MintTileEntity te){
		super(new ContainerMintTileEntity(playerInv,te));
		this.te = te;
		this.playerInv = playerInv;
		//Note to self, MAKE THE IMAGE SMALLER THAN THIS SO ITS NOT A COMPLETE BITCH
		// DRAWING THE GUI
		this.xSize = (int)(352/decreaseBy);
		this.ySize = (int)(232/decreaseBy);
	}
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mousex, int mousey) {
		GlStateManager.color(1.0f,1.0f,1.0f,1.0f);
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		this.mc.getTextureManager().bindTexture(new ResourceLocation("simplecoins:textures/gui/container/mint.png"));
		this.drawModalRectWithCustomSizedTexture(this.guiLeft,this.guiTop,
				0,0,xSize,ySize, xSize, ySize);
//		this.fontRendererObj.drawString("Mint", 10, 10, 1, true);

	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
	    String s = this.te.getDisplayName().getUnformattedText();
	    this.fontRendererObj.drawString("Mint", 88 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);            //#404040
	    this.fontRendererObj.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, 24, 4210752);      //#404040
	}

}
