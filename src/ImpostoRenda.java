import java.io.*;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class ImpostoRenda extends Thread{
    private Funcionario[][] listaFuncionariosDividida;
    private GestorSemaforo gestorSemaforo;

    public ImpostoRenda(Funcionario[][] listaFuncionariosDividida, GestorSemaforo gestorSemaforo) {
        this.listaFuncionariosDividida = listaFuncionariosDividida;
        this.gestorSemaforo = gestorSemaforo;
    }

    public void run() {
        for(int i = 0; i < listaFuncionariosDividida.length; i++){
            Semaphore currentSemaphore = gestorSemaforo.getSemaphoreByIndex(i);
            String txt = "";
            try{
                currentSemaphore.acquire();
                for(int j = 0; j < listaFuncionariosDividida[i].length; j++) {
                    Funcionario funcionario = listaFuncionariosDividida[i][j];

                    double salarioBruto = funcionario.getSalarioBruto();
                    double imposto = salarioBruto * 1 / 5;

                    funcionario.setDescontoImpostoRenda(imposto);

                    double descontos = funcionario.getDescontoTotal();
                    descontos += imposto;
                    funcionario.setDescontoTotal(descontos);

                    double salarioLiquido = funcionario.getSalarioLiquido();
                    salarioLiquido -= imposto;
                    funcionario.setSalarioLiquido(salarioLiquido);

                    txt = txt + funcionario + "\n";
                }
                WriteFile.WriteFilePath("./parte1.txt", txt);

                currentSemaphore.release();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}