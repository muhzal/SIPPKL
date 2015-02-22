package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;
@Entity
public class User extends Model {
	public String nama;
	public String nip;
	public String username;
	public String password;
	@ManyToOne
	public JenisKelamin jeniskelamin_id;	
	public String jabatan;
	@ManyToOne
	public Level level_id;
}
