/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.slide3;

/**
 *
 * @author ongmuoigiao
 */
public class SinhVien {
    String mssv;
    String tenSV;
    float diemTB;

    public SinhVien() {
    }

    public SinhVien(String mssv, String tenSV, float diemTB) {
        this.mssv = mssv;
        this.tenSV = tenSV;
        this.diemTB = diemTB;
    }

    
    @Override
    public String toString() {
        return "SinhVien{" + "mssv=" + mssv + ", tenSV=" + tenSV + ", diemTB=" + diemTB + '}';
    }
    
    
}
