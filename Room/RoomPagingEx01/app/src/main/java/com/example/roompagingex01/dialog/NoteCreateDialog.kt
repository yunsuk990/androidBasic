package com.example.roompagingex01.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.roompagingex01.MainActivity
import com.example.roompagingex01.database.NoteEntity
import com.example.roompagingex01.databinding.DialogNoteCreateBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

//Create 다이얼로그 프래그먼트
class NoteCreateDialog() : DialogFragment(), CoroutineScope {
    private var _binding: DialogNoteCreateBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    //CoroutineContext 설정
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    //noteDao 가져오기
    private val noteDao by lazy { (requireContext() as MainActivity).noteDao }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /* 추가/수정 다이얼로그 위한 레이아웃(dialog_note_create.xml) inflate */
        _binding = DialogNoteCreateBinding.inflate(inflater, container, false)

        /* 추가/수정 버튼 클릭시 데이터베이스에 값을 저장하는 이벤트 처리 */
        binding.btnNewNote.setOnClickListener {
            Log.d("TAG", "NoteCreateDialog-insert 버튼 클릭")
            /* insert */
            launch(coroutineContext + Dispatchers.IO) {
                /* 데이터를 데이터베이스에 insert */
                createNote(NoteEntity(
                    noteContent = binding.editNewNote.text.toString()
                ))
                
                withContext(Dispatchers.Main) {
                    /* Toast 메시지 출력후 dismiss(다이얼로그 종료)하는 UI 작업 수행 */
                    Toast.makeText(requireContext(), "데이터가 저장되었습니다.", Toast.LENGTH_SHORT).show()
                    dismiss()//다이얼로그 종료
                }//end of  withContext(Dispatchers.Main)
            }//end of launch(coroutineContext + Dispatchers.IO)
        }
        return binding.root
    }//end of onCreateView

    /* 데이터베이스에 insert 작업을 위한 suspend 함수(createNote) 선언
       - insert 작업을 IO스레드에서 수행
     */
    private suspend fun createNote(note: NoteEntity) = withContext(Dispatchers.IO) {
        //insert
        Log.d("TAG", "NoteCreateDialog-createNote-insert")
        noteDao.insertNotes(note)
    }//end of withContext(Dispatchers.IO)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}