/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dehocky1;

import java.util.Date;

/**
 *
 * @author admin
 */
public class lophoc 
{

    public lophoc(String malop, String tengv, int sotietquydoi) {
        this.malop = malop;
        this.tengv = tengv;
        this.sotietquydoi = sotietquydoi;
    }

    public String getMalop() {
        return malop;
    }

    public String getTengv() {
        return tengv;
    }

    public String getTenmonhoc() {
        return tenmonhoc;
    }

    public int getSiso() {
        return siso;
    }

    public int getSotietquydoi() {
        return sotietquydoi;
    }

    public Date getThoigianbatdau() {
        return thoigianbatdau;
    }

    public Date getThoigiankethuc() {
        return thoigiankethuc;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }

    public void setTengv(String tengv) {
        this.tengv = tengv;
    }

    public void setTenmonhoc(String tenmonhoc) {
        this.tenmonhoc = tenmonhoc;
    }

    public void setSiso(int siso) {
        this.siso = siso;
    }

    public void setSotietquydoi(int sotietquydoi) {
        this.sotietquydoi = sotietquydoi;
    }

    public void setThoigianbatdau(Date thoigianbatdau) {
        this.thoigianbatdau = thoigianbatdau;
    }

    public void setThoigiankethuc(Date thoigiankethuc) {
        this.thoigiankethuc = thoigiankethuc;
    }

    public lophoc() {
    }

    public lophoc(String tengv,String malop,  String tenmonhoc, int siso, Date thoigianbatdau, Date thoigiankethuc , int sotietquydoi) {
        this.malop = malop;
        this.tengv = tengv;
        this.tenmonhoc = tenmonhoc;
        this.siso = siso;
        this.sotietquydoi = sotietquydoi;
        this.thoigianbatdau = thoigianbatdau;
        this.thoigiankethuc = thoigiankethuc;
    }
    private String malop,tengv;
    private String tenmonhoc;
    private int siso,sotietquydoi;
    private Date thoigianbatdau;
    private Date thoigiankethuc;
    
    
}
