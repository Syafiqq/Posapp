package com.example.lontongboy.posapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.lontongboy.posapp.adapter.IsiAdapter;
import com.example.lontongboy.posapp.core.AppController;
import com.example.lontongboy.posapp.core.CustomRequest;
import com.example.lontongboy.posapp.model.Barang;
import com.example.lontongboy.posapp.networking.NetworkingConfig;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hippo on 9/18/16.
 */

// TODO: 10/1/16 hasil scan tambah ke list view
// TODO: 10/1/16 list view ke array untuk di query
// TODO: 10/1/16 tombol submit belum berfungsi 
// TODO: 10/1/16 submit barang = query ke database tambah barang pesanan 

public class IsiPesanan extends AppCompatActivity
{

    private Button            cekBarang;
    private String            hasilScan;
    private EditText          namaPem;
    private ArrayList<Barang> barangs;
    private ListView          ls;
    private IsiAdapter barangAA;
    private Button checkout;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        MultiDex.install(this);
        setContentView(R.layout.isi_pesanan);
        cekBarang = (Button) this.findViewById(R.id.btn_scan);
        checkout = (Button) this.findViewById(R.id.btn_checkout);
        final Activity activity = this;
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
        this.barangs = new ArrayList<>();
        ls = (ListView) findViewById(R.id.list_barang);
        this.barangAA = new IsiAdapter(super.getApplicationContext(), R.layout.isi_pesanan_content, this.barangs);
        ls.setAdapter(barangAA);
        barangAA.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        namaPem = (EditText) findViewById(R.id.editText);
        checkout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name = namaPem.getText().toString();
                if(name.trim().length() > 0)
                {
                    IsiPesanan.this.checkOut(name, IsiPesanan.this.barangs);
                }
                else
                {
                    Toast.makeText(IsiPesanan.this.getApplicationContext(), "Silahkan isi nama terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null)
        {
            if(result.getContents() == null)
            {
                Log.d("MainActivity", "Canceled scan");

            }
            else
            {
                Log.d("MainActivity", "Scanned");
                hasilScan = result.getContents();

                final Map<String, String> jsonParams = new HashMap<String, String>();
                jsonParams.put("barcode", hasilScan);
                CustomRequest getGoodsRequest1 = new CustomRequest(Request.Method.POST, NetworkingConfig.SITE_URL("ws/goods/get"), jsonParams,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response)
                            {
                                try
                                {
                                    if(Integer.valueOf(response.getString("status")) == 1)
                                    {
                                        Toast.makeText(IsiPesanan.this.getApplicationContext(), "Barang ada", Toast.LENGTH_SHORT).show();

                                        final JSONObject data = response.getJSONObject("data");

                                        IsiPesanan.this.barangs.add(new Barang(
                                                data.getString("id_barcode"),
                                                data.getString("nama_barang"),
                                                data.getString("letak_barang"),
                                                data.getString("spesifikasi"),
                                                data.getInt("harga"),
                                                data.getInt("stok")
                                        ));
                                        IsiPesanan.this.barangAA.notifyDataSetChanged();
                                        Log.d("Total Tambah", String.valueOf(IsiPesanan.this.barangs.size()));
                                    }
                                    else
                                    {
                                        Toast.makeText(IsiPesanan.this.getApplicationContext(), "Barang tidak ditemukasn", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch(JSONException e)
                                {
                                    Toast.makeText(IsiPesanan.this.getApplicationContext(), "Terdapat kesalahan", Toast.LENGTH_SHORT).show();

                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Toast.makeText(IsiPesanan.this.getApplicationContext(), "Terdapat kesalahan", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                AppController.getInstance().addToRequestQueue(getGoodsRequest1, "GetGoodRequest1");
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void checkOut(String CustName, List<Barang> barangs)
    {
        final Map<String, String> jsonParams = new HashMap<>();
        jsonParams.put("name", CustName);
        jsonParams.put("total", String.valueOf(barangs.size()));
        int counter = -1;
        for(final Barang barang : barangs)
        {
            jsonParams.put("barang"+(++counter), barang.getId_barcode());
        }
        CustomRequest checkoutRequest = new CustomRequest(Request.Method.POST, NetworkingConfig.SITE_URL("ws/goods/checkout"), jsonParams,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            if(Integer.valueOf(response.getString("status")) == 1)
                            {
                                finish();
                                Toast.makeText(IsiPesanan.this.getApplicationContext(), "Checkout berhasil", Toast.LENGTH_SHORT).show();
                                Toast.makeText(IsiPesanan.this.getApplicationContext(), "Total " + response.getString("total"), Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(IsiPesanan.this.getApplicationContext(), "Checkout gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch(JSONException e)
                        {
                            Toast.makeText(IsiPesanan.this.getApplicationContext(), "Checkout gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(IsiPesanan.this.getApplicationContext(), "Terdapat kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(checkoutRequest, "checkoutRequest");
    }
}
