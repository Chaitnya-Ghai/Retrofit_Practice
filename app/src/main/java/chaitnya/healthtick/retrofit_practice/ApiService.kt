package chaitnya.healthtick.retrofit_practice

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Path

interface ApiService {
    @DELETE("posts/{id}")
    suspend fun deletePost(
        @Path("id") id: Int
    ): Response<Void>
//    eske response mai  kuch nhi ayeega , status 200 kai ilava
}