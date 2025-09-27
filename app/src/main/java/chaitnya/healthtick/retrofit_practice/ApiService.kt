package chaitnya.healthtick.retrofit_practice

import chaitnya.healthtick.retrofit_practice.model.PostResponseItem
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    /**
     * Updates an existing post on the API using a PUT request.
     *
     * Endpoint example : `posts/1`
     *
     * The endpoint is `posts/{userId}`, where `userId` is dynamic and passed via [Path].
     *@param post The [PostResponseItem] object containing the updated post data.
     *@param userId The ID of the post/user to be updated.
     *
     */
    @PUT("posts/{userId}")
    suspend fun putPost(
        @Body post: PostResponseItem,
        @Path("userId")userId : Int
    ) : PostResponseItem
//---------------------------------------------------------------
    @PATCH("posts/{userId}")
    suspend fun patch(
        @Body postField : Map<String, String>,//as json is = key-value pair
        @Path("userId")userId : Int
    ) : PostResponseItem



}