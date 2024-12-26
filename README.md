Приложение «Загрузчик картинок»

Необходимо написать приложение, в котором по нажатию на кнопку «Загрузить», будет выполнен GET – запрос и получена картинка (фото) какого-нибудь животного, природы и тд. Рассматриваем закрепление темы получение информации из интернета (тема прошлого занятия). Для примера представляю ресурс для получения фото собак baseUrl = https://random.dog/

Зависимости для работы с библиотекой Glide для асинхронной подгрузки изображений из сети implementation ("com.github.bumptech.glide:glide:4.12.0") annotationProcessor ("com.github.bumptech.glide:compiler:4.11.0")

Функция запроса:

@GET("woof.json?ref=apilist.fun") 
suspend fun getRandomDog(): ApiData

Класс модели:

data class ApiData(
val fileSizeBytes: Int,
val url: String
)

Для работы приложения на экране необходимо создать:

1. Toolbar с заголовком.
2. Меню с пунктом выхода «Exit».
3. ImageView для отображения загруженной картинки.
4. Кнопку «Загрузить» для загрузки рандомной картинки.

[Video](https://rutube.ru/video/private/1e7bdb2dc7d0993859e73e7d816e5451/?p=vN1esv7l6oJsODlBR3heCQ)