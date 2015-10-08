package br.com.caelum.financas.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.exception.ValorInvalidoException;
import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.modelo.ValorPorMesEAno;

@Stateless
public class MovimentacaoDao {

	//@PersistenceContext //Aqui quem gerencia o EntityManager é o EJB
	@Inject //Agora é o CDI quem vai gerenciar o EntityManager
	private EntityManager manager;
	
	public void adiciona(Movimentacao movimentacao) {
		manager.joinTransaction();
		this.manager.persist(movimentacao);

		if (movimentacao.getValor().compareTo(BigDecimal.ZERO) < 0) {
			throw new ValorInvalidoException("Movimentação negativa");
		}
	}

	public Movimentacao busca(Integer id) {
		manager.joinTransaction();
		return this.manager.find(Movimentacao.class, id);
	}

	public List<Movimentacao> lista() {
		manager.joinTransaction();
		return this.manager.createQuery("select m from Movimentacao m",
				Movimentacao.class).getResultList();
	}

	public List<Movimentacao> listaComCategorias() {
		manager.joinTransaction();
		return this.manager.createQuery("select distinct m from Movimentacao m left join fetch m.categorias",
				Movimentacao.class).getResultList();
	}

	public List<Movimentacao> lista(Conta conta) {
		// Essa primeira atribuição possibilita um sql injection
		// String jpql = "select m from Movimentacao m where m.conta.id = " +
		// conta.getId();
		// Positional parameters - Essa segunda atribuição fica inviável quando
		// existem muitos parâmetros
		// String jpql = "select m from Movimentacao m where m.conta.id = ?1";
		// Named parameters
		String jpql = "select m from Movimentacao m where m.conta = :conta"
				+ " order by m.valor desc";
		manager.joinTransaction();
		Query query = manager.createQuery(jpql);
		// query.setParameter(1, conta.getId());
		query.setParameter("conta", conta);
		return query.getResultList();
	}

	public BigDecimal calculaTotalMovimentado(Conta conta, TipoMovimentacao tipo) {
		String jpql = "select sum(m.valor) from Movimentacao m "
				+ "where m.conta = :conta and m.tipoMovimentacao = :tipo";
		manager.joinTransaction();
		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql,
				BigDecimal.class);
		query.setParameter("tipo", tipo);
		query.setParameter("conta", conta);
		return query.getSingleResult();
	}

	public List<Movimentacao> listaMovimentacaoPorTitular(String titular) {
		String jpql = "select m from Movimentacao m where m.conta.titular like :titular";
		manager.joinTransaction();
		TypedQuery<Movimentacao> query = manager.createQuery(jpql,
				Movimentacao.class);
		query.setParameter("titular", "%" + titular + "%");
		return query.getResultList();
	}

	public List<Movimentacao> listaPorValorTipo(BigDecimal valor,
			TipoMovimentacao tipo) {
		String jpql = "select m from Movimentacao m where m.valor = :valor "
				+ "and m.tipoMovimentacao = :tipo " + "order by m.valor desc";
		manager.joinTransaction();
		Query query = manager.createQuery(jpql);
		query.setParameter("valor", valor);
		query.setParameter("tipo", tipo);
		return query.getResultList();
	}

	public List<ValorPorMesEAno> listaMesesComMovimentacoes(Conta conta,
			TipoMovimentacao tipoMovimentacao) {
		String jpql = "select new br.com.caelum.financas.modelo.ValorPorMesEAno(month(m.data),year(m.data),sum(m.valor)) "
				+ "from Movimentacao m where m.conta = :conta and m.tipoMovimentacao = :tipoMovimentacao "
				+ "group by year(m.data)||month(m.data) order by sum(m.valor) desc";
		manager.joinTransaction();
		Query query = manager.createQuery(jpql);
		query.setParameter("conta", conta);
		query.setParameter("tipoMovimentacao", tipoMovimentacao);
		return query.getResultList();
	}

	public void remove(Movimentacao movimentacao) {
		manager.joinTransaction();
		Movimentacao movimentacaoParaRemover = this.manager.find(
				Movimentacao.class, movimentacao.getId());
		this.manager.remove(movimentacaoParaRemover);
	}

}
