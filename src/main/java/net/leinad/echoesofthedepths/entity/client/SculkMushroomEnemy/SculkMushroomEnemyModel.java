package net.leinad.echoesofthedepths.entity.client.SculkMushroomEnemy;

import net.leinad.echoesofthedepths.EchoesOfTheDepths;
import net.leinad.echoesofthedepths.entity.custom.SculkMushroomEnemyEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class SculkMushroomEnemyModel<T extends SculkMushroomEnemyEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer SCULK_MUSHROOM_ENEMY = new EntityModelLayer(Identifier.of(EchoesOfTheDepths.MOD_ID, "sculk_mushroom_enemy"), "main");

    private final ModelPart body;
    private final ModelPart head;

    public SculkMushroomEnemyModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 19.0F, 0.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 39).cuboid(-4.0F, -3.0F, -4.0F, 8.0F, 6.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData up_part = head.addChild("up_part", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -4.0F, -6.0F, 12.0F, 5.0F, 12.0F, new Dilation(0.0F))
                .uv(0, 17).cuboid(-5.0F, -5.0F, -5.0F, 10.0F, 1.0F, 10.0F, new Dilation(0.0F))
                .uv(0, 28).cuboid(-5.0F, 0.5F, -5.0F, 10.0F, 1.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -4.0F, 0.0F));

        ModelPartData right_leg_1 = body.addChild("right_leg_1", ModelPartBuilder.create().uv(32, 39).cuboid(-2.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 2.0F, -3.0F));

        ModelPartData left_leg_1 = body.addChild("left_leg_1", ModelPartBuilder.create().uv(32, 39).mirrored().cuboid(0.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(4.0F, 2.0F, -3.0F));

        ModelPartData right_leg_2 = body.addChild("right_leg_2", ModelPartBuilder.create().uv(32, 39).cuboid(-2.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 2.0F, 0.0F));

        ModelPartData left_leg_2 = body.addChild("left_leg_2", ModelPartBuilder.create().uv(32, 39).mirrored().cuboid(0.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(4.0F, 2.0F, 0.0F));

        ModelPartData right_leg_3 = body.addChild("right_leg_3", ModelPartBuilder.create().uv(32, 39).cuboid(-2.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 2.0F, 3.0F));

        ModelPartData left_leg_3 = body.addChild("left_leg_3", ModelPartBuilder.create().uv(32, 39).mirrored().cuboid(0.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(4.0F, 2.0F, 3.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(SculkMushroomEnemyAnimations.ANIM_SCULK_MUSHROOM_ENEMY_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, SculkMushroomEnemyAnimations.ANIM_SCULK_MUSHROOM_ENEMY_IDLE, ageInTicks, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        body.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return body;
    }


}
