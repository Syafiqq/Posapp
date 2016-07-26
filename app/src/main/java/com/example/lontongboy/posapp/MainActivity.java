package com.example.lontongboy.posapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button scanBtn;
    private TextView formatTxt, contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);



    }


    @Override
    public void onClick(View v) {

    }
}
// cek barang
class CekBarang{
    public void openFlashCam(){
        System.out.print("open flash cam");
    }

    public void scanBarcode(){
        System.out.print("scan barcode");
    }
}

// isi pesanan
class IsiPesanan{

    public void isiNama(){
        System.out.print("Isi Nama");
    }

    public void scanBarang(){
        System.out.print("scan Barang");

    }

    public void listViewUpdater(){
        System.out.print("update list view");
    }

    public void checkoutStatus(){
        System.out.print("True | False");
    }
}