package com.github.sarhatabaot.treasureplugins;

import net.kyori.text.TextComponent;
import net.kyori.text.adapter.bukkit.TextAdapter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PluginCommand implements Listener {
	private TreasurePlugins plugin;

	public PluginCommand(final TreasurePlugins plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onCommandPreProcess(PlayerCommandPreprocessEvent event){
		if(event.getMessage().equalsIgnoreCase("/plugins") && event.getPlayer().hasPermission("treasureplugin.command")){
			event.setCancelled(true);
			onCommand(event.getPlayer());
		}
	}

	private void onCommand(final Player player){
		TextAdapter.sendMessage(player,TextComponent.of(String.format("Plugins (%s):", plugin.getTextComponentCache().size())));
		if("line".equalsIgnoreCase(plugin.getConfig().getString("style", "line"))){
			TextComponent lineComponent = TextComponent.of("");
			for (TextComponent component : plugin.getTextComponentCache().values()){
				lineComponent.append(component).append(TextComponent.of(", "));
			}
			lineComponent.children().remove(lineComponent.children().size()-1);
			TextAdapter.sendMessage(player,lineComponent);
		} else {
			for (TextComponent component : plugin.getTextComponentCache().values())
				TextAdapter.sendMessage(player, component);
		}
	}
}
