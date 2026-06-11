package net.sillyclient.modules;

import net.sillyclient.modules.combat.CombatManager;
import net.sillyclient.modules.movement.Speed;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public void initializeModules() {
        modules.add(new Speed("Speed", "BunnyHop movement system", GLFW.GLFW_KEY_V));
        modules.add(new CombatManager("CombatManager", "AutoWeapon & Mace Smash Exploit", GLFW.GLFW_KEY_R));
    }

    public List<Module> getModules() { return modules; }
}
