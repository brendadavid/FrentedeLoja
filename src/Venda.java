package src;

import java.util.ArrayList;

public class Venda {
  private int numero;
  private double total;
  private double desconto;
  private double imposto;
  private double valorDaVenda;

  private ArrayList<ItemDeVenda> itens;

  public Venda() {
    itens = new ArrayList<ItemDeVenda>();
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public double getDesconto() {
    return desconto;
  }

  public void setDesconto(double desconto) {
    this.desconto = desconto;
  }

  public double getImposto() {
    return imposto;
  }

  public void setImposto(double imposto) {
    this.imposto = imposto;
  }

  public double getValorDaVenda() {
    return valorDaVenda;
  }

  public void setValorDaVenda(double valorDaVenda) {
    this.valorDaVenda = valorDaVenda;
  }

  public int getNumero() {
    return numero;
  }

  public boolean insereItem(ItemDeVenda item) {
    this.itens.add(item);
    return true;
  }

  public Produto getItem(int numero) {
    for (ItemDeVenda Item: itens) {
      if (Item.getProduto().getCodigo() == numero) {
        return Item.getProduto();
      }
    }
    return null;
  }

  public int getQuantidadeItens() {
    return itens.size();
  }
}
