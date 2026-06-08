# DesignSystem Component Catalog

A modular Jetpack Compose design system featuring reusable components with advanced animations, semantic layout behaviors, and a built-in catalog for previewing variations.

## 🚀 Key Features

### 🛠 Reusable Components
- **Advanced Snackbars**:
  - Three variations: **Basic** (Centered), **WithIcon**, and **Complex** (Action Column).
  - **Auto-Queue Logic**: Displays snackbars sequentially with a standardized 4s duration.
  - **Solid Slide-in**: Animates from the right edge as a solid unit using `AnimatedContent`.
  - **3-Way Swipe-to-Dismiss**: Supports swiping Left, Right, or Down with directional off-screen animations.
- **Adaptive Bottom Sheets**:
  - **Highly Flexible**: Now supports any custom content lambda and optional titles.
  - **Standard Transitions**: Includes built-in entry/exit animations for all modal content.
  - **Big Style**: Full-screen overlap with automatic Status Bar padding (WindowInsets) and a close icon.
  - **Small Style**: Fixed height (approx. half-screen) with a drag handle and close icon.
  - **Stacking Logic**: Recursive launching with depth-based offsets (**8dp** for Big, **5dp** for Small height reduction).
  - **Animated Dismissal**: Explicitly handles `hide()` transitions before clearing from state.
- **Smart Login Pattern ([DSLogin])**:
  - **Multi-Entry**: Reusable UI pattern that can be launched as a full-screen **Screen** or within a **Modal** (Bottom Sheet).
  - **Smart Validation**: Immediate feedback for illegal characters (`&#`) and delayed (focus-lost) validation for email formats (`@`) and password complexity.
  - **Visual Stability**: Error message areas have reserved height to prevent UI "jumps" during validation.
- **Standardized Text ([DSText])**: Maps design tokens to typography variations (`Headline`, `Body`, `Error`, `Label`) for unified styling.
- **Global Banners ([DSBanner])**: Reusable alerts for **Error**, **Info**, and **Success** states, used for server-side feedback.
- **Expandable Top Bar**: Collapses smoothly based on scroll progress using `lerp` interpolation for height, font size, and background colors.
- **Standardized Screens ([DSScreen])**:
  - A unified page container that manages `Scaffold` structure, `TopBar` integration, and automatic entry animations for a consistent "app-like" transition between catalog items.
- **Form Controls**: [DSTextField] with built-in password masking, visibility toggles, and externalized error icons for better touch targets.

### 🎭 Centralized Animations (`DSAnimations.kt`)
Standardized semantic animation specs and transitions:
- `dsExpand()` / `dsCollapse()`: Unified spring specs for all expansion/contraction behaviors.
- `dsEnterFromRight()` / `dsExitToRight()`: Global standard for horizontal component entry.

### ♿ Accessibility & Localization
- **Zero Hardcoding**: 100% of strings and dimensions are externalized to `strings.xml` and `dimens.xml`.
- **Mandatory Content Descriptions**: Strict enforcement of accessibility labels for all image-based components.
- **Semantic States**: components announce "Expanded", "Collapsed", or "Stack depth X" to screen readers.

---

## 🎨 Theme & Color System
The system uses a hybrid approach to provide a modern look while protecting brand identity:
- **Dynamic Color Support**: Core components like buttons follow the Android "Material You" palette.
- **Brand Protection**: Uses a custom `LocalDesignSystemColors` provider to ensure critical elements (like snackbar actions) remain in vibrant **DeepSkyBlue** even when system-wide dynamic coloring is active.
- **Contrast Awareness**: Automatically switches between light/dark semantic roles (e.g., `inverseSurface` for snackbars).

---

## 📂 Project Structure
Organized into three strict layers for maximum scalability:

- **`foundation/`**: Atomic building blocks (Theme, Colors, Type, Motion Tokens).
- **`components/`**: Purely reusable UI library components.
  - `button/`: [DSButton] with primary/secondary and text/round styles.
  - `snackbar/`: [DSSnackbar] with auto-queueing and multi-directional swipe logic.
  - `bottomsheet/`: [DSBottomSheet] with recursive stacking, animated dismissal, and standard content transitions.
  - `topBar/`: [DSTopBar] and [DSExpandableTopBar] scroll-reactive headers.
  - `screen/`: [DSScreen] a standard page layout with built-in Scaffold and entry animations.
  - `textfield/`: [DSTextField] with styles (Basic, Outlined) and variations (Default, Password).
  - `text/`: [DSText] component mapping design tokens to typography variations.
  - `banner/`: [DSBanner] for global status alerts.
  - `login/`: [DSLogin] high-level authentication pattern.
- **`catalog/`**: A developers-only "showroom" containing demo screens and navigation logic.

---

## 📖 Development Best Practices
- **KDoc Documentation**: Every component is documented with headers describing parameters and expected behaviors.
- **Stable UI**: Usage of `heightIn` and `Spacer` weight logic prevents layout shifting during state changes.
- **Encapsulation**: Catalog code is strictly isolated to ensure the library can be extracted or published without bloat.

## 🏁 Getting Started
1. Open in **Android Studio Ladybug**.
2. Sync Gradle and run the `:app` module.
3. Browse the categories to test animations, stacking, and validation logic.
