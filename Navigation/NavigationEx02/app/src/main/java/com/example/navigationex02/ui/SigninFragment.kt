package com.example.navigationex02.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigationex02.R
import com.example.navigationex02.databinding.FragmentSigninBinding

class SigninFragment : Fragment() {

    private var _binding: FragmentSigninBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        val view = binding.root

        //fragment_signin_signin_btn 버튼 클릭 이벤트 처리
        binding.fragmentSigninSigninBtn.setOnClickListener {
            findNavController().navigate(R.id.action_signinFragment_to_friendsFragment)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
