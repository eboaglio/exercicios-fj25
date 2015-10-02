package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.ContaDAO;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestaRemoveConta {

	public static void main(String[] args) {
		
		long inicio = System.currentTimeMillis();
		
		EntityManager manager = new JPAUtil().getEntityManager();
		ContaDAO dao = new ContaDAO(manager);
		manager.getTransaction().begin();

		Conta conta = dao.busca(7);
		System.out.println("Conta - titular " + conta.getTitular());
		dao.remove(conta);
		dao.salva();
		manager.close();

		long fim = System.currentTimeMillis();
		System.out.println("Executado em " + (fim - inicio) + "ms");
	}
}
