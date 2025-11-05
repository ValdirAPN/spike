### 🏐 Spike: Volleyball Match Finder
Spike is a cross-platform mobile application designed to connect volleyball players, making it easy to create and find local matches. Whether you're looking for a casual game or a serious pickup match, Spike helps you get on the court!

### ✨ Features
Create Matches: Easily set up a new match with details like location, time, skill level, and required number of players.
Find Matches: Discover nearby matches using a map view or a filterable list.
Join/Manage Matches: Quickly join open matches and manage the matches you've created.

### 💻 Tech Stack
Spike is built using cutting-edge, modern technologies to ensure performance, maintainability, and a beautiful user interface.
| Category | Technology | Description |
| :--- | :--- | :--- |
| **Primary Language** | **Kotlin** | The primary language for all business logic and cross-platform modules. |
| **Framework** | **Kotlin Multiplatform (KMP)** | Used to share common code between the Android and iOS platforms. |
| **UI Framework** | **Compose Multiplatform** | The modern declarative UI toolkit, allowing a single UI to be rendered natively on both mobile platforms. |
| **Android UI** | **Compose for Android** | The native UI implementation on the Android platform. |
| **iOS UI** | **Compose for iOS** | The native UI implementation on the iOS platform. |
| **State Management** | *MVVM/Kotlin Flows* | Handles complex application state in a predictable and reactive manner. |

## 🚀 Getting Started
This is a Kotlin Multiplatform project targeting Android, iOS.

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
    folder is the appropriate location.

* [/iosApp](./iosApp/iosApp) contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:
- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
