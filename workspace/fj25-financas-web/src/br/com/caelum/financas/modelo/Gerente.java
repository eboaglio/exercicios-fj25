package br.com.caelum.financas.modelo;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import org.hibernate.id;

@Entity
//@IdClass(GerenteId.class)
public class Gerente implements Serializable{
	
	@Id // 
	private String rg;
	@Id
	private String cpf;
	
	private String nome;
	private String telefone;
	
	@Embedded
	private Endereco endereco = new Endereco();
	
	@Embedded
	private GerenteId gerenteId;
	
}
