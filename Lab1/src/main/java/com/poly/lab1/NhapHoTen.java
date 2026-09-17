/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.lab1;

import java.util.Scanner;

/**
 *
 * @author ongmuoigiao
 */
public class NhapHoTen {
    public static void main(String[] args) {
        String ten;
        int namSinh;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ho ten: ");
        ten = sc.nextLine();
        System.out.print("Nhap nam sinh: ");
        namSinh = sc.nextInt();
        //xuat du lieu
        System.out.printf("Ho va ten: %s\nNam sinh: %d\nTuoi: %d\n",ten,namSinh,2026 - namSinh);
    }
}
