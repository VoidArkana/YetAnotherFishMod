package net.voidarkana.yetanotherfishmod.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.client.layer.GuppyFins;
import net.voidarkana.yetanotherfishmod.client.layer.GuppyPatternMain;
import net.voidarkana.yetanotherfishmod.client.layer.GuppyPatternSecond;
import net.voidarkana.yetanotherfishmod.client.layer.GuppyTail;
import net.voidarkana.yetanotherfishmod.client.models.GuppyModel;
import net.voidarkana.yetanotherfishmod.common.entity.custom.GuppyEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GuppyRenderer extends GeoEntityRenderer<GuppyEntity> {

    public GuppyRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GuppyModel());
        this.addRenderLayer(new GuppyFins(this));
        this.addRenderLayer(new GuppyTail(this));
        this.addRenderLayer(new GuppyPatternMain(this));
        this.addRenderLayer(new GuppyPatternSecond(this));
    }

    @Override
    public ResourceLocation getTextureLocation(GuppyEntity barbfishEntity) {
        return new ResourceLocation(YetAnotherFishMod.MOD_ID, barbfishEntity.isBaby()
                ? "textures/entity/guppy/baby_guppy.png"
                : "textures/entity/guppy/base/guppy_base_"+barbfishEntity.getVariantSkin()+".png");
    }

    @Override
    public void render(GuppyEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }
}
