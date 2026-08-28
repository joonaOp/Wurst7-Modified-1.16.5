# Wurst7 Modified v1.16.5

Custom modifications to Wurst Client for Minecraft 1.16.5 with enhanced features.

## Features Added

### 1. **Double Backspace Master Toggle**
- Press **Backspace twice rapidly** (within 500ms) to disable/enable all hacks at once
- When disabled, all currently active hacks are paused and saved
- When re-enabled, the previously active hacks are automatically restored
- Perfect for quick panic disabling when needed
- **File:** `src/main/java/net/wurstclient/keybinds/MasterToggleManager.java`

### 2. **ArrowDMG with Damage Level Selector**
- Choose between **LOW**, **MEDIUM**, and **HIGH** damage levels
- LOW (50 packets), MEDIUM (150 packets), HIGH (300 packets)
- Configurable through Wurst GUI
- Includes Trident yeet mode support
- **File:** `src/main/java/net/wurstclient/hacks/ArrowDmgHack.java`

### 3. **Block Selection Hack**
- Search and filter blocks by name
- Categorized filtering (Stone, Dirt, Wood, Ore variants)
- Adjustable result limit (10-500 blocks)
- Easily select blocks for other hacks
- **File:** `src/main/java/net/wurstclient/hacks/BlockSelectionHack.java`

### 4. **NoFall Hack**
- Prevents fall damage by sending position packets
- Supports elytra flying mode
- Automatically detects fall damage threshold
- **File:** `src/main/java/net/wurstclient/hacks/NoFallHack.java`

## System Requirements

- **Java 8 JDK** (required for compilation)
- **Gradle** (included in repository via gradlew)
- **Git** (for cloning)
- Minecraft 1.16.5 with Fabric Loader

## Build Instructions

### Windows:
```bash
git clone https://github.com/joonaOp/Wurst7-Modified-1.16.5.git
cd Wurst7-Modified-1.16.5
./gradlew.bat build
```

### Linux/macOS:
```bash
git clone https://github.com/joonaOp/Wurst7-Modified-1.16.5.git
cd Wurst7-Modified-1.16.5
chmod +x ./gradlew
./gradlew build
```

## JAR Location

After building, the compiled JAR will be at:
```
build/libs/Wurst-Client-v7.35.1-MC1.16.5.jar
```

## Installation with TLauncher

1. Build the project using the commands above
2. Copy the JAR file from `build/libs/` to your Minecraft mods folder:
   - Windows: `%APPDATA%\.minecraft\mods\`
   - Linux: `~/.minecraft/mods/`
   - macOS: `~/Library/Application Support/minecraft/mods/`
3. Launch TLauncher with Fabric loader
4. Start Minecraft 1.16.5

## Usage

### Master Toggle (Double Backspace)
1. Press Backspace twice quickly to disable all hacks
2. Press Backspace twice again to restore all previously enabled hacks
3. No need to manually enable/disable individual hacks

### ArrowDMG Settings
1. Enable the ArrowDMG hack
2. Open Wurst Settings (usually Right-Click on hack in menu)
3. Select Damage Level: Low, Medium, or High
4. Damage scales accordingly when shooting arrows

### Block Selection
1. Enable BlockSelection hack
2. Use the search box to filter blocks
3. Toggle category filters (Stone, Dirt, Wood, Ore)
4. Adjust max results display
5. Use filtered blocks in other hacks that need block selection

### NoFall
1. Enable NoFall hack
2. Fall from any height without taking damage
3. Works seamlessly with elytra flying

## Configuration Files

All settings are saved to:
```
.minecraft/wurst/
├── settings.json          # All hack settings
├── enabled-hacks.json     # Which hacks are enabled
├── keybinds.json          # Key bindings
└── windows.json           # GUI window positions
```

## Troubleshooting

### Build fails with "Java version error"
- Ensure you have Java 8 JDK installed, not just JRE
- Check `java -version` returns version 1.8.x

### Gradle build stuck
- Delete `build/` and `.gradle/` folders
- Run `./gradlew clean build`

### JAR not found after build
- Check for build errors in console output
- Ensure no antivirus is blocking build process
- Try running with `./gradlew build --stacktrace`

## Minecraft Version Compatibility

This version is specifically built for **Minecraft 1.16.5** with **Fabric Loader**.

For other versions, see the original Wurst7 repository:
https://github.com/Wurst-Imperium/Wurst7

## License

GNU General Public License v3 - See LICENSE file

## Credits

- Original Wurst Client: https://github.com/Wurst-Imperium/Wurst7
- Modifications by: joonaOp
- Fabric API: https://fabricmc.net/

## Support

For issues specific to these modifications, check the GitHub issues.
For general Wurst Client issues, refer to the original repository.

---

**Last Updated:** 2026-08-28
**Version:** v7.35.1-MC1.16.5
