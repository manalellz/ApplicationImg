
package com.example.applicationimg

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView

    // Choisir une image depuis le téléphone
    private val imagePicker =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                Glide.with(this)
                    .load(uri)
                    .into(imageView)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Récupération des composants
        imageView = findViewById(R.id.imageView)

        val btnLocal = findViewById<Button>(R.id.btnLocal)
        val btnUrl = findViewById<Button>(R.id.btnUrl)
        val btnGallery = findViewById<Button>(R.id.btnGallery)

        // 1. Afficher l'image locale
        btnLocal.setOnClickListener {
            imageView.setImageResource(R.drawable.img)
        }

        // 2. Charger une image depuis Internet
        btnUrl.setOnClickListener {
            val url = "https://picsum.photos/800/600"

            Glide.with(this)
                .load(url)
                .placeholder(R.drawable.img)
                .error(R.drawable.img)
                .into(imageView)
        }

        // 3. Choisir une image depuis le téléphone
        btnGallery.setOnClickListener {
            imagePicker.launch("image/*")
        }
    }
}
