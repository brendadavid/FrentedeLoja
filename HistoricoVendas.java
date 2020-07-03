import java.util.ArrayList;

public class HistoricoVendas {
    private ArrayList<Venda> vendas;

    public HistoricoVendas() {
        vendas = new ArrayList<Venda>();
    }

    public ArrayList<Venda> getVendas() {
        return vendas;
    }

    public boolean cadastraVendas(Venda venda){
        this.vendas.add(venda);
        return true;
    }

    public Venda getVenda(int nro){
        for (Venda Venda:vendas) {
            if(Venda.getNumero() == nro){
                return Venda;
            }
        }
        return null;
    }
}
