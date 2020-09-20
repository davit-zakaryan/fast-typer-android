# fast-typer-android
Application for improving your typing speed.

## Languages, libraries and tools used

* [Kotlin](https://kotlinlang.org/)
* Android Support Libraries
* [Coroutines](https://https://github.com/Kotlin/kotlinx.coroutines)
* [Koin](https://github.com/InsertKoinIO/koin)
* [Firebase-auth](https://github.com/InsertKoinIO/koin)
* [Koin](https://github.com/InsertKoinIO/koin)
* [Picasso](https://https://github.com/square/picasso)
* [Retrofit](http://square.github.io/retrofit/)
* [OkHttp](http://square.github.io/okhttp/)
* [Gson](https://github.com/google/gson)
* [Mockito](http://site.mockito.org/)

## Architecture

The architecture of the project follows the principles of Clean Architecture + Google suggested guide to app architecture (My approach).

Here are main principles
* The UI can only communicate with the ViewModel
* The ViewModel can only communicate with the Repository (I throw UseCase layer)
* And the Repository can only communicate with the Datasource
