package model;

public class Conteiner {
	private String numero;
	private String cliente;
	private String tipo;
	private String estado;
	private String categoria;
	
	public Conteiner() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Conteiner(String numero, String cliente, String tipo, String estado, String categoria) {
		super();
		this.numero = numero;
		this.cliente = cliente;
		this.tipo = tipo;
		this.estado = estado;
		this.categoria = categoria;
	}

	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
