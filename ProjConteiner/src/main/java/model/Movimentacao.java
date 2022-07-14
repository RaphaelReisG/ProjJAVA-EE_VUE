package model;

public class Movimentacao {
	private String id;
	private String numero;
	private String tipoMov;
	private String inicio;
	private String fim;
	
	public Movimentacao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Movimentacao(String id, String numero, String tipoMov, String inicio, String fim) {
		super();
		this.id = id;
		this.numero = numero;
		this.tipoMov = tipoMov;
		this.inicio = inicio;
		this.fim = fim;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTipoMov() {
		return tipoMov;
	}
	public void setTipoMov(String tipoMov) {
		this.tipoMov = tipoMov;
	}
	public String getInicio() {
		return inicio;
	}
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	public String getFim() {
		return fim;
	}
	public void setFim(String fim) {
		this.fim = fim;
	}
}
