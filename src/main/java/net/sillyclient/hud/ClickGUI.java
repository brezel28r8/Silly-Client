package net.sillyclient.hud;

import net.sillyclient.altmanager.AltManager;
import net.sillyclient.utility.HideMeController;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import java.awt.Color;

public class ClickGUI extends Screen {
    public ClickGUI() { super(Text.of("Silly Client ClickGUI")); }

    @Override
    protected void init() {
        this.addDrawableChild(ButtonWidget.builder(Text.of("Login cracked Alt"), b -> {
            AltManager.loginCracked("SillyPlayer_" + (int)(Math.random() * 1000));
        }).dimensions(this.width / 2 - 100, 80, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.of("Reconnect hide.me VPN"), b -> {
            HideMeController.reconnectVPN("usa");
        }).dimensions(this.width / 2 - 100, 110, 200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(15, 15, this.width - 15, this.height - 15, new Color(0, 0, 0, 160).getRGB());
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPauseGame() { return false; }
}
