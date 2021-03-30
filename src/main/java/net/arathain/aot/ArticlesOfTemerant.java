package net.arathain.aot;

import com.terraformersmc.terraform.boat.TerraformBoat;
import com.terraformersmc.terraform.boat.TerraformBoatEntity;
import com.terraformersmc.terraform.boat.TerraformBoatItem;
import net.arathain.aot.armor.BrigandineArmorMaterial;
import net.arathain.aot.block.BloodlessBlock;
import net.arathain.aot.effect.BlissStatusEffect;
import net.arathain.aot.effect.SweeteaterStatusEffect;
import net.arathain.aot.entity.BloodlessEntity;
import net.arathain.aot.item.BloodlessItem;
import net.arathain.aot.world.TemerantFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.*;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


import java.util.function.Supplier;

public class ArticlesOfTemerant implements ModInitializer {
	public static final String MOD_ID = "aot";
	public static final ArmorMaterial BRIGANDINE_ARMOR_MATERIAL = new BrigandineArmorMaterial();
	public static BlockEntityType<BloodlessEntity> BLOODLESS_ENTITY;
	public static final Item BRIGANDINE_CHESTPLATE = new ArmorItem(BRIGANDINE_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item BRIGANDINE_LEGGINGS = new ArmorItem(BRIGANDINE_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Block BLOODLESS = new BloodlessBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f).nonOpaque().requiresTool());
	public static EntityType<TerraformBoatEntity> DENNER_BOAT;
	public static final StatusEffect BLISS = new BlissStatusEffect();
	public static final StatusEffect SWEETEATER = new SweeteaterStatusEffect();
	public static final FoodComponent.Builder DENNER_RESIN_BUILDER = new FoodComponent.Builder().hunger(5).saturationModifier(0.2F).statusEffect(new StatusEffectInstance(ArticlesOfTemerant.BLISS, 1200), 100).statusEffect(new StatusEffectInstance(ArticlesOfTemerant.SWEETEATER, 12000000), 100);
	public static final FoodComponent DENNER_RESIN_FOOD = DENNER_RESIN_BUILDER.build();
	public static Item DENNER_RESIN = new Item( new Item.Settings().group(ItemGroup.FOOD).food(DENNER_RESIN_FOOD));
	public static final BooleanProperty WEEPING = BooleanProperty.of("weeping");

	public static void register() {
		TemerantBlocks.register();
		Registry.register(Registry.ITEM, new Identifier("aot", "brigandine_chestplate"), BRIGANDINE_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier("aot", "brigandine_leggings"), BRIGANDINE_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier("aot", "bloodless"), new BloodlessItem(BLOODLESS, new FabricItemSettings().group(ItemGroup.COMBAT)));
		Registry.register(Registry.BLOCK, new Identifier("aot", "bloodless"), BLOODLESS);
		BLOODLESS_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "aot:bloodless", BlockEntityType.Builder.create(BloodlessEntity::new, BLOODLESS).build(null));
		DENNER_BOAT = registerBoat("denner", TemerantBlocks.DENNER_PLANKS, BoatEntity.Type.OAK, () -> DENNER_BOAT);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("aot", "bliss"), BLISS);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("aot", "sweeteater"), SWEETEATER);
		Registry.register(Registry.ITEM, new Identifier("aot", "denner_resin"), DENNER_RESIN);
		TemerantFeatures.init();
	}



	@Override
	public void onInitialize() {
		ArticlesOfTemerant.register();
	}



	private static EntityType<TerraformBoatEntity> registerBoat(String name, ItemConvertible planks, BoatEntity.Type vanilla, Supplier<EntityType<TerraformBoatEntity>> boatSupplier) {
		Identifier id = new Identifier(ArticlesOfTemerant.MOD_ID, "denner_boat");
		Identifier skin = new Identifier(ArticlesOfTemerant.MOD_ID, "textures/entity/boat/denner_boat.png");
		Item item = Registry.register(Registry.ITEM, id, new TerraformBoatItem(boatSupplier, new Item.Settings().maxCount(1).group(ItemGroup.TRANSPORTATION)));
		TerraformBoat boat = new TerraformBoat(item.asItem(), planks.asItem(), skin, vanilla);

		EntityType<TerraformBoatEntity> type = FabricEntityTypeBuilder.<TerraformBoatEntity>create(
				SpawnGroup.MISC, (entity, world) -> new TerraformBoatEntity(entity, world, boat))
				.dimensions(EntityDimensions.fixed(0.375F, 0.5625F))
				.build();

		return Registry.register(Registry.ENTITY_TYPE, id, type);
	}




}
