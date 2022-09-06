import java.util.concurrent.Semaphore;

public class PlanoSaude extends Thread{

    private Funcionario[][] listaFuncionariosDividida;

    private GestorSemaforo gestorSemaforo;

    public PlanoSaude(Funcionario[][] listaFuncionariosDividida, GestorSemaforo gestorSemaforo) {
        this.listaFuncionariosDividida = listaFuncionariosDividida;
        this.gestorSemaforo = gestorSemaforo;
    }

    public double calculaPrev(Funcionario funcionario){
        double prevDesc = funcionario.getSalarioBruto() * 0.02;
        return prevDesc;
    }

    public void run(){
        int[] listaQualquer = new int[4];

        for(int it = 0; it < listaFuncionariosDividida.length; it++){
            Semaphore currentSemaphore = gestorSemaforo.getSemaphoreByIndex(it);
            String txt = "";

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

                    txt = txt + funcionario + "\n";
                }
                WriteFile.WriteFilePath("./parte4.txt", txt);
                currentSemaphore.release();

            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}