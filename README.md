This is a Kotlin Multiplatform project targeting Android, iOS.

### Before running

* Get a [TMDB API Key](https://www.themoviedb.org/settings/api)
* Add to `local.properties` file located in the root directory (create if it doesn't exist):
  `api_key=YOUR_KEY`

### Technologies

* [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) for UI
* [Ktor](https://ktor.io/) for networking
* [DataStore](https://developer.android.com/kotlin/multiplatform/datastore)
* [Room](https://developer.android.com/kotlin/multiplatform/room)
* [gradle-buildconfig-plugin](https://github.com/gmazzo/gradle-buildconfig-plugin) to expose the API
  Key as a constant from `local.properties`
* [Chucker](https://github.com/ChuckerTeam/chucker) HTTP inspector for Android

### Screenshots

<p align="center">
<img src="https://github.com/user-attachments/assets/b8c596bf-1b1f-494c-8b2d-ab6e8063548d" width="26%"  />  
<img src="https://github.com/user-attachments/assets/fd0336ea-41be-4818-a26f-4d4e89626fa0" width="25%" />  
</p>

  Learn more
about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
