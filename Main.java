import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(new File("entrada.txt"));
            
            int N = sc.nextInt();
            
            Set<Lote> lotes = new HashSet<>();

            int largura, comprimento;

            for(int i = 0; i < N; i++) {
                largura = sc.nextInt();
                comprimento = sc.nextInt();
                lotes.add(new Lote(largura, comprimento));
            }

            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de entrada não foi encontrado.");
        }
    }
}
