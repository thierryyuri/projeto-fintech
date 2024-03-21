package br.com.vocealcanca.teste;

import java.util.Calendar;
import java.util.List;

import br.com.vocealcanca.bean.Renda;
import br.com.vocealcanca.dao.RendaDAO;
import br.com.vocealcanca.factory.DAOFactory;

public class RendaDAOTeste {

	public static void main(String[] args) {
		RendaDAO dao = DAOFactory.getRendaDAO();
		
		// Cadastrar
		Calendar dataInicio = Calendar.getInstance();
		dataInicio.set(2023, 5, 15); // Exemplo: 15 de junho de 2023

		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(2023, 11, 30); // Exemplo: 30 de dezembro de 2023

		float valor = 500;
		float valorTotal = 1000;
		String nome = "ExemploNome";
		String tipoReceita = "Renda";
		String descricao = "ExemploDescricao";
		int clienteIdReceita = 7;
		String fonte = "ExemploFonte";
		String tipoRenda = "ExemploTipoRenda";
		String periodicidade = "ExemploPeriodicidade";

		Renda renda = new Renda(valor, valorTotal, nome, tipoReceita, descricao, dataInicio, dataFinal, clienteIdReceita, fonte, tipoRenda, periodicidade);
		try {
			dao.cadastrar(renda);
			System.out.println("Renda cadastrada");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Atualizar
		renda = dao.buscar(3);
		renda.setTipoRenda("TipoAtualizado");
		renda.setPeriodicidade("Atualizada");
		try {
			dao.atualizar(renda);
			System.out.println("Renda atualizada");
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
		
		// Buscar por ID
		Renda rendaBuscada = dao.buscar(3);
		System.out.println(rendaBuscada.getValor());
		
		// Listar
		List<Renda> lista = dao.listar(7);
		
		for (Renda item : lista) {
		    System.out.println(item.getIdRenda());
		}

	}

}
