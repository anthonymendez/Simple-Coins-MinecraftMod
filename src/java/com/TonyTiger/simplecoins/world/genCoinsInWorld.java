package com.TonyTiger.simplecoins.world;

import java.util.ArrayList;
import java.util.Random;

import com.TonyTiger.simplecoins.items.ModItems;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.event.LootTableLoadEvent;

public class genCoinsInWorld {
	public static int goldChance = 5, ironChance = 5;
	public static int minGoldGen = 0, maxGoldGen = 5, 
					  minIronGen = 0, maxIronGen = 5;
	
	public static void lootLoad(LootTableLoadEvent event){
		if(!event.getName().getResourcePath().contains("chest"))
			return;
		addItemToTable(event.getTable(),ModItems.IRONCOIN,minIronGen,maxIronGen,ironChance,
				1,0,"simplecoins:ironcoin_Item");
		addItemToTable(event.getTable(),ModItems.GOLDCOIN,minGoldGen,maxGoldGen,goldChance,
				1,0,"simplecoins:goldcoin_Item");
	}
	
	private static void addItemToTable(LootTable table, Item item,int min,int max,int weight,int rolls, int bonusrolls,String name){
		ICondition LootConditions = new ICondition(){
			@Override
			public void FunctionsCallback(LootCondition lootconds) {}
		};
		IMethod LootCallbacks = new IMethod(){
			@Override
			public void FunctionsCallback(ArrayList<LootFunction> lootfuncs){}
		};
		LootCondition conditions = new RandomChance(rolls);
		LootConditions.FunctionsCallback(conditions);
		LootCondition[] chance = {conditions};
		
		ArrayList<LootFunction> funcArr = new ArrayList<LootFunction>();
		funcArr.add(new SetCount(chance, new RandomValueRange(min, max)));
		funcArr.add(new SetMetadata(new LootCondition[]{}, new RandomValueRange(0, 0)));
		LootCallbacks.FunctionsCallback(funcArr);
		LootFunction[] count = funcArr.toArray(new LootFunction[0]);
		
		LootEntryItem[] LEI = {new LootEntryItem(item,weight,0,count,chance,name)};
		
		LootPool LP = new LootPool(LEI, chance, new RandomValueRange(1,rolls),new RandomValueRange(0), name);
		
		table.addPool(LP);
	}
	public static int randRange(int min, int max){
		RandomValueRange r = new RandomValueRange(min,max);
		return r.generateInt(new Random());
	}
	
	public static interface IMethod {
		public void FunctionsCallback(ArrayList<LootFunction> lootfuncs);
	}
	
	public static interface ICondition {
		public void FunctionsCallback(LootCondition lootconds);
	}
}