package chaitnya.healthtick.retrofit_practice

import chaitnya.healthtick.retrofit_practice.model.PostResponseItem
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceTest {
    /**
     * Real-APIs-are-unpredictable — they can be slow, unavailable, or change behavior without notice.
     *
     * [MockWebServer] solves this by:
     * - Acting like a **fake API server** that runs locally during your tests.
     * - Allowing you to **predefine responses** (status codes, headers, body) for given requests.
     * - Enabling you to **verify and inspect outgoing requests** made by your app.
     *
     * This ensures your tests are **fast, reliable, and independent** of real network conditions.
     */

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    @Test
    fun `test getPosts returns expected response`() = runTest {
        // Arrange
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("[{\"id\":1,\"body\":\"Post Body\",\"userId\":\"101\",\"title\":\"Post Title\"}]")
        mockWebServer.enqueue(mockResponse)

        // Act
        val response = apiService.getPosts()

        // Assert
        assertTrue(response.isSuccessful)
        assertEquals(200, response.code())
    }

    @Test
    fun `test for post-method on Posts`()=runTest{
        val body = PostResponseItem(
            body = "Body",
            id = 1,
            title = "Post Title",
            userId = 108
        )

        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("[{\"id\":1,\"body\":\"Post Body\",\"userId\":\"101\",\"title\":\"Post Title\"}]")
        mockWebServer.enqueue(mockResponse)

//        ACT
        val response = apiService.postPosts(body)

//        Assert
        assertTrue(response.isSuccessful)
        assertEquals(200, response.code())

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
