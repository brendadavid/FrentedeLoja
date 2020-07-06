import java.util.ArrayList;
import java.util.List;

public class Venda {

    private int numero;
    private double subtotal=0;
    private double desconto=0;
    private double imposto;
    private double valorDaVenda;
    private String status; //(efetuada ou cancelada)  transformar em enumeration

    private List<ItemDeVenda> itens;

    public Venda(int numero) {
        this.numero = numero;
        itens = new ArrayList<ItemDeVenda>();
    }

    public double getSubTotal() {
        return subtotal;
    }

    public void setSubTotal() {
        for (ItemDeVenda Item: itens) {
            this.subtotal += Item.getValor();
        }
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto() {
        if (subtotal>250){
            desconto = subtotal * 0.1;
        }else{
            System.out.println("Desconto somente para compras acima de R$250.");
        }
    }

    public double getImposto() {
        return imposto;
    }

    public void setImposto() {
        imposto = subtotal * 0.25;
    }

    public double getValorDaVenda() {
        return valorDaVenda;
    }

    public void setValorDaVenda() {
        setImposto();
        this.valorDaVenda = subtotal - desconto + imposto;
    }

    public int getNumero() {
        return numero;
    }

    public void setStatusVenda(String status){
        this.status = status;
    }

    public String getStatusVenda(){
        return status;
    }

    public boolean insereItem(ItemDeVenda item) {
        return this.itens.add(item);
    }

    public boolean removeItem(ItemDeVenda item){
        return this.itens.remove(item);
    }

    public Produto getItem(int codigo) {
        for (ItemDeVenda Item: itens) {
            if (Item.getProduto().getCodigo() == codigo) {
                return Item.getProduto();
            }
        }
        return null;
    }

    public ItemDeVenda getItemVenda(int codigo) {
        for (ItemDeVenda Item: itens) {
            if (Item.getProduto().getCodigo()==codigo){
                return Item;
            }
        }
        return null;
    }

    public int getQuantidadeItens() {
        return itens.size();
    }

    public List<ItemDeVenda> getItensDeVenda() {
        return itens;
    }

    public void baixaEstoque(Estoque estoque){
        for (ItemDeVenda Item: itens) {
            estoque.baixaEstoque(Item.getProduto().getCodigo(),Item.getQuantidade());
        }
    }

    public void reposicaoEstoque(Estoque estoque){
        for (ItemDeVenda Item: itens) {
            estoque.reposicaoEstoque(Item.getProduto().getCodigo(),Item.getQuantidade());
        }
    }

    @Override
    public String toString() {
        return "Venda{" +
                "numero=" + numero +
                ", subtotal=" + subtotal +
                ", desconto=" + desconto +
                ", imposto=" + imposto +
                ", valorDaVenda=" + valorDaVenda +
                ", status='" + status + '\'' +
                ", itens=" + itens +
                '}';
    }

    public String mostraRecibo(){
        return toString();
    }

}
