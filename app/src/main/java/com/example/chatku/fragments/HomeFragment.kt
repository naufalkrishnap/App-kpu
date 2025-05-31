 package com.example.chatku.fragments

 import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatku.R
import com.example.chatku.SignInActivity
import com.example.chatku.databinding.FragmentHomeBinding
import com.example.chatku.mvvm.ChatAppViewModel
import com.google.firebase.auth.FirebaseAuth
import de.hdodenhof.circleimageview.CircleImageView



 @Suppress("DEPRECATION")
 class HomeFragment : Fragment() {


    lateinit var rvUsers : RecyclerView
    lateinit var userViewModel: ChatAppViewModel
    lateinit var homebinding: FragmentHomeBinding
    lateinit var fbauth : FirebaseAuth
    lateinit var toolbar : Toolbar
    lateinit var circleImageVIew : CircleImageView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homebinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        return  homebinding.root
    }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)

         userViewModel = ViewModelProvider(this).get(ChatAppViewModel::class.java)

         fbauth = FirebaseAuth.getInstance()

         toolbar = view.findViewById(R.id.toolbarMain)
         circleImageVIew = toolbar.findViewById(R.id.tlImage)

         homebinding.lifecycleOwner = viewLifecycleOwner


         val layoutManagerUsers = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
//         rvUsers.layoutManager = layoutManagerUsers

         homebinding.logOut.setOnClickListener {
             fbauth.signOut()

             startActivity(Intent(requireContext(), SignInActivity::class.java))
         }

         userViewModel.imageUrl.observe(viewLifecycleOwner, Observer {

             Glide.with(requireContext()).load(it).into(circleImageVIew)
         })

         circleImageVIew.setOnClickListener {

             view?.findNavController()?.navigate(R.id.action_homeFragment_to_settingFragment)
         }

         homebinding.keluar.setOnClickListener {
             fbauth.signOut()

             startActivity(Intent(requireContext(), SignInActivity::class.java))
         }

         homebinding.information.setOnClickListener {
             view.findNavController().navigate(R.id.information)
         }

//         homebinding.informasi.setOnClickListener {
//             view.findNavController().navigate(R.id.informasi)
//         }
//
//         homebinding.informasi.setOnClickListener {
//             view.findNavController().navigate(R.id.informasi)
//         }
     }

     }

