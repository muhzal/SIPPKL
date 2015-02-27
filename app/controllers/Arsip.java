package controllers;

import java.util.List;

import models.Pengajuan;
import models.Siswa;
import models.Status;
import play.mvc.Controller;
import play.mvc.With;
@With(Secure.class)
public class Arsip extends Controller {
	public static void pengajuan(){		
		render();
	}
	public static void smk(){		
		render();
	}
	public static void mahasiswa(){		
		render();
	}
	public static void ditolak(){		
		render();
	}
	public static void listpengajuan(){
		List<Pengajuan> pengajuan=Pengajuan.find("tampil=?", false).fetch();
		render(pengajuan);
	}
	public static void listsmk(){
		List siswa=Siswa.find("pengajuan.jenisinstansi_id=1 and status.id=7").fetch();
		List<Status> status=Status.findAll();
		render(siswa,status);
	}
	public static void listmahasiswa(){		
		List siswa=Siswa.find("pengajuan.jenisinstansi_id=2 and status.id=7").fetch();
		List<Status> status=Status.findAll();
		render(siswa,status);
	}
	public static void listditolak(){
		List siswa=Siswa.find("status.id=2").fetch();
		List<Status> status=Status.findAll();
		render(siswa,status);
	}
	
}
