# DesignSystem Component Catalog

A modular Jetpack Compose design system featuring reusable components with advanced animations, semantic layout behaviors, and a built-in catalog for previewing variations.

## 🚀 Key Features

### 🛠 Reusable Components
- **Advanced Snackbars**:
  - Three variations: **Basic** (Centered), **WithIcon**, and **Complex** (Action Column).
  - **Auto-Queue Logic**: Displays snackbars sequentially with a standardized 4s duration.
  - **Slide-in Animation**: Always slides in from the right edge as a solid unit using `AnimatedContent`.
  - **3-Way Swipe-to-Dismiss**: Supports swiping Left, Right, or Down with directional off-screen animations.
- **Adaptive Bottom Sheets**:
  - **Big Style**: Full-screen overlap with automatic Status Bar padding (WindowInsets) and a close icon.
  - **Small Style**: Fixed height (approx. half-screen) with a drag handle and close icon.
  - **Stacking Logic**: Recursive launching with depth-based offsets (**8dp** for Big, **5dp** for Small height reduction).
  - **Animated Dismissal**: Explicitly handles `hide()` transitions before clearing from state.
- **Expandable Top Bar**:
  - Collapses smoothly from `120dp` (34sp title) to `64dp` (18sp title) based on scroll progress.
  - Uses `lerp` (Linear Interpolation) to synchronize height, font size, and background color transitions.
- **Standard Top Bars**: Simple, Back, and Action variations with configurable `WindowInsets` for catalog vs. app usage.
- **App Buttons**: Supporting Text and Round (Icon) styles with mandatory accessibility labels and "Back" arrow defaults.

### 🎭 Centralized Animations (`DSAnimations.kt`)
Standardized animation specs and transitions used across the system:
- `dsExpand()` / `dsCollapse()`: Descriptive spring specs for sizing changes.
- `dsEnterFromRight()` / `dsExitToRight()`: Unified horizontal slide + fade transitions.

### ♿ Accessibility & Localization
- **Zero Hardcoding**: All strings and dimensions are moved to `strings.xml` and `dimens.xml` for easy scaling and translation.
- **Mandatory Content Descriptions**: Components with images strictly enforce `contentDescription` arguments.
- **Semantic States**: Dynamic components use `stateDescription` (e.g., "Expanded", "Stack depth 2") to inform assistive technologies.
- **Custom Semantic Actions**: Snackbars include accessibility actions for dismissal without requiring physical gestures.

---

## 🛠 Tech Stack
- **Jetpack Compose**: Modern declarative UI framework.
- **Material 3**: Foundation for the theme-aware component library.
- **Navigation Compose**: Type-safe navigation for the catalog app.
- **Kotlin Coroutines**: Powering queue delays and coordinated animations.
- **Version Catalog**: Centralized dependency management via `libs.versions.toml`.

---

## 📂 Project Structure
The project is organized into three distinct layers to ensure scalability and maintainability:

- **`foundation/`**: The atomic building blocks of the design system.
  - `theme/`: Design tokens for Colors, Typography (`AppTypography`), and the global `DesignSystemTheme`.
  - `animation/`: Centralized `DSAnimations` defining semantic motion specs.
- **`components/`**: Purely reusable UI components that consume foundation tokens.
  - `button/`: [DSButton] with primary/secondary and text/round styles.
  - `snackbar/`: [DSSnackbar] with auto-queueing and multi-directional swipe logic.
  - `bottomsheet/`: [DSBottomSheet] with recursive stacking and animated dismissal.
  - `topBar/`: [DSTopBar] and [DSExpandableTopBar] scroll-reactive headers.
- **`catalog/`**: A "showroom" app layer used for development and documentation.
  - `screens/`: Isolated preview screens for testing every component variation.
  - `navigation/`: Internal navigation logic and routes for the catalog browser.

---

## 📖 Development Best Practices
- **Documentation**: All core components include KDoc headers describing parameters and behavior.
- **Theme Awareness**: Components use `MaterialTheme.colorScheme` instead of hardcoded colors, supporting Light and Dark modes natively.
- **Encapsulation**: Catalog code is strictly separated from library code to prevent leaking demo logic into production.

## 🏁 Getting Started
1. Clone the repository.
2. Open in **Android Studio Ladybug (or newer)**.
3. Sync Gradle (Uses Version Catalogs).
4. Run the `:app` module to browse the catalog.

## 📝 Usage Example (Expandable Top Bar)
```kotlin
// Reusable component usage
DSExpandableTopBar(
    title = "My Page Title",
    collapseFraction = scrollState.fraction // Derived from scroll offset
)
```
