package cn.com.karl.music;

public class St {
	private int id;
	 private String name;
	 private String pass;
	 private String gender;
	 private String country;
	 
	 public St(int id, String name, String pass,String gender,String country) {
	  this.id = id;
	  this.name = name;
	  this.pass = pass;
	  this.gender = gender;
	  this.country = country;
	  
	 }
	 
	 public int getId() {
	  return id;
	 }
	 
	 public void setId(int id) {
	  this.id = id;
	 }
	 
	 public String getName() {
	  return name;
	 }
	 
	 public void setName(String name) {
	  this.name = name;
	 }
	 public String getPass() {
		  return pass;
		 }
		 
		 public void setPass(String pass) {
		  this.pass = pass;
		 }
	 public String getGender() {
	  return gender;
	 }
	 
	 public void setGender(String gender) {
	  this.gender = gender;
	 }
	 public String getCountry() {
		  return country;
		 }
		 
		 public void setCountry(String country) {
		  this.country = country;
		 }
		
	 
	 @Override
	 public String toString() {
	  return "St{" +
	    "id=" + id +
	    ", name='" + name + '\'' +
	    ", pass='" + pass + '\'' +
	    ", gender='" + gender + '\'' +
	    ", country='" + country + '\'' +
	    '}';
	 }
}
