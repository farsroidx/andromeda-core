package ir.farsroidx.andromeda

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import ir.farsroidx.m31.Andromeda
import ir.farsroidx.m31.preferences
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    data class User(val id: Long, val name: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch { function() }
    }

    private suspend fun function() {

        Andromeda.preferences.put("id", 1024)

        Log.i("CentralCore", "-------------------------------------------------------")

        if (Andromeda.preferences.containsKey("id")) {

            Log.w("CentralCore", Andromeda.preferences.get<Int>("id").toString())

            Log.i("CentralCore", "-------------------------------------------------------")

            Andromeda.preferences.remove("id")

            if (Andromeda.preferences.containsKey("id")) {

                Log.w("CentralCore", Andromeda.preferences.get("id", "alternate"))

                Log.i("CentralCore", "-------------------------------------------------------")

            } else {

                Log.w("CentralCore", "id not found")

                Log.i("CentralCore", "-------------------------------------------------------")
            }

        } else {

            Log.w("CentralCore", "id not found")

            Log.i("CentralCore", "-------------------------------------------------------")
        }
    }
}