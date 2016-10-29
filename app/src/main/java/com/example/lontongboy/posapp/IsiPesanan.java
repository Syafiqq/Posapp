package com.example.lontongboy.posapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hippo on 9/18/16.
 */
 
// TODO: 10/1/16 hasil scan tambah ke list view
// TODO: 10/1/16 list view ke array untuk di query
// TODO: 10/1/16 tombol submit belum berfungsi 
// TODO: 10/1/16 submit barang = query ke database tambah barang pesanan 

public class IsiPesanan extends AppCompatActivity{

    private Button cekBarang;
    private String hasilScan;
    private EditText namaPem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiDex.install(this);
        setContentView(R.layout.isi_pesanan);
        cekBarang = (Button) this.findViewById(R.id.btn_scan);
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

        namaPem = (EditText) findViewById(R.id.editText);
        assert namaPem != null;
        String namaPemesan = namaPem.getText().toString();
        String[] jumlahPesanan;

        DBInter inter = new DBInter();
        inter.initDBConnection();
        try {
            inter.stmt = inter.conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if (result.getContents() == null){
                Log.d("MainActivity", "Canceled scan");
                Toast.makeText(this, "cancelled", Toast.LENGTH_LONG).show();

            } else{
                Log.d("MainActivity", "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                hasilScan = result.getContents();

                // dicocokan
                // add data by barcode id
                // add them to an string array
                // display the string array to list adapter.
                // after button submit pressed, run the sql query to server
                String sql = "SELECT id_barcode FROM tb_barang";
                try {
                    ResultSet rs = inter.stmt.executeQuery(sql);
                    while (rs.next()){
                        String id = rs.getString("id_barcode");
                        if(id.equals(hasilScan))
                            Log.d("Sama", "asd");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                System.out.println(hasilScan);
                
            }
        } else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public String checkOut(String CustName, String[] barang){
        return "Sel";
    }
}
