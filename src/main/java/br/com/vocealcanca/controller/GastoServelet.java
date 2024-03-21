package br.com.vocealcanca.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.vocealcanca.bean.Gasto;
import br.com.vocealcanca.dao.GastoDAO;
import br.com.vocealcanca.exception.DBException;
import br.com.vocealcanca.factory.DAOFactory;

/**
 * Servlet implementation class GastoServelet
 */
@WebServlet("/GastoServelet")
public class GastoServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GastoDAO dao = DAOFactory.getGastoDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GastoServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
    	switch (acao) {
		case "listar":
			listar(request, response);
			break;
		case "abrir-form-edicao":
			int id = Integer.parseInt(request.getParameter("idGasto"));
			Gasto gasto = dao.buscar(id);
			request.setAttribute("gasto", gasto);
			request.getRequestDispatcher("./views/gasto/edicao-gasto.jsp").forward(request, response);
		}		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		switch (acao) {
		case "cadastrar":
			cadastrar(request, response);
			break;
		case "editar":
			editar(request, response);
			break;
		case "excluir":
			excluir(request, response);
		}	
	
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idCliente = getCookies(request);
		
		List<Gasto> lista = dao.listar(idCliente);
		request.setAttribute("gastos", lista);
		request.getRequestDispatcher("./views/gasto/lista-gastos.jsp").forward(request, response);
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int idCliente = getCookies(request);
			
			String nome = request.getParameter("nome");
			float valor = Float.parseFloat(request.getParameter("valor"));
			float valorTotal = Float.parseFloat(request.getParameter("valorTotal"));
			String fonte = request.getParameter("fonte");
			String descricao = request.getParameter("descricao");
			String tipoGasto = request.getParameter("tipoGasto");
			String periodicidade = request.getParameter("periodicidade");
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataInicio = Calendar.getInstance();
			Calendar dataFinal = Calendar.getInstance();
			
			dataInicio.setTime(format.parse(request.getParameter("dataInicio")));
			dataFinal.setTime(format.parse(request.getParameter("dataFinal")));
			
			
			
			Gasto gasto = new Gasto(valor, valorTotal, nome, "Gasto", descricao, dataInicio, dataFinal, idCliente, fonte, tipoGasto, periodicidade);
			dao.cadastrar(gasto);
			
			request.setAttribute("msg", "Gasto Cadastrado!");
		} catch (DBException e){
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados!");
		}
		request.getRequestDispatcher("./views/gasto/cadastro-gasto.jsp").forward(request, response);
		
		doGet(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int idCliente = getCookies(request);
			
			int idGasto = Integer.parseInt(request.getParameter("idGasto"));
			int idReceita = Integer.parseInt(request.getParameter("idReceita"));
			
			String nome = request.getParameter("nome");
			float valor = Float.parseFloat(request.getParameter("valor"));
			float valorTotal = Float.parseFloat(request.getParameter("valorTotal"));
			String fonte = request.getParameter("fonte");
			String descricao = request.getParameter("descricao");
			String tipoGasto = request.getParameter("tipoGasto");
			String periodicidade = request.getParameter("periodicidade");
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataInicio = Calendar.getInstance();
			Calendar dataFinal = Calendar.getInstance();
			
			dataInicio.setTime(format.parse(request.getParameter("dataInicio")));
			dataFinal.setTime(format.parse(request.getParameter("dataFinal")));
			
			
			Gasto gasto = new Gasto(idReceita, valor, valorTotal, nome, "Gasto", descricao, dataInicio, dataFinal, idCliente, idGasto, fonte, tipoGasto, periodicidade);
			dao.atualizar(gasto);
			
			request.setAttribute("msg", "Gasto Atualizado!");
		} catch (DBException e){
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados!");
		}
		request.getRequestDispatcher("./views/gasto/edicao-gasto.jsp").forward(request, response);
		
		doGet(request, response);
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		try {
			dao.remover(codigo);
			request.setAttribute("msg", "Gasto removido!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao remover");
		}
		request.getRequestDispatcher("pagina-principal.jsp").forward(request, response);
		
		doGet(request, response);
	}
	
	private int getCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    int idCliente = 0;
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("idCliente")) {
	                idCliente = Integer.parseInt(cookie.getValue());
	                break;
	            }
	        }
	    }
		return idCliente;
	}

}
