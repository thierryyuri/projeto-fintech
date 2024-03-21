package br.com.vocealcanca.teste;

//import java.util.Calendar;
import java.util.List;

import br.com.vocealcanca.bean.Gasto;
import br.com.vocealcanca.dao.GastoDAO;
import br.com.vocealcanca.factory.DAOFactory;

public class GastoDAOTeste {
	public static void main(String[] args) {
		GastoDAO dao = DAOFactory.getGastoDAO();
		
		// Cadastrar
//		Calendar dataInicio = Calendar.getInstance();
//		dataInicio.set(2023, 5, 15); // Exemplo: 15 de junho de 2023
//
//		Calendar dataFinal = Calendar.getInstance();
//		dataFinal.set(2023, 11, 30); // Exemplo: 30 de dezembro de 2023
//
//		float valor = 500.0f;
//		float valorTotal = 1000.0f;
//		String nome = "ExemploNome";
//		String tipoReceita = "Gasto";
//		String descricao = "ExemploDescricao";
//		int clienteIdReceita = 7;
//		String fonte = "ExemploFonte";
//		String tipoGasto = "ExemploTipoGasto";
//		String periodicidade = "ExemploPeriodicidade";
//
//		Gasto gasto = new Gasto(valor, valorTotal, nome, tipoReceita, descricao, dataInicio, dataFinal, clienteIdReceita, fonte, tipoGasto, periodicidade);
//		try {
//			dao.cadastrar(gasto);
//			System.out.println("Gasto cadastrado");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		// Atualizar
//		gasto = dao.buscar(22);
//		gasto.setTipoGasto("TipoAtualizado");
//		gasto.setPeriodicidade("Atualizada");
//		try {
//			dao.atualizar(gasto);
//			System.out.println("Gasto atualizado");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		// Remover
		try {
			dao.remover(2);
			System.out.println("Removido");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// Buscar por ID
//		Gasto gastoBuscado = dao.buscar(3);
//		System.out.println(gastoBuscado.getValor());
		
		
		// Listar
		List<Gasto> lista = dao.listar(7);
		
		for (Gasto item : lista) {
		    System.out.println(item.getDataInicioString());
		}
	}
}
