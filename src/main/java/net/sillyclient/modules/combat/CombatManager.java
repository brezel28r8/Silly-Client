package net.sillyclient.modules.combat;

import net.sillyclient.modules.Module;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class CombatManager extends Module {
    public CombatManager(String name, String desc, int key) { super(name, desc, key); }

    public void onAttackEntity(Entity target) {
        if (!this.isEnabled() || mc.player == null) return;

        int targetedSlot = -1;

        if (mc.player.fallDistance > 0.4F && !mc.player.isOnGround()) {
            targetedSlot = getSlotWithItem(Items.MACE);
        }

        if (targetedSlot == -1) {
            targetedSlot = getStrongestWeaponSlot();
        }

        if (targetedSlot != -1) {
            mc.player.getInventory().selectedSlot = targetedSlot;
        }
    }

    private int getSlotWithItem(net.minecraft.item.Item item) {
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).isOf(item)) return i;
        }
        return -1;
    }

    private int getStrongestWeaponSlot() {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (stack.isOf(Items.NETHERITE_SWORD) || stack.isOf(Items.DIAMOND_SWORD)) return i;
        }
        return -1;
    }
}
