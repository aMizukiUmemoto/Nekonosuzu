package com.access_compnay.trainingproject_nekonosuzu.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.SurfaceHolder
import com.access_compnay.trainingproject_nekonosuzu.R
import com.access_compnay.trainingproject_nekonosuzu.viewmodel.EquipmentDetailViewModel
//import com.access_compnay.trainingproject_nekonosuzu.viewmodel.QrCaptureViewModel
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.qr_capture_activity.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class QrCaptureActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_VALUE = "value"
    }

    private var cameraSource: CameraSource? = null
    //private lateinit var viewModel: QrCaptureViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qr_capture_activity)
    }

    override fun onResume() {
        super.onResume()

        if (cameraSource == null) {
            val barcodeDetector = BarcodeDetector.Builder(this)
                    .setBarcodeFormats(Barcode.QR_CODE)
                    .build()
                    .also { it.setProcessor(detectorProcessor) }
            cameraSource = CameraSource.Builder(this, barcodeDetector)
                    .setAutoFocusEnabled(true)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .build()
        }
        surfaceView.holder.addCallback(surfaceCallback)
    }

    override fun onPause() {
        super.onPause()

        cameraSource?.stop()
    }

    @SuppressLint("NeedOnRequestPermissionsResult") // 不要のはずだが、PermissionsDispatcherのバグかも
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @SuppressLint("MissingPermission") // NeedsPermissionで処理するので権限確認は不要
    @NeedsPermission(android.Manifest.permission.CAMERA)
    fun startCamera() {
        cameraSource?.start(surfaceView.holder)
    }

    private val detectorProcessor = object : Detector.Processor<Barcode> {
        override fun release() = Unit

        override fun receiveDetections(detections: Detector.Detections<Barcode>) {
            // TODO: 2つ目以降をどう処理するか？
            if (detections.detectedItems.size() > 0) {
                val barcode = detections.detectedItems.valueAt(0)
                println("■■■barcode="+barcode.rawValue)
                setResult(RESULT_OK, Intent().apply { putExtra(EXTRA_VALUE, barcode.rawValue) })
                finish()
            }
        }
    }

    private val surfaceCallback = object : SurfaceHolder.Callback {
        override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) = Unit

        override fun surfaceDestroyed(holder: SurfaceHolder?) {
            cameraSource?.stop()
            cameraSource?.release()
            cameraSource = null
        }

        override fun surfaceCreated(holder: SurfaceHolder?) {
            startCameraWithPermissionCheck()
        }
    }
}
