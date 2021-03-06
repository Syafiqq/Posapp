/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.lontongboy.posapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Main activity demonstrating how to pass extra parameters to an activity that
 * reads barcodes.
 */
public class MainActivity extends AppCompatActivity {

    public Button cekBarang;
    public Button isiPesanan;
    public Button keluar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiDex.install(this);
        setContentView(R.layout.home_layout);
        DBInter inter = new DBInter();
        inter.initDBConnection();

        try {
            inter.stmt = inter.conn.createStatement();
            String sql = "SELECT id_barcode FROM tb_barang";
            ResultSet rs = inter.stmt.executeQuery(sql);
//            while (rs.next()){
//                String id = rs.getString("id_barcode");
//                if (id.equals("8888166336568"))
//                    System.out.println("dkdjf");
//                else
//                    System.out.println("not found");
//                System.out.println("id : " + id);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // inisialisasi button dengan ui
//        cekBarang = (Button) findViewById(R.id.btn_cekbrang);
//        isiPesanan = (Button) findViewById(R.id.btn_pesan);
//        keluar = (Button) findViewById(R.id.btn_exit);
//
//        // setiap button di pencet maka akan ke fitur
//        cekBarang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, CheckBarang.class);
//                startActivity(intent);
//            }
//        });
//
//        isiPesanan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, IsiPesanan.class);
//                startActivity(intent);
//            }
//        });
//
//        // tombol keluar aplikasi
//        keluar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
    }


}
