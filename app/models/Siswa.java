package models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.google.gson.annotations.Expose;

import play.db.jpa.Model;
@Entity
public class Siswa extends Model {
	
	public String nama;
	
	@ManyToOne
	public JenisKelamin jeniskelamin;
	
	@ManyToOne
	public Divisi divisi;
	
	public String jurusan;
	@ManyToOne	
	public Pengajuan pengajuan;
	
	@ManyToOne
	public Status status;
	
	public Date tglmulai;
	
	public Date tglselesai;
}
