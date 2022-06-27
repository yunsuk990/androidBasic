package com.example.freshlauctionapp.result

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.freshlauctionapp.model.Fruits
import com.example.freshlauctionapp.result.ResultAdapter
import com.example.freshlauctionapp.database.DatabaseModule
import com.example.freshlauctionapp.databinding.FragmentResultBinding
import com.example.freshlauctionapp.result.ResultViewModel

//공공 DB에 검색 요청하고, 결과를 Local DB에 저장 
class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    /* 데이터베이스를 가져오기 */
    val database by lazy {
        DatabaseModule.getDatabase(requireContext())
    }

    //resultViewModel 참조
    val resultViewModel by lazy {
        ViewModelProvider(this).get(ResultViewModel::class.java)
    }

    /* Result 화면을 위한 resultAdpater 생성 */
    val resultAdpater = ResultAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //fragment_result 뷰 inflate
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //todo5 네트워크 call을 수행 - 서버에 검색 요청

        /* SearchFragment에서 전달한 arguments에서 검색 요청에 필요한
           SELECT_FRUIT, SELECT_DATE, RESULT_AMOUNT 추출 */
        val selectFruit = arguments?.getString("SELECT_FRUIT")
        val selectDate = arguments?.getString("SELECT_DATE")
        val resultAmount = arguments?.getString("RESULT_AMOUNT")

        //경락가격정보 서버에 검색 요청(resultViewModel.loadDataFromURL() 함수 호출)
        if (selectDate != null && selectFruit != null && resultAmount != null) {
            /* 경락가격정보 서버에 검색 요청
               - resultViewModel.loadDataFromURL() 함수 호출   */
            resultViewModel.loadDataFromURL(selectDate, selectFruit, resultAmount)

            //서버에서 응답한 응답 데이터의 변화를 감지하기 위해 LiveData(resultList())에 observe 설정
            resultViewModel.resultList().observe(viewLifecycleOwner, Observer {
                //검색한  List<FreshData>를  resultAdpater에 전달
                resultAdpater.freshList = it

                Log.i("FRESH", "it: $it")

                /* resultAdpater에 데이터에 변동됨을 알려주기 */
                resultAdpater.notifyDataSetChanged()

                /* 로딩은 사라지고 */
                binding.progressLoader.visibility = View.GONE
                /* 저장버튼을 보여주기 */
                binding.flotingSave.visibility = View.VISIBLE
            })//end of observe

            /* 에러가 발생하면 toast를 이용하여 에러 메시지 띄우기 */
            resultViewModel.notificationMsg.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                binding.progressLoader.visibility = View.GONE
            })

            /*  리사이클러뷰에 구분선 설정 */
            binding.recycleReesult.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            );

            /* 리사이클러뷰에 어댑터 및 레이아웃메니저 설정 */
            binding.recycleReesult.adapter = resultAdpater
            binding.recycleReesult.layoutManager = LinearLayoutManager(requireContext())

            /* 저장버튼을 눌렀을때 */
            binding.flotingSave.setOnClickListener {

                /* 데이터가 없거나 null일경우?*/
                if (resultViewModel.resultList().value.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "저장할 데이터가 없습니다.", Toast.LENGTH_LONG).show()
                } else {
                    /* 데이터가 있으면 DB에 저장하기 위해 resultViewModel.saveResult() 함수 호출
                      - saveResult(context, saveName)
                      - saveName: 검색일자+과일명+검색결과("2022-06-01 사과 검색결과")
                   * */
                    resultViewModel.saveResult(
                        requireContext(),
                        "${selectDate} ${Fruits.valueOf(selectFruit).holder} 검색결과"
                    )
                    Toast.makeText(requireContext(), "데이터가 저장되었습니다.", Toast.LENGTH_LONG).show()
                }
            }//end of view.floting_save.setOnClickListener
        }//end of if
    }//end of onViewCreated

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
