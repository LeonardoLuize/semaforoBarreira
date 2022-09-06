import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class PlanoSaude {

    private Funcionario[][] listaFuncionariosDividida;
    private GestorSemaforo gestorSemaforo;

    public PlanoSaude(Funcionario[][] listaFuncionariosDividida, GestorSemaforo gestorSemaforo) {
        this.listaFuncionariosDividida = listaFuncionariosDividida;
        this.gestorSemaforo = gestorSemaforo;
    }

    public void run(){
        for(int i = 0; i < listaFuncionariosDividida.length; i++){
            Semaphore currentSemaphore = gestorSemaforo.getSemaphoreByIndex(i);
            String txt = "";

            try {
                currentSemaphore.acquire();
            for(int j = 0; j < listaFuncionariosDividida[i].length; j++)    {
                Funcionario funcionario = listaFuncionariosDividida[i][j];

                double salarioBruto = funcionario.getSalarioBruto();
                double planoSaude = salarioBruto * (2/100);

                funcionario.setDescontoPlanoSaude(planoSaude);

                txt = txt + String.valueOf(funcionario.getCodigo()) + "\n";
            }

            WriteFile.WriteFilePath("./PlanoSaudeParte" + (i + 1) + ".txt", txt);
            currentSemaphore.release();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}