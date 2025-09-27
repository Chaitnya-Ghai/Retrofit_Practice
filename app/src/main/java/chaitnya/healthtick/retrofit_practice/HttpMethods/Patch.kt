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
fun PatchComposable(
    patchData: suspend () -> PostResponseItem,
    modifier: Modifier = Modifier
) {
    var apiResult by remember { mutableStateOf<PostResponseItem?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            val response = patchData() // returns PostResponseItem directly
            apiResult = response
        } catch (e: Exception) {
            errorMessage = e.message ?: "Unknown error"
        } finally {
            isLoading = false
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            isLoading -> CircularProgressIndicator()
            apiResult != null -> Text(
                text = "Updated Patch title : ${apiResult!!.title}\n of id:${apiResult!!.id}",
                textAlign = TextAlign.Center
            )
            errorMessage != null -> Text(errorMessage!!, color = Color.Red)
        }
    }
}