package br.com.caelum.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Movimentacao;

public class MovimentacaoDAO {
	
	private EntityManager manager;
	
	public MovimentacaoDAO(EntityManager manager){
		this.manager = manager;
	}

	public void adiciona(Movimentacao movimentacao){
		this.manager.persist(movimentacao);
	}
	
	public void salva(){
		this.manager.getTransaction().commit();
	}
	
	public Movimentacao busca(Integer id){
		return this.manager.find(Movimentacao.class, id);
	}
	
	public void remove(Movimentacao movimentacao){
		this.manager.remove(movimentacao);
	}
	
	public List<Movimentacao> lista(){
		return this.manager.createQuery("select m from Movimentacao m",Movimentacao.class).getResultList();
	}
}
