package com.example.navigationex01.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationex01.R
import com.example.navigationex01.databinding.FragmentBBinding


class BFragment : Fragment() {
    private var _binding: FragmentBBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnBToC.setOnClickListener {
            findNavController().navigate(R.id.action_BFragment_to_CFragment)
        }

        binding.btnBToD.setOnClickListener {
            findNavController().navigate(R.id.action_BFragment_to_DFragment)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}