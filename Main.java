import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

        try {
            String nomeDoArquivo = "entrada.txt";
            File arquivo = new File(nomeDoArquivo);
            Scanner sc = new Scanner(arquivo);
            
            int quantidadeTerrenos = sc.nextInt();
            
            Map<Integer, Integer> terrenos = new HashMap<>();

            int largura, comprimento;

            for(int i = 0; i < quantidadeTerrenos; i++) {
                largura = sc.nextInt();
                comprimento = sc.nextInt();
                terrenos.put(largura, comprimento);
            }

            sc.close();

            System.out.println("Dados no HashMap");
            for(Map.Entry<Integer, Integer> mapa : terrenos.entrySet()) {
                System.out.println(mapa.getKey() + "->" + mapa.getValue());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de entrada não foi encontrado.");
        }
    }
}
