package br.com.vocealcanca.teste;

import java.util.Calendar;
import java.util.List;

import br.com.vocealcanca.bean.Cliente;
import br.com.vocealcanca.dao.ClienteDAO;
import br.com.vocealcanca.factory.DAOFactory;

public class ClienteDAOTeste {

	public static void main(String[] args) {
		ClienteDAO dao = DAOFactory.getClienteDAO();
		
		// Cadastrar
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(2004, 5, 15); // Exemplo: 15 de junho de 2004
		
		String nome = "ExemploNome";
		String telefone = "(11) 99999-9999";
		String email = "email@gmail.com";
		String senha = "senhaTeste";
		String endereco = "Endereco teste, 450";
		String cpf = "99999999999";

		Cliente cliente = new Cliente(dataNascimento, nome, telefone, email, senha, endereco, cpf);
		try {
			dao.cadastrar(cliente);
			System.out.println("Cliente cadastrado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Atualizar
		cliente = dao.buscar(23);
		cliente.setNome("Nome Atualizado");
		cliente.setTelefone("(11) 88888-8888");
		try {
			dao.atualizar(cliente);
			System.out.println("Cliente atualizado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Remover
		try {
			dao.remover(2);
			System.out.println("Removido");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Remover
		try {
			dao.remover(8);
			System.out.println("Removido");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Buscar por ID
		Cliente clienteBuscado = dao.buscar(7);
		System.out.println(clienteBuscado.getNome());
		
		// Listar
		List<Cliente> lista = dao.listar();
		
		for (Cliente item : lista) {
		    System.out.println(item.getDataNascimentoString());
		}
		
		//Login
		Cliente cliente2 = new Cliente(email, "aaa");
		System.out.println(dao.validarUsuario(cliente2));

	}

}
