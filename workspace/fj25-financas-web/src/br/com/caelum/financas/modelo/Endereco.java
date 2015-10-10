package br.com.caelum.financas.modelo;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

	private String rua;
	private String cidade;
	private String estado;
}
