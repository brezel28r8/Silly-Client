package net.sillyclient.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.sillyclient.Client;
import net.sillyclient.modules.Module;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final File clientDir;
    private final File modulesFile;

    public FileManager() {
        this.clientDir = new File(MinecraftClient.getInstance().runDirectory, "SillyClient");
        this.modulesFile = new File(clientDir, "modules.json");
    }

    public void init() {
        if (!clientDir.exists()) clientDir.mkdirs();
        loadModules();
    }

    public void saveModules() {
        try {
            JsonObject jsonObject = new JsonObject();
            for (Module module : Client.INSTANCE.getModuleManager().getModules()) {
                JsonObject moduleData = new JsonObject();
                moduleData.addProperty("enabled", module.isEnabled());
                moduleData.addProperty("keybind", module.getKey());
                jsonObject.add(module.getName(), moduleData);
            }
            try (FileWriter writer = new FileWriter(modulesFile)) {
                GSON.toJson(jsonObject, writer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadModules() {
        if (!modulesFile.exists()) return;
        try (FileReader reader = new FileReader(modulesFile)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            for (Module module : Client.INSTANCE.getModuleManager().getModules()) {
                if (jsonObject.has(module.getName())) {
                    JsonObject moduleData = jsonObject.getAsJsonObject(module.getName());
                    if (moduleData.get("enabled").getAsBoolean() && !module.isEnabled()) {
                        module.toggle();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
