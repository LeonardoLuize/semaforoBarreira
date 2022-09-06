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

    public Previdencia(Funcionario[][] listaFuncionariosDividida, GestorSemaforo gestorSemaforo){
        this.listaFuncionariosDividida = listaFuncionariosDividida;
        this.gestorSemaforo = gestorSemaforo;
    }

    public double calculaPrev(Funcionario funcionario){
        double prevDesc = funcionario.getSalarioBruto() * 0.04;
        return prevDesc;
    }

    public void run() {
        for (int it = 0; it < listaFuncionariosDividida.length; it++) {
            int i=2;
            if (it == 1) i = 3;
            else if (it == 2) i = 0;
            else if (it == 3) i = 1;

            Semaphore currentSemaphore = gestorSemaforo.getSemaphoreByIndex(it);
            String txt = "";
            try {
                currentSemaphore.acquire();
                for (int j = 0; j < listaFuncionariosDividida[i].length; j++) {
                    Funcionario funcionario = listaFuncionariosDividida[i][j];
                    double valorDesconto = calculaPrev(funcionario);

                    funcionario.setDescontosPrevidencia(valorDesconto);
                    funcionario.setDescontoTotal(funcionario.getDescontoTotal() + valorDesconto);
                    funcionario.setSalarioLiquido(funcionario.getSalarioLiquido() - valorDesconto);

                    txt = txt + funcionario + "\n";
                }

                WriteFile.WriteFilePath("./parte3.txt", txt);

                currentSemaphore.release();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}