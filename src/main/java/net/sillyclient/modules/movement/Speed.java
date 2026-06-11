package net.sillyclient.modules.movement;

import net.sillyclient.modules.Module;
import net.minecraft.util.math.Vec3d;

public class Speed extends Module {
    public Speed(String name, String desc, int key) { super(name, desc, key); }

    @Override
    public void onUpdate() {
        if (!this.isEnabled() || mc.player == null) return;

        if (mc.player.input.movementForward != 0 || mc.player.input.movementSideways != 0) {
            if (mc.player.isOnGround()) {
                mc.player.jump(); 
                Vec3d vel = mc.player.getVelocity();
                mc.player.setVelocity(vel.x * 1.26, vel.y, vel.z * 1.26); 
            } else {
                Vec3d vel = mc.player.getVelocity();
                mc.player.setVelocity(vel.x * 1.015, vel.y, vel.z * 1.015); 
            }
        }
    }
}
