package com.github.sarhatabaot.treasureplugins;


import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class TreasurePlugins extends JavaPlugin {
	private Map<String, TextComponent> textComponentCache;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		cacheTextComponents();
		getServer().getPluginManager().registerEvents(new PluginCommand(this),this);
		new Metrics(this,8712);
	}

	@Override
	public void onDisable() {
		textComponentCache = null;
	}

	private void cacheTextComponents(){
		textComponentCache = new HashMap<>();
		for(Plugin plugin: getServer().getPluginManager().getPlugins()){
			if(!getConfig().getStringList("hide-plugins").contains(plugin.getName()))
				textComponentCache.put(plugin.getName(),component(plugin));
		}
	}

	private TextComponent component(Plugin plugin){
		String title = (plugin.getConfig().getBoolean("show-version.title")) ? String.format("%s (%s)",plugin.getName(),plugin.getDescription().getVersion()) : String.format("%s",plugin.getName());
		return TextComponent.builder().content(title)
				.hoverEvent(HoverEvent.showText(generatePluginHoverComponent(plugin)))
				.clickEvent(ClickEvent.of(ClickEvent.Action.OPEN_URL,getSafeString(plugin.getDescription().getWebsite()))).build();
	}

	private TextComponent generatePluginHoverComponent(final Plugin plugin){
		String title = (plugin.getConfig().getBoolean("show-version.hover")) ? String.format("%s (%s)",plugin.getName(),plugin.getDescription().getVersion()) : String.format("%s",plugin.getName());
		return TextComponent.builder(title)
				.append(TextComponent.newline())
				.append(TextComponent.of(getSafeString(plugin.getDescription().getAPIVersion())))
				.append(TextComponent.newline())
				.append(TextComponent.of(getSafeString(plugin.getDescription().getDescription())))
				.append(TextComponent.newline())
				.append(TextComponent.of("Click to see more information!")).build();
	}

	private String getSafeString(final String string){
		return string == null ? "" : string;
	}

	public Map<String, TextComponent> getTextComponentCache() {
		return textComponentCache;
	}
}
