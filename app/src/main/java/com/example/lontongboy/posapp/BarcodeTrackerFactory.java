package com.example.lontongboy.posapp;

import com.example.lontongboy.posapp.camera.GraphicOverlay;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;

/**
 * factory for creating tracker and associated graphic to be  associated with new barcode. The
 * multi-processor uses this factory to create barcode trackers as needed --one for each barcode
 */
class BarcodeTrackerFactory implements MultiProcessor.Factory<Barcode>{

    private GraphicOverlay<BarcodeGraphic> mGraphicOverlay;

    BarcodeTrackerFactory(GraphicOverlay<BarcodeGraphic> barcodeGraphicGraphicOverlay){
        mGraphicOverlay = barcodeGraphicGraphicOverlay;
    }

    @Override
    public Tracker<Barcode> create(Barcode barcode) {
        BarcodeGraphic graphic = new BarcodeGraphic(mGraphicOverlay);
        return new BarcodeGraphicTracker(mGraphicOverlay, graphic);
    }


}
