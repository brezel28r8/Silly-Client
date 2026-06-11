package net.sillyclient.mixins;

import net.sillyclient.Client;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    private boolean guiKeyPressed = false;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        Client.INSTANCE.init();
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null) {
            Client.INSTANCE.getModuleManager().getModules().forEach(net.sillyclient.modules.Module::onUpdate);
            
            boolean isPressed = GLFW.glfwGetKey(mc.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;
            if (isPressed && !guiKeyPressed && mc.currentScreen == null) {
                mc.setScreen(new net.sillyclient.hud.ClickGUI());
            }
            guiKeyPressed = isPressed;
        }
    }
}
