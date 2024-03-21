package br.com.vocealcanca.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.vocealcanca.bean.Meta;
import br.com.vocealcanca.dao.MetaDAO;
import br.com.vocealcanca.exception.DBException;
import br.com.vocealcanca.factory.DAOFactory;

/**
 * Servlet implementation class MetaServelet
 */
@WebServlet("/MetaServelet")
public class MetaServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MetaDAO dao = DAOFactory.getMetaDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MetaServelet() {
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
			int id = Integer.parseInt(request.getParameter("codigo"));
			Meta meta = dao.buscar(id);
			request.setAttribute("meta", meta);
			request.getRequestDispatcher("./views/meta/edicao-meta.jsp").forward(request, response);
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
		
		List<Meta> lista = dao.listar(idCliente);
		request.setAttribute("metas", lista);
		request.getRequestDispatcher("./views/meta/lista-meta.jsp").forward(request, response);
	}

	

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int idCliente = getCookies(request);
			String nome = request.getParameter("nome");
			float valor = Float.parseFloat(request.getParameter("valor"));
			String tipo = request.getParameter("tipo");
			String descricao = request.getParameter("descricao");
			
			
			Meta meta = new Meta(idCliente, tipo, descricao, valor, nome);
			dao.cadastrar(meta);
			
			request.setAttribute("msg", "Meta Cadastrada!");
		} catch (DBException e){
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados!");
		}
		request.getRequestDispatcher("./views/meta/cadastro-meta.jsp").forward(request, response);
		
		doGet(request, response);
	}
	
	
	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int idCliente = getCookies(request);
			
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			String nome = request.getParameter("nome");
			float valor = Float.parseFloat(request.getParameter("valor"));
			String tipo = request.getParameter("tipo");
			String descricao = request.getParameter("descricao");
			
			
			Meta meta = new Meta(idCliente, codigo, tipo, descricao, valor, nome);
			dao.atualizar(meta);
			
			request.setAttribute("msg", "Meta Atualizada!");
		} catch (DBException e){
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados!");
		}
		request.getRequestDispatcher("./views/meta/edicao-meta.jsp").forward(request, response);
		
		doGet(request, response);
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		try {
			dao.remover(codigo);
			request.setAttribute("msg", "Meta removida!");
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
