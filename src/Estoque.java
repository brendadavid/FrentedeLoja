import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private final List<ItemDeEstoque> itens;

    public Estoque() {
        itens = new ArrayList<>();
    }

    public int getQuantidadeProdutos() {
        return itens.size();
    }

    public boolean cadastraProduto(Produto produto, int estoqueInicial) {
        ItemDeEstoque item = new ItemDeEstoque(produto, estoqueInicial);
        return itens.add(item);
    }

    public int getQuantidadeEstoque(int codigo) {
        ItemDeEstoque item = getItemDaLista(codigo);
        return item != null ? item.getQuantidade() : -1;
    }

    public Produto getProduto(int codigo) {
        ItemDeEstoque item = getItemDaLista(codigo);
        return item != null ? item.getProduto() : null;
    }

    public void baixaEstoque(int codigo, int quantidade) {
        getItemDaLista(codigo).baixaEstoque(quantidade);
    }

    public void reposicaoEstoque(int codigo, int quantidade) {
        ItemDeEstoque item = getItemDaLista(codigo);
        item.reposicaoEstoque(quantidade);
    }

    private ItemDeEstoque getItemDaLista(int codigo) {
        for (ItemDeEstoque item : itens) {
            if (item.getProduto().getCodigo() == codigo) {
                return item;
            }
        }
        return null;
    }

    public List<ItemDeEstoque> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "itens=" + itens +
                '}';
    }
}