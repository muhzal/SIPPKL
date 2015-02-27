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
import play.mvc.With;
@With(Secure.class)
public class Laporan extends Controller {
	public static void index(){
		List<JenisInstansi> jenis=JenisInstansi.findAll();
		List<Status> status=Status.findAll();
		render(jenis,status);
	}
	public static void cetak(long status,long jenis,@As("dd-MM-yyyy") Date tglmulai, @As("dd-MM-yyyy") Date tglselesai,String nik,String jabatan,String atasnama){
		Options option=new Options();
		List<Siswa> siswa=Siswa.find("tglmulai BETWEEN ? AND ? order by pengajuan",tglmulai,tglselesai).fetch();;
		option.pageSize=IHtmlToPdfTransformer.A4P;
		if(jenis!=3 && status!=0){
		siswa=Siswa.find("pengajuan.jenisinstansi_id.id=? and status.id=? and tglmulai BETWEEN ? AND ? order by pengajuan",status,jenis,tglmulai,tglselesai).fetch();
		}else if(jenis==3 && status!=0){
			siswa=Siswa.find("status.id=? and tglmulai BETWEEN ? AND ? order by pengajuan",status,tglmulai,tglselesai).fetch();			
		}else if(jenis!=3 && status==0){
			siswa=Siswa.find("pengajuan.jenisinstansi_id.id=? and tglmulai BETWEEN ? AND ? order by pengajuan.tglsurat",jenis,tglmulai,tglselesai).fetch();			
		}
		//List<Siswa> siswa=Siswa.find("order by pengajuan").fetch();
		renderPDF(siswa,option,tglmulai,tglselesai,atasnama,jabatan,nik,jenis);
	}
	public static void sertifikat(long id,String nik,String jabatan,String atasnama){
		Siswa siswa=Siswa.findById(id);		
		Options option=new Options();
		option.pageSize=IHtmlToPdfTransformer.A4L;
		renderPDF(siswa,option,nik,jabatan,atasnama);
	}
}
