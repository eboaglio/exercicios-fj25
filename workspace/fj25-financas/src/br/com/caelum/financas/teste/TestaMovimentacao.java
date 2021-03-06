package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.ContaDAO;
import br.com.caelum.financas.dao.MovimentacaoDAO;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TestaMovimentacao {
	public static void main(String[] args) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setTitular("Rebeca Boaglio");
		conta.setBanco("Bradesco");
		conta.setAgencia("1234");
		conta.setNumero("100000-1");

		ContaDAO contaDAO = new ContaDAO(manager);
		contaDAO.adiciona(conta);
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setData(Calendar.getInstance());
		movimentacao.setDescricao("conta NET - outubro/2015");
		movimentacao.setValor(new BigDecimal("150.97"));
		movimentacao.setTipo(TipoMovimentacao.SAIDA);
		movimentacao.setConta(conta);
		
		MovimentacaoDAO movDAO = new MovimentacaoDAO(manager);
		movDAO.adiciona(movimentacao);

		movDAO.salva();
		manager.close();
		
	}
}
