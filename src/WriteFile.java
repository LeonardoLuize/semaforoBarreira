import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteFile {
    public static void WriteFilePath(String caminho, String texto) throws IOException {
        try (
                FileWriter fileWriter = new FileWriter(caminho, false);
                BufferedWriter buffer = new BufferedWriter(fileWriter);
                PrintWriter pw = new PrintWriter(buffer);)
        {
            pw.append(texto);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}