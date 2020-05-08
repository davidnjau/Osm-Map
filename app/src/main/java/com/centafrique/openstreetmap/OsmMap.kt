package com.centafrique.openstreetmap

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.ItemizedOverlay
import org.osmdroid.views.overlay.OverlayItem


class OsmMap : AppCompatActivity() {

    private lateinit var map: MapView

    private val REQUEST_PERMISSIONS_REQUEST_CODE: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_osm_map)

        val ctx: Context = applicationContext
        Configuration.getInstance()
            .load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))

        val txtLatitude: Double = intent.getDoubleExtra("Lat", 0.444393)
        val txtLongitude: Double = intent.getDoubleExtra("Long", 35.749397)

        map = findViewById(R.id.map)

        map.setTileSource(TileSourceFactory.OpenTopo)

        map.setMultiTouchControls(true)
        map.controller.setZoom(4.0)

        val GeoPoint = GeoPoint(txtLatitude, txtLongitude)
        val mapController = map.controller
        mapController.setCenter(GeoPoint)
        mapController.animateTo(GeoPoint, 18.5, 10000L)


        val overlayItem = OverlayItem("Lat", "Long", GeoPoint)
        val markerDrawable = this.resources.getDrawable(R.drawable.blue_pin)
        overlayItem.setMarker(markerDrawable)
        val overlayItemArrayList =
            java.util.ArrayList<OverlayItem>()
        overlayItemArrayList.add(overlayItem)

        val locationOverlay: ItemizedOverlay<OverlayItem> =
            ItemizedIconOverlay(this, overlayItemArrayList, null)
        map.overlays.add(locationOverlay)

        if (!checkAndRequestPermissions()){

            Toast.makeText(this, "There are permissions required and they have not been enabled", Toast.LENGTH_LONG).show()
        }

    }

    private fun checkAndRequestPermissions() : Boolean{

        val smsPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val listPermissionNeeded = ArrayList<String>()
        if (smsPermission != PackageManager.PERMISSION_GRANTED)listPermissionNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (!listPermissionNeeded.isEmpty()){

            ActivityCompat.requestPermissions(this, listPermissionNeeded.toTypedArray(),
                REQUEST_ID_MULTIPLE_PERMISSIONS)
            return false

        }

        return true
    }


    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>, grantResults: IntArray) {

        when(requestCode){

            REQUEST_ID_MULTIPLE_PERMISSIONS -> {

                val perms = HashMap<String, Int>()
                perms[android.Manifest.permission.WRITE_EXTERNAL_STORAGE] = PackageManager.PERMISSION_GRANTED

                if (grantResults.size > 0) {

                    for (i in permissions.indices)
                        perms[permissions[i]] = grantResults[i]
                    if (perms[android.Manifest.permission.WRITE_EXTERNAL_STORAGE] == PackageManager.PERMISSION_GRANTED){

                        Toast.makeText(applicationContext, "All permissions granted", Toast.LENGTH_SHORT).show()
                    }else{

                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE )
                        ){
                            showDialogOK("These permissions are required for the app to work.",
                                DialogInterface.OnClickListener { dialog, which ->
                                    when(which){

                                        DialogInterface.BUTTON_POSITIVE -> checkAndRequestPermissions()
                                        DialogInterface.BUTTON_NEGATIVE -> finish()
                                    }
                                })


                        }else{

                            explain("The app needs these permissions to work and cannot proceed without them. Do you want to go to settings?")
                        }



                    }

                }


            }

        }

    }

    private fun showDialogOK(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", okListener)
            .create()
            .show()
    }

    private fun explain(msg: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage(msg)
            .setPositiveButton("Yes") { paramDialogInterface, paramInt ->
                //  permissionsclass.requestPermission(type,code);
                startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:com.centafrique.openstreetmap")))
            }
            .setNegativeButton("Cancel") { paramDialogInterface, paramInt -> finish() }
        dialog.show()
    }

    companion object{

        val REQUEST_ID_MULTIPLE_PERMISSIONS = 1
    }
}
