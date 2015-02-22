package models;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.google.gson.annotations.Expose;

import play.data.binding.As;
import play.db.jpa.Model;
@Entity
public class Pengajuan extends Model {

	public String nosurat;

	public Date tglsurat;

	@ManyToOne(cascade=CascadeType.ALL)
	public JenisInstansi jenisinstansi_id;

	public String namainstansi;

	public long notelp;

	public int jumlahorang;

	@OneToMany(cascade=CascadeType.ALL,mappedBy="pengajuan")
	public Collection<Siswa> siswa;

	public boolean tampil;
	
}
