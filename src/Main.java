import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        int total = 12;
        int split = 4;
        int[] count = new int[1];
        count[0] = 0;

        Funcionario[][] listaFuncionariosDividida = Main.divideLista(total, split);
        GestorSemaforo gestorSemaforo = new GestorSemaforo();

        Previdencia previdencia = new Previdencia(listaFuncionariosDividida, gestorSemaforo, count, split);
        Inss inss = new Inss(listaFuncionariosDividida, gestorSemaforo, count, split);
        PlanoSaude planoSaude = new PlanoSaude(listaFuncionariosDividida, gestorSemaforo, count, split);
        ImpostoRenda impostoRenda = new ImpostoRenda(listaFuncionariosDividida, gestorSemaforo, count, split);

        impostoRenda.start();
        inss.start();
        previdencia.start();
        planoSaude.start();
    }

    public static Funcionario[][] divideLista(int total, int split){
        Funcionario[] listaFuncionarios = new Funcionario[total];
        Funcionario[][] listaFuncionariosDividida = new Funcionario[split][total/split];

        for (int i = 0; i < total; i++){
            Random random = new Random();
            Funcionario funcionario = new Funcionario(i, random.nextDouble(1000, 5000));
            listaFuncionarios[i] = funcionario;
        }

        int counter = 1;
        int indexJ = 0;
        int index = 0;

        for(int i = 0; i < total; i++){
            if(counter == total) break;
            if(i == (total/split) * counter) {
                index++;
                counter++;
                indexJ = 0;
            }

            listaFuncionariosDividida[index][indexJ] = listaFuncionarios[i];

            indexJ++;
        }

        return listaFuncionariosDividida;
    }
}
