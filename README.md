# PickMeCommentator

Это Android-приложение, разработанное на Kotlin с использованием современных практик разработки.

## Описание

PickMeCommentator - это приложение для отображения шуток к поездке робота. Оно умеет получать данные о прошедших поездках с сервера и шуток к ним, использует веб-сокеты для получения шуток в реальном времени.

## Ключевые файлы и экраны

### Экраны

*   `MainScreen.kt`: Основной экран приложения, который содержит нижнюю навигационную панель и управляет переключением между двумя основными разделами.

*   `CurrentTripMessagesScreen.kt`: Экран, отображающий комментарии (шутки) к текущей поездке в реальном времени. Позволяет отслеживать состояние подключения к серверу и прокручивать список сообщений.

*   `TripsDetalizationScreen.kt`: Раздел, предоставляющий детализацию по поездкам. Включает в себя собственную навигацию для просмотра списка поездок и подробной информации о каждой из них.

*   `ListOfTripsScreen.kt`: Экран, отображающий список завершенных поездок, полученных с сервера.

*   `TripMessagesScreen.kt`: Экран, отображающий комментарии (шутки) для выбранной завершенной поездки.

### ViewModel

*   `MainScreenViewModel.kt`: ViewModel для `MainScreen` (в настоящее время не содержит логики).

*   `CurrentTripMessagesScreenViewModel.kt`: Управляет логикой `CurrentTripMessagesScreen`, включая получение комментариев через веб-сокет и обработку состояния подключения.

*   `ListOfTripsScreenViewModel.kt`: Управляет логикой `ListOfTripsScreen`, включая получение списка завершенных поездок с сервера.

*   `TripMessagesScreenViewModel.kt`: Управляет логикой `TripMessagesScreen`, включая получение комментариев для выбранной завершенной поездки.

### Навигация

*   `TripsDetalizationNavigationHost.kt`: Определяет навигационный граф для раздела детализации поездок, позволяя переключаться между списком поездок и подробным экраном.

## Технологический стек и зависимости

*   **Kotlin**: Основной язык программирования.
*   **Jetpack Compose**: Для создания декларативного пользовательского интерфейса.
*   **Coroutines**: Для управления асинхронными операциями.
*   **Koin**: Для внедрения зависимостей.
*   **Retrofit**: Для сетевых запросов.
*   **Socket IO**: Для коммуникации в реальном времени.

## Архитектура

Проект следует принципам чистой архитектуры, разделяя код на три основных слоя:

*   `presentation`: Отвечает за пользовательский интерфейс и взаимодействие с пользователем.
*   `domain`: Содержит бизнес-логику приложения.
*   `data`: Отвечает за получение и хранение данных.

## Диаграмма архитектуры

Ниже представлена диаграмма, описывающая архитектуру приложения и распределение ответственности по модулям.

```
[ Presentation Layer (UI) ]
 |
 +--- screens (Composable-экраны)
 |      |-- main/MainScreen.kt
 |      |-- main/bottomnav/current_trip_messages/CurrentTripMessagesScreen.kt
 |      |-- main/bottomnav/trips_detalization/TripsDetalizationScreen.kt
 |      |-- main/bottomnav/trips_detalization/list_of_trips/ListOfTripsScreen.kt
 |      |-- main/bottomnav/trips_detalization/trip_messages/TripMessagesScreen.kt
 |
 +--- viewmodels (ViewModel'и)
 |      |-- main/MainScreenViewModel.kt
 |      |-- main/bottomnav/current_trip_messages/CurrentTripMessagesScreenViewModel.kt
 |      |-- main/bottomnav/trips_detalization/list_of_trips/ListOfTripsScreenViewModel.kt
 |      |-- main/bottomnav/trips_detalization/trip_messages/TripMessagesScreenViewModel.kt
 |
 +--- theme (Тема приложения)
 |      |-- Theme.kt, Color.kt
 |
 +--- navigation (Навигация)
 |      |-- bottom/BottomNavRoutes.kt
 |      |-- trips_detalization/TripsDetalizationNavigationHost.kt

       |
       ▼

[ Domain Layer (Business Logic) ]
 |
 +--- usecase (Сценарии использования)
 |      |-- GetTripsUseCase.kt
 |      |-- FormatTimestampUseCase.kt
 |
 +--- repositories (Абстракции репозиториев)
        |-- SocketRepository.kt
        |-- TripsApiRepository.kt

       |
       ▼

[ Data Layer (Data Sources) ]
 |
 +--- network
 |      |-- api (Сетевые интерфейсы - Retrofit)
 |      |   |-- TripsApiService.kt
 |      |
 |      |-- repository (Реализации репозиториев)
 |      |   |-- TripsApiRepositoryImpl.kt
 |      |   |-- SocketRepositoryImpl.kt
 |      |
 |      |-- models (Модели данных - DTO)
 |          |-- Trip.kt, TripListResponse.kt
 |
 +--- di (Koin-модули для внедрения зависимостей)

[ Common ]
 |
 +--- (Вспомогательные классы и утилиты)
```

## Структура проекта

*   `app/src/main/java/com/example/pickmecommentator`: Корневой пакет проекта.
    *   `common`: Общие утилиты и вспомогательные классы.
    *   `data`: Реализация репозиториев, сетевые запросы и модели данных.
    *   `domain`: Абстракции репозиториев и бизнес-логика.
    *   `presentation`: Экраны, composable-элементы, ViewModel'и и навигация.

## Сборка проекта

1.  Клонируйте репозиторий:
    ```bash
    git clone https://gitverse.ru/dany/PickMeCommentator.git
    ```
2.  Откройте проект в Android Studio.
3.  Синхронизируйте проект с Gradle.
4.  Запустите приложение на эмуляторе или физическом устройстве.
