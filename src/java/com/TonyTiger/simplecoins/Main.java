package com.TonyTiger.simplecoins;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.VERSION)

public class Main {
	@SidedProxy(clientSide="com.TonyTiger.simplecoins.ClientProxy",serverSide="com.TonyTiger.simplecoins.ServerProxy")
	public static ClientProxy proxy;
	
	public static final String MODID = "simplecoins";
	public static final String MODNAME = "Simple Coins mod";
	public static final String VERSION = "0.2.0";
	
	@Instance(MODID)
    public static Main instance = new Main();
        
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
//    	System.out.println("Called method: preInit");
    	Main.proxy.preInit(e);
    }
        
    @EventHandler
    public void init(FMLInitializationEvent e) {
//    	System.out.println("Called method: init");
    	Main.proxy.init(e);
    }
        
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
//    	System.out.println("Called method: postInit");
    	Main.proxy.postInit(e);
    }
}
