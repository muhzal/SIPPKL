package controllers;

import static play.modules.pdf.PDF.renderPDF;

import java.util.List;

import org.allcolor.yahp.converter.IHtmlToPdfTransformer;

import models.Divisi;
import models.Pengajuan;
import models.Siswa;
import play.modules.pdf.PDF;
import play.modules.pdf.PDF.Options;
import play.modules.pdf.PDF.PDFDocument;
import play.mvc.Controller;

public class Home extends Controller {
	public static void index(){
		int jmlpengajuan=Pengajuan.findAll().size();
		int jmlsmk=Siswa.find("pengajuan.jenisinstansi_id.id=1 and status.id=5").fetch().size();
		int jmlmahasiswa=Siswa.find("pengajuan.jenisinstansi_id.id=2 and status.id=5").fetch().size();
		List divisi=Divisi.findAll();
		List pengajuan=Pengajuan.find("order by tglsurat asc").fetch(10);
		render(divisi,pengajuan,jmlpengajuan,jmlsmk,jmlmahasiswa);
	}
	public static void pdf(){
		List siswa=Siswa.findAll();
		
		PDFDocument content=new PDFDocument();
//		content.template="Home/template.html";
//		Options option=new Options();
//		option.HEADER_TEMPLATE="Home/template.html";
//		option.pageSize = IHtmlToPdfTransformer.A4P;
		renderPDF(siswa);
	}
}
