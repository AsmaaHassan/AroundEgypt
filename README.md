Around Egypt 🏜️

An Android application built as part of a technical task to showcase experience listing, search, and interaction features for the Around Egypt app.

The project focuses on clean architecture (MVVM), Jetpack Compose UI, and modern Android development practices, including an offline-first approach.

📱 Features
🏠 Home Screen
- Displays Recommended Experiences (horizontal list)
- Displays Most Recent Experiences (vertical list)
- UI implemented based on the provided Figma design

Actions
- **View Experience**: Tap an experience to open the Experience details screen
- **Like Experience**: Like an experience using the provided API. Likes count and state are updated locally and synced.

🔍 Search
- IME Search action enabled on the search text field
- Displays search results based on the experience title
- Fallback to local search when offline

💾 Offline Support (Offline-First Approach)
- **Local Persistence**: Uses Room Database to cache experiences locally.
- **Single Source of Truth**: The app follows an offline-first strategy where the repository manages data synchronization. Recommended and Recent lists are fetched from the API and cached, ensuring they remain available without an internet connection.
- **Conflict Management**: Implements a robust upsert logic in the DAO to handle overlapping items between "Recommended" and "Recent" categories.
- **UI Fallbacks**: Includes placeholder and error image handling in the UI to ensure a seamless experience even when images fail to load or the device is offline.

📄 Experience Screen
- Displays experience details in a dialog
- UI follows the provided Figma design
- Updates likes count and like state locally

🛠️ Tech Stack & Architecture
- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: Clean Architecture with MVVM (Model–View–ViewModel)
- **Local Database**: Room (with Offline-First implementation)
- **Dependency Injection**: Hilt
- **Networking**: Retrofit & OkHttp
- **Concurrency**: Kotlin Coroutines & Flow
- **Image Loading**: Coil (with local caching support)
- **Serialization**: Moshi

🌐 Backend APIs
Base URL: `https://aroundegypt.34ml.com`

| Feature | Endpoint |
| :--- | :--- |
| Recommended Experiences | `/api/v2/experiences?filter[recommended]=true` |
| Recent Experiences | `/api/v2/experiences` |
| Search Experiences | `/api/v2/experiences?filter[title]={search_text}` |
| Like Experience | `/api/v2/experiences/{id}/like` |

Screenshots:
![around4](https://github.com/user-attachments/assets/e7d235dd-4b3b-4056-a1dc-2893c7d7e675)
![around1](https://github.com/user-attachments/assets/51a32d8f-cdb6-4246-b46a-43c3232e2986)
![around3](https://github.com/user-attachments/assets/25151085-d46c-46a4-b275-32d396b447ad)
![around2](https://github.com/user-attachments/assets/14611dc1-2d9b-48ce-b004-f07f401962d9)
