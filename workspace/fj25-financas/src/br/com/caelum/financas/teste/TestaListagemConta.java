package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.ContaDAO;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestaListagemConta {
	public static void main(String[] args) {
		EntityManager manager = new JPAUtil().getEntityManager();
		ContaDAO dao = new ContaDAO(manager);
		List<Conta> lista = dao.lista();
		for (Conta conta: lista){
			System.out.println("Conta "+ conta.getNumero() + " pertencente à " + conta.getTitular());
		}
	}
}
