import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class HistoricoVendas {
    private final List<Venda> vendas;

    public HistoricoVendas() {
        vendas = new ArrayList<Venda>();
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public boolean cadastraVendas(Venda venda) {
        return this.vendas.add(venda);
    }

    public Venda getVenda(int numero) {
        for (Venda Venda : vendas) {
            if (Venda.getNumero() == numero) {
                return Venda;
            }
        }
        return null;
    }

    public int getQuantidadeVendas() {
        return vendas.size();
    }

    public void cancelaVenda(int numero, Estoque estoque) {
        //atualiza o estoque e muda o status da venda
        getVenda(numero).reposicaoEstoque(estoque);
        getVenda(numero).setStatusVenda(StatusEnum.CANCELADA);
        System.out.println("Venda Cancelada!\n");
    }

    public void listarVendasEfetuadas() {
        for (Venda venda : vendas) {
            if (StatusEnum.EFETIVADA.equals(venda.getStatusVenda())) {
                System.out.println(venda.toString());
            }
        }
    }

    public void listarVendasCanceladas() {
        for (Venda venda : vendas) {
            if (StatusEnum.EFETIVADA.equals(venda.getStatusVenda())) {
                System.out.println(venda.toString());
            }
        }
    }

    public double getFaturamentoBruto() {
        double valorBruto = 0.0;
        for (Venda venda : vendas) {
            if (StatusEnum.EFETIVADA.equals(venda.getStatusVenda())) {
                valorBruto += venda.getValorDaVenda();
            }
        }
        return valorBruto;
    }

    public double getFaturamentoLiquido() {
        double valorLiquido = 0.0;
        for (Venda venda : vendas) {
            if (StatusEnum.EFETIVADA.equals(venda.getStatusVenda())) {
                valorLiquido += venda.getValorDaVenda() - venda.getImposto();
            }

        }
        return valorLiquido;
    }

    public void getValorMedioVendas() {
        OptionalDouble ValorMedio = vendas.stream()
            .mapToDouble(Venda::getValorDaVenda)
            .average();
        ValorMedio.ifPresent(System.out::println);
    }

    public List<MaisVendido> getCincoItensMaisVendidos() {
        return this.vendas.stream()
            .flatMap(item -> item.getItensDeVenda().stream())
            .collect(Collectors.toMap(
                item -> item.getProduto().getDescricao(), //mapeia o produto como chave
                ItemDeVenda::getQuantidade, //quantidade vai ser o valor do mapa
                Integer::sum //em caso de conflito no valor da chave ele soma os valores
            ))
            //pega o conjunto dos dados do mapa da linha anterior
            .entrySet()
            .stream()
            //ordena comparando o valor dos no mapa (contagem de ocorrencias do objeto)
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            //pega somente as 5 maiores ocorrencias
            .limit(5)
            //mapeia pra pegar só o o objeto
            .map(item -> new MaisVendido(item.getKey(), item.getValue()))
            //trnasforma em uma lista
            .collect(Collectors.toList());
    }



    @Override
    public String toString() {
        return "HistoricoVendas{" +
            "vendas=" + vendas +
            '}';
    }
}