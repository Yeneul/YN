package com.example.yn_ui

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.yn_ui.Fragment.Gpspop
import com.google.android.gms.location.*
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission

import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MapActivity : AppCompatActivity() {
    val TAG: String = "LOG"

    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null // 현재 위치를 가져오기 위한 변수
    lateinit var mLastLocation: Location // 위치 값을 가지고 있는 객체
    internal lateinit var mLocationRequest: LocationRequest // 위치 정보 요청의 매개변수를 저장하는

    private val eventListener = MarkerEventListener(this)

    private var mapView: MapView? = null
    var permissionlistener: PermissionListener = object : PermissionListener {
        override fun onPermissionGranted() { // 권한 허가시 실행 할 내용
            startLocationUpdates()
        }

        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            // 권한 거부시 실행  할 내용
            Toast.makeText(this@MapActivity, "권한 허용을 하지 않으면 서비스를 이용할 수 없습니다.",
                Toast.LENGTH_SHORT)
                .show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        mLocationRequest = LocationRequest.create().apply {
            //interval = 60000 // 업데이트 간격 단위(밀리초)
            //fastestInterval = 1000 // 가장 빠른 업데이트 간격 단위(밀리초)
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY // 정확성
            //maxWaitTime= 2000 // 위치 갱신 요청 최대 대기 시간 (밀리초)
        }
        mapView = MapView(this)
        val mapViewContainer = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.kakaoMapView)
        mapViewContainer.addView(mapView)
        checkPermissions(this)
        // 지도를 띄워줌
    }


    private fun checkPermissions(context:Context){

        if (Build.VERSION.SDK_INT >= 31) {
            TedPermission.with(context)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("앱을 이용하기 위해서는 접근 권한이 필요합니다")
                .setDeniedMessage("앱에서 요구하는 권한설정이 필요합니다...\n [설정] > [권한] 에서 사용으로 활성화해주세요.")
                .setPermissions(
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                    //Manifest.permission.ACCESS_BACKGROUND_LOCATION
                ).check()
        } else {
            startLocationUpdates()
        }
    }


    private fun mapSetting(lastLocation:Location) {

        //MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading
        //


        //val userNowLocation: Location?=mLocationManager

        //val userLatitude =userNowLocation?.latitude
        //val userLongtitude = userNowLocation?.longitude
        val lat = lastLocation.latitude
        val lng = lastLocation.longitude
        val mapPoint = MapPoint.mapPointWithGeoCoord(lat, lng)

        //지도의 중심점을 설정, 확대 레벨 설정 (값이 작을수록 더 확대됨)
        mapView?.setMapCenterPoint(mapPoint, true)
        mapView?.setZoomLevel(1, true)
        //트래킹 현재위치 표시 수정필요

        // TODO 지도가를 움직였을때 트래킹 모드로 인한 화면전환을 막아야 함
        mapView?.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading

        val markerPoint0 = MapPoint.mapPointWithGeoCoord(lat,lng)
        val markerPoint1 = MapPoint.mapPointWithGeoCoord(35.8158391, 127.1221840)
        val markerPoint2 = MapPoint.mapPointWithGeoCoord(35.8390547, 127.063720)
        val markerPoint3 = MapPoint.mapPointWithGeoCoord(35.8184599, 127.1220976)
        val markerPoint4 = MapPoint.mapPointWithGeoCoord(35.8158033, 127.1160739)
        val markerPoint5 = MapPoint.mapPointWithGeoCoord(35.8100080, 127.1265250)
        //마커 생성
        /*
        val marker0 = MapPOIItem()
        marker0.itemName = "현재 내위치 "
        marker0.userObject =""
        marker0.mapPoint = markerPoint0
        marker0.markerType=MapPOIItem.MarkerType.CustomImage
        marker0.customImageResourceId=R.drawable.marker
        //marker.setCustomImageAnchor(0.5f, 1.0f);
        marker0.markerType = MapPOIItem.MarkerType.BluePin
        marker0.selectedMarkerType = MapPOIItem.MarkerType.RedPin
        mapView?.addPOIItem(marker0)
         */
        val marker = MapPOIItem()
        marker.itemName = "중화산동2가 630 한빛안과 뒤편 (중산9길공원)"
        marker.userObject ="중화산동2가 630 "
        marker.mapPoint = markerPoint1
        marker.markerType=MapPOIItem.MarkerType.CustomImage
        marker.customImageResourceId= R.drawable.marker
        //marker.setCustomImageAnchor(0.5f, 1.0f);
        //marker.markerType = MapPOIItem.MarkerType.BluePin
        //marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin
        mapView?.addPOIItem(marker)

        val marker2 = MapPOIItem()
        marker2.itemName = "전북개발공사"
        marker2.userObject = "전북개발공사"
        marker2.mapPoint = markerPoint2
        marker2.markerType=MapPOIItem.MarkerType.CustomImage
        marker2.customImageResourceId= R.drawable.marker
        //marker2.markerType = MapPOIItem.MarkerType.BluePin
        //marker2.selectedMarkerType = MapPOIItem.MarkerType.RedPin
        mapView?.addPOIItem(marker2)

        val marker3 = MapPOIItem()
        marker3.itemName = "중화산동2가 598(산월길공원, 밀렛 뒤)"
        marker3.userObject = "중화산동2가 598"
        marker3.mapPoint = markerPoint3
        marker3.markerType=MapPOIItem.MarkerType.CustomImage
        marker3.customImageResourceId= R.drawable.marker
        //marker2.markerType = MapPOIItem.MarkerType.BluePin
        //marker2.selectedMarkerType = MapPOIItem.MarkerType.RedPin
        mapView?.addPOIItem(marker3)

        val marker4 = MapPOIItem()
        marker4.itemName = "중화산로 55-40 옆(미건빌)"
        marker4.userObject = ">중화산로 55-40"
        marker4.mapPoint = markerPoint4
        marker4.markerType=MapPOIItem.MarkerType.CustomImage
        marker4.customImageResourceId= R.drawable.marker
        //marker2.markerType = MapPOIItem.MarkerType.BluePin
        //marker2.selectedMarkerType = MapPOIItem.MarkerType.RedPin
        mapView?.addPOIItem(marker4)

        val marker5 = MapPOIItem()
        marker5.itemName = "중화산동 2가 선너머공원옆"
        marker5.userObject = ">중화산동 2가 선너머공원"
        marker5.mapPoint = markerPoint5
        marker5.markerType=MapPOIItem.MarkerType.CustomImage
        marker5.customImageResourceId= R.drawable.marker
        //marker2.markerType = MapPOIItem.MarkerType.BluePin
        //marker2.selectedMarkerType = MapPOIItem.MarkerType.RedPin
        mapView?.addPOIItem(marker5)



        mapView?.setPOIItemEventListener(eventListener)

    }

    // 마커 클릭 이벤트 리스너
    class MarkerEventListener(val context: Context): MapView.POIItemEventListener {

        override fun onPOIItemSelected(mapView: MapView?, poiItem: MapPOIItem?) {
            // 마커 클릭 시
            val a = context as MapActivity
            a.supportFragmentManager
                .beginTransaction()
                .replace(R.id.gps_pop, Gpspop())
                .commit()
        }

        override fun onCalloutBalloonOfPOIItemTouched(mapView: MapView?, poiItem: MapPOIItem?) {
            // 말풍선 클릭 시 (Deprecated)
            // 이 함수도 작동하지만 그냥 아래 있는 함수에 작성하자
        }

        override fun onCalloutBalloonOfPOIItemTouched(mapView: MapView?, poiItem: MapPOIItem?, buttonType: MapPOIItem.CalloutBalloonButtonType?) {
            // 말풍선 클릭 시

            val builder = AlertDialog.Builder(context)
            val itemList = arrayOf("토스트", "길찾기 이동 ", "취소")
            builder.setTitle("${poiItem?.itemName}")
            val intent = Intent(context, LoadActivity::class.java)
            val value = poiItem?.userObject.toString()
            intent.putExtra("ddd",value).toString()
            builder.setItems(itemList) { dialog, which ->
                when(which) {
                    0 -> Toast.makeText(context, "토스트", Toast.LENGTH_SHORT).show()  // 토스트
                    1 -> moving(poiItem)
                    2 -> dialog.dismiss()   // 대화상자 닫기
                }
            }
            builder.show()
        }

        fun moving(poiItem: MapPOIItem?){
            val intent = Intent(context, LoadActivity::class.java)
            val value = poiItem?.userObject.toString()
            intent.putExtra("ddd",value).toString()
            context.startActivity(intent)
        }

        override fun onDraggablePOIItemMoved(mapView: MapView?, poiItem: MapPOIItem?, mapPoint: MapPoint?) {
            // 마커의 속성 중 isDraggable = true 일 때 마커를 이동시켰을 경우
        }
    }


    private fun startLocationUpdates() {
        Log.d(TAG, "startLocationUpdates()")

        //FusedLocationProviderClient의 인스턴스를 생성.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "startLocationUpdates() 두 위치 권한중 하나라도 없는 경우 ")
            return
        }
        Log.d(TAG, "startLocationUpdates() 위치 권한이 하나라도 존재하는 경우")
        // 기기의 위치에 관한 정기 업데이트를 요청하는 메서드 실행
        // 지정한 루퍼 스레드(Looper.myLooper())에서 콜백(mLocationCallback)으로 위치 업데이트를 요청합니다.
        mFusedLocationProviderClient!!.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper())
    }

    // 시스템으로 부터 위치 정보를 콜백으로 받음
    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            Log.d(TAG, "onLocationResult()")
            // 시스템에서 받은 location 정보를 onLocationChanged()에 전달
            onLocationChanged(locationResult.lastLocation)
        }
    }

    // 시스템으로 부터 받은 위치정보를 화면에 갱신해주는 메소드
    fun onLocationChanged(location: Location) {
        Log.d(TAG, "onLocationChanged()")
        mLastLocation = location
        mapSetting(mLastLocation)
        //mapView?.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(location.latitude, location.longitude), true);
        /*
        Toast.makeText(this, "위도 : ${mLastLocation.latitude}, 경도 : ${mLastLocation.longitude}",
            Toast.LENGTH_SHORT)
            .show()

         */
    }

    // 위치 업데이터를 제거 하는 메서드
    private fun stoplocationUpdates() {
        Log.d(TAG, "stoplocationUpdates()")
        // 지정된 위치 결과 리스너에 대한 모든 위치 업데이트를 제거
        mFusedLocationProviderClient!!.removeLocationUpdates(mLocationCallback)
    }

    override fun onStop() {
        super.onStop()
        stoplocationUpdates()
    }

}


