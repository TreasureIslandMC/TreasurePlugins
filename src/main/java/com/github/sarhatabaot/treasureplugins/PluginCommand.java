package com.github.sarhatabaot.treasureplugins;


import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.AudienceProvider;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PluginCommand implements Listener {
	private TreasurePlugins plugin;
	private BukkitAudiences bukkitAudiences;

	public PluginCommand(final TreasurePlugins plugin) {
		this.plugin = plugin;
		this.bukkitAudiences = BukkitAudiences.create(plugin);
	}

	@EventHandler
	public void onCommandPreProcess(PlayerCommandPreprocessEvent event){
		if(event.getMessage().equalsIgnoreCase("/plugins") && event.getPlayer().hasPermission("treasureplugin.command")){
			event.setCancelled(true);
			onCommand(event.getPlayer());
		}
	}

	private void onCommand(final Player player){
		Audience audience = bukkitAudiences.player(player);
		audience.sendMessage(TextComponent.of(String.format("Plugins (%s):", plugin.getTextComponentCache().size())));
		if("line".equalsIgnoreCase(plugin.getConfig().getString("style", "line"))){
			TextComponent.Builder builderComponent = TextComponent.builder();
			for (TextComponent component : plugin.getTextComponentCache().values()){
				builderComponent.append(component).append(TextComponent.of(", "));
			}
			TextComponent lineComponent = builderComponent.build();
			audience.sendMessage(lineComponent);
		} else {
			for (TextComponent component : plugin.getTextComponentCache().values())
				audience.sendMessage(component);
		}
	}
}
