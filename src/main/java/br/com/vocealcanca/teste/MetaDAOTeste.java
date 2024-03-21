package br.com.vocealcanca.teste;

import java.util.List;

import br.com.vocealcanca.bean.Meta;
import br.com.vocealcanca.dao.MetaDAO;
import br.com.vocealcanca.factory.DAOFactory;

public class MetaDAOTeste {

	public static void main(String[] args) {
		MetaDAO dao = DAOFactory.getMetaDAO();
		
		// Cadastrar
		int IdCliente = 7;
		float valorMeta = 1000;
		String nome = "ExemploNome";
		String descricao = "ExemploDescricao";
		String tipoMeta = "ExemploTipoMeta";

		Meta meta = new Meta(IdCliente, tipoMeta, descricao, valorMeta, nome);
		try {
			dao.cadastrar(meta);
			System.out.println("Meta cadastrada");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Atualizar
//		meta = dao.buscar(6);
//		meta.setTipoMeta("TipoAtualizado");
//		meta.setValorMeta(1232);
//		try {
//			dao.atualizar(meta);
//			System.out.println("Meta atualizada");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		// Remover
		try {
			dao.remover(3);
			System.out.println("Removido");
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		// Buscar por ID
//		Meta metaBuscada = dao.buscar(6);
//		System.out.println(metaBuscada.getNome());
		
		// Listar
		List<Meta> lista = dao.listar(1);
		
		for (Meta item : lista) {
		    System.out.println(item.getNome());
		}

	}

}
