import java.util.ArrayList;

public class Venda {
    private int numero;
    private double subtotal;
    private double imposto;
    private double desconto;
    private double valorVenda;

    private ArrayList<ItemDeVenda> itens;

    public Venda(){
        itens = new ArrayList<ItemDeVenda>();
    }

    public int getNumero() {
        return numero;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getImposto() {
        return imposto;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }

    public boolean insereItem(ItemDeVenda item){
        this.itens.add(item);
        return true;
    }

    public Produto getItem(int nro){
        for (ItemDeVenda Item:itens) {
            if(Item.getProduto().getCodigo() == nro){
                return Item.getProduto();
            }
        }
        return null;
    }

    public int getQtdadeItens(){
        return itens.size();
    }
}
