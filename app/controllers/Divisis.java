package controllers;

import java.util.List;

import models.Divisi;
import models.Siswa;
import models.Status;
import play.mvc.Controller;
import play.mvc.With;
@With(Secure.class)
public class Divisis extends Controller {
	public static void index(){
		render();
	}
	public static void listdivisi(){
		List<Divisi> divisi=Divisi.findAll();
		render(divisi);
	}
	public static void tambahdivisi(Divisi divisi){
		if (divisi.namadivisi != null) {
			divisi.save();
			index();
		}else{
			render();
		}
	}	
	public static void ubahdivisi(long id){
		Divisi divisi=Divisi.findById(id);
		render(divisi);
	}
	public static void hapusdivisi(long id){
		
		Divisi divisi=Divisi.findById(id);				
		divisi.delete();		
		index();
	}	
	public static void detail(long id){
		Divisi divisi=Divisi.findById(id);			
		render(divisi);
	}
	public static void listsiswa(long id){
		List<Siswa> siswa=Siswa.find("divisi.id=? and status.id!=6 and status.id!=7 and status.id!=2 order by status.id",id).fetch();
		List<Status> status=Status.find("id!=7").fetch();
		render(siswa,status);
	}
}
