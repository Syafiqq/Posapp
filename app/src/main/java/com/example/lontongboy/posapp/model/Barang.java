package com.example.lontongboy.posapp.model;

/**
 * This <PosappSP> project in package <com.example.lontongboy.posapp.model> created by :
 * Name         : syafiq
 * Date / Time  : 30 October 2016, 10:11 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

public class Barang
{
    private String id_barcode;
    private String nama_barang;
    private String letak_barang;
    private String spesifikasi;
    private int harga;
    private int stok;

    public Barang(String id_barcode, String nama_barang, String letak_barang, String spesifikasi, int harga, int stok)
    {
        this.id_barcode = id_barcode;
        this.nama_barang = nama_barang;
        this.letak_barang = letak_barang;
        this.spesifikasi = spesifikasi;
        this.harga = harga;
        this.stok = stok;
    }

    public String getId_barcode()
    {
        return id_barcode;
    }

    public void setId_barcode(String id_barcode)
    {
        this.id_barcode = id_barcode;
    }

    public String getNama_barang()
    {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang)
    {
        this.nama_barang = nama_barang;
    }

    public String getLetak_barang()
    {
        return letak_barang;
    }

    public void setLetak_barang(String letak_barang)
    {
        this.letak_barang = letak_barang;
    }

    public String getSpesifikasi()
    {
        return spesifikasi;
    }

    public void setSpesifikasi(String spesifikasi)
    {
        this.spesifikasi = spesifikasi;
    }

    public int getHarga()
    {
        return harga;
    }

    public void setHarga(int harga)
    {
        this.harga = harga;
    }

    public int getStok()
    {
        return stok;
    }

    public void setStok(int stok)
    {
        this.stok = stok;
    }
}
