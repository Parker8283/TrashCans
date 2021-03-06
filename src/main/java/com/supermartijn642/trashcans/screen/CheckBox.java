package com.supermartijn642.trashcans.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.AbstractButton;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

/**
 * Created 7/7/2020 by SuperMartijn642
 */
public class CheckBox extends AbstractButton {

    private final ResourceLocation BUTTONS = new ResourceLocation("trashcans", "textures/checkmarkbox.png");

    private final Runnable onPress;
    public boolean checked;

    public CheckBox(int x, int y, Runnable onPress){
        super(x, y, 17, 17, "");
        this.onPress = onPress;
    }

    public void update(boolean checked){
        this.checked = checked;
    }

    @Override
    public void onPress(){
        this.onPress.run();
    }

    @Override
    public void renderButton(int mouseX, int mouseY, float partialTicks){
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bindTexture(BUTTONS);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        drawTexture(this.x, this.y - 3, this.checked ? 0 : 20, (this.active ? this.isHovered ? 1 : 0 : 2) * 20);
        this.renderBg(minecraft, mouseX, mouseY);
    }

    private static void drawTexture(int x, int y, int textureX, int textureY){
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(x, y + 20, 0).tex(textureX / 40f, (textureY + 20) / 60f).endVertex();
        bufferbuilder.pos(x + 20, y + 20, 0).tex((textureX + 20) / 40f, (textureY + 20) / 60f).endVertex();
        bufferbuilder.pos(x + 20, y, 0).tex((textureX + 20) / 40f, textureY / 60f).endVertex();
        bufferbuilder.pos(x, y, 0).tex(textureX / 40f, textureY / 60f).endVertex();
        bufferbuilder.finishDrawing();
        RenderSystem.enableAlphaTest();
        WorldVertexBufferUploader.draw(bufferbuilder);
    }
}
