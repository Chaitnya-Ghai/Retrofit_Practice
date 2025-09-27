package chaitnya.healthtick.retrofit_practice.HttpMethods

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import okhttp3.ResponseBody
import retrofit2.Response

@Composable
fun PostComposable(
    postData: suspend () -> Response<ResponseBody>
) {
    var apiResult by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            val response = postData()
            if (response.isSuccessful) {
                apiResult = response.body()?.string()
            } else {
                errorMessage = "Error code: ${response.code()}"
            }
        } catch (e: Exception) {
            errorMessage = e.message ?: "Unknown error"
        } finally {
            isLoading = false
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            isLoading -> CircularProgressIndicator()
            apiResult != null -> Text(apiResult!!)
            errorMessage != null -> Text(errorMessage!!, color = Color.Red)
        }
    }
}
