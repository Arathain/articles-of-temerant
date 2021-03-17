package net.arathain.aot;

import net.arathain.aot.armor.BrigandineArmorMaterial;
import net.arathain.aot.block.BloodlessBlock;
import net.arathain.aot.entity.BloodlessEntity;
import net.arathain.aot.item.BloodlessItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ArticlesOfTemerant implements ModInitializer {
	public static final ArmorMaterial BRIGANDINE_ARMOR_MATERIAL = new BrigandineArmorMaterial();
	public static BlockEntityType<BloodlessEntity> BLOODLESS_ENTITY;
	public static final Item BRIGANDINE_CHESTPLATE = new ArmorItem(BRIGANDINE_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item BRIGANDINE_LEGGINGS = new ArmorItem(BRIGANDINE_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Block BLOODLESS = new BloodlessBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f).nonOpaque().requiresTool());

public static void register() {
		Registry.register(Registry.ITEM, new Identifier("aot", "brigandine_chestplate"), BRIGANDINE_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier("aot", "brigandine_leggings"), BRIGANDINE_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier("aot", "talent"), TALENT);
        Registry.register(Registry.BLOCK, new Identifier("aot", "bloodless"), BLOODLESS);
        Registry.register(Registry.ITEM, new Identifier("aot", "bloodless"), new BloodlessItem(BLOODLESS, new FabricItemSettings().group(ItemGroup.COMBAT)));
		BLOODLESS_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "aot:bloodless", BlockEntityType.Builder.create(BloodlessEntity::new, BLOODLESS).build(null));
}
	public static final Item TALENT = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	@Override
	public void onInitialize() {
		ArticlesOfTemerant.register();

	}
}
