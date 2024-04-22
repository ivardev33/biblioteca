package model;

public class Lector {
	
	private long idLector;
	private String nombre;
	private String apellido;
	private String email;
	
	
	public Lector(int idLector,String nombre,String apellido,String email) {
		
		this.setIdLector(idLector);
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setEmail(email);
		
	}
	
	public Lector() {
		
		
	}


	public long getIdLector() {
		return idLector;
	}


	public void setIdLector(long idLector) {
		this.idLector = idLector;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}



}
