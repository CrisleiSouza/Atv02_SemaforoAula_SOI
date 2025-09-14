package controller;

import java.util.concurrent.Semaphore;

public class ThreadAeroporto extends Thread{
	private String pista;
	private static Semaphore semaforo1, semaforo2;
	private int aviao;
	
	public ThreadAeroporto(Semaphore semaforo1, Semaphore semaforo2, int aviao, String pista) {
		this.semaforo1 = semaforo1;
		this.semaforo2 = semaforo2;
		this.aviao = aviao;
		this.pista = pista;
	}
	
	@Override
	public void run() {
		avioesPista();
	}
	
	public void avioesPista() {
		try {
			System.out.println("Aviao #" + aviao + " iniciará procedimento de decolagem na pista " + pista);
			semaforo2.acquire();
				System.out.println("Aviao #" + aviao + " iniciou manobra.");
				tempoEtapa(300, 700);	// etapa manobra
				System.out.println("Aviao #" + aviao + " finalizou manobra.");
				System.out.println("Aviao #" + aviao + " iniciou taxiação.");
				tempoEtapa(500, 1000);	// etapa taxiar
				System.out.println("Aviao #" + aviao + " finalizou taxiação.");	
				
				semaforo1.acquire();
					System.out.println("Aviao #" + aviao + " iniciou decolagem na pista " + pista);
					tempoEtapa(600, 800);			// etapa decolagem
					System.out.println("Aviao #" + aviao + " finalizou decolagem.");
				semaforo1.release();
				
				System.out.println("Aviao #" + aviao + " iniciou afastamento.");
				tempoEtapa(600, 800);			// etapa afastamento
				System.out.println("Aviao #" + aviao + " finalizou procedimento de decolagem.");
				
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			semaforo1.release();
			semaforo2.release();
		}
	}
	
	private void tempoEtapa(int tempoMin, int tempoMax) {
		try {
			sleep((int) (Math.random() * (tempoMax - tempoMin) + tempoMin));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
}
