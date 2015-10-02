package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.ContaDAO;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestaEstadosEntidade {
	public static void main(String[] args) {
		EntityManager manager = new JPAUtil().getEntityManager();
		ContaDAO dao = new ContaDAO(manager);
		manager.getTransaction().begin();

		Conta conta = new Conta();
		//dao.salva(conta);
		//dao.remove(conta);
		//manager.close();
		dao.remove(conta);
		
	}
}
