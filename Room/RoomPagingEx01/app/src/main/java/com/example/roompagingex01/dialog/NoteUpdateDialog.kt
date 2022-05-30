package com.example.roompagingex01.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.roompagingex01.MainActivity
import com.example.roompagingex01.database.NoteEntity
import com.example.roompagingex01.databinding.DialogNoteCreateBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

//Update 다이얼로그 프래그먼트
class NoteUpdateDialog : DialogFragment(), CoroutineScope {
    private var _binding: DialogNoteCreateBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    //CoroutineContext 설정
    override val coroutineContext: CoroutineContext get() = Dispatchers.Main

    //noteDao
    private val noteDao by lazy { (requireContext() as MainActivity).noteDao }

    //noteId
    private val noteId by lazy {
        arguments?.getInt("NOTE_KEY") ?: throw IllegalArgumentException("전달받은 Note 값이 없습니다.")
    }

    /* 추가/수정 다이얼로그 위한 레이아웃(dialog_note_create.xml) inflate */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogNoteCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* Coroutine 설정 */
        GlobalScope.launch(Dispatchers.Main) {

            /* IO 스레드에서 데이터 조회 */
            val noteResult = withContext(Dispatchers.IO) {
                Log.d("TAG", "NoteUpdateDialog-selectNote 쿼리")
                noteDao.selectNote(noteId)
            }

            //조회한 데이터를 해당 뷰에 표시
            binding.editNewNote.setText(noteResult.noteContent)
        }//end of GlobalScope.launch(Dispatchers.Main)

        /* 추가/수정 버튼 누른경우*/
        binding.btnNewNote.setOnClickListener {
            Log.d("TAG", "NoteUpdateDialog-업데이트")
            /* update 작업을 위한 코루틴 수행 */
            launch(Dispatchers.Main) {
                withContext(Dispatchers.IO) {
                    noteDao.updateNote(
                        NoteEntity(
                            noteIdx = noteId,
                            noteContent = binding.editNewNote.text.toString()
                        )
                    )
                }
                dismiss()//다이얼로그 종료
            }//end of launch(Dispatchers.Main)
        }//end of binding.btnNewNote.setOnClickListener
    }//end of onViewCreated

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}