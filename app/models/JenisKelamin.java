package models;

import javax.persistence.Entity;

import com.google.gson.annotations.Expose;

import play.db.jpa.Model;
@Entity
public class JenisKelamin extends Model {	 
	public String namakelamin;	 
	public String kode;	 
}
