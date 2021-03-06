package br.com.caelum.financas.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;

//EJB precisa ter atributo privado, não pode ter construtor

// @Singleton - session bean para uma classe que não tem lógica - ex.: classe de busca de configuração
// @Stateful - session bean que é utilizado por uma única instancia, tem um custo maior de memória por isso
@Stateless //Session Bean compartilhado entre instancias - escolhemos esse tipo de session bean pq o único atributo é um EntityManager e ele pode ser compartilhado por qualquer instancia sem problemas.
public class ContaDao {
	//@PersistenceContext //Aqui quem gerencia o EntityManager é o EJB
	@Inject //Agora é o CDI quem vai gerenciar o EntityManager
	private EntityManager manager;

	public void adiciona(Conta conta) {
		manager.joinTransaction();
		this.manager.persist(conta);
	}

	public Conta busca(Integer id) {
		manager.joinTransaction();
		return this.manager.find(Conta.class, id);
	}

	public List<Conta> lista() {
		manager.joinTransaction();
		return this.manager.createQuery("select c from Conta c", Conta.class)
				.getResultList();
	}

	public void remove(Conta conta) {
		manager.joinTransaction();
		Conta contaParaRemover = this.manager.find(Conta.class, conta.getId());
		this.manager.remove(contaParaRemover);
	}

	public void altera(Conta conta){
		manager.joinTransaction();
		this.manager.merge(conta);
	}
	
	@PostConstruct
	public void aposInjecao(){
		System.out.println("Mostra quantas instancias foram criadas.");
	}
	
	@PreDestroy
	public void preDestruicao(){
		System.out.println("Mostra quantas instancias foram liberadas.");
	}
}




