package com.example.roompagingex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roompagingex01.database.AppDatabase
import com.example.roompagingex01.database.NoteEntity
import com.example.roompagingex01.databinding.ActivityMainBinding
import com.example.roompagingex01.dialog.NoteCreateDialog
import com.example.roompagingex01.list.NoteAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    //noteDao 가져오기
    val noteDao by lazy { AppDatabase.getDatabase(this).noteDao() }

    //CoroutineContext 설정
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /* noteAdapter 생성 및 deleteCallback(it)을 받아 처리할 콜백 작성
           - NoteAdpater에서 삭제 버튼을 클릭하면 삭제를 자신을 만든 MainAcitivity에
             deleteCallback(it)을 통해 위임함
           - 따라서 deleteCallback(it)을 받아 노트를 삭제할 콜백을 람다식으로 작성
         */
        val noteAdapter = NoteAdapter(noteDao) { note ->
            Log.d("TAG", "MainActivity-delete")

            /* 노트 Delete */
            launch(coroutineContext + Dispatchers.IO) {
                noteDao.deleteNots(note)
            }
        }
    /* NoteAdapter()의 마지막 인자가 람다식인 경우 람다식을 소괄호 바깥으로 분리
        val noteAdapter = NoteAdapter(noteDao, { note ->
            launch(coroutineContext + Dispatchers.IO) {
                noteDao.deleteNots(note)
            }
        })

    */
        /* RecycleView Initialize*/
        binding.recycleNote.adapter = noteAdapter
        binding.recycleNote.layoutManager = LinearLayoutManager(this)

        /* PagingData 빌드 및 생성(LiveData)
           - 페이징 구성을 정의한 PagingConfig를 기반으로 LiveData<PagingData>를 생성
             .PagingConfig(pageSize, initialKey,...)
              pageSize: 각 페이지에 로드할 데이터 수(필수 매개변수)
           - NoteDao에 정의한 DataSource.Factory를 이용하여 생성한 DataSource 를,
             asPagingSourceFactory()를 이용하여 LiveData<PagingData> 형태로 변환
        */
        val pagingData = Pager(
            PagingConfig(20), 1,
            noteDao.selectNotes().asPagingSourceFactory(Dispatchers.IO)
        )

        /* pagingData(LiveData)에 observe() 메서드로 Observer 객체 등록(데이터 변경시 자동 호출)
           - LiveData를 관찰하여 PageData에 변경이 있을 시 Adapter에 전달
           - owner : Observer를 제어하는 LifecycleOwner
           - Observer : LiveDta 로부터 이벤트를 수신하는 Observer 객체(데이터가 변경되면 호출됨)
        */
        pagingData.liveData.observe(this, Observer {
            Log.d("TAG", "MainActivity-observe()")
            /* 변경된 pagingData를 NoteAdapter에 전달
               - pagingData를 submit하면 NoteAdapter 의 DIFF_CALLBACK은
                 기존에 pagingData가 존재하면 그 차이를 비교한 후 리사이클러뷰를 새로운 페이지로 갱신
               - MainActivity의 Lifecycle이 끝날 때 코루틴 작업이 자동으로 취소
           */
            lifecycleScope.launch {
                noteAdapter.submitData(it)
            }
        })

        /* "+"(fab_add_note) 버튼을 클릭하면 NoteCreateDialog 출력  */
        binding.fabAddNote.setOnClickListener {
            Log.d("TAG", "MainActivity-NoteCreateDialog()")
            NoteCreateDialog().show(supportFragmentManager, null)
        }
    }//end of onCreate
}