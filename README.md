# Test Client (Fabric 1.21.1)

Minimal client-side utility mod for **testing** — e.g. checking what a client can do on your own server.

> ⚠️ Use only on your own server or where you have explicit permission. Using this on public servers will most likely get you banned.

## Menu

Press **Right Shift** to open the module menu, click a module to toggle it. Fly, Speed, Fullbright and Sprint also have hotkeys (G, H, J, K); all keys can be changed in *Options → Controls → Test Client*.

## Modules

| Module | What it does |
|---|---|
| Fly | Client-side flight |
| Speed | Boosts ground movement speed |
| Fullbright | Night vision effect |
| Sprint | Always sprint while moving forward |
| Step | Step up full blocks |
| Glide | Slow falling |
| HighJump | Higher jumps |
| AutoJump | Jump automatically while moving |
| Spider | Climb walls |
| NoFall | Cancels fall damage |
| KillAura | Attacks the nearest entity in range |
| Scaffold | Places blocks under you (hold a block) |
| ESP | Glow outline on players |
| BlockESP | Highlights ores/chests/spawners in render distance |
| SoundESP | Marks where sounds come from |
| Tracers | Lines to other players |
| AntiAFK | Jumps/swings periodically |

## Build

Easiest: push to GitHub → Actions → **build** → download the `testclient-jar` artifact.

Locally (JDK 21 + Gradle 8.10+):

```
gradle build
```

Put the jar from `build/libs/` into `mods/` together with Fabric Loader (0.16+) and Fabric API for 1.21.1.

## Adding a module

Create a class extending `Module` in `modules/`, implement `onTick`, and register it in `ModuleManager`. To draw things in the world, also implement `EspSource`.
