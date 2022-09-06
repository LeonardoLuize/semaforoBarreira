import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Inss extends Thread{
    private double inssValor;
    private float salarioLiquido;
    private Funcionario[][] listaFuncionariosDividida;
    private GestorSemaforo gestorSemaforo;

    public Inss(Funcionario[][] listaFuncionariosDividida, GestorSemaforo gestorSemaforo){
        this.listaFuncionariosDividida = listaFuncionariosDividida;
        this.gestorSemaforo = gestorSemaforo;
    }

    public double calculaPrev(Funcionario funcionario){
        double prevDesc = funcionario.getSalarioBruto() * 0.08;
        return prevDesc;
    }

    public void run() {
        for (int it = 0; it < listaFuncionariosDividida.length; it++) {
            String txt = "";
            int i=1;
            if (it == 1) i = 2;
            else if (it == 2) i = 3;
            else if (it == 3) i = 0;

            Semaphore currentSemaphore = gestorSemaforo.getSemaphoreByIndex(it);
            try {
                currentSemaphore.acquire();

                for (int j = 0; j < listaFuncionariosDividida[i].length; j++) {
                    Funcionario funcionario = listaFuncionariosDividida[i][j];
                    double valorDesconto = calculaPrev(funcionario);

                    funcionario.setDescontoTotal(funcionario.getDescontoTotal() + valorDesconto);
                    funcionario.setDescontoInss(valorDesconto);
                    funcionario.setSalarioLiquido(funcionario.getSalarioLiquido() - valorDesconto);

                    txt = txt + funcionario + "\n";
                }

                WriteFile.WriteFilePath("./parte2.txt", txt);

                currentSemaphore.release();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}