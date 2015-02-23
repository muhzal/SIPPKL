package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.*;

import models.Divisi;
import models.JenisInstansi;
import models.JenisKelamin;
import models.Level;
import models.Pengajuan;
import models.Siswa;
import models.Status;
import play.mvc.Controller;

public class Pengajuans extends Controller {
	
//----Render data Pengajuan----///	
	public static void index(){
		render();
	}
	public static void listPengajuan(){
		List pengajuan=Pengajuan.find("tampil=?",true).fetch();
		render(pengajuan);
	}	
//----Menambah data Pengajuan-----///	
	public static void tambah(Pengajuan pengajuan,Date tglsurat){
		
		if (pengajuan.nosurat == null) {
			
			List<Status> status=Status.findAll();
			List<JenisInstansi> jenis=JenisInstansi.findAll();			
			render(status,jenis);			
		}
			pengajuan.tglsurat=tglsurat;
			pengajuan.tampil=true;
			pengajuan.save();
			tambahSiswa(pengajuan);
	}
	
//----------Menambahkan Siswa KP-----------//	
	public static void tambahSiswa(Pengajuan pengajuan){		
		Pengajuan surat=Pengajuan.find("nosurat=? order by id DESC", pengajuan.nosurat).first();
		List<JenisKelamin> jk=JenisKelamin.findAll();
		render(jk,surat);
	}
	public static void simpanSiswa(List<Siswa> object){				
			for (Siswa siswa : object) {				
				siswa.save();
			}				
			//redirect("Pengajuan.index");
			index();
	}
//---------Edit Pengajuan----------//
	public static void editPengajuan(long id){
		Pengajuan pengajuan=Pengajuan.findById(id);
		List<JenisInstansi> jenis=JenisInstansi.findAll();
		render(pengajuan,jenis);
	}
	public static void simpanEdit(Pengajuan pengajuan,Date tglsurat){
		pengajuan.tglsurat=tglsurat;
		pengajuan.save();		
		index();
	}
	public static void arsip(long id){
		Pengajuan pengajuan=Pengajuan.findById(id);
		pengajuan.tampil=false;
		pengajuan.save();
		index();
	}
//-------Detail Pengajuan-------//
	public static void detail(long id){
		Pengajuan pengajuan=Pengajuan.findById(id);
		render(pengajuan);
	}
	
}










