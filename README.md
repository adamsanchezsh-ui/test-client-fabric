# Test Client (Fabric 1.21.1)

Minimal client-side utility mod for **testing** — e.g. checking how your own server's anti-cheat reacts to common movement modifications.

> ⚠️ Use only on your own server or where you have explicit permission. Using this on public servers will most likely get you banned.

## Modules

| Module | Default key | What it does |
|---|---|---|
| Fly | G | Enables client-side flight |
| Speed | H | Boosts ground movement speed |
| Fullbright | J | Night vision effect (client-side) |
| Sprint | K | Always sprint while moving forward |

Keys can be changed in *Options → Controls → Test Client*.

## Build

Easiest: push to GitHub → Actions → **build** → download the `testclient-jar` artifact.

Locally (JDK 21 + Gradle 8.10+):

```
gradle build
```

The jar is in `build/libs/`. Put it into `mods/` together with Fabric Loader (0.16+) and Fabric API for 1.21.1.

## Adding a module

Create a class extending `Module` in `modules/`, implement `onTick`, and register it in `ModuleManager`.
