package com.github.sarhatabaot.treasureplugins;

import org.bukkit.plugin.Plugin;

public class TextStyle {
	private static Plugin plugin;

	public TextStyle(final Plugin plugin) {
		TextStyle.plugin = plugin;
		new Title();
		new Hover();
	}

	public static class Title {
		private static String pluginTitle;
		private static String pluginApiVersion;

		public Title() {
			this.pluginTitle = plugin.getConfig().getString("text-style.title.plugin-title","&6");
			this.pluginApiVersion = plugin.getConfig().getString("text-style.title.plugin-version","&a");
		}

		public static String getPluginTitle() {
			return pluginTitle;
		}

		public static String getPluginVersion() {
			return pluginApiVersion;
		}
	}
	public static class Hover{
		private static String pluginTitle;
		private static String pluginVersion;
		private static String pluginDescription;
		private static String pluginLink;

		public Hover() {
			this.pluginTitle = plugin.getConfig().getString("text-style.hover.plugin-title","&6&b");
			this.pluginVersion = plugin.getConfig().getString("text-style.hover.plugin-version","&a");
			this.pluginDescription = plugin.getConfig().getString("text-style.hover.plugin-description", "&7");
			this.pluginLink = plugin.getConfig().getString("text-style.hover.plugin-link","&5&b");
		}

		public static String getPluginTitle() {
			return pluginTitle;
		}

		public static String getPluginVersion() {
			return pluginVersion;
		}

		public static String getPluginDescription() {
			return pluginDescription;
		}

		public static String getPluginLink() {
			return pluginLink;
		}
	}
}
