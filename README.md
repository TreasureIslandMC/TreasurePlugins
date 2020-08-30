# TreasurePlugins [![Maintainability](https://api.codeclimate.com/v1/badges/26b1a23d46543e44f59f/maintainability)](https://codeclimate.com/github/TreasureIslandMC/TreasurePlugins/maintainability)
 
Display additional plugin information ingame.

Currently you can only display a list of plugins.

By hovering over one of the items you'll display additional information about that plugin.
Also including a link to it's provided website.


# Usage & Permissions

`/plugins` - `treasureplugin.command`

# Styles
TreasurePlugins currently supports 2 styles: **Line** and **List**.

## Line
Show the plugins in a line.
![Line](https://puu.sh/Gnn5J/64b09c4506.gif)
## List
Show the plugin in a list.
![List](https://puu.sh/Gnn7A/b60bc3bc72.gif)

## Show Version
Show the version in the list & while hovering over a plugin.

## Default Config
```yaml
# The exact plugin name, you can see this by running /plugins in your console.
hide-plugins:
  -
show-version:
  title: true
  hover: true
# LIST - Show plugins on one line
# LINE - Show plugins on a list
# Defaults to LINE
style: LINE
```
