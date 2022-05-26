package binar.and.chaptertujuh_diskusids

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import binar.and.chaptertujuh_diskusids.model.GetUserItem
import binar.and.chaptertujuh_diskusids.network.ApiClient
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Response

class LoginFragment : Fragment() {

    lateinit var username : String
    lateinit var passsword : String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_login, container, false)

        val view = inflater.inflate(R.layout.fragment_login,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnlogin.setOnClickListener{
            if (login_username.text.isNotEmpty() && login_password.text.isNotEmpty()){
                username = login_username.text.toString()
                passsword = login_password.text.toString()

            }else{}
        }
    }


    fun login(username :String, password : String){
        ApiClient.instance.getUser(username)
            .enqueue(object : retrofit2.Callback<List<GetUserItem>>{
                override fun onResponse(
                    call: Call<List<GetUserItem>>,
                    response: Response<List<GetUserItem>>
                ) {
                    if (response.isSuccessful){
                        if (response.body()?.isEmpty()==true){
                            Toast.makeText(requireContext(),"Unknown User", Toast.LENGTH_LONG).show()
                        }else{
                            when{
                                response.body()?.size!! > 1 -> {
                                    Toast.makeText(requireContext(), "mohon masukkan data yang benar",android.widget.Toast.LENGTH_LONG).show()
                                }
                                username != response.body()!![0].username -> {
                                    Toast.makeText(requireContext(), "email tidak terdaftar",android.widget.Toast.LENGTH_LONG).show()
                                }
                                password != response.body()!![0].password -> {
                                    Toast.makeText(requireContext(), "password salah",android.widget.Toast.LENGTH_LONG).show()

                                }else ->{
                                    Navigation.findNavController(view!!).navigate(R.id.action_loginFragment_to_homeFragment)
                                }
                            }
                        }

                    }else{
                        Toast.makeText(requireContext(),response.message(),Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<List<GetUserItem>>, t: Throwable) {
                    Toast.makeText(requireContext(),t.message,Toast.LENGTH_LONG).show()
                }
            })}

}