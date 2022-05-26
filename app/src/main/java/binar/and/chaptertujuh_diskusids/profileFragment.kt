package binar.and.chaptertujuh_diskusids

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import kotlinx.android.synthetic.main.fragment_profile.*


class profileFragment : Fragment() {

    private lateinit var usermanager : UserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usermanager = UserManager(requireContext())

        usermanager.userNama.asLiveData().observe(viewLifecycleOwner){
            profile_nama.setText(it)
        }
        usermanager.userUmur.asLiveData().observe(viewLifecycleOwner){
            profile_umur.setText(it)
        }
        usermanager.userAddress.asLiveData().observe(viewLifecycleOwner){
            profile_address.setText(it)
        }
    }


}