package com.TonyTiger.simplecoins.config;

import java.util.*;

import com.TonyTiger.simplecoins.Main;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.*;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
//Thanks to Minalien and ljfa on FTB forum for the awesome example
public class ConfigGui extends GuiConfig{
	public ConfigGui(GuiScreen parent){
		super(parent, getConfigElements(),Main.MODID,false,false,"Money money money...");
		titleLine2 = ConfigHandler.configMain.getConfigFile().getAbsolutePath();
	}
	private static List<IConfigElement> getConfigElements(){
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		
		list.add(categoryElement("Villagers", "Villagers", "com.TonyTiger.simplecoins.configV"));
		list.add(categoryElement("Trading", "Trading", "com.TonyTiger.simplecoins.configT"));
		list.add(categoryElement("Crafting", "Crafting", "com.TonyTiger.simplecoins.configC"));
		list.add(categoryElement("Mob Drops", "Mob Drops", "com.TonyTiger.simplecoins.configMD"));
		
		return list;
	}
	
	private static IConfigElement categoryElement(String category, String name, String tooltip_key) {
        return new DummyConfigElement.DummyCategoryElement(name, tooltip_key,
                new ConfigElement(ConfigHandler.configMain.getCategory(category)).getChildElements());
    }
	
}
