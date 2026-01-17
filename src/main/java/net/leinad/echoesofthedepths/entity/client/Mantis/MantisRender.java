package net.leinad.echoesofthedepths.entity.client.Mantis;

import net.leinad.echoesofthedepths.EchoesOfTheDepths;
import net.leinad.echoesofthedepths.entity.custom.MantisEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class MantisRender extends MobEntityRenderer<MantisEntity, MantisModel<MantisEntity>> {
    public MantisRender(EntityRendererFactory.Context context) {
        super(context, new MantisModel<>(context.getPart(MantisModel.MANTIS)), 0.75f);
    }

    @Override
    public Identifier getTexture(MantisEntity entity) {
        return Identifier.of(EchoesOfTheDepths.MOD_ID, "textures/entity/mantis/mantis.png");
    }

    @Override
    public void render(MantisEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if (livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
