package br.com.caelum.financas.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class GerenteId implements Serializable{
	
	private String rg;
	private String cpf;

}
