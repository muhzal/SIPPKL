package controllers;

import java.util.List;

import models.Divisi;
import models.JenisKelamin;
import models.Level;
import models.Siswa;
import models.User;
import play.mvc.Controller;

public class Master extends Controller {
//==========Mengelola Divisi==========//	
	public static void divisi(){
		render();
	}
	public static void listdivisi(){
		List<Divisi> divisi=Divisi.findAll();
		render(divisi);
	}
	public static void tambahdivisi(Divisi divisi){
		if (divisi.namadivisi != null) {
			divisi.save();
			divisi();
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
		divisi();
	}	
//==========Mengelola USER==============//
	public static void user(){
		render();
	}
	public static void listuser(){
		List<User> user=User.findAll();
		render(user);
	}

	public static void tambahuser(User user){
		if (user.nama!=null) {
			user.save();
			user();
		}else{
			List<JenisKelamin> jk=JenisKelamin.findAll();
			List<Level> level=Level.findAll();
			render(jk,level);
		}
	}
	public static void ubahuser(long id){
		User user=User.findById(id);
		List<JenisKelamin> jk=JenisKelamin.findAll();
		List<Level> level=Level.findAll();
		render(user,jk,level);
	}
	public static void hapususer(long id){
		User user=User.findById(id);		
		user.delete();		
		user();
	}
	
}	
