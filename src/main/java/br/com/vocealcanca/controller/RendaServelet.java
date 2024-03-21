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

import br.com.vocealcanca.bean.Renda;
import br.com.vocealcanca.dao.RendaDAO;
import br.com.vocealcanca.exception.DBException;
import br.com.vocealcanca.factory.DAOFactory;

/**
 * Servlet implementation class MetaServelet
 */
@WebServlet("/RendaServelet")
public class RendaServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RendaDAO dao = DAOFactory.getRendaDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RendaServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		String acao = request.getParameter("acao");
        	switch (acao) {
    		case "listar":
    			listar(request, response);
    			break;
    		case "abrir-form-edicao":
    			int id = Integer.parseInt(request.getParameter("idRenda"));
    			Renda renda = dao.buscar(id);
    			request.setAttribute("renda", renda);
    			request.getRequestDispatcher("./views/renda/edicao-renda.jsp").forward(request, response);
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
		
		List<Renda> lista = dao.listar(idCliente);
		request.setAttribute("rendas", lista);
		request.getRequestDispatcher("./views/renda/lista-rendas.jsp").forward(request, response);
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
			String tipoRenda = request.getParameter("tipoRenda");
			String periodicidade = request.getParameter("periodicidade");
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataInicio = Calendar.getInstance();
			Calendar dataFinal = Calendar.getInstance();
			
			dataInicio.setTime(format.parse(request.getParameter("dataInicio")));
			dataFinal.setTime(format.parse(request.getParameter("dataFinal")));
			
			
			
			Renda renda = new Renda(valor, valorTotal, nome, "Renda", descricao, dataInicio, dataFinal, idCliente, fonte, tipoRenda, periodicidade);
			dao.cadastrar(renda);
			
			request.setAttribute("msg", "Renda Cadastrada!");
		} catch (DBException e){
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados!");
		}
		request.getRequestDispatcher("./views/renda/cadastro-renda.jsp").forward(request, response);
		
		doGet(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int idCliente = getCookies(request);
			
			int idRenda = Integer.parseInt(request.getParameter("idRenda"));
			int idReceita = Integer.parseInt(request.getParameter("idReceita"));
			
			String nome = request.getParameter("nome");
			float valor = Float.parseFloat(request.getParameter("valor"));
			float valorTotal = Float.parseFloat(request.getParameter("valorTotal"));
			String fonte = request.getParameter("fonte");
			String descricao = request.getParameter("descricao");
			String tipoRenda = request.getParameter("tipoRenda");
			String periodicidade = request.getParameter("periodicidade");
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataInicio = Calendar.getInstance();
			Calendar dataFinal = Calendar.getInstance();
			
			dataInicio.setTime(format.parse(request.getParameter("dataInicio")));
			dataFinal.setTime(format.parse(request.getParameter("dataFinal")));
			
			Renda renda = new Renda(idReceita, valor, valorTotal, nome, "Renda", descricao, dataInicio, dataFinal, idCliente, idRenda, fonte, tipoRenda, periodicidade);
			dao.atualizar(renda);
			
			request.setAttribute("msg", "Renda Atualizada!");
		} catch (DBException e){
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados!");
		}
		request.getRequestDispatcher("./views/renda/edicao-renda.jsp").forward(request, response);
		
		doGet(request, response);
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		try {
			dao.remover(codigo);
			request.setAttribute("msg", "Renda removida!");
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

