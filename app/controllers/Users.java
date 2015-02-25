package controllers;

import java.util.List;

import models.Divisi;
import models.JenisKelamin;
import models.Level;
import models.Siswa;
import models.User;
import play.mvc.Controller;

public class Users extends Controller {
//==========Mengelola Divisi==========//	

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
