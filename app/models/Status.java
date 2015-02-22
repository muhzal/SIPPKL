package models;

import javax.persistence.Entity;

import com.google.gson.annotations.Expose;

import play.db.jpa.Model;
@Entity
public class Status extends Model {

	public String namastatus;
}
