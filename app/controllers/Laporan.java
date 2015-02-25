package controllers;
import static play.modules.pdf.PDF.renderPDF;

import java.util.Date;
import java.util.List;

import org.allcolor.yahp.converter.IHtmlToPdfTransformer;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer.PageSize;

import models.JenisInstansi;
import models.Siswa;
import models.Status;
import play.data.binding.As;
import play.modules.pdf.PDF;
import play.modules.pdf.PDF.Options;
import play.modules.pdf.RenderPDFTemplate;
import play.mvc.Controller;

public class Laporan extends Controller {
	public static void index(){
		List<JenisInstansi> jenis=JenisInstansi.findAll();
		List<Status> status=Status.findAll();
		render(jenis,status);
	}
	public static void cetak(long jenis,@As("dd-MM-yyyy") Date tglmulai, @As("dd-MM-yyyy") Date tglselesai,String nik,String jabatan,String atasnama){
		Options option=new Options();
		option.pageSize=IHtmlToPdfTransformer.A4P;
		List<Siswa> siswa=Siswa.find("pengajuan.jenisinstansi_id.id=? and tglmulai BETWEEN ? AND ? order by pengajuan",jenis,tglmulai,tglselesai).fetch();
		//List<Siswa> siswa=Siswa.find("order by pengajuan").fetch();
		renderPDF(siswa,option,tglmulai,tglselesai,atasnama,jabatan,nik,jenis);
	}
}
