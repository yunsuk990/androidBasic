package com.example.navigationex02.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigationex02.R
import com.example.navigationex02.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        /* 로그인 버튼을 눌렀을 때 이벤트 처리 */
        binding.btnSignin.setOnClickListener {

            /*  NavController를 이용하여  navigate() 인자로 전달된 action의 destination으로 이동
                - homeFragment에서 signinFragment로 이동
            */
            findNavController().navigate(R.id.action_homeFragment_to_signinFragment)
        }

        /* 회원가입 버튼을 눌렀을 때 이벤트 처리 */
        binding.btnSignup.setOnClickListener {
            //homeFragment에서 signupFragment 화면으로 이동
            findNavController().navigate(R.id.action_homeFragment_to_signupFragment)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
