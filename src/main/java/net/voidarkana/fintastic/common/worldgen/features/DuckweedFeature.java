package net.voidarkana.fintastic.common.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;

public class DuckweedFeature extends Feature<RandomPatchConfiguration> {

    public DuckweedFeature(Codec<RandomPatchConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<RandomPatchConfiguration> pContext) {
        RandomPatchConfiguration randompatchconfiguration = pContext.config();
        RandomSource randomsource = pContext.random();
        BlockPos blockpos = pContext.origin();
        WorldGenLevel worldgenlevel = pContext.level();
        int i = 0;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        int j = randompatchconfiguration.xzSpread() + 1;
        int k = randompatchconfiguration.ySpread() + 1;

        for(int l = 0; l < randompatchconfiguration.tries(); ++l) {
            blockpos$mutableblockpos.setWithOffset(blockpos, randomsource.nextInt(j) - randomsource.nextInt(j), randomsource.nextInt(k) - randomsource.nextInt(k), randomsource.nextInt(j) - randomsource.nextInt(j));
            if (randompatchconfiguration.feature().value().place(worldgenlevel, pContext.chunkGenerator(), randomsource, blockpos$mutableblockpos)) {
                ++i;
            }
        }

        return i > 0;
    }
}
