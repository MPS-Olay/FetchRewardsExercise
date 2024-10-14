
# Fetch Reward Exercise

This repository contains a modular Android application that demonstrates a hiring system built using **Kotlin**, **Ktor**, and **Jetpack Compose**. The project follows **clean architecture**, separating responsibilities into the following layers:

- **Presentation**: Jetpack Compose views and ViewModels.
- **Domain**: Use cases encapsulating business logic.
- **Data**: Repositories, DTOs, and networking logic.

---

## 📂 Project Structure

```
app
│
├── src
│   ├── main
│   │   ├── java/com/fetch/rewards
│   │   │   ├── common/di         # Dependency Injection Modules
│   │   │   ├── data              # Data layer (DTOs, Entities, Repositories)
│   │   │   │   ├── di            # Data-related DI modules
│   │   │   │   ├── dto           # Data Transfer Objects (DTOs)
│   │   │   │   ├── entity        # Entity classes (e.g., Hiring)
│   │   │   │   ├── repo          # Repository interfaces & implementations
│   │   │   └── domain            # Domain layer (Use cases)
│   │   │       ├── di            # Domain-related DI modules
│   │   │       └── usecase       # Use case classes
│   │   │   └── presentation      # Presentation layer (Compose UI, ViewModels)
│   │   │       ├── di            # Presentation-related DI modules
│   │   │       ├── hiringList    # Hiring List components (UI, State, ViewModel)
│   │   │       └── ui/theme      # Theme configuration (Color, Typography)
│   │   └── res                   # Android resource files (layout, values, etc.)
│   └── test                      # Unit tests
│       ├── data/repo             # Repository tests
│       ├── domain/usecase        # Use case tests
│       ├── presentation/hiringList # ViewModel tests
│       └── testUtil              # Utilities for testing
├── build.gradle.kts              # Gradle build configuration (Kotlin DSL)
├── libs.versions.toml            # Dependency version management
└── proguard-rules.pro            # Proguard configuration (for release builds)
```

---

## 🛠️ Setup & Configuration

### Prerequisites

Ensure you have the following installed:
- **Android Studio** (latest stable version)
- **Java 11 or higher**
- **Gradle 7.x**

### Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-repo-url/fetch-reward-exercise.git
   cd fetch-reward-exercise
   ```

2. **Open the project in Android Studio:**
   - Select **File > Open** and choose the project directory.

3. **Sync Gradle:**  
   In Android Studio, click **Sync Project with Gradle Files** to download dependencies.

4. **Run the Application:**  
   Select a device or emulator, then click **Run** to build and deploy the app.

---

## 🧪 Running Tests

This project includes unit tests for:

- **Repository Layer**: Located in `test/java/com/fetch/rewards/data/repo`
- **Use Case Layer**: Located in `test/java/com/fetch/rewards/domain/usecase`
- **ViewModel Layer**: Located in `test/java/com/fetch/rewards/presentation/hiringList`

To run all tests, use the following command:

```bash
./gradlew test
```

---

## 📦 Dependencies

The project uses the following dependencies:

- **Kotlin Coroutines**: For async programming.
- **Ktor**: For networking.
- **Jetpack Compose**: For UI.
- **MockK**: For mocking dependencies in tests.
- **Turbine**: For testing `StateFlow`.

---

## ⚠️ Troubleshooting

If you encounter Gradle sync issues, try the following:

```bash
./gradlew clean
./gradlew build --refresh-dependencies
```

---

## 📈 Test Coverage

Ensure that all critical classes have tests to maintain high test coverage. The project is configured to generate test coverage reports.

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

---

## 📞 Contact

For any inquiries, please contact [olay-recruitment@multiplatformservices.com].
