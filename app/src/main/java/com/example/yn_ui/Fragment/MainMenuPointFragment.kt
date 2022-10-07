package com.example.yn_ui.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.yn_ui.MainActivity
import com.example.yn_ui.MainActivity2
import com.example.yn_ui.R
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainMenuPointFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainMenuPointFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_main_menu_point, container, false)
        var btn_accumulate:Button = view.findViewById(R.id.btn_accumulate)

        btn_accumulate.setOnClickListener{accClick()}

        var tadLayout :TabLayout = view.findViewById(R.id.tab_layout)
        tadLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
                when(tab?.position){
                    0 -> {
                        val Fragment1 =BlankFragment1()
                        replaceView(Fragment1)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }
        })
        return view
    }
    private fun replaceView(tab : Fragment) {
        var selectFragment: Fragment? = null
        selectFragment
    }

    private fun accClick(){
        val intentIntegrator = IntentIntegrator(activity)

        intentIntegrator.setPrompt("안내선 안에 QR코드를 맞추면 자동으로 인식됩니다.") //QR코드 스캔 액티비티 하단에 띄울 텍스트 설정
        intentIntegrator.setOrientationLocked(false)                       //화면회전을 막을 것인지 설정 (default 세로모드)
        intentIntegrator.setBeepEnabled(false)                             //QR코드 스캔 시 소리를 낼 지 설정
        activityResult.launch(intentIntegrator.createScanIntent())
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainMenuPointFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainMenuPointFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private val activityResult = this.registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        val data = result.data

        val intentResult: IntentResult? = IntentIntegrator.parseActivityResult(result.resultCode, data)
        if(intentResult != null){
            //QRCode Scan 성공
            if(intentResult.contents != null){
                //QRCode Scan result 있는경우
                Log.e("QR-data","QR-data: ${intentResult.contents}")
                //Toast.makeText(this, "인식된 QR-data: ${intentResult.contents}", Toast.LENGTH_LONG).show()

                try{
                    var tmpString = intentResult.contents

                    //다른 프래그먼트로 전환
//                   val fragment_blank = BlankFragment()
//                    val mActivity = activity as MainActivity
//                    mActivity.supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.fl_con, fragment_blank)
//                        .commit()

                    //액티비티로 전환
                    val intent = Intent(context, MainActivity2::class.java)
                    intent.putExtra("data", tmpString)
                    startActivity(intent)
                    activity?.finish()
                    
                } catch (e: Exception){
                    e.printStackTrace()
                }

            }else{
                //QRCode Scan result 없는경우
                Toast.makeText(activity, "인식된 QR-data가 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }else{
            //QRCode Scan 실패
            Toast.makeText(activity, "QR스캔에 실패했습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}