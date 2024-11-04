package org.iesharia.coroutineszerotohero

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import android.widget.Toast
import androidx.lifecycle.lifecycleScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import org.iesharia.coroutineszerotohero.ui.theme.CoroutinesZeroToHeroTheme

class MainActivity : ComponentActivity() {

    val retrofit = RetrofitHelper.getInstance()

    //Main = Hilo principal
    // IO = Retrofit
    // Default = procesar informacion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            val response: Response<SuperHeroDataResponse> = retrofit.getSuperheroes("a")
            if (response.isSuccessful) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "FUNCIONA", Toast.LENGTH_SHORT).show()
                    Log.i("DAM", "$response")
                }
            }
        }

    }

    fun waitForCoroutines() {
        lifecycleScope.launch(Dispatchers.IO) {
            val deferreds = listOf(
                async { retrofit.getSuperheroes("a") },
                async { retrofit.getSuperheroes("a") },
                async { retrofit.getSuperheroes("a") },
                async { retrofit.getSuperheroes("a") },
                async { retrofit.getSuperheroes("a") },
                async { retrofit.getSuperheroes("a") },
                async { retrofit.getSuperheroes("a") },
                async { retrofit.getSuperheroes("a") },
                async { retrofit.getSuperheroes("a") }
            )

            val response = deferreds.awaitAll()

        }
    }

    suspend fun suscribete() {

    }
}