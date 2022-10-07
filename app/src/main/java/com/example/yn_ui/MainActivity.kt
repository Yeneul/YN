package com.example.yn_ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.yn_ui.Fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_main_menu_more.view.*


class MainActivity : FragmentActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private final var FINISH_INTERVAL_TIME: Long = 2000
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<RelativeLayout>(R.id.toolbar) //툴바
        val homeBtn = findViewById<ImageButton>(R.id.btnHome) //툴바에있는 백버튼
        val menuSearch = findViewById<ImageButton>(R.id.search) //내비게이션바 메뉴 가운데에 있는 검색 버튼
        val search_window = findViewById<LinearLayout>(R.id.search_window) //분리수거 검색창

        val bnv_main = findViewById<View>(R.id.bottomNavigationView) as BottomNavigationView
        bnv_main.setOnNavigationItemSelectedListener(this)

        bnv_main.selectedItemId = R.id.menu_home

        homeBtn.setOnClickListener { //툴바에 있는 백버튼 클릭 이벤트
            val fragmentC = MainMenuHomeFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fl_con,fragmentC, "home")
            val bnv_main = findViewById<View>(R.id.bottomNavigationView) as BottomNavigationView
            bnv_main.setOnNavigationItemSelectedListener(this)
            bnv_main.selectedItemId = R.id.menu_home

            toolbar.setVisibility(View.GONE)//툴바 안보이게
             }

        menuSearch.setOnClickListener {  //내비게이션바 메뉴 가운데에 있는 검색 버튼 클릭 이벤트
            if (search_window.getVisibility() == View.VISIBLE) {
                search_window.setVisibility(View.GONE)
            }else {
                search_window.setVisibility(View.VISIBLE)
            }
        }
    }
    // 프래그먼트 내부에서 다른 프래그먼트 이동하는 함수
    fun openFragmentOnFrament(int: Int){
        val toolbar = findViewById<RelativeLayout>(R.id.toolbar) //툴바
        val title = findViewById<TextView>(R.id.title) //툴바제목
        val transaction = supportFragmentManager.beginTransaction()
        when(int){
            1 -> { //MainMenuRecycleFragment에서 두번째 버튼(sd_btn2) 클릭 이벤트
                supportFragmentManager.popBackStackImmediate("recycle_btn2", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                val fragmentF = SeparateCollectionButton2Fragment()
                transaction.replace(R.id.fl_con,fragmentF)
                transaction.addToBackStack("recycle_btn2")

                toolbar.setVisibility(View.GONE)//툴바 안보이게
            }
            2 ->{ //MainMenuMoreFragment에서 공지사항 버튼(notice_btn) 클릭 이벤트
                supportFragmentManager.popBackStackImmediate("more_btn1", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                val fragmentG = NoticeFragment()
                transaction.replace(R.id.fl_con,fragmentG, "more_btn1")
                transaction.addToBackStack("more_btn1")

                toolbar.setVisibility(View.GONE)//툴바 안보이게
            }
            3 ->{ //MainMenuMoreFragment, MainMenuPointFragment, MainMenuRecycleFragment, MainMenuSpotFragment에서 백버튼(btnhome) 클릭했을때 메인(home) 화면출력
                val fragmentC = MainMenuHomeFragment()
                transaction.replace(R.id.fl_con,fragmentC, "home")
                val bnv_main = findViewById<View>(R.id.bottomNavigationView) as BottomNavigationView
                bnv_main.setOnNavigationItemSelectedListener(this)
                bnv_main.selectedItemId = R.id.menu_home

                toolbar.setVisibility(View.GONE)//툴바 안보이게
            }
            4 -> { //MainMenuMoreFragment에서 설정 버튼(set_btn) 클릭 이벤트
                supportFragmentManager.popBackStackImmediate("more_btn3", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                val fragmentH = SetFragment()
                transaction.replace(R.id.fl_con,fragmentH, "more_btn3")
                transaction.addToBackStack("more_btn3")

                toolbar.setVisibility(View.GONE)//툴바 안보이게
            }
            5 ->{ //NoticeFragment, SetFragment에서 백버튼(btnback) 클릭했을때 더보기 화면출력
                supportFragmentManager.popBackStackImmediate("more", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                val fragmentE = MainMenuMoreFragment()
                transaction.replace(R.id.fl_con,fragmentE, "more")
                transaction.addToBackStack("more")

                toolbar.setVisibility(View.VISIBLE) //툴바 보이게
                title.text = "더보기" //툴바 제목 변경
            }
            6 ->{ //SeparateCollectionButton2Fragment에서 백버튼(btnback) 클릭했을때 분리수거 가이드 화면출력
                supportFragmentManager.popBackStackImmediate("recycle", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                val fragmentB = MainMenuRecycleFragment()
                transaction.replace(R.id.fl_con,fragmentB, "recycle")
                transaction.addToBackStack("recycle")

                toolbar.setVisibility(View.VISIBLE) //툴바 보이게
                title.text = "분리수거 가이드" //툴바 제목 변경
            }
        }
        transaction.commit()
        transaction.isAddToBackStackAllowed
    }
    //내비게이션바 아이템에 따라 화면출력, 스택관리, 상태바변경을 해주는 함수
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val toolbar = findViewById<RelativeLayout>(R.id.toolbar) //툴바
        val title = findViewById<TextView>(R.id.title) //툴바제목

        when(p0.itemId){
            R.id.menu_spot ->{ //내비게이션바에 있는 아이템이 menu_spot일때
                supportFragmentManager.popBackStackImmediate("spot", FragmentManager.POP_BACK_STACK_INCLUSIVE) //스택저장
                val fragmentA = MainMenuSpotFragment()
                transaction.replace(R.id.fl_con,fragmentA, "spot") //화면출력
                transaction.addToBackStack("spot") //백스택추가

                toolbar.setVisibility(View.VISIBLE) //툴바 보이게
                title.text = "분리수거함 찾기" //툴바 제목 변경

                window.setStatusBarColor(Color.WHITE) //상태바 배경색 WHITE로 변경
                window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) //상태바 아이템 색상 검정색으로 변경
            }
            R.id.menu_recycle -> { //내비게이션바에 있는 아이템이 menu_recycle일때
                supportFragmentManager.popBackStackImmediate("recycle", FragmentManager.POP_BACK_STACK_INCLUSIVE) //스택저장
                val fragmentB = MainMenuRecycleFragment()
                transaction.replace(R.id.fl_con,fragmentB, "recycle") //화면출력
                transaction.addToBackStack("recycle") //백스택추가

                toolbar.setVisibility(View.VISIBLE) //툴바 보이게
                title.text = "분리수거 가이드" //툴바 제목 변경

                window.setStatusBarColor(Color.WHITE) //상태바 배경색 WHITE로 변경
                window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) //상태바 아이템 색상 검정색으로 변경
            }
            R.id.menu_home -> { //내비게이션바에 있는 아이템이 menu_home일때
                supportFragmentManager.popBackStackImmediate("home", FragmentManager.POP_BACK_STACK_INCLUSIVE) //스택저장
                val fragmentC = MainMenuHomeFragment()
                transaction.replace(R.id.fl_con,fragmentC, "home") //화면출력
                transaction.addToBackStack("home") //백스택추가

                toolbar.setVisibility(View.GONE)//툴바 안보이게

                window.statusBarColor = ContextCompat.getColor(this, R.color.blue) //상태바 배경색 blue로 변경
                window.decorView.setSystemUiVisibility(0) //상태바 아이템 색상 하얀색으로 변경
            }
            R.id.menu_point -> { //내비게이션바에 있는 아이템이 menu_point일때
                supportFragmentManager.popBackStackImmediate("point", FragmentManager.POP_BACK_STACK_INCLUSIVE) //스택저장
                val fragmentD = MainMenuPointFragment()
                transaction.replace(R.id.fl_con,fragmentD, "point") //화면출력
                transaction.addToBackStack("point") //백스택추가

                toolbar.setVisibility(View.VISIBLE) //툴바 보이게
                title.text = "포인트" //툴바 제목 변경

                window.setStatusBarColor(Color.WHITE) //상태바 배경색 WHITE로 변경
                window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) //상태바 아이템 색상 검정색으로 변경
            }
            R.id.menu_more -> { //내비게이션바에 있는 아이템이 menu_more일때
                supportFragmentManager.popBackStackImmediate("more", FragmentManager.POP_BACK_STACK_INCLUSIVE) //스택저장
                val fragmentE = MainMenuMoreFragment()
                transaction.replace(R.id.fl_con,fragmentE, "more") //화면출력
                transaction.addToBackStack("more") //백스택추가

                toolbar.setVisibility(View.VISIBLE) //툴바 보이게
                title.text = "더보기" //툴바 제목 변경

                window.setStatusBarColor(Color.WHITE) //상태바 배경색 WHITE로 변경
                window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) //상태바 아이템 색상 검정색으로 변경
            }
        }
        //transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
        transaction.isAddToBackStackAllowed
        return true
    }
    //백스택을 했을때 다음과 같은 화면과 아이템이 활성화 된다.
    fun updateBottomMenu(navigation: BottomNavigationView) {
        val toolbar = findViewById<RelativeLayout>(R.id.toolbar) //툴바
        val tag1: Fragment? = supportFragmentManager.findFragmentByTag("spot")
        val tag2: Fragment? = supportFragmentManager.findFragmentByTag("recycle")
        val tag3: Fragment? = supportFragmentManager.findFragmentByTag("home")
        val tag4: Fragment? = supportFragmentManager.findFragmentByTag("point")
        val tag5: Fragment? = supportFragmentManager.findFragmentByTag("more")

        if(tag1 != null && tag1.isVisible()) navigation.getMenu().findItem(R.id.menu_spot).setChecked(true)
        if(tag2 != null && tag2.isVisible()) navigation.getMenu().findItem(R.id.menu_recycle).setChecked(true)
        if(tag3 != null && tag3.isVisible()) {
            navigation.getMenu().findItem(R.id.menu_home).setChecked(true)
            window.statusBarColor = ContextCompat.getColor(this, R.color.blue) //상태바 배경색 blue로 변경
            window.decorView.setSystemUiVisibility(0) //상태바 아이템 색상 하얀색으로 변경
            toolbar.setVisibility(View.GONE)//툴바 안보이게

        }
        if(tag4 != null && tag4.isVisible()) navigation.getMenu().findItem(R.id.menu_point).setChecked(true)
        if(tag5 != null && tag5.isVisible()) navigation.getMenu().findItem(R.id.menu_more).setChecked(true)
    }
    //뒤로 버튼 한번더 클릭하면 종료
    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 1) { //스택이 한개일때 종료 -> 스택이 1이여야 메인화면에서 앱을 종료시킬수 있다.
            val tempTime = System.currentTimeMillis()
            val intervalTime = tempTime - backPressedTime
            if (!(0 > intervalTime || FINISH_INTERVAL_TIME < intervalTime)) {
                finishAffinity()
                System.runFinalization()
                System.exit(0)
            } else {
                backPressedTime = tempTime
                Toast.makeText(this, "'뒤로' 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
                return
            }
        }
        super.onBackPressed()
        val bnv = findViewById<View>(R.id.bottomNavigationView) as BottomNavigationView
        updateBottomMenu(bnv)
    }
}