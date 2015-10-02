package br.com.caelum.financas.modelo;

import java.util.Calendar;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class MeuEntityListener {
	@PrePersist
	@PreUpdate
	public void preAltera(Object entidade) {

		if (entidade instanceof Movimentacao) {
			Movimentacao mov = (Movimentacao) entidade;
			mov.setData(Calendar.getInstance());
		}
	}
}
