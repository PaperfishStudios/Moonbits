package net.paperfish.moonbits.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.MBData;
import net.paperfish.moonbits.MBParticles;
import net.paperfish.moonbits.screen.KilnScreen;


@Environment(EnvType.CLIENT)
public class MoonbitsClient implements ClientModInitializer {
    public static final CompassHud compassHud = new CompassHud(MinecraftClient.getInstance());

    @Override
    public void onInitializeClient() {
        // cutout
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                MBBlocks.ROPE_LADDER,
                MBBlocks.IRON_LADDER,
                MBBlocks.TOUGH_GRASS,

                MBBlocks.MYCELIUM_ROOTS,

                MBBlocks.BUTTERCUP,
                MBBlocks.POTTED_BUTTERCUP,
                MBBlocks.FORGETMENOT,
                MBBlocks.POTTED_FORGETMENOT,

                MBBlocks.PUFFBALLS,
                MBBlocks.POTTED_PUFFBALLS,
                MBBlocks.SAFFRON_MUSHROOM,
                MBBlocks.POTTED_SAFFRON_MUSHROOM,
                MBBlocks.SAFFRON_GILLS,
                MBBlocks.TOADSTOOL,
                MBBlocks.SMALL_TOADSTOOLS,
                MBBlocks.POTTED_SMALL_TOADSTOOLS,
                MBBlocks.TOADSTOOL_SHELF,

                MBBlocks.WHITE_HYACINTH,
                MBBlocks.PINK_HYACINTH,
                MBBlocks.LIGHT_BLUE_HYACINTH,
                MBBlocks.RED_HYACINTH,

                MBBlocks.WHITE_HEATHER,
                MBBlocks.RED_HEATHER,
                MBBlocks.ORANGE_HEATHER,
                MBBlocks.PURPLE_HEATHER,
                MBBlocks.LUPINE,

                MBBlocks.BARREL_CACTUS,
                MBBlocks.MARIGOLD,
                MBBlocks.POTTED_MARIGOLD,

                MBBlocks.WILD_CARROTS,
                MBBlocks.WILD_POTATOES,
                MBBlocks.SEA_BEETS,

                MBBlocks.BEACHGRASS,
                MBBlocks.TALL_BEACHGRASS,
                MBBlocks.COTTONGRASS,
                MBBlocks.TALL_COTTONGRASS,
                MBBlocks.DESERT_BRUSH,
                MBBlocks.TALL_DESERT_BRUSH,

                MBBlocks.LAMPROOT,
                MBBlocks.CAVEBLOOM_VINE,
                MBBlocks.CAVEBLOOM_FLOWERS,

                MBBlocks.JUNIPER_DOOR,
                MBBlocks.JUNIPER_TRAPDOOR,
                MBBlocks.JUNIPER_SAPLING,
                MBBlocks.POTTED_JUNIPER_SAPLING,
                MBBlocks.CEDAR_DOOR,
                MBBlocks.CEDAR_TRAPDOOR,
                MBBlocks.CEDAR_SAPLING,
                MBBlocks.POTTED_CEDAR_SAPLING,

                MBBlocks.ASPEN_LATTICE,
                MBBlocks.ASPEN_LEAF_CARPET,
                MBBlocks.ASPEN_SAPLING,
                MBBlocks.POTTED_ASPEN_SAPLING,

                MBBlocks.FLOWERING_ACACIA_LEAVES,
                MBBlocks.HANGING_FLOWERING_ACACIA_LEAVES,
                MBBlocks.TALL_FLOWERING_ACACIA_LEAVES,

                MBBlocks.HONEY_DOOR,
                MBBlocks.HONEY_TRAPDOOR,

                MBBlocks.RED_OAK_LEAF_CARPET,
                MBBlocks.RED_OAK_SAPLING,
                MBBlocks.POTTED_RED_OAK_SAPLING,
                MBBlocks.GOLDEN_BIRCH_LEAF_CARPET,
                MBBlocks.GOLDEN_BIRCH_SAPLING,
                MBBlocks.POTTED_GOLDEN_BIRCH_SAPLING,
                MBBlocks.GLASS_DOOR,
                MBBlocks.WALL_LANTERN,
                MBBlocks.WALL_SOUL_LANTERN
        );

        MBData.registerClient();
        MBParticles.registerParticleClient();

        MBEntityType.initEntityClient();

        ScreenRegistry.register(MBData.KILN_SCREEN_HANDLER, KilnScreen::new);

        HudRenderCallback.EVENT.register((((matrixStack, tickDelta) -> {
            compassHud.renderCompass(matrixStack);
        })));
        
    }
    
}
