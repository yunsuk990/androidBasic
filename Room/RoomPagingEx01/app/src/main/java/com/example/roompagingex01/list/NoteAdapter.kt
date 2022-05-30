package com.example.roompagingex01.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roompagingex01.database.NoteDao
import com.example.roompagingex01.database.NoteEntity
import com.example.roompagingex01.databinding.ListItemNoteBinding
import com.example.roompagingex01.dialog.NoteUpdateDialog

/* PagingData(RecyclerView) Adapter(NoteAdapter)선언
  - RecyclerView에서 페이징 된 데이터를 받으려면 어댑터를 구현해야 하며,
    이를 위해 Paging 라이브러는 PagingDataAdapter 클래스를 제공
  - PagingDataAdapter: 페이징 데이터(PagingData)를 RecyclerView에 표시하기 위한 기본 클래스
  - 선언 시 NoteAdapter가 PagingDataAdapter를 상속하고, 초기화시 DIFF_CALLBACK 객체를 넘겨줌
  - PagingData는 LiveData로 observe 중, 변경이 발생할 때마다 submitData()를 이용하여
    변경된 pagingData를 이곳 NoteAdapter로 전달되며, 어댑터는 DiffUtil 객체를 이용하여
    전달된 new PagingData와 기존 old PagingData를 비교하여 UI 갱신(변경된 PagingData만 갱신)

   ** RecyclerView를 위한 NoteAdapter를 PagingDataAdapter를 상속하여 구현하고,
      PagingData가 변경될 때, submitData()를 이용하여 PagingData를 어댑터에 전달하면
      어댑터가 알아서 UI를 갱신함

    **  NoteAdapter는 PagingDataAdapter를 상속하여 NoteEntity 타입의 리스트에 대한
        RecyclerView 어댑터를 제공하고, ItemViewHolder를 뷰 홀더로 사용
 */
class NoteAdapter(val noteDao: NoteDao, val deleteCallback: (note: NoteEntity) -> Unit) :
                     PagingDataAdapter<NoteEntity, NoteAdapter.ItemViewHolder>(DIFF_CALLBACK) {

    //뷰홀더에 데이터를 바인딩
    override fun onBindViewHolder(holder: NoteAdapter.ItemViewHolder, position: Int) {
        /* 뷰홀더에 데이터를 바인딩하는 bindItems() 메서드 호출
           - getItem(position) 메소드를 통하여 데이터를 가져온다
         */
        holder.bindItems(getItem(position))
    }//end of override fun onBindViewHolder()

    /*뷰홀더 생성하여 반환*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //리사이클러뷰에 출력할 아이템 레이아웃(list_item.xml)의 binding(itemView) 객체 생성
        val binding = ListItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        // ViewHolder 생성
        return ItemViewHolder(binding)
    }//end of override fun onCreateViewHolder()

    /* ItemViewHolder 클래스 선언
       - itemView를 인자로 받아 mapping    */
    inner class ItemViewHolder(private val binding: ListItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        /* bindItems() 함수 선언
           - 데이터 바인딩  */
        fun bindItems(note: NoteEntity?) {
            /* note가 있을 경우에만 바인딩 */
            note?.let {
                /* itemView(list_item_note.xml)의 txt_note_content 뷰에
                   note 테이블의 noteContent 컬럼값을 표시    */
                binding.txtNoteContent.text = it.noteContent

                /* "수정" 버튼 클릭시 업데이트 */
                //NoteUpdateDialog
                binding.btnUpdateNote.setOnClickListener { _ ->
                    Log.d("TAG", "ItemViewHolder-수정버튼 클릭시 ")
                    //추가/수정 다이얼로그 생성
                    val dialog = NoteUpdateDialog().apply {
                        arguments = Bundle().apply { putInt("NOTE_KEY", it.noteIdx!!) }
                    }

                    //프래그먼트 다이얼로그(NoteUpdateDialog) 출력
                    dialog.show(
                        (itemView.context as AppCompatActivity).supportFragmentManager,
                        null
                    )
                }//end of binding.btnUpdateNote.setOnClickListener

                /* "삭제" 버튼 클릭시 삭제 */
                binding.btnDeleteNote.setOnClickListener { _ ->
                    Log.d("TAG", "ItemViewHolder-삭제버튼 클릭시 ")
                    /* 삭제할 Entity를 callback으로 전달(삭제를 MainActivity에 위임)  */
                    deleteCallback(it)
                }//end of binding.btnDeleteNote.setOnClickListener
            }//end of let{}
        }//end of fun bindItems()
    }//end of inner class ItemViewHolder()

    /* DiffUtil Callback 구현
       - page가 load될 때 callback을 수신하고,
         background thread에서 DiffUtil을 사용해, 두 객체간의 차이점을 찾고
         업데이트 되어야 할 pagingData를 반환
         (기존 pagingData와 new pagingData를 비교하여 다른 경우, 리사이클러뷰를 새로운 페이지로 갱신)
       - areItemsTheSame() : 두 객체(pagingData)가 동일한 객체인지 확인
       - areContentsTheSame() : 두 객체가 내용(Contents)까지 동일한지 확인
     */
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NoteEntity>() {
            override fun areItemsTheSame(oldConcert: NoteEntity, newConcert: NoteEntity): Boolean =
                oldConcert.noteIdx == newConcert.noteIdx  //id 비교

            override fun areContentsTheSame(oldConcert: NoteEntity, newConcert: NoteEntity): Boolean =
                oldConcert.noteContent == newConcert.noteContent //noteContent 비교
        }
    }//end of companion object
}//end of NoteAdapter