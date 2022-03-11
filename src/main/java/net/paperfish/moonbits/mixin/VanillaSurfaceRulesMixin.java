package net.paperfish.moonbits.mixin;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.world.gen.MBNoiseParameters;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VanillaSurfaceRules.class)
public class VanillaSurfaceRulesMixin {

    @Inject(method = "createOverworldSurfaceRule", at = @At("RETURN"), cancellable = true)
    private static void appendMBSurface(CallbackInfoReturnable<MaterialRules.MaterialRule> cir) {
        MaterialRules.MaterialRule rules = cir.getReturnValue();
        MaterialRules.MaterialCondition notDesert = MaterialRules.not(MaterialRules.biome(
                BiomeKeys.DESERT,
                BiomeKeys.DEEP_FROZEN_OCEAN,
                BiomeKeys.DEEP_LUKEWARM_OCEAN,
                BiomeKeys.DEEP_COLD_OCEAN,
                BiomeKeys.OCEAN,
                BiomeKeys.DEEP_OCEAN,
                BiomeKeys.COLD_OCEAN,
                BiomeKeys.LUKEWARM_OCEAN,
                BiomeKeys.FROZEN_OCEAN,
                BiomeKeys.BEACH,
                BiomeKeys.STONY_SHORE,
                BiomeKeys.SNOWY_BEACH,
                BiomeKeys.GROVE,
                BiomeKeys.FROZEN_PEAKS,
                BiomeKeys.JAGGED_PEAKS,
                BiomeKeys.SNOWY_SLOPES,
                BiomeKeys.STONY_PEAKS,
                BiomeKeys.RIVER,
                BiomeKeys.FROZEN_RIVER
        ));
        MaterialRules.MaterialRule dirt = MaterialRules.block(Blocks.DIRT.getDefaultState());
        MaterialRules.MaterialRule tough_dirt = MaterialRules.block(MBBlocks.TOUGH_DIRT.getDefaultState());
        MaterialRules.MaterialRule regolith = MaterialRules.block(MBBlocks.REGOLITH.getDefaultState());

        cir.setReturnValue(MaterialRules.sequence(
            rules,
            MaterialRules.condition(MaterialRules.biome(BiomeKeys.DESERT), MaterialRules.block(MBBlocks.CHERT.getDefaultState())),
//            MaterialRules.condition(
//                MaterialRules.water(-4, 0),
//                MaterialRules.sequence(
//                    MaterialRules.condition(
//                        notDesert,
//                        MaterialRules.condition(
//                            MaterialRules.aboveY(YOffset.fixed(55), 0),
//                            tough_dirt
//                        )
//                        //MaterialRules.condition(MaterialRules.stoneDepth(0, true, true, VerticalSurfaceType.FLOOR), tough_dirt)
//                    )
//                )
//            ),
//            MaterialRules.condition(
//                notDesert,
//                MaterialRules.condition(
//                    MaterialRules.aboveY(YOffset.fixed(47), 0),
//                    MaterialRules.condition(
//                            MaterialRules.noiseThreshold(MBNoiseParameters.DIRT_CAVES, 0.4, 0.9),
//                            MaterialRules.condition(
//                                    MaterialRules.not(MaterialRules.verticalGradient("tough_dirt", YOffset.fixed(48), YOffset.fixed(55))), tough_dirt
//                    ))
//                )
//            ),
            MaterialRules.condition(
                notDesert,
                MaterialRules.condition(MaterialRules.surface(),
                MaterialRules.condition(MaterialRules.stoneDepth(0, true, 34, VerticalSurfaceType.FLOOR),
                MaterialRules.sequence(
                MaterialRules.condition(
                        MaterialRules.stoneDepth(0, true, 30, VerticalSurfaceType.FLOOR), tough_dirt), regolith
                ))
            )))
        );
    }
}
