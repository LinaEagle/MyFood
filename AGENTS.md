# AGENTS.md

## Rules
- Никогда не удаляй .env
- Перед коммитом — npm run lint

## Restrictions
- do not build .apk and .exe without explicit command
- don't touch directory refs/

## Stack

- Kotlin Multiplatform (KMP) + Compose Multiplatform
- Domain: food photography

## Project structure

```
composeApp/              # shared KMP module (Android + iOS)
  build.gradle.kts       # KMP + Android app + Compose config
  src/
    commonMain/          # shared Compose UI and logic
    androidMain/         # Android entry (MainActivity, AndroidManifest.xml)
    iosMain/             # iOS entry (MainViewController)
```

## Commands

```sh
./gradlew :composeApp:assembleDebug              # build debug APK
./gradlew :composeApp:assembleRelease            # build release APK
./gradlew :composeApp:compileDebugKotlinAndroid  # fast Kotlin compile check only
```

APK output: `composeApp/build/outputs/apk/debug/composeApp-debug.apk`

## Gotchas

- Network is slow — Gradle wrapper download may timeout. Increase `networkTimeout` in `gradle/wrapper/gradle-wrapper.properties` if needed.
- iOS targets (`iosArm64`, `iosSimulatorArm64`, `iosX64`) are silently skipped on Linux (configured via `kotlin.native.ignoreDisabledTargets=true`).
- `sdk.dir` in `local.properties` must point to a valid Android SDK with `platforms;android-35` and `build-tools;35.0.0` installed.
- Only debug APK is configured (no signing for release yet).

## Versions (current)

- Kotlin: 2.0.21
- Compose Multiplatform: 1.7.0
- AGP: 8.5.2
- Gradle: 8.12
- Compile/Target SDK: 35
- Min SDK: 24
