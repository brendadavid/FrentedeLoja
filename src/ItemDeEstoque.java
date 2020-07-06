import java.util.List;

public class ItemDeEstoque {
    private Produto produto;
    private int quantidade;

    ItemDeEstoque(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void baixaEstoque(int quantidade) {
        if (this.quantidade < quantidade) {
            return;
        }
        this.quantidade -= quantidade;
    }

    public void reposicaoEstoque(int quantidade) {
        this.quantidade += quantidade;
    }

    @Override
    public String toString() {
        return "ItemDeEstoque{" +
                "produto=" + produto +
                ", quantidade=" + quantidade +
                '}';
    }
}
