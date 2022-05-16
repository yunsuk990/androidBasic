package com.example.navigationex01.UI


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationex01.R
import com.example.navigationex01.databinding.FragmentCBinding


class CFragment : Fragment() {

    private var _binding: FragmentCBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnCToD.setOnClickListener {
            findNavController().navigate(R.id.action_CFragment_to_DFragment)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}