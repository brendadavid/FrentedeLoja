package src;

import java.util.ArrayList;

public class HistoricoVendas {
  private ArrayList<Venda> vendas;

  public HistoricoVendas() {
    vendas = new ArrayList<Venda>();
  }

  public ArrayList<Venda> getVendas() {
    return vendas;
  }

  public boolean cadastraVendas(Venda venda) {
    return this.vendas.add(venda);
  }

  public Venda getVenda(int numero) {
    for (Venda Venda: vendas) {
      if (Venda.getNumero() == numero) {
        return Venda;
      }
    }
    return null;
  }
}
