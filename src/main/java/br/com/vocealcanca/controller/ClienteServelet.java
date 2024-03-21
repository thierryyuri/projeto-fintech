package br.com.vocealcanca.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.vocealcanca.bean.Cliente;
import br.com.vocealcanca.dao.ClienteDAO;
import br.com.vocealcanca.exception.DBException;
import br.com.vocealcanca.factory.DAOFactory;

/**
 * Servlet implementation class ClienteServelet
 */
@WebServlet("/ClienteServelet")
public class ClienteServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteDAO dao = DAOFactory.getClienteDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteServelet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		int id;
		
		switch (acao) {
		case "listar":
			int idCliente = getCookies(request);
			Cliente lista = dao.buscar(idCliente);
			request.setAttribute("cliente", lista);
			request.getRequestDispatcher("./views/cliente/dados-cliente.jsp").forward(request, response);
			break;
		case "abrir-form-edicao":
			id = Integer.parseInt(request.getParameter("idCliente"));
			Cliente cliente = dao.buscar(id);
			request.setAttribute("cliente", cliente);
			request.getRequestDispatcher("./views/cliente/edicao-cliente.jsp").forward(request, response);
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
		case "login":
			login(request, response);
			break;
		}
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			String nome = request.getParameter("nome");
			String cpf = request.getParameter("cpf");
			String endereco = request.getParameter("endereco");
			String telefone = request.getParameter("telefone");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.setTime(format.parse(request.getParameter("dataNascimento")));
			
			Cliente cliente = new Cliente(data, nome, telefone, email, senha, endereco, cpf);
			dao.cadastrar(cliente);
			
			Cliente clienteBanco = dao.buscarPorCpf(cpf);
			
			int idCliente = clienteBanco.getIdCliente();
			Cookie idClienteCookie = new Cookie("idCliente", String.valueOf(idCliente));
			response.addCookie(idClienteCookie);
			
			request.setAttribute("msg", "Cliente Cadastrado!");
			request.getRequestDispatcher("./views/cliente/login-cliente.jsp").forward(request, response);
		} catch (DBException e){
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados!");
		}
		request.getRequestDispatcher("./views/cliente/cadastro-usuario.jsp").forward(request, response);
		
		doGet(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idCliente = Integer.parseInt(request.getParameter("idCliente"));
			
			String nome = request.getParameter("nome");
			String cpf = request.getParameter("cpf");
			String endereco = request.getParameter("endereco");
			String telefone = request.getParameter("telefone");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.setTime(format.parse(request.getParameter("dataNascimento")));

			Cliente cliente = new Cliente(idCliente, data, nome, telefone, email, senha, endereco, cpf);
			dao.atualizar(cliente);

			request.setAttribute("msg", "Cliente atualizado!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		request.getRequestDispatcher("./views/cliente/edicao-cliente.jsp").forward(request, response);
		
		doGet(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String cpf = request.getParameter("cpf");
			String senha = request.getParameter("senha");
			
			Cliente clienteAutenticar = new Cliente(cpf, senha);
			
			if(dao.validarUsuario(clienteAutenticar)) {
				Cliente clienteBanco = dao.buscarPorCpf(cpf);
				
				int idCliente = clienteBanco.getIdCliente();
				Cookie idClienteCookie = new Cookie("idCliente", String.valueOf(idCliente));
				response.addCookie(idClienteCookie);
				
				
				request.setAttribute("msg", "Cliente logado!");
				
				request.getRequestDispatcher("pagina-principal.jsp").forward(request, response);
			} else {
				request.setAttribute("erro", "Por favor, valide os dados!");
				request.getRequestDispatcher("./views/cliente/login-cliente.jsp").forward(request, response);
			};
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados!");
		}
		
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
