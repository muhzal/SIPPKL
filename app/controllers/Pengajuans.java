package controllers;

import java.util.ArrayList;
import java.util.Collection;
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
import play.data.binding.As;
import play.data.validation.IsTrue;
import play.mvc.Controller;
import play.mvc.With;
@With(Secure.class)
public class Pengajuans extends Controller {
	
//----Render data Pengajuan----///	
	public static void index(){
		render();
	}
	public static void listPengajuan(){
		List pengajuan=Pengajuan.find("tampil=? order by tglsurat asc",true).fetch();
		render(pengajuan);
	}	
//----Menambah data Pengajuan-----///	
	public static void tambah(Pengajuan pengajuan,@As("dd-MM-yyyy") Date tglsurat){
		
		if (pengajuan.nosurat == null) {
			
			List<Status> status=Status.find("id!=7").fetch();
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
		List<Divisi> divisi=Divisi.findAll();
		List<Status> status=Status.find("id!=7").fetch();
		List<JenisKelamin> jk=JenisKelamin.findAll();
		render(jk,surat,divisi,status);
	}
	public static void simpanSiswa(List<Siswa> object,@As("dd-MM-yyyy") Date[] tglmulai,@As("dd-MM-yyyy") Date[] tglselesai){
		long idpengajuan = 0;
		int index=0;
			for (Siswa siswa : object) {
				siswa.tglmulai=tglmulai[index];				
				siswa.tglselesai=tglselesai[index];
				idpengajuan=siswa.pengajuan.id;
				siswa.save();				
				index++;
			}				
			//redirect("Pengajuan.index");
			if(idpengajuan==0){
				index();	
			}else{
				detail(idpengajuan);
			}
	}
//---------Edit Pengajuan----------//
	public static void editPengajuan(long id){
		Pengajuan pengajuan=Pengajuan.findById(id);
		List<JenisInstansi> jenis=JenisInstansi.findAll();
		render(pengajuan,jenis);
	}
	public static void simpanEdit(Pengajuan pengajuan,@As("dd-MM-yyyy") Date tglsurat){
		pengajuan.tglsurat=tglsurat;
		pengajuan.save();		
		index();
	}
	public static void arsip(long id){
		long arsip=7;
		Status status=Status.findById(arsip);
		List<Siswa> siswa=Siswa.find("pengajuan.id=? and status.id!=2", id).fetch();
		Pengajuan pengajuan=Pengajuan.findById(id);
		if(pengajuan.tampil){
			pengajuan.tampil=false;
		}else{
			pengajuan.tampil=true;
		}
		
		pengajuan.save();
		for (Siswa i : siswa) {
			i.status=status;
			i.save();
		}
		index();
	}
//-------Detail Pengajuan-------//
	public static void detail(long id){
		Pengajuan pengajuan=Pengajuan.findById(id);
		render(pengajuan);
	}
	
}










