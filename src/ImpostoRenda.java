import java.io.*;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class ImpostoRenda extends Thread{
    private Funcionario[][] listaFuncionariosDividida;
    private GestorSemaforo gestorSemaforo;
    private int[] counter;
    private int limit;

    public ImpostoRenda(Funcionario[][] listaFuncionariosDividida, GestorSemaforo gestorSemaforo, int[] counter, int limit) {
        this.listaFuncionariosDividida = listaFuncionariosDividida;
        this.gestorSemaforo = gestorSemaforo;
        this.counter = counter;
        this.limit = limit;
    }

    public void run() {
        for(int i = 0; i < listaFuncionariosDividida.length; i++){
            Semaphore currentSemaphore = gestorSemaforo.getSemaphoreByIndex(i);
            try{
                currentSemaphore.acquire();
                for(int j = 0; j < listaFuncionariosDividida[i].length; j++) {
                    Funcionario funcionario = listaFuncionariosDividida[i][j];

                    double salarioBruto = funcionario.getSalarioBruto();
                    double imposto = salarioBruto * 0.2;


                    double descontos = funcionario.getDescontoTotal();
                    descontos += imposto;

                    double salarioLiquido = funcionario.getSalarioLiquido();
                    salarioLiquido -= imposto;

                    funcionario.setDescontoImpostoRenda(imposto);
                    funcionario.setDescontoTotal(descontos);
                    funcionario.setSalarioLiquido(salarioLiquido);
                }

                currentSemaphore.release();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        try {
            Semaphore barreira = gestorSemaforo.getSemaphoreByIndex(4);
            Semaphore counterSemaphore = gestorSemaforo.getSemaphoreByIndex(5);

            counterSemaphore.acquire();
            counter[0]++;
            counterSemaphore.release();

            if (counter[0] == limit) barreira.release();

            barreira.acquire();
            barreira.release();

            String txt = "";

            for(int i = 0; i < listaFuncionariosDividida[0].length; i++){
                txt = txt + listaFuncionariosDividida[0][i] + "\n";
            }

            WriteFile.WriteFilePath("./parte1.txt", txt);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}