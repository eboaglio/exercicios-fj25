package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.ContaDAO;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestaInsereConta {

	public static void main(String[] args) {
		
		long inicio = System.currentTimeMillis();
		
		EntityManager manager = new JPAUtil().getEntityManager();
		ContaDAO dao = new ContaDAO(manager);

		Conta conta = new Conta();
		conta.setTitular("Eliana Boaglio");
		conta.setBanco("Santander");
		conta.setAgencia("4550");
		conta.setNumero("123456-6");

		manager.getTransaction().begin();
		dao.adiciona(conta);
		dao.salva();
		manager.close();

		long fim = System.currentTimeMillis();
		System.out.println("Executado em " + (fim - inicio) + "ms");
	}
}
