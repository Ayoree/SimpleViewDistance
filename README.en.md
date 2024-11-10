# Simpe View Distance

## Translation
- [Rus](README.md)

The plugin allows players to change their render distance on the server.
<br/><br/>

## **Features**
- **No unnecessary**: The plugin is designed to be understandable for any player. It does not include unnecessary functions.
- **Global settings**: Ability to set a default render distance for all incoming players on the server.
- **Individual settings for each player**: Each player can change their own render distance.
- **Permissions for specific groups**: The plugin allows easy assignment of permissions to change render distance for each individual group (e.g., for groups in LuckPerms).
- **Saving individual settings**: Automatically saves and loads the player's custom render distance upon leaving and joining the server.
- **Easy to configure**: The configuration does not contain excessive information and is easy to set up.

<br/>

## **Commands**
### Base command:: `/viewdistance`
- **Usage:** `/viewdistance <number>`
- **Aliases:** `vd`
- Sets the render distance to a new value
### Subcommands
#### - `reload`
- **Usage:** `/vd reload`
- Reloads the plugin configuration.

<br/>

## **Permissions**
- Use the command /viewdistance: `viewdistance.set`
- Reload the plugin configuration: `viewdistance.reload`

<br/>

- By default, only operators have `viewdistance.reload` permission
- By default, all players have `viewdistance.set` permission

## config.yml
```yaml
# Minimum possible render distance (Cannot be less than 2)
min-distance: 2

# Render distances by groups, granted by the permission 'group.<name>' (automatically granted to all groups in LuckPerms)
# Players will not be able to set a value greater than that specified here
# If a player belongs to multiple groups, the highest value will be selected
distances:
  default: 12 # Default value for all players (Cannot be greater than 32)
  moder: 16
  admin: 32

#----------- Messages -----------
# For color, use the symbol '§'

# Prefix before messages; if you want to remove it, just make it empty - ''
prefix: '§f[§lV§r§7iew§f§lD§r§7instance§f] §r'

# Message shown when changing render distance. '{chunks}' is a placeholder for the render distance value
view-distance-change-msg: '§aYou have changed your render distance to {chunks} chunks'

# Message shown when there is a syntax error in the command
incorrect-args-msg: '§cSyntax error§e - use §l/vd <number>'

# Message shown when reloading the config (/vd reload)
reload-config-msg: '§aConfig successfully reloaded'

# Message shown when lacking permission to execute the command
no-permission-msg: '§cYou do not have permission to use this command'
```
