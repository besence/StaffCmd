package com.besence.testcmd;

import com.besence.testcmd.commands.Fly;
import com.besence.testcmd.commands.Staff;
import com.besence.testcmd.commands.deathmochiko;
import org.bukkit.plugin.java.JavaPlugin;

public final class TestCmd extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("fly").setExecutor(new Fly());
        getCommand("staff").setExecutor(new Staff());
        getCommand("mochi").setExecutor(new deathmochiko());
        super.onEnable();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        super.onDisable();
    }
}
