package net.sillyclient.altmanager;

import net.sillyclient.mixins.IMinecraftClientAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Session;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AltManager {
    private static final List<String> cachedAlts = new ArrayList<>();
    private static final File altsFile = new File(new File(MinecraftClient.getInstance().runDirectory, "SillyClient"), "alts.txt");

    public static void loginCracked(String username) {
        MinecraftClient mc = MinecraftClient.getInstance();
        UUID offlineUuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + username).getBytes());
        
        Session offlineSession = new Session(
            username, offlineUuid, "", Optional.empty(), Optional.empty(), Session.AccountType.MOJANG
        );
        
        ((IMinecraftClientAccessor) mc).setSession(offlineSession);
        System.out.println("Switched profile to: " + username);
    }

    public static void loadAltsFromFile() {
        if (!altsFile.exists()) return;
        cachedAlts.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(altsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) cachedAlts.add(line.trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
