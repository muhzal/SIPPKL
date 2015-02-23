package controllers;

import java.util.List;

import models.Divisi;
import models.Pengajuan;
import play.mvc.Controller;

public class Home extends Controller {
	public static void index(){
		List divisi=Divisi.findAll();
		List pengajuan=Pengajuan.find("order by tglsurat asc").fetch(10);		
		render(divisi,pengajuan);
	}
}
