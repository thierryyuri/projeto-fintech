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

import br.com.vocealcanca.bean.Investimento;
import br.com.vocealcanca.dao.InvestimentoDAO;
import br.com.vocealcanca.exception.DBException;
import br.com.vocealcanca.factory.DAOFactory;

/**
 * Servlet implementation class InvestimentoServelet
 */
@WebServlet("/InvestimentoServelet")
public class InvestimentoServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InvestimentoDAO dao = DAOFactory.getInvestimentoDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvestimentoServelet() {
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
			int id = Integer.parseInt(request.getParameter("idInvestimento"));
			Investimento investimento = dao.buscar(id);
			request.setAttribute("investimento", investimento);
			request.getRequestDispatcher("./views/investimento/edicao-investimento.jsp").forward(request, response);
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
		
		List<Investimento> lista = dao.listar(idCliente);
		request.setAttribute("investimentos", lista);
		request.getRequestDispatcher("./views/investimento/lista-investimentos.jsp").forward(request, response);
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int idCliente = getCookies(request);
			
			String nome = request.getParameter("nome");
			float valor = Float.parseFloat(request.getParameter("valor"));
			float valorTotal = Float.parseFloat(request.getParameter("valorTotal"));
			String descricao = request.getParameter("descricao");
			String tipoInvestimento = request.getParameter("tipoInvestimento");
			float valorAporteMensal = Float.parseFloat(request.getParameter("valorAporteMensal"));
			int numAportesMensais = Integer.parseInt(request.getParameter("numAportesMensais"));
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataInicio = Calendar.getInstance();
			Calendar dataFinal = Calendar.getInstance();
			
			dataInicio.setTime(format.parse(request.getParameter("dataInicio")));
			dataFinal.setTime(format.parse(request.getParameter("dataFinal")));
			
			Investimento investimento = new Investimento(valor, valorTotal, nome, "Investimento", descricao, dataInicio, dataFinal, idCliente, valorAporteMensal, tipoInvestimento, numAportesMensais);
			dao.cadastrar(investimento);
			
			request.setAttribute("msg", "Investimento Cadastrado!");
		} catch (DBException e){
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados!");
		}
		request.getRequestDispatcher("./views/investimento/cadastro-investimento.jsp").forward(request, response);
		
		doGet(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int idCliente = getCookies(request);
			
			int idInvestimento = Integer.parseInt(request.getParameter("idInvestimento"));
			int idReceita = Integer.parseInt(request.getParameter("idReceita"));
			
			String nome = request.getParameter("nome");
			float valor = Float.parseFloat(request.getParameter("valor"));
			float valorTotal = Float.parseFloat(request.getParameter("valorTotal"));
			String descricao = request.getParameter("descricao");
			String tipoInvestimento = request.getParameter("tipoInvestimento");
			float valorAporteMensal = Float.parseFloat(request.getParameter("valorAporteMensal"));
			int numAportesMensais = Integer.parseInt(request.getParameter("numAportesMensais"));
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataInicio = Calendar.getInstance();
			Calendar dataFinal = Calendar.getInstance();
			
			dataInicio.setTime(format.parse(request.getParameter("dataInicio")));
			dataFinal.setTime(format.parse(request.getParameter("dataFinal")));
			
			Investimento investimento = new Investimento(idReceita, valor, valorTotal, nome, "Investimento", descricao, dataInicio, dataFinal, idCliente, idInvestimento, valorAporteMensal, tipoInvestimento, numAportesMensais);
			dao.atualizar(investimento);
			
			request.setAttribute("msg", "Investimento Atualizado!");
		} catch (DBException e){
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados!");
		}
		request.getRequestDispatcher("./views/investimento/edicao-investimento.jsp").forward(request, response);
		
		doGet(request, response);
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		try {
			dao.remover(codigo);
			request.setAttribute("msg", "Investimento removido!");
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
