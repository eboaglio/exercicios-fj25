package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.MovimentacaoDAO;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TestaBuscaMovimentacao {

	public static void main(String[] args) {
		
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		
		Movimentacao movimentacao = manager.getReference(Movimentacao.class, 2);
		//Movimentacao movimentacao = manager.find(Movimentacao.class, 2);
		System.out.println(movimentacao.getConta().getTitular() + " pagou a " + movimentacao.getDescricao());
		movimentacao.getConta().setTitular("Eliana Lig");
		System.out.println(movimentacao.getConta().getTitular() + " pagou a " + movimentacao.getDescricao());
		MovimentacaoDAO movDAO = new MovimentacaoDAO(manager);
		movDAO.salva();
		manager.close();
		
	}
}
