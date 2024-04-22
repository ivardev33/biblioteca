package model;


import java.util.Date;


public class Prestamo {
	
	private long idPrestamo;
	private Date fchPrestamo;
	private Date fchDevolucion;
	private Libro libro;
	private Lector lector;
	
	
	public Prestamo(long idPrestamo, Date fchPrestamo, Date fchDevolucion) {
		
		this.setIdPrestamo(idPrestamo);
		this.setFchPrestamo(fchPrestamo);
		this.setFchDevolucion(fchDevolucion);
		
	}
	
	public Prestamo() {
		
	}


	public long getIdPrestamo() {
		return idPrestamo;
	}


	public void setIdPrestamo(long id) {
		this.idPrestamo = id;
	}


	public Date getFchPrestamo() {
		return fchPrestamo;
	}


	public void setFchPrestamo(Date date) {
		this.fchPrestamo = date;
	}


	public Date getFchDevolucion() {
		return fchDevolucion;
	}


	public void setFchDevolucion(Date date) {
		this.fchDevolucion = date;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Lector getLector() {
		return lector;
	}

	public void setLector(Lector lector) {
		this.lector = lector;
	}

}
