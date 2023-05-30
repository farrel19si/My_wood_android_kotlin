package com.farrel.projectwpm

import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat.checkSelfPermission
import androidx.fragment.app.FragmentActivity
import com.farrel.projectwpm.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : FragmentActivity(), OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    companion object{
        private val MY_PERMISSION_FINE_LOCATION = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val pekanbaru = LatLng(0.510440, 101.438309)
        mMap.addMarker(MarkerOptions().position(pekanbaru).title("Marker in Pekanbaru"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pekanbaru,12f))
        mMap.uiSettings.isZoomControlsEnabled= true

        if(checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )== PackageManager.PERMISSION_GRANTED
        ){
            mMap.isMyLocationEnabled=true
        }
        mMap.setOnMapLongClickListener(this)
        tambahMarkerLongClick(mMap)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.map_normal -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                true
            }
            R.id.map_hybrid -> {
                mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
                true
            }
            R.id.map_satelit -> {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                true
            }
            R.id.map_terrain -> {
                mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun GoogleMap.OnMarkerClickListener(p0:Marker?)= false


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            MY_PERMISSION_FINE_LOCATION -> if (grantResults[0]==
                PackageManager.PERMISSION_GRANTED){
                if(checkSelfPermission(
                        this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    )== PackageManager.PERMISSION_GRANTED
                ){
                    mMap.isMyLocationEnabled=true
                }
            }else{
                Toast.makeText(
                    this,
                    "Aplikasi ini membutuhkan akses izin lokasi",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }
    private  fun getAlamat(lat: LatLng):String?{
        val geocoder = Geocoder(this)
        val list =geocoder.getFromLocation(lat.latitude,lat.longitude,1)
        return list[0].getAddressLine(0)
    }
    fun tambahMarkerLongClick(googleMap: GoogleMap){
        googleMap.setOnMapLongClickListener { LatLng ->
            val koordinat = LatLng(LatLng.latitude,LatLng.longitude)
            val markerOptions = MarkerOptions().position(koordinat)
            val namaJalan = getAlamat(koordinat)
            markerOptions.title(namaJalan)

            googleMap.addMarker(
                MarkerOptions().position(koordinat).title("Marker Baru").snippet(namaJalan)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
            )
        }
    }
    override fun onMapLongClick(p0:LatLng){
        TODO("Not Yet Implemented")
    }
}