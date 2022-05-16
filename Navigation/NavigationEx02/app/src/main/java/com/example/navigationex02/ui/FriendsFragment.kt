package com.example.navigationex02.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationex02.FriendsAdapter
import com.example.navigationex02.MainActivity
import com.example.navigationex02.databinding.FragmentFriendsBinding

class FriendsFragment : Fragment() {

    private var _binding: FragmentFriendsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //FriendsFragment에 출력할 뷰를 생성하여 반환
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return  inflater.inflate(R.layout.fragment_friends, container, false)
        _binding = FragmentFriendsBinding.inflate(inflater, container, false)
        return binding.root
    }

    /* onActivityCreated()는 액티비티 생성이 완료된 순간 호출
       - 따라서 액티비티가 생성된 후 requireContext()를 통해 액티비티를 가져와
         액티비티에 선언된 친구 데이터(friendRepository)를 가져온다.
     */
    /*
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /* 액티비티에서 친구데이터(friendRepository)를 가져와서 items에 저장 */
        val items = (requireContext() as MainActivity).friendRepository

        /* 리사이클러뷰(recycle_friends)에 adapter와 layoutManager를 설정 */
        view?.let {
            binding.recycleFriends.adapter = FriendsAdapter(items)
            binding.recycleFriends.layoutManager = LinearLayoutManager(requireContext())
        }
    } */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* 액티비티에서 친구데이터(friendRepository)를 가져와서 items에 저장 */
        val items = (requireContext() as MainActivity).friendRepository

        /* 리사이클러뷰(recycle_friends)에 adapter와 layoutManager를 설정 */
        view?.let {
            binding.recycleFriends.adapter = FriendsAdapter(items)
            binding.recycleFriends.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
