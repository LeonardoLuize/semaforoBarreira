import java.io.IOException;
import java.util.concurrent.Semaphore;

public class PlanoSaude extends Thread{

    private Funcionario[][] listaFuncionariosDividida;
    private GestorSemaforo gestorSemaforo;
    private int[] counter;
    private int limit;

    public PlanoSaude(Funcionario[][] listaFuncionariosDividida, GestorSemaforo gestorSemaforo, int[] counter, int limit){
        this.listaFuncionariosDividida = listaFuncionariosDividida;
        this.gestorSemaforo = gestorSemaforo;
        this.limit = limit;
        this.counter = counter;
    }

    public double calculaPrev(Funcionario funcionario){
        double prevDesc = funcionario.getSalarioBruto() * 0.02;
        return prevDesc;
    }

    public void run(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int[] listaQualquer = new int[4];
        for(int it = 0; it < listaFuncionariosDividida.length; it++){
            Semaphore currentSemaphore = gestorSemaforo.getSemaphoreByIndex(it);

            int i=3;
            if (it == 1) i = 0;
            else if (it == 2) i = 1;
            else if (it == 3) i = 2;

            try{
                currentSemaphore.acquire();

                for(int j = 0; j < listaFuncionariosDividida[i].length; j++)    {
                    Funcionario funcionario = listaFuncionariosDividida[i][j];
                    double valorDesconto = calculaPrev(funcionario);

                    funcionario.setDescontoTotal(funcionario.getDescontoTotal() + valorDesconto);
                    funcionario.setDescontoPlanoSaude(valorDesconto);
                    funcionario.setSalarioLiquido(funcionario.getSalarioLiquido() - valorDesconto);
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

            for(int i = 0; i < listaFuncionariosDividida[3].length; i++){
                txt = txt + listaFuncionariosDividida[3][i] + "\n";
            }

            WriteFile.WriteFilePath("./parte4.txt", txt);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}