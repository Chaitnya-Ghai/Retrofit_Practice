package chaitnya.healthtick.retrofit_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import chaitnya.healthtick.retrofit_practice.HttpMethods.PatchComposable
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
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        val post = PostResponseItem(
                            userId = 871,
                            id = 1,
                            body = "body of the post",
                            title = "title of the post"
                        )

                        PutComposable(
                            putData = { apiService.putPost(post, 1) },
                            modifier = Modifier.weight(1f)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        PatchComposable(
                            patchData = { apiService.patch(mapOf("title" to "new title"), 1) },
                            modifier = Modifier.weight(1f)
                        )
                    }

                }
            }
        }
    }
}