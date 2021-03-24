package net.arathain.aot;

import net.arathain.aot.armor.BrigandineArmorMaterial;
import net.arathain.aot.block.BloodlessBlock;
import net.arathain.aot.entity.BloodlessEntity;
import net.arathain.aot.item.BloodlessItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import com.terraformersmc.terraform.wood.block.StrippableLogBlock;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class ArticlesOfTemerant implements ModInitializer {
	public static final String MOD_ID = "aot";
	public static final ArmorMaterial BRIGANDINE_ARMOR_MATERIAL = new BrigandineArmorMaterial();
	public static BlockEntityType<BloodlessEntity> BLOODLESS_ENTITY;
	public static final Item BRIGANDINE_CHESTPLATE = new ArmorItem(BRIGANDINE_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item BRIGANDINE_LEGGINGS = new ArmorItem(BRIGANDINE_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Block BLOODLESS = new BloodlessBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f).nonOpaque().requiresTool());




	public static void register() {
		TemerantBlocks.register();
		Registry.register(Registry.ITEM, new Identifier("aot", "brigandine_chestplate"), BRIGANDINE_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier("aot", "brigandine_leggings"), BRIGANDINE_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier("aot", "bloodless"), new BloodlessItem(BLOODLESS, new FabricItemSettings().group(ItemGroup.COMBAT)));
		Registry.register(Registry.BLOCK, new Identifier("aot", "bloodless"), BLOODLESS);
		BLOODLESS_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "aot:bloodless", BlockEntityType.Builder.create(BloodlessEntity::new, BLOODLESS).build(null));

	}



	@Override
	public void onInitialize() {
		ArticlesOfTemerant.register();

	}

}
