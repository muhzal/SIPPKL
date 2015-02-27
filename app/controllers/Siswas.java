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
import play.mvc.With;
@With(Secure.class)
public class Siswas extends Controller {	

	public static void smk(){
		render();
	}
	public static void tunggu(){
		render();
	}
	public static void mahasiswa(){
		render();
	}
	public static void listsiswa(long id){
		List<Siswa> siswa =Siswa.find("pengajuan.id=?",id).fetch();
		List<Status> status=Status.find("id!=7").fetch();
		render(siswa,status);
	}	
	public static void listsmk(){
		List siswa=Siswa.find("pengajuan.jenisinstansi_id=1 and status.id!=2 and status.id!=7 order by pengajuan.id ASC ").fetch();
		List<Status> status=Status.find("id!=7").fetch();
		render(siswa,status);
	}
	public static void listmahasiswa(){
		List siswa=Siswa.find("pengajuan.jenisinstansi_id=2 and status.id!=7 and  status.id!=2 order by pengajuan.id ASC").fetch();
		List<Status> status=Status.find("id!=7").fetch();
		render(siswa,status);
	}
	public static void ubahStatus(long id){
		Siswa siswa =Siswa.findById(id);
		List<Divisi> divisi=Divisi.findAll();
		List<Status> status=Status.find("id!=7").fetch();
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
			List<Status> status=Status.find("id!=7").fetch();	
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
		List<Status> status=Status.find("id!=7").fetch();
		List<Divisi> divisi=Divisi.findAll();
		Siswa siswa=Siswa.findById(id);
		if (siswa.status.namastatus.equals("Arsip")) {
			status=Status.findAll();
		}
		List<JenisKelamin> jk=JenisKelamin.findAll();
		render(siswa,divisi,status,jk);
	}
	public static void listtunggu(){
		List<Siswa> siswa=Siswa.find("status.id=4 order by tglmulai ASC").fetch();
		List<Status> status=Status.find("id!=7").fetch();
		render(siswa,status);
	}
}

