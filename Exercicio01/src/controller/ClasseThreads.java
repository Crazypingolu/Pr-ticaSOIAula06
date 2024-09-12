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
    @Override
    public void run(){
        Random rand = new Random();
        int ctoTocha = 50;
        int ctoPedra = 50;
        // Loop do caminho:
        for (int i = 0; i <= distMetro; i += (rand.nextInt(2) + 2)) { // i + rand : aumenta de forma aleatória
            try {
                sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // tenta pegar a tocha
            if(i >= 500 && tocha == 0){
                try {
                    semaforo.acquire();
                    if( tocha == 0 )
                        tocha = idCavaleiro;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaforo.release();
                }
            }
            // Tenta pegar a pedra:
            if(i >= 1500 && pedra == 0){
                try {
                    semaforo.acquire();
                    if( pedra == 0 && tocha != idCavaleiro )
                        pedra = idCavaleiro;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaforo.release();
                }
            }  
            // Cavaleiro x tem a tocha:
            if (tocha == idCavaleiro && ctoTocha > 0){
                i += 2;
                ctoTocha -= 2;
            }
            // Cavaleiro x tem a pedra:
            if (pedra == idCavaleiro && ctoPedra > 0){
                i += 2;
                ctoPedra -= 2;
            }
            // mostra quantos metros totais ele percorreu:
            if (i < distMetro)
                System.out.println("O cavaleiro #" + idCavaleiro + " percorreu: " + i);
        }
        // Chegou nas portas:
        boolean flag = true;
        int opc;
        while (flag == true){
            opc = rand.nextInt(3);
            if(portas[opc] != -1){
                if (portas[opc] == 1){
                    System.out.println("O cavaleiro #" + idCavaleiro + " escolheu a porta: " + opc + " e Sobreviveu!");
                    portas[opc] = -1;
                } else {
                    System.out.println("O cavaleiro #" + idCavaleiro + " escolheu a porta: " + opc + " e foi de comes e bebes...");
                    portas[opc] = -1;
                }
                flag = false;
            }
        }
    }
}
