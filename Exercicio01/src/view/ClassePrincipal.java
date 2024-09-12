package view;
// Trazer Bibliotecas e Pacotes
import java.util.concurrent.Semaphore;
import controller.ClasseThreads;
import java.util.Random;

public class ClassePrincipal {
    public static void main(String[] args) {
        Random r = new Random();
        Semaphore s = new Semaphore(1);
        int[] por = new int[4];
        int portaCerta = r.nextInt(0, 3);
        por[portaCerta] = 1;
        int pedra = 0;
        int tocha = 0;
        int distancia = 2000;

        for (int i = 1; i < 5; i++) {
            ClasseThreads t = new ClasseThreads(i, s, pedra, tocha, distancia, por);
            t.start();
        }
    }
}

