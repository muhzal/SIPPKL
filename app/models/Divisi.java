package models;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.google.gson.annotations.Expose;

import flexjson.JSONSerializer;
import play.db.jpa.Model;
@Entity
public class Divisi extends Model {
	public String namadivisi;
	public int kuota;
	
	@OneToMany(mappedBy="divisi",cascade=CascadeType.ALL)
	public Collection<Siswa> siswa;
}
