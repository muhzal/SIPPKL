package controllers;

import java.util.List;

import models.Pengajuan;
import play.mvc.Controller;

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
	public static void listpengajuan(){
		List<Pengajuan> pengajuan=Pengajuan.find("tampil=?", false).fetch();
		render(pengajuan);
	}
	public static void listsmk(){		
		render();
	}
	public static void listmahasiswa(){		
		render();
	}
	
}
