package io.github.errror404.durabilityplugin;

import io.github.errror404.durabilityplugin.Commands.MainCommand;
import io.github.errror404.durabilityplugin.Commands.MainEvent;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class DurabilityPlugin extends JavaPlugin {
    public MainCommand command;

    @Override
    public void onEnable() {
        MainCommand.border.reset();
        command = new MainCommand(this);
        PluginCommand startCommand = getCommand("d_start");
        PluginCommand stopCommand = getCommand("d_stop");
        startCommand.setExecutor(command);
        stopCommand.setExecutor(command);
        getServer().getPluginManager().registerEvents(new MainEvent(), this);
        getLogger().info("Plugin Enable");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Disable");
    }
}
