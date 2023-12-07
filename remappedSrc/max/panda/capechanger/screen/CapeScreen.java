package max.panda.capechanger.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import max.panda.capechanger.CapeChanger;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class CapeScreen extends Screen {
    private final Screen parent;

    public CapeScreen(Screen parent) {
        super(Text.literal("Cape Toggle"));
        this.parent = parent;
    }

    public ButtonWidget button1;
    public ButtonWidget button2;
    public ButtonWidget exit;

    @Override
    protected void init() {
        button1 = ButtonWidget.builder(Text.literal("Turn Cape On"), button -> {
                    CapeChanger.CAPE_ON = true;
                })
                .dimensions(width / 2 - 205, height/4*3-25, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Turn Custom Cape On")))
                .build();
        button2 = ButtonWidget.builder(Text.literal("Turn Cape Off"), button -> {
                    CapeChanger.CAPE_ON = false;
                })
                .dimensions(width / 2 + 5, height/4*3-25, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Turn Custom Cape Off")))
                .build();

        exit = ButtonWidget.builder(Text.literal("Exit"), button -> {
                    this.close();
                })
                .dimensions(width / 2 - 50, height-50, 100, 20)
                .build();
        addDrawableChild(button1);
        addDrawableChild(button2);
        addDrawableChild(exit);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        assert this.client != null;
        if (this.client.world != null) {
            this.renderInGameBackground(context);
        } else {
            this.renderBackgroundTexture(context);
        }
        int i = width/2-50;
        int j = height/2-70;
        assert Objects.requireNonNull(this.client).player != null;
        assert this.client.player != null;
        drawEntity(context, i + 26, j + 8, i + 75, j + 78, 40, 0.0625F, (float) width /2, (float) height /2-50, this.client.player);
    }

    public static void drawEntity(DrawContext context, int x1, int y1, int x2, int y2, int size, float f, float mouseX, float mouseY, LivingEntity entity) {
        float g = (float)(x1 + x2) / 2.0F;
        float h = (float)(y1 + y2) / 2.0F;
        context.enableScissor(x1, y1, x2, y2);
        float i = (float)Math.atan((double)((g - mouseX) / 40.0F));
        float j = (float)Math.atan((double)((h - mouseY) / 40.0F));
        Quaternionf quaternionf = (new Quaternionf()).rotateZ(3.1415927F);
        Quaternionf quaternionf2 = (new Quaternionf()).rotateX(j * 20.0F * 0.017453292F);
        quaternionf.mul(quaternionf2);
        float k = entity.bodyYaw;
        float l = entity.getYaw();
        float m = entity.getPitch();
        float n = entity.prevHeadYaw;
        float o = entity.headYaw;
        entity.bodyYaw = i * 20.0F;
        entity.setYaw(i * 40.0F);
        entity.setPitch(-j * 20.0F);
        entity.headYaw = entity.getYaw();
        entity.prevHeadYaw = entity.getYaw();
        Vector3f vector3f = new Vector3f(0.0F, entity.getHeight() / 2.0F + f, 0.0F);
        drawEntity(context, g, h, size, vector3f, quaternionf, quaternionf2, entity);
        entity.bodyYaw = k;
        entity.setYaw(l);
        entity.setPitch(m);
        entity.prevHeadYaw = n;
        entity.headYaw = o;
        context.disableScissor();
    }

    public static void drawEntity(DrawContext context, float x, float y, int size, Vector3f vector3f, Quaternionf quaternionf, @Nullable Quaternionf quaternionf2, LivingEntity entity) {
        context.getMatrices().push();
        context.getMatrices().translate((double)x, (double)y, 50.0);
        context.getMatrices().multiplyPositionMatrix((new Matrix4f()).scaling((float)size, (float)size, (float)(-size)));
        context.getMatrices().translate(vector3f.x, vector3f.y, vector3f.z);
        context.getMatrices().multiply(quaternionf);
        DiffuseLighting.method_34742();
        EntityRenderDispatcher entityRenderDispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
        if (quaternionf2 != null) {
            quaternionf2.conjugate();
            entityRenderDispatcher.setRotation(quaternionf2);
        }

        entityRenderDispatcher.setRenderShadows(false);
        RenderSystem.runAsFancy(() -> {
            entityRenderDispatcher.render(entity, 0.0, 0.0, 0.0, 0.0F, 1.0F, context.getMatrices(), context.getVertexConsumers(), 15728880);
        });
        context.draw();
        entityRenderDispatcher.setRenderShadows(true);
        context.getMatrices().pop();
        DiffuseLighting.enableGuiDepthLighting();
    }

    @Override
    public void close() {
        assert client != null;
        client.setScreen(parent);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
