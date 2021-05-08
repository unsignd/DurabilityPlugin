package io.github.errror404.durabilityplugin.Commands;

import io.github.errror404.durabilityplugin.DurabilityPlugin;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Random;

public class MainCommand implements CommandExecutor {
    private final DurabilityPlugin plugin;

    public static Boolean isStart = false;
    public static ArrayList<Player> playerList = new ArrayList<Player>();
    public static ArrayList<Player> allPlayer = new ArrayList<Player>();
    public static WorldBorder border;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch(command.getName()){
            case "d_start":
                if(isStart){
                    sender.sendMessage(ChatColor.RED + "이미 게임이 시작되었습니다.");
                    break;
                }

                if(Bukkit.getOnlinePlayers().size() <= 1){
                    sender.sendMessage(ChatColor.RED + "2명 이상의 인원이 있어야 게임을 시작할 수 있습니다.");
                    break;
                }

                if(!sender.isOp()){
                    sender.sendMessage(ChatColor.RED + "op를 가진 유저만 게임을 시작할 수 있습니다.");
                    break;
                }

                Random randomPos = new Random();
                int randomX = randomPos.nextInt(10000) - 5000;
                int randomZ = randomPos.nextInt(10000) - 5000;

                border = Bukkit.getWorld("World").getWorldBorder();
                border.setCenter(randomX + 1, randomZ + 1);
                border.setWarningDistance(0);
                border.setSize(10000);
                border.setWarningTime(5);
                border.setDamageAmount(1.0);

                playerList.clear();

                for(World world : Bukkit.getWorlds()){
                    world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
                    world.setDifficulty(Difficulty.HARD);
                }

                isStart = true;
                for (Player p : Bukkit.getOnlinePlayers()) {
                    Block block = Bukkit.getWorld("World").getHighestBlockAt(randomPos.nextInt(5000) - 2500 + randomX, randomPos.nextInt(5000) - 2500 + randomZ);
                    p.teleport(block.getLocation().add(.5, 1, .5));

                    p.getInventory().clear();
                    p.setGameMode(GameMode.SURVIVAL);
                    playerList.add(p);
                    p.setExp(0);
                    p.setLevel(0);
                    p.setHealth(20);
                    p.setFoodLevel(20);

                    for(PotionEffect potion : p.getActivePotionEffects()){
                        p.removePotionEffect(potion.getType());
                    }

                    p.sendTitle(ChatColor.RED + "게임이 시작되었습니다!", "끝까지 살아남아 최후의 1인이 되세요!");
                }
                border.setSize(50, 10800);
                break;
            case "d_stop":
                if(!isStart){
                    sender.sendMessage(ChatColor.RED + "아직 게임이 시작되지 않았습니다.");
                    break;
                }

                if(!sender.isOp()){
                    sender.sendMessage(ChatColor.RED + "op를 가진 유저만 게임을 시작할 수 있습니다.");
                    break;
                }

                border.reset();
                isStart = false;
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendTitle(ChatColor.RED + "게임 도중 게임이 종료되었습니다", "");
                }
                break;
        }
        return false;
    }

    public MainCommand(DurabilityPlugin instance){
        plugin = instance;
    }
}
