package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Conteiner;
import model.DAO;
import model.Movimentacao;


@WebServlet(urlPatterns = {"/controller", "/main", "/inserirConteiner", "/inserirMov", "/updateConteiner", "/updateMov", "/deleteConteiner", "/deleteMov"})

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DAO dao = new DAO();
	Conteiner objConteiner = new Conteiner();
	Movimentacao objMov = new Movimentacao();
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		
		if(action.equals("/main")) {
			home(request, response);
		}
		else if(action.equals("/inserirConteiner")) {
			novoConteiner(request, response);
		}
		else if(action.equals("/inserirMov")) {
			novoMov(request, response);
		}
		else if(action.equals("/updateConteiner")) {
			editarConteiner(request, response);
		}
		else if(action.equals("/updateMov")) {
			editarMov(request, response);
		}
		else if(action.equals("/deleteConteiner")) {
			removerConteiner(request, response);
		}
		else if(action.equals("/deleteMov")) {
			removerMov(request, response);
		}
		
		
	}
	
	protected void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Conteiner> listaConteiner = dao.listaConteiners();
		ArrayList<Movimentacao> listaMov = dao.listaMovimentacao();
		
		request.setAttribute("conteiners", listaConteiner);
		request.setAttribute("movimentacoes", listaMov);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		rd.forward(request, response);
	}
	
	protected void novoConteiner(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inserindo.....");
		
		
		objConteiner.setNumero(request.getParameter("numero"));
		objConteiner.setCliente(request.getParameter("cliente"));
		objConteiner.setTipo(request.getParameter("tipo"));
		objConteiner.setEstado(request.getParameter("estado"));
		objConteiner.setCategoria(request.getParameter("categoria"));
		
		dao.inserirConteiner(objConteiner);
		
		response.sendRedirect("main");
		
	}
	
	protected void novoMov(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inserindo.....");
		
		
		objMov.setNumero(request.getParameter("numero"));
		objMov.setTipoMov(request.getParameter("tipoMov"));
		objMov.setInicio(request.getParameter("inicio"));
		objMov.setFim(request.getParameter("fim"));
		
		dao.inserirMov(objMov);
		
		response.sendRedirect("main");
		
	}
	
	protected void editarConteiner(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Editando.....");
		
		
		objConteiner.setNumero(request.getParameter("numero"));
		objConteiner.setCliente(request.getParameter("cliente"));
		objConteiner.setTipo(request.getParameter("tipo"));
		objConteiner.setEstado(request.getParameter("estado"));
		objConteiner.setCategoria(request.getParameter("categoria"));
		
		dao.alterarConteiner(objConteiner);
		
		response.sendRedirect("main");
		
	}
	
	protected void editarMov(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("editando.....");
		
		objMov.setId(request.getParameter("id"));
		objMov.setNumero(request.getParameter("numero"));
		objMov.setTipoMov(request.getParameter("tipoMov"));
		objMov.setInicio(request.getParameter("inicio"));
		objMov.setFim(request.getParameter("fim"));
		
		dao.alterarMov(objMov);
		
		response.sendRedirect("main");
		
	}
	
	protected void removerConteiner(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		objConteiner.setNumero(request.getParameter("numero"));
		
		
		dao.deletarConteiner(objConteiner);
		
		response.sendRedirect("main");
		
	}
	
	protected void removerMov(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		objMov.setId(request.getParameter("id"));
		
		
		dao.deletarMov(objMov);
		
		response.sendRedirect("main");
		
	}
	
	
	

}
