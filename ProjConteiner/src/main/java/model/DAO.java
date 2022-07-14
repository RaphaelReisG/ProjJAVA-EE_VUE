package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/proj?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "123456";
	
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	
	public void inserirConteiner(Conteiner objConteiner) {
		String create = "INSERT INTO conteiner (numero, cliente, tipo, estado, categoria) values (?,?,?,?,?)";
		try {
			Connection con  = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			
			pst.setString(1, objConteiner.getNumero());
			pst.setString(2, objConteiner.getCliente());
			pst.setString(3, objConteiner.getTipo());
			pst.setString(4, objConteiner.getEstado());
			pst.setString(5, objConteiner.getCategoria());
			
			pst.executeUpdate();
			
			con.close();
			
			System.out.println("Inserido");			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void inserirMov(Movimentacao objMov) {
		String create = "INSERT INTO movimentacao (numero, tipoMov, inicio, fim) values (?,?,?,?)";
		try {
			Connection con  = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			
			pst.setString(1, objMov.getNumero());
			pst.setString(2, objMov.getTipoMov());
			pst.setString(3, objMov.getInicio());
			pst.setString(4, objMov.getFim());
			
			pst.executeUpdate();
			
			con.close();
			
			System.out.println("Inserido");			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<Conteiner> listaConteiners(){
		ArrayList<Conteiner> objConteiner = new ArrayList<>();
		
		String read = "SELECT * FROM conteiner ORDER BY cliente";
		try {
			Connection con  = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				String numero = rs.getString(1);
				String cliente = rs.getString(2);
				String tipo = rs.getString(3);
				String estado = rs.getString(4);
				String categoria = rs.getString(5);
				
				objConteiner.add(new Conteiner(numero, cliente, tipo, estado, categoria));
			}
			con.close();
			return objConteiner;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	public ArrayList<Movimentacao> listaMovimentacao(){
		ArrayList<Movimentacao> objMov = new ArrayList<>();
		
		String read = "SELECT * FROM movimentacao ORDER BY numero";
		try {
			Connection con  = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString(1);
				String numero = rs.getString(2);
				String tipoMov = rs.getString(3);
				String inicio = rs.getString(4);
				String fim = rs.getString(5);
				
				objMov.add(new Movimentacao(id, numero, tipoMov, inicio, fim));
			}
			con.close();
			return objMov;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	public void alterarConteiner(Conteiner objConteiner) {
		String create = "UPDATE conteiner SET numero=?, cliente=?, tipo=?, estado=?, categoria=? WHERE numero=?";
		try {
			Connection con  = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			
			pst.setString(1, objConteiner.getNumero());
			pst.setString(2, objConteiner.getCliente());
			pst.setString(3, objConteiner.getTipo());
			pst.setString(4, objConteiner.getEstado());
			pst.setString(5, objConteiner.getCategoria());
			pst.setString(6, objConteiner.getNumero());
			
			pst.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void alterarMov(Movimentacao objMov) {
		String create = "UPDATE movimentacao SET numero=?, tipoMov=?, inicio=?, fim=? WHERE id=? ";
		try {
			Connection con  = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			
			pst.setString(1, objMov.getNumero());
			pst.setString(2, objMov.getTipoMov());
			pst.setString(3, objMov.getInicio());
			pst.setString(4, objMov.getFim());
			pst.setString(5, objMov.getId());
			
			pst.executeUpdate();
			
			con.close();
			
			System.out.println("Alterado");			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void deletarConteiner(Conteiner objConteiner) {
		String delete = "DELETE FROM conteiner WHERE numero=?";
		String delete2 = "DELETE FROM movimentacao WHERE numero=?";
		
		try {
			Connection con  = conectar();
			
			PreparedStatement pst = con.prepareStatement(delete2);
			pst.setString(1, objConteiner.getNumero());
			pst.executeUpdate();
			
			pst = con.prepareStatement(delete);
			pst.setString(1, objConteiner.getNumero());
			pst.executeUpdate();
			
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void deletarMov(Movimentacao objMov) {
		String delete = "DELETE FROM movimentacao WHERE id=?";
		try {
			Connection con  = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			
			pst.setString(1, objMov.getId());
			
			
			pst.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	

}
