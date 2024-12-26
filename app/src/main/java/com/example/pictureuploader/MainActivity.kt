package com.example.pictureuploader

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.pictureuploader.utils.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    //https://random.dog/
    private lateinit var toolbar: Toolbar
    private lateinit var imageView: ImageView
    private lateinit var buttonLoad: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        title = ""
        setSupportActionBar(toolbar)
        imageView = findViewById(R.id.imageView)
        buttonLoad = findViewById(R.id.buttonLoad)

        buttonLoad.setOnClickListener {
            loadRandomDogImage()
        }
    }

    private fun loadRandomDogImage() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = try {
                RetrofitInstance.api.getRandomDog()
            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "Ошибка сети: ${e.message}", Toast.LENGTH_SHORT).show()
                }
                return@launch
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "Ошибка сервера: ${e.message}", Toast.LENGTH_SHORT).show()
                }
                return@launch
            }

            if (response.url.endsWith(".mp4") || response.url.endsWith(".webm")) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "Получен видео файл. Нажмите загрузить ещё раз.", Toast.LENGTH_SHORT).show()
                }
            } else {
                withContext(Dispatchers.Main) {
                    Glide.with(applicationContext)
                        .load(response.url)
                        .into(imageView)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenu -> {
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}