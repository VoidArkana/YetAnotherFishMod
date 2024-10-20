package net.voidarkana.yetanotherfishmod.client.renderers;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.yetanotherfishmod.common.entity.custom.DaphneaSwarmEntity;

public class DaphneaSwarmRenderer<T extends DaphneaSwarmEntity> extends EntityRenderer<DaphneaSwarmEntity> {

    public DaphneaSwarmRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.shadowRadius = 0;
        this.shadowStrength = 0;
    }

    @Override
    public ResourceLocation getTextureLocation(DaphneaSwarmEntity pEntity) {
        return null;
    }
}
