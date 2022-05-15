package com.example.fragmentexam01

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FragmentFirst: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container,false)
        view.findViewById<Button>(R.id.btn_go_second).setOnClickListener {
            (activity as MainActivity).onReplaceFragment(FragmentSecond())
        }
        return view
    }
}