package br.com.vocealcanca.teste;

import java.util.Calendar;
import java.util.List;

import br.com.vocealcanca.bean.Investimento;
import br.com.vocealcanca.dao.InvestimentoDAO;
import br.com.vocealcanca.factory.DAOFactory;

public class InvestimentoDAOTeste {

	public static void main(String[] args) {
		InvestimentoDAO dao = DAOFactory.getInvestimentoDAO();
		
		float valor = 500.0f;
		float valorTotal = 1000.0f;
		String nome = "ExemploNome";
		String tipoReceita = "Investimento";
		String descricao = "ExemploDescricao";
		int clienteIdReceita = 7;
		float valorAporteMensal = 300;
		String tipoInvestimento = "ExemploTipo";
		int numAportesMensais = 3;

		// Crie uma instância de Calendar para as datas de início e final
		Calendar dataInicio = Calendar.getInstance();
		dataInicio.set(2023, 5, 15); // Exemplo: 15 de junho de 2023

		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(2023, 11, 30); // Exemplo: 30 de dezembro de 2023

		// Crie uma instância de Investimento com os valores das variáveis
		Investimento investimento = new Investimento(valor, valorTotal, nome, tipoReceita, descricao, dataInicio, dataFinal, clienteIdReceita, 
				valorAporteMensal, tipoInvestimento, numAportesMensais);
		
		try {
			dao.cadastrar(investimento);
			System.out.println("Investimento cadastrado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Atualizar
		investimento = dao.buscar(22);
		investimento.setTipoInvestimento("Tipo Legal");
		investimento.setNumAportesMensais(2);
		try {
			dao.atualizar(investimento);
			System.out.println("Investimento atualizado");
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
		Investimento investimentoBuscado = dao.buscar(22);
		System.out.println(investimentoBuscado.getValorAporteMensal());
		
		
		// Listar
		List<Investimento> lista = dao.listar(7);
		
		for (Investimento item : lista) {
		    System.out.println(item.getDataFinalString());
		}

	}

}
