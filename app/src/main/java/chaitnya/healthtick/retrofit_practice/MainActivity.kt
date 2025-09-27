package chaitnya.healthtick.retrofit_practice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import chaitnya.healthtick.retrofit_practice.ui.theme.Retrofit_PracticeTheme

class MainActivity : ComponentActivity() {
    private val apiService : ApiService by lazy {RetrofitInstance.getApiService()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Retrofit_PracticeTheme {
                var apiResult by remember { mutableStateOf<String?>(null) }
                var errorMessage by remember { mutableStateOf<String?>(null) }

                LaunchedEffect(Unit) {
                    try {
                        val apiResponse = apiService.deletePost(1)
                        apiResult = " Response: "+apiResponse.body().toString().plus("  &  Status Code: ").plus(apiResponse.code())
                        Log.d("TAG", "onCreate: ${apiResponse.code()}")
                    } catch (e: Exception) {
                        errorMessage = e.message
                    }
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        when {
                            apiResult != null -> {
                                Text(text = apiResult!!)
                                Log.d("TAG", " $apiResult ") // get the post
                            }
                            errorMessage != null -> Text(text = "Error: $errorMessage", color = Color.Red)
                            else -> CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}