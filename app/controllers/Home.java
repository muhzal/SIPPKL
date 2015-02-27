package controllers;

import static play.modules.pdf.PDF.renderPDF;

import java.util.List;

import org.allcolor.yahp.converter.IHtmlToPdfTransformer;

import controllers.Secure.Security;
import models.Divisi;
import models.Pengajuan;
import models.Siswa;
import models.User;
import play.modules.pdf.PDF;
import play.modules.pdf.PDF.Options;
import play.modules.pdf.PDF.PDFDocument;
import play.mvc.Controller;
import play.mvc.With;
@With(Secure.class)
public class Home extends Controller {
	public static void index(){
		int jmlpengajuan=Pengajuan.findAll().size();
		int jmlsmk=Siswa.find("pengajuan.jenisinstansi_id.id=1 and status.id=5").fetch().size();
		int jmlmahasiswa=Siswa.find("pengajuan.jenisinstansi_id.id=2 and status.id=5").fetch().size();
		int jmlmenunggu=Siswa.find("status.id=4").fetch().size();
		List<Siswa> menunggu=Siswa.find("status.id=4 order by tglmulai ASC").fetch(10);
		List divisi=Divisi.findAll();
		List pengajuan=Pengajuan.find("order by tglsurat DESC").fetch(5);
		render(divisi,pengajuan,jmlpengajuan,jmlsmk,jmlmahasiswa,jmlmenunggu,menunggu);
	}
	public static User getNama(){
		User user=User.find("username", Security.connected()).first();
		return user;
	}
	public static void tentang(){
		render();
	}
	public static void listtunggu(){
		Siswas.tunggu();
	}
}
