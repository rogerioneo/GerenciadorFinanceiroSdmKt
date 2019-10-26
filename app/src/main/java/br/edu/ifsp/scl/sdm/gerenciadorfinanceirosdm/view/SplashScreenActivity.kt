package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        var handler = Handler()
        handler.postDelayed(Runnable {
            mostrarMainActivity()
        }, 2000)
    }

    fun mostrarMainActivity(){
        val intent = Intent(
            this, MainActivity::class.java
        )
        startActivity(intent)
        finish()
    }
}
