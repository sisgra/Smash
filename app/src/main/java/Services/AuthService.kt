package Services
/*
import Utilities.URL_REGISTER
import android.content.Context
import android.util.Log */

import org.json.JSONObject
/*
object AuthService {
    fun registerUser(context: Context,email:String,password:String,complete: (Boolean)->Unit){

        val url= URL_REGISTER

        val jsonBody=JSONObject()
        jsonBody.put("email",email)
        jsonBody.put("password",password)
        val requestBody=jsonBody.toString()

        val registerRequest=object:StringRequest(Method.POST, URL_REGISTER,Response.Listener{
          response-> println(response)
            complete(true)
        }, Response.ErrorListener { error -> Log.d("ERROR","could not register user:$error")
        complete(false)
        }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }

        Volley.newRequestQueue(context).add(registerRequest)
    }
}*/