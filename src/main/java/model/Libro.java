package model;

public class Libro {
	
	private long idLibro;
	private String titulo;
	private String autor;
	private int anoPublicacion;
	private boolean disponible;
	
	public Libro(int idLibro,String titulo,String autor,int anoPublicacion,boolean disponible) {
		
		this.setIdLibro(idLibro);
		this.setTitulo(titulo);
		this.setAutor(autor);
		this.setAnoPublicacion(anoPublicacion);
		this.setDisponible(disponible);
		
		
		
	}
	
	public Libro() {
		
	}

	public long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(long idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAnoPublicacion() {
		return anoPublicacion;
	}

	public void setAnoPublicacion(int anoPublicacion) {
		this.anoPublicacion = anoPublicacion;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	

}
