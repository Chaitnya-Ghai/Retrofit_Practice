package chaitnya.healthtick.retrofit_practice

import chaitnya.healthtick.retrofit_practice.model.PostResponseItem
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
//---------------------------------------------------------------------------------
    /**
     *
     *     Keep the response as Response<ResponseBody> or Response<JsonElement>
     *     until you know its structure.
     *     */
    @GET("posts")
    suspend fun getPosts() : Response<ResponseBody>
    /**
     * @return [List]<[PostResponseItem]> representing the list of post items in the response.
     */

//---------------------------------------------------------------------------------
    /**
     * Sends a new post to the API using a POST request.
     *
     * @param post The [PostResponseItem] object containing the post data to upload.
     * @return [Response]<[ResponseBody]> containing the server response after uploading the post.
     *         Once the response structure is fully known, you can replace [ResponseBody]
     *         with a more specific type if needed.
     */
    @POST("posts")
    suspend fun postPosts(@Body post: PostResponseItem): Response<ResponseBody>
    /**
     * @return [PostResponseItem] representing a post item in the response.
     *  */

//---------------------------------------------------------------------------------



}