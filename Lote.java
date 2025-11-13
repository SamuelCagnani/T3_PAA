import java.util.Objects;

public class Lote {
    private int largura;
    private int comprimento;

    public Lote(int largura, int comprimento) {
        this.largura = largura;
        this.comprimento = comprimento;
    }

    public int getLargura() {
        return largura;
    }

    public int getComprimento() {
        return comprimento;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Lote lote = (Lote) o;

        return largura == lote.largura && comprimento == lote.comprimento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(largura, comprimento);
    }

}


