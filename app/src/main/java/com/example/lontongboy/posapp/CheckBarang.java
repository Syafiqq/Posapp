package com.example.lontongboy.posapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.lontongboy.posapp.core.AppController;
import com.example.lontongboy.posapp.core.CustomRequest;
import com.example.lontongboy.posapp.networking.NetworkingConfig;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.Thing;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

// TODO: 10/1/16 query barang di tampilkan d list view, udah gitu aja 

public class CheckBarang extends AppCompatActivity
{

    public String hasilScan;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    // private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        MultiDex.install(this);

        setContentView(R.layout.activity_check_barang);
        Button         cekBarang = (Button) this.findViewById(R.id.btn_check);
        final Activity activity  = this;
        cekBarang.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
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
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        final TextView namaBarang        = (TextView) findViewById(R.id.namaBarang);
        final TextView letaBarang        = (TextView) findViewById(R.id.letakBarang);
        final TextView spesifikasiBarang = (TextView) findViewById(R.id.spesifikasiBarang);
        final TextView hargaBarang       = (TextView) findViewById(R.id.hargaBarang);
        final TextView stokBarang        = (TextView) findViewById(R.id.stokBarang);
        IntentResult   result            = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null)
        {
            if(result.getContents() == null)
            {
                Log.d("MainActivity", "Canceled scan");
                Toast.makeText(this, "cancelled", Toast.LENGTH_LONG).show();

            }
            else
            {
                Log.d("MainActivity", "Scanned");
                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                hasilScan = result.getContents();
                Log.d("Hasil Scan", hasilScan);

                final Map<String, String> jsonParams = new HashMap<String, String>();
                jsonParams.put("barcode", hasilScan);
                CustomRequest getGoodsRequest = new CustomRequest(Request.Method.POST, NetworkingConfig.SITE_URL("ws/goods/get"), jsonParams,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response)
                            {
                                try
                                {
                                    if(Integer.valueOf(response.getString("status")) == 1)
                                    {
                                        Toast.makeText(CheckBarang.this.getApplicationContext().getApplicationContext(), "Barang ada", Toast.LENGTH_SHORT).show();

                                        final JSONObject data = response.getJSONObject("data");

                                        namaBarang.setText(data.getString("nama_barang"));
                                        letaBarang.setText(data.getString("letak_barang"));
                                        spesifikasiBarang.setText(data.getString("spesifikasi"));
                                        hargaBarang.setText(data.getString("harga"));
                                        stokBarang.setText(data.getString("stok"));
                                    }
                                    else
                                    {
                                        Toast.makeText(CheckBarang.this.getApplicationContext().getApplicationContext(), "Barang tidak ditemukasn", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch(JSONException e)
                                {
                                    Toast.makeText(CheckBarang.this.getApplicationContext().getApplicationContext(), "Terdapat kesalahan", Toast.LENGTH_SHORT).show();

                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Toast.makeText(CheckBarang.this.getApplicationContext().getApplicationContext(), "Terdapat kesalahan", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                AppController.getInstance().addToRequestQueue(getGoodsRequest, "GetGoodRequest");
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction()
    {
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
    public void onStart()
    {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client.connect();
        //AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop()
    {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //AppIndex.AppIndexApi.end(client, getIndexApiAction());
        //client.disconnect();
    }
}
