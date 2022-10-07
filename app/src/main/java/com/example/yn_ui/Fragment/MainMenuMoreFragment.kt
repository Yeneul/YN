package com.example.yn_ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import com.example.yn_ui.MainActivity
import com.example.yn_ui.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainMenuMoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainMenuMoreFragment : Fragment() {
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
        val rootView = inflater.inflate(R.layout.fragment_main_menu_more, container, false)

        val mActivity = activity as MainActivity
        val notice_btn: Button = rootView.findViewById(R.id.notice_btn)//더보기화면에서 1번째 버튼인 공지사항버튼
        notice_btn.setOnClickListener { //공지사항버튼 클릭 이벤트
            mActivity.openFragmentOnFrament(2) //메인액티비티에서 openFragmentOnFrament함수에 2을 실행 -> 공지사항화면 출력
        }
        val set_btn: Button = rootView.findViewById(R.id.set_btn) //더보기화면에서 3번째 버튼인 설정버튼
        set_btn.setOnClickListener { //설정버튼 클릭 이벤트
            mActivity.openFragmentOnFrament(4)//메인액티비티에서 openFragmentOnFrament함수에 4을 실행 -> 설정화면 출력
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
         * @return A new instance of fragment MainMenuMoreFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainMenuMoreFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}