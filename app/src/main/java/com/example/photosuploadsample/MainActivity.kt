package com.example.photosuploadsample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val selectPictureLauncher =
        // 今回は標準ギャラリーをIntentで起動するため、ActivityResultContracts.StartActivityForResult()を使用
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val imageView: ImageView = findViewById(R.id.image_view)
                // 選択した画像をImageViewに表示
                imageView.setImageURI(it.data?.data)
            } else {
                // エラーが発生した場合はトーストを表示
                Toast.makeText(this, "エラーが発生しました", Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<View>(R.id.btn_choose_image)

        button.setOnClickListener {
            // ギャラリーを開いて画像を選択するIntentを作成
            val intent = Intent(Intent.ACTION_PICK).also {
                it.type = "image/*"
            }

            // selectPictureLauncherを使用し、ギャラリーを開く
            selectPictureLauncher.launch(intent)
        }
    }
}