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
import androidx.compose.ui.text.style.TextAlign
import chaitnya.healthtick.retrofit_practice.model.PostResponseItem


@Composable
fun PutComposable(
    postData: suspend () -> PostResponseItem
) {
    var apiResult by remember { mutableStateOf<PostResponseItem?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            val response = postData() // returns PostResponseItem directly
            apiResult = response
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
            apiResult != null -> Text(
                text = "Updated Post: ${apiResult!!.title}\n${apiResult!!.body}",
                textAlign = TextAlign.Center
            )
            errorMessage != null -> Text(errorMessage!!, color = Color.Red)
        }
    }
}

/**
 * CASE 1: Unknown / Raw Response
 * --------------------------------
 * Use this approach when the response format is unknown or generic, for example:
 *  - A testing endpoint
 *  - A third-party API where the response structure may change
 *
 * Retrofit return type: Response<ResponseBody>
 *
 * Steps:
 * 1. Check if the HTTP response was successful using `response.isSuccessful`.
 * 2. If successful, extract the raw response body as a string:
 *      apiResult = response.body()?.string() ?: ""
 * 3. If not successful, handle the error by reading the HTTP status code:
 *      errorMessage = "Error code: ${response.code()}"
 *
 * Note:
 *  - `ResponseBody` gives the raw JSON or text from the server.
 *  - You can parse it manually later if needed (e.g., using Gson or kotlinx.serialization).
 */
