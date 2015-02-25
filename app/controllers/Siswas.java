package controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import models.Divisi;
import models.JenisKelamin;
import models.Pengajuan;
import models.Siswa;
import models.Status;
import play.data.binding.As;
import play.mvc.Controller;

public class Siswas extends Controller {	
	 
	public static void smk(){
		render();
	}
	public static void mahasiswa(){
		render();
	}
	public static void listsiswa(long id){
		List<Siswa> siswa =Siswa.find("pengajuan.id=?",id).fetch();
		List<Status> status=Status.findAll();
		render(siswa,status);
	}	
	public static void listsmk(){
		List siswa=Siswa.find("pengajuan.jenisinstansi_id=1 and status.id!=6 or pengajuan.jenisinstansi_id=1 and status.id!=2").fetch();
		List<Status> status=Status.findAll();
		render(siswa,status);
	}
	public static void listmahasiswa(){
		List siswa=Siswa.find("pengajuan.jenisinstansi_id=2 and status.id!=6  or pengajuan.jenisinstansi_id=2 and  status.id!=2").fetch();
		List<Status> status=Status.findAll();
		render(siswa,status);
	}
	public static void ubahStatus(long id){
		Siswa siswa =Siswa.findById(id);
		List<Divisi> divisi=Divisi.findAll();
		List<Status> status=Status.findAll();
		render(siswa,divisi,status);
	}
	public static void simpanStatus(Siswa siswa){		
		siswa.save();
		if (siswa.pengajuan.jenisinstansi_id.equals(1)) {
			smk();
		}else if (siswa.pengajuan.jenisinstansi_id.equals(2)) {
			mahasiswa();
		}
	}
	public static void simpanUbahStatus(long id,long status_id){		
		Status x=new Status();
		x.id=status_id;
		Siswa siswa=Siswa.findById(id);
		siswa.status=x;
		siswa.save();
		smk();
	}
	public static void tambah(long id, Siswa siswa,@As("dd-MM-yyyy") Date tglmulai,@As("dd-MM-yyyy") Date tglselesai){
		if (siswa.nama == null) {
			List<Divisi> divisi=Divisi.findAll();
			List<Status> status=Status.findAll();	
			List<JenisKelamin> jk=JenisKelamin.findAll();
			Pengajuan pengajuan=Pengajuan.findById(id);
			render(divisi,status,jk,pengajuan);
		}else{
			siswa.tglmulai=tglmulai;
			siswa.tglselesai=tglselesai;
					
			siswa.save();
			Pengajuans.index();
		}			
	}
	public static void hapus(long id){
		Siswa siswa=Siswa.findById(id);
		siswa.delete();
		Pengajuans.index();
	}
	public static void ubahsiswa(long id){
		List<Divisi> divisi=Divisi.findAll();
		List<Status> status=Status.findAll();	
		List<JenisKelamin> jk=JenisKelamin.findAll();
		Siswa siswa=Siswa.findById(id);
		render(siswa,divisi,status,jk);
	}
}

