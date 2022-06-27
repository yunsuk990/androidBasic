package com.example.freshlauctionapp.result

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.freshlauctionapp.databinding.ListItemFreshBinding
import com.example.freshlauctionapp.model.FreshData

/* ResultFragment에서 검색 결과(DB)를 리사이클러뷰에 보여주는 어댑터  */
class ResultAdapter() : RecyclerView.Adapter<ItemViewHolder>() {

    //검색한 데이터(List<FreshData)를 저장할 list
    var freshList: List<FreshData> = ArrayList()

    /* 뷰홀더를 생성하여 반환 */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //list_item_fresh 뷰 inflate
        val binding = ListItemFreshBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    //뷰홀더에 데이터 바인딩(bindItems() 함수를 호출)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItems(freshList[position])
    }

    //어댑터에서 관리할 아이템 갯수를 반환
    override fun getItemCount() = freshList.size
}//end of ResultAdapter

/* SaveFragment에서 DB에 저장된 검색 결과를 리사이클러뷰에 데이터를 보여주는 어댑터
   - 검색된 결과(SAVE_ID)를 KEY로 FreshData 테이블에서 해당 id를 찾아 화면에 출력
   - SAVE_ID로 저장된 모든 데이터를 리사이클러뷰의 랜더링 사이즈 만큼 씩 보여줌 */
class SaveAdpater : PagingDataAdapter<FreshData, ItemViewHolder>(DIFF_CALLBACK) {
    /* 뷰홀더를 생성하여 반환 */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemFreshBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    //뷰홀더에 데이터 바인딩(bindItems() 함수를 호출)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItems(getItem(position))//(getItem(position) == list<FreshData>
        Log.d("GETITEM", "${getItem(position)}, SaveAdpater")
        //D/GETITEM: FreshData(id=301, saveId=7, lname=과실류, mname=사과, sname=후지....SaveAdpater
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FreshData>() {
            override fun areItemsTheSame(oldConcert: FreshData, newConcert: FreshData): Boolean =
                oldConcert.id == newConcert.id

            override fun areContentsTheSame(oldConcert: FreshData, newConcert: FreshData): Boolean =
                oldConcert.id == newConcert.id
        }
    }
}//end of SaveAdpater

//뷰홀더 클래스 선언
class ItemViewHolder(private val binding: ListItemFreshBinding) : RecyclerView.ViewHolder(binding.root) {
    // 검색한 경락가격정보를 아이템뷰(list_item_fresh)에 바인딩하는 함수
    fun bindItems(fresh: FreshData?) {
        fresh?.let {
            binding.txtGongpanInfo.text = fresh.cprName//법인명
            binding.txtUnit.text =
                "${fresh.lname}> ${fresh.mname} > ${fresh.sname} (등급:${fresh.grade})"
            binding.txtMinPrice.text = "최저: " + String.format("%,d", fresh.minPrice.toInt())
            binding.txtMaxPrice.text = "최고: " + String.format("%,d", fresh.maxPrice.toInt())
            binding.txtAvgPrice.text = "거래량: " + String.format("%,d", fresh.tradeAmt.toInt()) + fresh.uname

        }
    }
}//end of ItemViewHolder
