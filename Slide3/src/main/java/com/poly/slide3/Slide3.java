/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.poly.slide3;

/**
 *
 * @author ongmuoigiao
 */
public class Slide3 {

    public static void main(String[] args) {
        SinhVien sv = new SinhVien("PS12345","Tran Quang Binh",6.7f);
        System.out.println(sv.toString());
        SinhVien dsSV[] = new SinhVien[10];
        dsSV[0] = new SinhVien("PS00001","An",8.0f);
        
//        int[] a = new int[10];
//        a[0] = 1;
//        try {
//            System.out.println(a[10]);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }
}
