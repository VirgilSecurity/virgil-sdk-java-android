# Changelog

All notable changes to this project will be documented in this file.

## 7.4.0 - 2026-02-12

### Added
- Added GitHub Actions CI workflow `.github/workflows/build-and-test.yml` for JVM and Android build/test coverage.
- Added GitHub Actions release workflow `.github/workflows/publish-release.yml` with tag/version validation and automated publishing.
- Added `scripts/encrypt-env.sh` and `scripts/decrypt-env.sh` for `env.json` management in local/CI environments.
- Added constructor-focused tests for `VirgilCardClient` URL handling in `sdk/src/test/java/com/virgilsecurity/sdk/client/VirgilCardClientConstructorTest.java`.

### Changed
- Migrated publishing pipeline to Sonatype Central Portal bundle upload flow:
  - publish all artifacts to local `build/central-bundle-repo`,
  - build and upload `build/central-bundle.zip`,
  - poll deployment status until `PUBLISHED`.
- Upgraded build toolchain and dependencies:
  - Android Gradle Plugin to `8.5.2`,
  - Gradle wrapper to `8.7`,
  - Kotlin to `1.9.24`,
  - `com.virgilsecurity.crypto` dependency to `0.17.2`.
- Migrated Android modules (`crypto-android`, `android-utils`, `sdk-android`) to modern AGP configuration:
  - `namespace`,
  - `compileSdk 34`,
  - `targetSdk 34`,
  - `singleVariant("release")` publishing.
- Updated `settings.gradle` to include Android modules only when Android SDK is configured.
- Updated `sdk` generated source wiring (`generateVirgilInfo`) to use a dedicated directory and explicit task dependencies for Gradle 8 task validation.
- Unified `test-common` published version with `SDK_VERSION` to avoid manual version drift.

### Fixed
- Fixed `sourcesJar`/`compileJava` task ordering issues under Gradle 8 by declaring explicit dependencies and isolating generated sources.
- Updated `VirgilCardClient` service URL normalization to always resolve to `/card/v5/` on the same host/protocol/port.

### Documentation
- Updated README build and runtime guidance (JDK/Android SDK requirements, local setup, test commands, encrypted env usage).
- Removed Travis CI in favor of GitHub Actions.

### Notes
- `common` artifact remains published under `com.virgilsecurity:common` due to explicit group override in `common/build.gradle`.
