package br.com.caelum.financas.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

@Singleton
@Startup
public class Agendador {

	private static int totalCriado;

	@Resource
	private TimerService timerService;

	public void agenda(String expressaoMinutos, String expressaoSegundos) {
		ScheduleExpression expression = new ScheduleExpression();
		expression.hour("*");
		expression.minute(expressaoMinutos);
		expression.second(expressaoSegundos);

		TimerConfig config = new TimerConfig();
		config.setInfo(expression.toString());
		config.setPersistent(false);

		this.timerService.createCalendarTimer(expression, config);

		System.out.println("Agendamento " + expression);
	}

	@Timeout
	public void verificacaoPeriodica(Timer timer) {
		System.out.println(timer.getInfo());
	}

	//@Schedule(hour = "*", minute = "*/1", second = "0", persistent = false)
	public void enviaEmail() {
		System.out.println("Enviando email a cada minuto");
	}

	public void executa() {
		System.out.printf("%d instancias criadas %n", totalCriado);

		// simulando demora de 4s na execucao
		try {
			System.out.printf("Executando %s %n", this);
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}
	}

	@PostConstruct
	void posConstrucao() {
		System.out.println("Criando agendador");
		totalCriado++;
	}

	@PreDestroy
	void preDestruicao() {
		System.out.println("Destruindo agendador");
	}

}
