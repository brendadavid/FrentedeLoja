public class ItemDeVenda {
    private Produto produto;
    private int quantidade;
    private double valor;

    ItemDeVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = quantidade * produto.getPrecoUnitario();
    }

    public Produto getProduto(){
        return produto;
    }

    public int getQuantidade(){
        return quantidade;
    }

}
