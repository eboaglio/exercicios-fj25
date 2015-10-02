package br.com.caelum.financas.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@EntityListeners(MeuEntityListener.class)
//se tiver que usar mais de um listener, informar entre colchetes
//EntityListeners(value={MeuEntityListener.class,OutraEntityListener.class})
@Entity
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	private BigDecimal valor;
	@ManyToOne
	@JoinColumn(name="id_conta")
	private Conta conta;
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipo;
	public void setId(Integer id) {
		this.id = id;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public Conta getConta() {
		return conta;
	}
	

}
