import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Previdencia extends Thread{
    private double previdenciaValor;
    private float salarioLiquido;
    private GestorSemaforo gestorSemaforo;
    private Funcionario[][] listaFuncionariosDividida;
    private int[] counter;
    private int limit;

    public Previdencia(Funcionario[][] listaFuncionariosDividida, GestorSemaforo gestorSemaforo, int[] counter, int limit){
        this.listaFuncionariosDividida = listaFuncionariosDividida;
        this.gestorSemaforo = gestorSemaforo;
        this.limit = limit;
        this.counter = counter;
    }

    public double calculaPrev(Funcionario funcionario){
        double prevDesc = funcionario.getSalarioBruto() * 0.04;
        return prevDesc;
    }

    public void run() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int it = 0; it < listaFuncionariosDividida.length; it++) {
            int i=2;
            if (it == 1) i = 3;
            else if (it == 2) i = 0;
            else if (it == 3) i = 1;

            Semaphore currentSemaphore = gestorSemaforo.getSemaphoreByIndex(it);
            try {
                currentSemaphore.acquire();
                for (int j = 0; j < listaFuncionariosDividida[i].length; j++) {
                    Funcionario funcionario = listaFuncionariosDividida[i][j];
                    double valorDesconto = calculaPrev(funcionario);

                    funcionario.setDescontosPrevidencia(valorDesconto);
                    funcionario.setDescontoTotal(funcionario.getDescontoTotal() + valorDesconto);
                    funcionario.setSalarioLiquido(funcionario.getSalarioLiquido() - valorDesconto);
                }

                currentSemaphore.release();
            } catch (InterruptedException e) {
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

            for(int i = 0; i < listaFuncionariosDividida[2].length; i++){
                txt = txt + listaFuncionariosDividida[2][i] + "\n";
            }

            WriteFile.WriteFilePath("./parte3.txt", txt);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}