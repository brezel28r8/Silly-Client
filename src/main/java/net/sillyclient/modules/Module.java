package net.sillyclient.modules;

import net.sillyclient.Client;
import net.minecraft.client.MinecraftClient;

public abstract class Module {
    protected final MinecraftClient mc = MinecraftClient.getInstance();
    private final String name;
    private final String description;
    private int key;
    private boolean enabled;

    public Module(String name, String description, int defaultKey) {
        this.name = name;
        this.description = description;
        this.key = defaultKey;
        this.enabled = false;
    }

    public void toggle() {
        this.enabled = !this.enabled;
        if (this.enabled) {
            onEnable();
        } else {
            onDisable();
        }
        if (Client.INSTANCE.getFileManager() != null) {
            Client.INSTANCE.getFileManager().saveModules();
        }
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onUpdate() {}

    public String getName() { return name; }
    public int getKey() { return key; }
    public boolean isEnabled() { return enabled; }
}
