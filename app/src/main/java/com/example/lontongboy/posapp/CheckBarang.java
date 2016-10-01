package com.example.lontongboy.posapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

// TODO: 10/1/16 query barang di tampilkan d list view, udah gitu aja 

public class CheckBarang extends AppCompatActivity {

    private Button cekBarang;
    public String hasilScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_barang);
        cekBarang = (Button) this.findViewById(R.id.btn_check);
        final Activity activity = this;
        cekBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if (result.getContents() == null){
                Log.d("MainActivity", "Canceled scan");
                Toast.makeText(this, "cancelled", Toast.LENGTH_LONG).show();

            } else{
                Log.d("MainActivity", "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                hasilScan = result.getContents();
                System.out.println(hasilScan);

            }
        } else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
