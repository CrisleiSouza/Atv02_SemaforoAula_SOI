package view;

import controller.ThreadAeroporto;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		Semaphore semaforo1 = new Semaphore(1);
		Semaphore semaforo2 = new Semaphore(2);
		for (int i = 0; i < 12; i++) {
			int pista = (int) (Math.random() * 2) + 1;
			if (pista == 1) {
				
				Thread t = new ThreadAeroporto(semaforo1, semaforo2, i + 1, "Norte");
				t.start();
			} else {
				Thread t = new ThreadAeroporto(semaforo1, semaforo2, i + 1, "Sul");
				t.start();
			}
		}
	}

}
