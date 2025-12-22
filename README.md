Around Egypt 🏜️

An Android application built as part of a technical task to showcase experience listing, search, and interaction features for the Around Egypt app.

The project focuses on clean architecture (MVVM), Jetpack Compose UI, and modern Android development practices.

📱 Features
🏠 Home Screen

Displays Recommended Experiences (horizontal list)

Displays Most Recent Experiences (vertical list)

UI implemented based on the provided Figma design

Actions

View Experience

Tap an experience to open the Experience details screen

Like Experience

Like an experience using the provided API

Likes count updates using the API response

Experience like state is updated locally

⚠️ Users can like only once (unlike is not supported)

🔍 Search

IME Search action enabled on the search text field

On IME Search click:

Displays search results based on the experience title

Clear Search

Exits search mode

Restores default Home screen data

📄 Experience Screen

Displays experience details in a dialog

UI follows the provided Figma design

Actions

Like Experience

Same behavior as Home Screen

Updates likes count and like state locally

🛠️ Tech Stack & Architecture

Language: Kotlin

UI: Jetpack Compose

Architecture: MVVM (Model–View–ViewModel)

State Management: StateFlow & Compose State

Networking: Retrofit

Concurrency: Kotlin Coroutines

🌐 Backend APIs

Base URL:

https://aroundegypt.34ml.com

Endpoints Used
Feature	Endpoint
Recommended Experiences	/api/v2/experiences?filter[recommended]=true
Recent Experiences	/api/v2/experiences
Search Experiences	/api/v2/experiences?filter[title]={search_text}
Like Experience	/api/v2/experiences/{id}/like
