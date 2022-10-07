package com.example.yn_ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.yn_ui.databinding.ActivityMain2Binding
import kotlinx.android.synthetic.main.activity_main2.*
import org.json.JSONObject


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            if (intent.hasExtra("data")) {
                val data = intent.getStringExtra("data")
                val json: JSONObject? = JSONObject(data)

                qrtext_loc.text = "위치 : " + json!!.getString("위치")
                qrtext_wedo.text = "위도 : " + json!!.getString("위도")
                qrtext_gyungdo.text = "경도 : " + json!!.getString("경도")
                qrtext_thing.text = "종류 : " + json!!.getString("종류")
            }
        } catch (e: Exception){
            Toast.makeText(this@MainActivity2, "인식된 QR-data가 비정상적입니다.", Toast.LENGTH_SHORT).show()
        }
    }

    fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_main2, container, false)

        btn_cam.setOnClickListener{camClick()}

        return view
    }

    private fun camClick(){

    }



}