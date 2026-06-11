package net.sillyclient.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HideMeController {

    public static void reconnectVPN(String serverLocation) {
        new Thread(() -> {
            String os = System.getProperty("os.name").toLowerCase();
            try {
                if (os.contains("win")) {
                    Runtime.getRuntime().exec("rasdial \"hide.me VPN\" /disconnect").waitFor();
                    Thread.sleep(1500);
                    Process p = Runtime.getRuntime().exec("rasdial \"hide.me VPN\"");
                    logProcess(p);
                } else if (os.contains("nix") || os.contains("nux")) {
                    Runtime.getRuntime().exec("sudo systemctl stop hide.me").waitFor();
                    Thread.sleep(1000);
                    Process p = Runtime.getRuntime().exec("sudo systemctl start hide.me@" + serverLocation);
                    logProcess(p);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void logProcess(Process process) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("[hide.me CLI] " + line);
        }
        process.waitFor();
    }
}
