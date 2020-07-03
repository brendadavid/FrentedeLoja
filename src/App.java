package src;

import java.util.Scanner;

public class App {
  private static final Scanner scanner = new Scanner(System.in);
  private static final Estoque estoque = new Estoque();
  private static final HistoricoVendas historicoVendas = new HistoricoVendas();
  private static boolean rodando = true;

  public static void main(String args[]) {
    populaEstoque(); // esse metodo pode inserir varias coisas no estoque

    while(rodando) {
      mostraMenu();
    }
  }

  private static void populaEstoque() {
    // aqui cadastra alguns produtos no estoque
    // estoque.cadastraProduto(
    //   new Produto(codigo, descricao, precoUnitario),
    //   estoqueInicial);
  }

  private static void mostraMenu() {
    System.out.println("Selecione uma opção meu parça:");
    System.out.println("1 - Vendas");
    System.out.println("2 - Controle de Estoque");
    System.out.println("3 - Relatórios");
    System.out.println("4 - Sair\n");

    int opcao = scanner.nextInt();
    switch(opcao) {
      case 1:
        mostraMenuVendas();
        break;
      case 2:
        // mostraMenuEstoque();
        break;
      case 3:
        // mostraMenuRelatorios();
        break;
      case 4:
        rodando = false;
        break;
      default:
        System.out.println("Opção inválida");
        break;
    }
  }

  private static void mostraMenuVendas() {
    System.out.println("Menu de vendas:");
    System.out.println("1 - Realizar venda");
    System.out.println("2 - Cancelar venda");
    System.out.println("3 - Listar vendas");
    System.out.println("4 - Imprimir segunda via recibo\n");

    int opcao = scanner.nextInt();
    switch(opcao) {
      case 1:
        // aqui cria uma nova venda, pergunta pro usuario o codigo do produto,
        // verifica se tem no estoque e adiciona se tiver, e depois adicionar no historico de vendas
        break;
      case 2:
        // aqui pega uma venda do historico de vendas, cancela ela e volta os produtos de volta pro estoque
        break;
      case 3:
        historicoVendas.getVendas().stream().limit(5).forEach(venda -> {
          // aqui dá um print do toString da venda (tem que criar esse metodo tambem)
        });
        break;
      case 4:
        // fazer um print bonitinho de uma venda no historico de vendas
        break;
      default:
        System.out.println("Opção inválida");
        break;
    }
  }
}
