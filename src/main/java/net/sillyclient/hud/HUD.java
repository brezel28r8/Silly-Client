package net.sillyclient.hud;

import net.sillyclient.Client;
import net.sillyclient.modules.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import java.awt.Color;

public class HUD {
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public void renderHUD(DrawContext context) {
        if (mc.options.hudHidden || mc.player == null) return;

        context.drawText(mc.textRenderer, "Silly Client v1.0", 6, 6, Color.MAGENTA.getRGB(), true);

        int yOffset = 18;
        for (Module mod : Client.INSTANCE.getModuleManager().getModules()) {
            if (mod.isEnabled()) {
                context.drawText(mc.textRenderer, "[+] " + mod.getName(), 6, yOffset, Color.WHITE.getRGB(), true);
                yOffset += 11;
            }
        }
    }
}
