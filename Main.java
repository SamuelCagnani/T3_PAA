import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {

    static long melhorCusto;

    static class Grupo {
        int maxLargura;
        int maxComprimento;

        Grupo(int largura, int comprimento) {
            this.maxLargura = largura;
            this.maxComprimento = comprimento;
        }

        long custo() {
            return (long) maxLargura * maxComprimento;
        }
    }

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(new File("entrada.txt"));
            
            int N = sc.nextInt();
            
            Set<Lote> input = new HashSet<>();

            int largura, comprimento;

            for(int i = 0; i < N; i++) {
                largura = sc.nextInt();
                comprimento = sc.nextInt();
                input.add(new Lote(largura, comprimento));
            }

            sc.close();

            List<Lote> lotes = new ArrayList<>(input);

            Collections.sort(lotes, (a, b) -> {
                int maiorA = Math.max(a.getLargura(), a.getComprimento());
                int maiorB = Math.max(b.getLargura(), b.getComprimento());
                return Integer.compare(maiorB, maiorA);
            });

            // Poda dos retângulos dominados
            lotes = removerDominados(lotes);

            // Calcula custo básico do grupo (comprar tudo individualmente)
            melhorCusto = 0;
            for (Lote l : lotes) {
                melhorCusto += (long) l.getLargura() * l.getComprimento();
            }

            // Backtracking exato
            buscar(0, lotes, new ArrayList<>(), 0);

            System.out.println(melhorCusto);

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de entrada não foi encontrado.");
        }
    }

    static List<Lote> removerDominados(List<Lote> lotes) {
        int n = lotes.size();
        boolean[] dominado = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (dominado[i]) continue;
            Lote a = lotes.get(i);

            for (int j = 0; j < n; j++) {
                if (i == j || dominado[j]) continue;
                Lote b = lotes.get(j);

                if (b.getLargura() >= a.getLargura() &&
                    b.getComprimento() >= a.getComprimento()) {

                    dominado[i] = true;
                    break;
                }
            }
        }

        List<Lote> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!dominado[i]) res.add(lotes.get(i));
        }
        return res;
    }

    static void buscar(int indice, List<Lote> lotes, List<Grupo> grupos, long custoAtual) {

        if (custoAtual >= melhorCusto) return;

        if (indice == lotes.size()) {
            melhorCusto = custoAtual;
            return;
        }

        Lote atual = lotes.get(indice);

        List<Grupo> copia = new ArrayList<>(grupos);

        for (Grupo g : copia) {

            long antes = g.custo();

            int oldL = g.maxLargura;
            int oldC = g.maxComprimento;

            g.maxLargura = Math.max(g.maxLargura, atual.getLargura());
            g.maxComprimento = Math.max(g.maxComprimento, atual.getComprimento());

            long depois = g.custo();

            buscar(indice + 1, lotes, grupos, custoAtual + (depois - antes));

            g.maxLargura = oldL;
            g.maxComprimento = oldC;
        }

        Grupo novo = new Grupo(atual.getLargura(), atual.getComprimento());
        grupos.add(novo);

        buscar(indice + 1, lotes, grupos, custoAtual + novo.custo());

        grupos.remove(grupos.size() - 1);
    }

}
