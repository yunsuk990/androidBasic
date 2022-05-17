package com.example.fragmentexam02

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentexam02.databinding.FragmentListBinding

class ListFragment: Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "ListFragment - onCreate() called")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "ListFragment - onAttach() called")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "ListFragment - onCreateView() called")
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}