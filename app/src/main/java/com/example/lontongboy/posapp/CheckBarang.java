package com.example.lontongboy.posapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

// TODO: 10/1/16 query barang di tampilkan d list view, udah gitu aja 

public class CheckBarang extends AppCompatActivity {

    public String hasilScan;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiDex.install(this);

        setContentView(R.layout.activity_check_barang);
        Button cekBarang = (Button) this.findViewById(R.id.btn_check);
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView namaBarang = (TextView) findViewById(R.id.namaBarang);
        TextView letaBarang = (TextView) findViewById(R.id.letakBarang);
        TextView spesifikasiBarang = (TextView) findViewById(R.id.spesifikasiBarang);
        TextView hargaBarang = (TextView) findViewById(R.id.hargaBarang);
        TextView stokBarang = (TextView) findViewById(R.id.stokBarang);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        DBInter inter = new DBInter();
        inter.initDBConnection();

        if (result != null) {
            if (result.getContents() == null) {
                Log.d("MainActivity", "Canceled scan");
                Toast.makeText(this, "cancelled", Toast.LENGTH_LONG).show();

            } else {
                Log.d("MainActivity", "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                hasilScan = result.getContents();
                System.out.println(hasilScan);

                // dicocokkan
                String sql = "SELECT id_barcode FROM tb_barang";
                try {
                    ResultSet rs = inter.stmt.executeQuery(sql);
                    System.out.println(rs);
                    while (rs.next()){
                        String id  = rs.getString("id_barcode");
                        Log.i("Akses", id);
//                        if(id.equals(hasilScan)){
//                            namaBarang.setText("asdasd");
//                            letaBarang.setText(rs.getString("letak_barang"));
//                            spesifikasiBarang.setText(rs.getString("spesifikasi"));
//                            hargaBarang.setText(rs.getString("harga"));
//                            stokBarang.setText(rs.getString("stok"));
//                        }
//                        else{
//                            Toast.makeText(this, "Barcode not found", Toast.LENGTH_SHORT).show();
//                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("CheckBarang Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
