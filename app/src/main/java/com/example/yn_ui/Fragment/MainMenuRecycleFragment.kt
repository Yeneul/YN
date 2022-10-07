package com.example.yn_ui.Fragment

import android.content.ClipData
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.view.menu.MenuView
import androidx.core.content.ContextCompat
import com.example.yn_ui.MainActivity
import com.example.yn_ui.R
import com.google.android.material.bottomnavigation.BottomNavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainMenuRecycleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainMenuRecycleFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_main_menu_recycle, container, false)

        val mActivity = activity as MainActivity //메인액티비티 가져오기
        val sd_btn2: Button = rootView.findViewById(R.id.sd_btn2) //분리수거 가이드 화면에서 두번째 버튼
        sd_btn2.setOnClickListener { //분리수거 가이드 화면에서 두번째 버튼 이벤트
            mActivity.openFragmentOnFrament(1) //메인액티비티에서 openFragmentOnFrament함수에 1을 실행 ->분리배출의 핵심 4가지 화면출력
        }

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainMenuRecycleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainMenuRecycleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}