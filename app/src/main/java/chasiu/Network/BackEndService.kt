package chasiu.Network

/**
 * Created by Davix on 3/2/16.
 */

import chasiu.Model.Model.User
import rx.Observable
import retrofit.http.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonDeserializationContext
import com.google.gson.FieldNamingPolicy
import org.json.JSONObject
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory

import java.lang.reflect.Type

interface BackEndService {

    @Headers("User-Agent: YKode Sample App/1.0")
    @FormUrlEncoded
    @POST("/post")
    public fun signUpUser(@Field("username") name: String,
                            @Field("email") email: String): Observable<User>

    @Headers("User-Agent: YKode Sample App/1.0")
    @FormUrlEncoded
    @POST("/post")
    public fun logInUser(@Field("username") name: String,
                          @Field("email") email: String): Observable<User>

    @Headers("User-Agent: YKode Sample App/1.0")
    @FormUrlEncoded
    @POST("/post")
    public fun getUserInfo(@Field("userId") id: String): Observable<User>

    @Headers("User-Agent: YKode Sample App/1.0")
    @FormUrlEncoded
    @POST("/post")
    public fun getUsers(@Field("QueryParam") loggedIn: JSONObject): Observable<List<User>>

    companion object {

        var endPoint : String = ""

        internal object UserDeserializer : JsonDeserializer<User> {
            override fun deserialize(je : JsonElement, type : Type,
                                     jdc: JsonDeserializationContext) : User
            {
                val form = je.asJsonObject.get("form")
                return Gson().fromJson(form, type)
            }
        }

        fun create() : BackEndService {
            val gsonBuilder = GsonBuilder()
            gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            gsonBuilder.registerTypeAdapter(User::class.java, UserDeserializer)

            val url = "https://httpbin.org/post"
            val restAdapter = Retrofit.Builder()
                    .baseUrl(url)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                    .build()

            this.endPoint = url

            return restAdapter.create(BackEndService::class.java)
        }
    }

}

