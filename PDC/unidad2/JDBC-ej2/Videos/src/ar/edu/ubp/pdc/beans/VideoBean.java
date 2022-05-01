package ar.edu.ubp.pdc.beans;

public class VideoBean {

	private String nomCategoria;
	private short nroCategoria;
	private String titulo;
	private String cantante;
	private String linkVideo;
	private int nroVideo;
	
	public String getNomCategoria() {
		return nomCategoria;
	}
	
	public void setNomCategoria(String nomCategoria) {
		this.nomCategoria = nomCategoria;
	}
	
	public short getNroCategoria() {
		return nroCategoria;
	}
	
	public void setNroCategoria(short nroCategoria) {
		this.nroCategoria = nroCategoria;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getCantante() {
		return cantante;
	}
	
	public void setCantante(String cantante) {
		this.cantante = cantante;
	}
	
	public String getLinkVideo() {
		return linkVideo;
	}
	
	public void setLinkVideo(String linkVideo) {
		this.linkVideo = linkVideo;
	}
	
	public int getNroVideo() {
		return nroVideo;
	}
	
	public void setNroVideo(int nroVideo) {
		this.nroVideo = nroVideo;
	}

}
