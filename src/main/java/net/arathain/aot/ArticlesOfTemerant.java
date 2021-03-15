package net.arathain.aot;

import net.arathain.aot.armor.BrigandineArmorMaterial;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ArticlesOfTemerant implements ModInitializer {
	public static final ArmorMaterial BRIGANDINE_ARMOR_MATERIAL = new BrigandineArmorMaterial();

	public static final Item BRIGANDINE_CHESTPLATE = new ArmorItem(BRIGANDINE_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item BRIGANDINE_LEGGINGS = new ArmorItem(BRIGANDINE_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
public static void register() {
		Registry.register(Registry.ITEM, new Identifier("aot", "brigandine_chestplate"), BRIGANDINE_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier("aot", "brigandine_leggings"), BRIGANDINE_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier("aot", "talent"), TALENT);
	}
	public static final Item TALENT = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	@Override
	public void onInitialize() {
		ArticlesOfTemerant.register();

	}
}
