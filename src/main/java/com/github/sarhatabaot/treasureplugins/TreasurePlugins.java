package com.github.sarhatabaot.treasureplugins;

import net.kyori.text.TextComponent;
import net.kyori.text.event.ClickEvent;
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

	private TextComponent component (Plugin plugin){
		return TextComponent.builder().content(String.format("%s (%s)",plugin.getName(),plugin.getDescription().getVersion()))
				.hoverEvent(net.kyori.text.event.HoverEvent.showText(generatePluginHoverComponent(plugin)))
				.clickEvent(ClickEvent.of(ClickEvent.Action.OPEN_URL,getSafeString(plugin.getDescription().getWebsite()))).build();
	}

	private TextComponent generatePluginHoverComponent(final Plugin plugin){
		final String title = String.format("%s (%s)",plugin.getName(),plugin.getDescription().getVersion());
		return TextComponent.builder(title)
				.append("\n")
				.append(TextComponent.of(getSafeString(plugin.getDescription().getAPIVersion())))
				.append("\n")
				.append(TextComponent.of(getSafeString(plugin.getDescription().getDescription())))
				.append("\n")
				.append(TextComponent.of("Click to see more information!")).build();
	}

	private String getSafeString(final String string){
		return string == null ? "" : string;
	}

	public Map<String, TextComponent> getTextComponentCache() {
		return textComponentCache;
	}
}
