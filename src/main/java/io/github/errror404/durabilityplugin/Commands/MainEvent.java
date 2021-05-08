package io.github.errror404.durabilityplugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Random;

public class MainEvent implements Listener {

    @EventHandler
    void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        if(MainCommand.isStart){
            Player player = e.getPlayer();

            if(player.getGameMode() == GameMode.SPECTATOR){
                e.setCancelled(true);
                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.getGameMode() == GameMode.SPECTATOR)
                        p.sendMessage(ChatColor.GRAY + "<" + player.getName() + "> " + e.getMessage());
                }
            }
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent e){
        if(MainCommand.isStart){
            Random random = new Random();
            Material m = e.getCurrentItem().getType();

            e.getCurrentItem().setDurability((short)(random.nextInt(m.getMaxDurability())));
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        if(MainCommand.isStart){
            for(int i = 0; i < MainCommand.playerList.size(); i++){
                if(MainCommand.playerList.get(i).getName() == e.getEntity().getName()){
                    MainCommand.playerList.remove(i);
                }
            }
            Bukkit.broadcastMessage(ChatColor.GOLD + "#" + (MainCommand.playerList.size() + 1) + " | " + e.getEntity().getName());
            e.getEntity().setGameMode(GameMode.SPECTATOR);
            e.setDeathMessage("");
            e.getEntity().sendTitle(ChatColor.RED + "사망하셨습니다", "게임모드가 업데이트되었습니다");

            if(MainCommand.playerList.size() < 1){
                MainCommand.isStart = false;
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendTitle(ChatColor.RED + "게임이 종료되었습니다", "");
                    MainCommand.border.reset();
                }
            }

            if(MainCommand.playerList.size() == 1){
                MainCommand.isStart = false;
                Bukkit.broadcastMessage(ChatColor.GOLD + "#1 | " + MainCommand.playerList.get(0).getName());
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendTitle(ChatColor.RED + "게임이 종료되었습니다", ChatColor.GOLD + "최후의 생존자 : " + MainCommand.playerList.get(0).getName());
                    MainCommand.border.reset();
                }
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(MainCommand.isStart){
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
            e.getPlayer().sendTitle(ChatColor.WHITE + "이미 게임이 시작되었습니다", "게임모드가 업데이트되었습니다");
        }
    }
}
