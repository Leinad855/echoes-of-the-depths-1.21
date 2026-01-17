package net.leinad.echoesofthedepths.entity.client.SculkMushroomEnemy;

import net.leinad.echoesofthedepths.EchoesOfTheDepths;
import net.leinad.echoesofthedepths.entity.custom.SculkMushroomEnemyEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class SculkMushroomEnemyRender extends MobEntityRenderer<SculkMushroomEnemyEntity, SculkMushroomEnemyModel<SculkMushroomEnemyEntity>> {
    public SculkMushroomEnemyRender(EntityRendererFactory.Context context) {
        super(context, new SculkMushroomEnemyModel<>(context.getPart(SculkMushroomEnemyModel.SCULK_MUSHROOM_ENEMY)), 0.5f);
    }

    @Override
    public Identifier getTexture(SculkMushroomEnemyEntity entity) {
        return Identifier.of(EchoesOfTheDepths.MOD_ID, "textures/entity/sculk_mushroom_enemy/sculk_mushroom_enemy.png");
    }

    @Override
    public void render(SculkMushroomEnemyEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {

        matrixStack.scale(1f, 1f, 1f);
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    protected void scale(SculkMushroomEnemyEntity sculkMushroomEnemy, MatrixStack matrixStack, float f) {
        float g = sculkMushroomEnemy.getClientFuseTime(f);
        float h = 1.0F + MathHelper.sin(g * 100.0F) * g * 0.01F;
        g = MathHelper.clamp(g, 0.0F, 1.0F);
        g *= g;
        g *= g;
        float i = (1.0F + g * 0.4F) * h;
        float j = (1.0F + g * 0.1F) / h;
        matrixStack.scale(i, j, i);
    }
}
