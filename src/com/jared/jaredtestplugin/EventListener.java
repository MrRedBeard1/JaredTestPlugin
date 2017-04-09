package com.jared.jaredtestplugin;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@SuppressWarnings("unused")
public class EventListener implements Listener {

    @EventHandler
    public void onPlayerLevelChangeEvent(PlayerLevelChangeEvent event) {
        if (event.getNewLevel() < event.getOldLevel()) {
            return;
        }

        Player player = event.getPlayer();
        levelUpBonus(player);

        Configuration config =  SpigotPlugin.getInstance().getConfig();

        int level = player.getLevel();

        String type = config.getString("buffs." + level + ".type");

        if (type == null) {
            return;
        }

        int duration = config.getInt("buffs." + level + ".duration");
        int amp = config.getInt("buffs." + level + ".amp");

        switch (type) {
            case "jump":
                givePlayerJump(player, duration, amp);
                player.sendMessage(ChatColor.GREEN + "Jump Jump Jump!");
                break;

            case "power":
                givePlayerPower(player,duration, amp);
                player.sendMessage(ChatColor.RED + "AAAAAAAAAHHHHHHHHHGGGGGGGGG");
                player.sendMessage(ChatColor.RED + "You've gone super saiyan!?");
                break;

            case "mining":
                givePlayerMining(player,duration, amp);
                player.sendMessage("Fast Mining");
                break;

            case "war":
                startAWar(player, duration, amp);
                player.sendMessage("WAAAR!");
                break;

        }
    }


    private void levelUpBonus(Player player) {
        player.setFoodLevel(20);
        player.setHealth(20);
    }

    private void givePlayerJump(Player player, int duration, int amp) {
        PotionEffect effect = new PotionEffect(PotionEffectType.JUMP, duration, amp, true, true, Color.GREEN);
        player.addPotionEffect(effect);
    }

    private void givePlayerPower(Player player, int duration, int amp) {
        PotionEffect effect = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, duration, amp, false, true, Color.RED);
        player.addPotionEffect(effect);
    }
    private void givePlayerMining(Player player, int duration, int amp) {
        PotionEffect effect = new PotionEffect(PotionEffectType.FAST_DIGGING, duration, amp, false, true, Color.AQUA);
        player.addPotionEffect(effect);
    }
    private void startAWar(Player player, int duration, int amp){
        PotionEffect effect = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, duration, amp, false, true, Color.YELLOW);
        PotionEffect effect2 = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, duration, amp, false, true,Color.BLACK);
        Location location = player.getLocation();
        player.addPotionEffect(effect);
        player.addPotionEffect(effect2);
        for(int i = 0 ; i <= 20 ; i++)
            player.getWorld().spawnEntity(location, EntityType.WITHER_SKELETON);
        for(int i = 0 ; i <= 10 ; i++)
            player.getWorld().spawnEntity(location, EntityType.WITCH);
        for(int i = 0 ; i <= 10 ; i++)
            player.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        for(int i = 0 ; i <= 10 ; i++)
            player.getWorld().spawnEntity(location, EntityType.SKELETON);
    }

}

