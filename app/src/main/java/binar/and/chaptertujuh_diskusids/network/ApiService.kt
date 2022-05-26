package binar.and.chaptertujuh_diskusids.network

import binar.and.chaptertujuh_diskusids.model.GetUserItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("user")
    fun getUser(
        @Query("username") username : String
    ) : Call<List<GetUserItem>>
}