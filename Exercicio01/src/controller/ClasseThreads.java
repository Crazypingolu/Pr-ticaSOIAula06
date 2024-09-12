package controller;
// Trazer Bibliotecas e Pacotes
import java.util.concurrent.Semaphore;
import java.util.Random;

public class ClasseThreads extends Thread {
    // Definir variáveis privadas:
    private int idCavaleiro;
    private Semaphore semaforo;
    private int pedra;
    private int tocha;
    private int distMetro;
    private int[] portas;
    // Criar construtores:
    public ClasseThreads(int idCavaleiro, Semaphore semaforo, int pedra, int tocha, int distMetro, int[] portas){
        this.idCavaleiro = idCavaleiro;
        this.semaforo = semaforo;
        this.pedra = pedra;
        this.tocha = tocha;
        this.distMetro = distMetro;
        this.portas = portas;
    }
    // Método escolher porta:
    public void escolherPorta(int id, int[] porta) {
        int cto = 0;
        Random r = new Random();
        boolean flag = true;
        int val;
        while(flag == true){
            val = r.nextInt(0,3);
            if(porta[val] != -1){
                System.out.println("O cavaleiro #" + idCavaleiro + " escolheu a porta: " + val);
                if (porta[val] == 1){
                    System.out.println("Ele Sobreviveu!!");
                } else {
                    System.out.println("F, ele foi de comes e bebes");
                }
                porta[val] = -1;
                flag = false;
            } else {
                for (int i = 0; i < porta.length; i++) {
                    if(porta[i] == -1)
                        cto++;
                }
            }
        }
    }
    @Override
    public void run(){
        Random rand = new Random();
        // Loop do caminho:
        for (int i = 0; i < distMetro; i += (rand.nextInt(2,4))) { // i + rand : aumenta de forma aleatória
            try {
                sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // tenta pegar a tocha:
            // Tenta pegar a pedra:
            // Cavaleiro x tem a tocha:
            // Cavaleiro x tem a pedra:
            // mostra quantos metros totais ele percorreu:
            if (i < distMetro)
                System.out.println("O cavaleiro #" + idCavaleiro + " percorreu: " + i + "m");
        }
        System.out.println("#" + idCavaleiro + " Chegou nas portas!");
        // Chegou nas portas:
        try {
            semaforo.acquire();
            escolherPorta(idCavaleiro, portas);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
        }
    }
}
