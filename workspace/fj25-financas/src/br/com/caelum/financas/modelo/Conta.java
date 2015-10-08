package br.com.caelum.financas.modelo;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

//@Table(name="tb_contas_bancarias")
@Entity
@DynamicUpdate
public class Conta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String titular;
	private String banco;
	private String agencia;
	private String numero;

	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getNumero(){
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public static void main(String[] args) {
		Conta conta = new Conta();
		conta.setTitular("José Roberto");
		conta.setBanco("Banco do Brasil");
		conta.setAgencia("0999");
		conta.setNumero("123456-6");
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("controlefinancas");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(conta);
		manager.getTransaction().commit();
		manager.close();
		
	}
}