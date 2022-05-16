package com.example.navigationex02.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigationex02.FriendsData
import com.example.navigationex02.MainActivity
import com.example.navigationex02.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //DetailFragment에 출력할 뷰를 생성하여 반환
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    /* onActivityCreated()는 액티비티 생성이 완료된 순간 호출
      - 따라서 액티비티가 생성된 후 requireContext()를 통해 액티비티를 가져와
         arguments로 전달받은 인덱스값(idx)을 이용하여 친구 데이터(friendRepository)에서
         해당 학생 정보를 가져온다.    */
    /*
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /* arguments에 idx 값이 있다면?*/
        arguments?.getInt("idx")?.let {

            /* 학생의 상세 정보를 optional로 가져와서 bindFriend(it) 함수를 호출하여 view에 데이터 바인딩 */
            (requireContext() as MainActivity).friendRepository.getOrNull(it)?.let {
                bindFriend(it)
            }
        }
    }//end of onActivityCreated
*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* arguments에 idx 값이 있다면?*/
        arguments?.getInt("idx")?.let {

            /* 학생의 상세 정보를 optional로 가져와서 bindFriend(it) 함수를 호출하여 view에 데이터 바인딩 */
            (requireContext() as MainActivity).friendRepository.getOrNull(it)?.let {
                bindFriend(it)
            }
        }
    }
    /* 친구 데이터를 뷰에 바인딩
       - view == getView()로서 onCreateView()에서 인플레이션한 뷰(fragment_detail.xml)
    */
    private fun bindFriend(item: FriendsData) {
        view?.let {
            //==requireView().let {
            binding.imageFriend.setImageResource(item.imageResource)
            binding.txtFriendName.text = item.firendName
        }
    }//end of bindFriend

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}//end of DetailFragment
