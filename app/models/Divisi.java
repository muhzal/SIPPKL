package models;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
@Entity
public class Divisi extends Model {
	public String namadivisi;
	public int kuota;
	public int terisi;
	
	@OneToMany(mappedBy="divisi",cascade=CascadeType.ALL)
	public Collection<Siswa> siswa;
}
