# DesignSystem Component Catalog

A modular Jetpack Compose design system featuring reusable components with advanced animations, layout behaviors, and a built-in catalog for previewing variations.

## 🚀 Key Features

### 🛠 Components
- **Advanced Snackbars**:
  - Three variations: Basic (Centered), Icon-Right, and Complex (Action Column).
  - **Auto-Queue Logic**: Displays snackbars one by one with a "Short" duration (4s).
  - **Slide-in Animation**: Always slides in from the right edge as a solid unit.
  - **3-Way Swipe-to-Dismiss**: Supports swiping Left, Right, or Down to clear.
- **Adaptive Bottom Sheets**:
  - **Big Style**: Full-screen overlap with a top bar and close icon.
  - **Small Style**: Fixed height (approx. half-screen) with drag handle and close icon.
  - **Stacking Logic**: Recursive launching with depth-based offsets (2dp for Big, 5dp for Small).
- **Expandable Top Bar**:
  - Collapses smoothly from `120dp` (34sp title) to `64dp` (18sp title) on scroll.
  - Uses `lerp` interpolation for smooth color and size transitions.
- **Top Bars**: Simple, Back, and Action variations with configurable WindowInsets.
- **App Buttons**: Supporting Text and Round (Icon) styles with Material 3 variations.

### 📱 Catalog App
The project includes a `CatalogHomeScreen` that categorizes all components, making it easy to test interactions like snackbar queuing and bottom sheet stacking in isolation.

---

## 🛠 Tech Stack
- **Jetpack Compose**: UI Framework.
- **Material 3**: Base design system and components.
- **Navigation Compose**: Type-safe navigation between catalog screens.
- **Kotlin Coroutines**: Manages snackbar queue timing and animations.
- **Compose Animation API**: High-level `AnimatedContent` and low-level `Animatable` for swipe gestures.

---

## 📂 Project Structure
- `core/designsystem/components/`: Source code for all reusable UI components.
  - `snackbar/`: `DSSnackbar` and its queue management logic.
  - `bottomsheet/`: `AppBottomSheet` and stacking implementation.
  - `topBar/`: `DSTopBar` and `ExpandableTopBar`.
- `feature/presentation/screen/`: Catalog screens for previewing components.
- `navigation/`: Route definitions and `NavHost` setup.

---

## 🏁 Getting Started
1. Clone the repository.
2. Open in **Android Studio Ladybug (or newer)**.
3. Sync Gradle (Uses Version Catalogs in `libs.versions.toml`).
4. Run the `:app` module.

## 📝 Usage Example (Snackbar)
```kotlin
snackbarQueue.add(
    SnackbarData(
        id = nextId++,
        variation = SnackbarVariation.WithIcon(
            text = "Notification Received", 
            icon = Icons.Default.Notifications
        )
    )
)
```
