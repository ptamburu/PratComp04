package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pais;
import service.PaisService;

/**
 * Servlet implementation class ManterPaisController
 */
@WebServlet("/ManterPais.do")
public class ManterPaisController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Nome = request.getParameter("nome");
		String Populacao = request.getParameter("populacao");
		String Area = request.getParameter("area");
		long populacao = Long.parseLong(Populacao);
		double area = Double.parseDouble(Area);
		
		//instanciar o javabean
		Pais pais = new Pais();
		pais.setNome(Nome);
		pais.setPopulacao(populacao);
		pais.setArea(area);
		
		//instanciar o service
		PaisService paisS = new PaisService();
		paisS.criar(pais);
		pais = paisS.carregar(pais.getId());
		
		//enviar para o jsp
		request.setAttribute("pais", pais);
		
		RequestDispatcher view =
		request.getRequestDispatcher("Pais.jsp");
		view.forward(request, response);
	}

}
