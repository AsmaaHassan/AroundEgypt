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

Screenshots:
![around4](https://github.com/user-attachments/assets/e7d235dd-4b3b-4056-a1dc-2893c7d7e675)![around1](https://github.com/user-attachments/assets/51a32d8f-cdb6-4246-b46a-43c3232e2986)
![around3](https://github.com/user-attachments/assets/25151085-d46c-46a4-b275-32d396b447ad)![Uploading around1.jpg…]()![around2](https://github.com/user-attachments/assets/14611dc1-2d9b-48ce-b004-f07f401962d9)





