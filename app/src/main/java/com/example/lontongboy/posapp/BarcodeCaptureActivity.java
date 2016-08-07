package com.example.lontongboy.posapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Toast;

import com.example.lontongboy.posapp.camera.CameraSource;
import com.example.lontongboy.posapp.camera.CameraSourcePreview;
import com.example.lontongboy.posapp.camera.GraphicOverlay;

/**
 * Activity for the multi-tracker app.  This app detects barcodes and displays the value with the
 * rear facing camera. During detection overlay graphics are drawn to indicate the position,
 * size, and ID of each barcode.
 */

public class BarcodeCaptureActivity extends AppCompatActivity{
    private static final String TAG = "Barcode-reader";

    // intent request code to handle updating play service if needed
    private static final int RC_HANDLE_GMS = 9001;

    // permission request coded need to be < 256
    private static final int RC_HANDLE_CAMERA_PERM = 2;

    // constants used to pass extra data in the intent
    public static final String AutoFocus = "AutoFocus";
    public static final String UseFlash = "UseFlash";
    public static final String BarcodeObject = "Barcode";

    private CameraSource mCameraSource;
    private CameraSourcePreview mPreview;
    private GraphicOverlay<BarcodeGraphic> mGraphicOverlay;

    // helper objects for detecting taps and pinches
    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector gestureDetector;

    /**
     * Initialize the UI and creates the detector pipeline
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.barcode_capture);

        mPreview = (CameraSourcePreview) findViewById(R.id.preview);
        mGraphicOverlay = (GraphicOverlay<BarcodeGraphic>) findViewById(R.id.graphicOverlay);

        // read parameters from the intent used to launch the activity
        boolean autoFocus = getIntent().getBooleanExtra(AutoFocus, false);
        boolean useFlash = getIntent().getBooleanExtra(UseFlash, false);

        // Check for the camera permission before accessing the camera. If the
        // permission is not grated yet, request permission.
        int rc = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (rc == PackageManager.PERMISSION_GRANTED){
            createCameraSource(autoFocus, useFlash);
        }
        else{
            requestCameraPermission();
        }

        gestureDetector = new GestureDetector(this, new CaptureGestureListener());
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListestener());

        Snackbar.
    }


private void requestCameraPermission() {
    }
    private void createCameraSource(boolean autoFocus, boolean useFlash) {
    }

    private class CaptureGestureListener implements GestureDetector.OnGestureListener {
    }

    private class ScaleListestener implements ScaleGestureDetector.OnScaleGestureListener {
    }
}
