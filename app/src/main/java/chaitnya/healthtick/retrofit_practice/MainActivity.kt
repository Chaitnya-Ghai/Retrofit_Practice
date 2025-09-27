package chaitnya.healthtick.retrofit_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import chaitnya.healthtick.retrofit_practice.HttpMethods.PutComposable
import chaitnya.healthtick.retrofit_practice.model.PostResponseItem
import chaitnya.healthtick.retrofit_practice.ui.theme.Retrofit_PracticeTheme

class MainActivity : ComponentActivity() {
    private val apiService : ApiService by lazy {RetrofitInstance.getApiService()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Retrofit_PracticeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        val post = PostResponseItem(
                            userId = 871,
                            id = 1,
                            body = "body of the post",
                            title = "title of the post"
                        )
                        PutComposable { apiService.putPost(post = post ,1) }
                    }
                }
            }
        }
    }
}