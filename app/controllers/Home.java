package controllers;

import java.util.List;

import models.Divisi;
import models.Pengajuan;
import play.mvc.Controller;

public class Home extends Controller {
	public static void index(){
		List divisi=Divisi.findAll();
		List pengajuan=Pengajuan.findAll();		
		render(divisi,pengajuan);
	}
}
