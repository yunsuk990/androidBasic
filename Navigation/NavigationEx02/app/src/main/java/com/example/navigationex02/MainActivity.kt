package com.example.navigationex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.navigationex02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    /* Fragment 공유 데이터 */
    val friendRepository = arrayListOf(
        FriendsData("홍길동", "HONG KIL DONG", R.drawable.avatar_1_raster, 1),
        FriendsData("홍길동-2", "HONG KIL DONG-2", R.drawable.avatar_2_raster, 2),
        FriendsData("홍길동-3", "HONG KIL DONG-3", R.drawable.avatar_3_raster, 3),
        FriendsData("홍길동-4", "HONG KIL DONG-4", R.drawable.avatar_4_raster, 4),
        FriendsData("홍길동-5", "HONG KIL DONG-5", R.drawable.avatar_5_raster, 5),
        FriendsData("홍길동-6", "HONG KIL DONG-6", R.drawable.avatar_6_raster, 6),
        FriendsData("홍길동-7", "HONG KIL DONG-7", R.drawable.avatar_7_raster, 7),
        FriendsData("홍길동-8", "HONG KIL DONG-8", R.drawable.avatar_8_raster, 8),
        FriendsData("홍길동-9", "HONG KIL DONG-9", R.drawable.avatar_9_raster, 9),
        FriendsData("홍길동-10", "HONG KIL DONG-10", R.drawable.avatar_10_raster, 10),
        FriendsData("홍길동-11", "HONG KIL DONG-11", R.drawable.avatar_11_raster, 11),
        FriendsData("홍길동-12", "HONG KIL DONG-12", R.drawable.avatar_12_raster, 12),
        FriendsData("홍길동-13", "HONG KIL DONG-13", R.drawable.avatar_13_raster, 13),
        FriendsData("홍길동-14", "HONG KIL DONG-14", R.drawable.avatar_14_raster, 14),
        FriendsData("홍길동-15", "HONG KIL DONG-15", R.drawable.avatar_15_raster, 15)
    )

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        navController = navHostFragment.navController

        /* navController를 ActionBar(AppBar)에 연결
             - AppBar에 UP 버튼(뒤로가기(<-)) 표시(Home fragment 제외)
             - UP 버튼을 눌렀을 때, 뒤로 이동하도록 하려면
               onSupportNavigateUp()을 오버라이드 해야 함
        */
        setupActionBarWithNavController(navController)

    }//end of onCreate

    /* AppBar에서 UP 버튼(<-) 눌렀을 때,
      뒤로 이동하려면 onSupportNavigateUp()를 오버라이드하여 구현  */
    override fun onSupportNavigateUp(): Boolean {
        //navController에서 navigateUp() 호출
        return  navController.navigateUp() ||   super.onSupportNavigateUp()
    }
}