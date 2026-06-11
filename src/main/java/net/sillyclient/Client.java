package net.sillyclient;

import net.sillyclient.modules.ModuleManager;
import net.sillyclient.hud.HUD;
import net.sillyclient.data.FileManager;
import net.sillyclient.altmanager.AltManager;
import org.lwjgl.glfw.GLFW;

public class Client {
    public static final Client INSTANCE = new Client();
    
    private final ModuleManager moduleManager = new ModuleManager();
    private final HUD hud = new HUD();
    private final FileManager fileManager = new FileManager();

    public void init() {
        moduleManager.initializeModules();
        fileManager.init();
        AltManager.loadAltsFromFile();
        
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }

    public void shutdown() {
        System.out.println("[Silly Client] Saving configuration files...");
        fileManager.saveModules();
    }

    public void onKeyPressed(int key) {
        if (key == GLFW.GLFW_KEY_UNKNOWN) return;
        moduleManager.getModules().forEach(module -> {
            if (module.getKey() == key) {
                module.toggle();
            }
        });
    }

    public ModuleManager getModuleManager() { return moduleManager; }
    public HUD getHud() { return hud; }
    public FileManager getFileManager() { return fileManager; }
}
