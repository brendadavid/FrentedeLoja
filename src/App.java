import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Estoque estoque = new Estoque();
    private static final HistoricoVendas historicoVendas = new HistoricoVendas();


    public static void main(String[] args) {
        popularEstoque();
        mostraMenuPrincipal();
    }


    private static void mostraMenuPrincipal() {
        System.out.println("Selecione uma opção:");
        System.out.println("1 - Vendas");
        System.out.println("2 - Controle de Estoque");
        System.out.println("3 - Relatórios");
        System.out.println("4 - Sair\n");

        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1:
                mostraMenuVendas();
                break;
            case 2:
                mostraMenuEstoque();
                break;
            case 3:
                mostraMenuRelatorios();
                break;
            case 4:
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
        System.out.println("4 - Imprimir segunda via recibo");
        System.out.println("5 - Voltar\n");

        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1:
                realizarVenda();
                mostraMenuVendas();
                break;
            case 2:
                cancelarVenda();
                mostraMenuVendas();
                break;
            case 3:
                listarVendas();
                mostraMenuVendas();
                break;
            case 4:
                imprimirRecibo();
                mostraMenuVendas();
                break;
            case 5:
                mostraMenuPrincipal();
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    private static void mostraMenuEstoque() {
        System.out.println("Controle de Estoque:");
        System.out.println("1 - Cadastrar novo produto");
        System.out.println("2 - Listar todos os produtos");
        System.out.println("3 - Repor produto em estoque");
        System.out.println("4 - Voltar\n");

        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1:
                cadastrarProduto();
                mostraMenuEstoque();
                break;
            case 2:
                listarProdutos();
                mostraMenuEstoque();
                break;
            case 3:
                reporProduto();
                mostraMenuEstoque();
                break;
            case 4:
                mostraMenuPrincipal();
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    private static void mostraMenuRelatorios() {
        System.out.println("Relatórios:");
        System.out.println("1 - Faturamento atual");
        System.out.println("2 - Ticket médio de vendas");
        System.out.println("3 - Produtos mais vendidos");
        System.out.println("4 - Listar vendas canceladas");
        System.out.println("5 - Voltar\n");

        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1:
                mostraFaturamento();
                mostraMenuRelatorios();
                break;
            case 2:
                //Ticket médio de vendas
                historicoVendas.getValorMedioVendas();
                mostraMenuRelatorios();
                break;
            case 3:
                // Mostra os 5 produtos mais vendidos em ordem decrescente
                break;
            case 4:
                listarVendasCanceladas();
                mostraMenuRelatorios();
                break;
            case 5:
                mostraMenuPrincipal();
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }



    //MÓDULO VENDA

    //item 1
    public static void realizarVenda(){

        // cria uma nova venda
        Venda venda = new Venda(historicoVendas.getQuantidadeVendas()+1);

        //registra os itens da venda
        registraItemVenda(venda);

        //finaliza a venda calculando o valor da venda e realiza a baixa no estoque
        finalizarVenda(venda);
    }

    private static void registraItemVenda(Venda venda) {
        boolean registrando=true;
        int quantidadeItens=0;

        while (registrando){

            System.out.println("Escolha uma opção:  1-Adicionar Item  2-Remover Item  3-Finalizar");
            int opcao = scanner.nextInt();

            //insere novo item na venda
            if(opcao==1){
                System.out.println("Digite o código do produto: ");
                int codigo = scanner.nextInt();

                while (estoque.getProduto(codigo) == null) {
                    System.out.println("O código do produto não existe, digite um novo código:");
                    codigo = scanner.nextInt();
                }

                int quantidadeDisponivel = estoque.getQuantidadeEstoque(codigo);

                int quantidadeDesejada;
                do {
                    System.out.println("Qual a quantidade desejada?");
                    System.out.println("Disponível: "+ quantidadeDisponivel);
                    quantidadeDesejada = scanner.nextInt();

                    if (quantidadeDesejada > quantidadeDisponivel){
                        System.out.println("Quantidade indisponível!");
                    }
                }while (quantidadeDesejada > quantidadeDisponivel);

                //insere novo item na venda
                venda.insereItem(new ItemDeVenda(estoque.getProduto(codigo),quantidadeDesejada));
                quantidadeItens++;
            }
            //remove item inserido na venda
            else if (opcao==2){
                if (quantidadeItens>0){
                    System.out.println("Digite o código do produto: ");
                    int codigo = scanner.nextInt();

                    while (estoque.getProduto(codigo) == null || venda.getItemVenda(codigo) == null){
                        System.out.println("O código do produto não existe ou o item não foi inserido na venda!");
                        System.out.println("Digite um novo código:");
                        codigo = scanner.nextInt();
                    }

                    //remove item inserido na venda
                    venda.removeItem(venda.getItemVenda(codigo));
                    quantidadeItens--;
                }
                else{
                    System.out.println("Nenhum item adicionado na venda!");
                }
            }
            else if(opcao==3){
                registrando=false;
            }
            else{
                System.out.println("Opção inválida!");
            }
        }

    }

    public static void finalizarVenda(Venda venda){
        //Seta a variável de status para incompleta
        venda.setStatusVenda("Incompleta!");
        //calcula o subtotal da venda
        venda.setSubTotal();

        //aplica desconto ou não
        System.out.println("Deseja aplicar desconto?  1-Sim  2-Não");
        int opcao;
        opcao = scanner.nextInt();
        if (opcao == 1){
            venda.setDesconto();
        }

        //calcula valor final da venda e mostra o recibo
        venda.setValorDaVenda();
        System.out.println(venda.toString());

        System.out.println("Deseja concluir a compra?  1-Sim  2-Cancelar compra");
        opcao = scanner.nextInt();

        if (opcao == 1){
            venda.setStatusVenda("Efetuada");

            //cadastra a venda no histórico de vendas
            historicoVendas.cadastraVendas(venda);

            //realiza a baixa no estoque
            venda.baixaEstoque(estoque);

            System.out.println("Compra finalizada!\n");
        }
    }

    //item 2
    public static void cancelarVenda(){

        //lista todas vendas efetuadas
        historicoVendas.listarVendasEfetuadas();
        System.out.println("Digite o número da venda:");
        int numero = scanner.nextInt();

        while (historicoVendas.getVenda(numero) == null) {
            System.out.println("O número da venda não existe, digite um novo número:");
            numero = scanner.nextInt();
        }

        //cancela a venda e efetua a reposição no estoque
        historicoVendas.cancelaVenda(numero,estoque);
    }

    //item 3
    public static void listarVendas(){

        //lista as últimas 5 vendas realizadas
        int tamanho = historicoVendas.getVendas().size();
        int itens = 0;

        if (tamanho > 0){
            while (tamanho > 0 && itens <= 4) {
                System.out.println(historicoVendas.getVendas().get(tamanho - 1));
                tamanho -= 1;
                itens += 1;
            }
        }else{
            System.out.println("Não há vendas efetuadas!\n");
        }
    }

    //item 4
    public static void imprimirRecibo(){

        System.out.println("Digite o número da venda:");
        int numero = scanner.nextInt();

        while (historicoVendas.getVenda(numero) == null) {
            System.out.println("O número da venda não existe, digite um novo número:");
            numero = scanner.nextInt();
        }
        System.out.println(historicoVendas.getVenda(numero));
    }


    //MÓDULO ESTOQUE

    //item 1
    private static void cadastrarProduto(){

        int opcao = 1;
        while (opcao == 1) {
            System.out.println("Digite a descrição do produto:");
            String descricao = scanner.next();

            System.out.println("Digite o preço unitário do produto:");
            double precoUnit = scanner.nextDouble();

            System.out.println("Digite a quantidade inicial do produto:");
            int qtdInicial = scanner.nextInt();

            //cadastra um novo produto e a quantidade inicial em estoque
            Produto produto = new Produto(estoque.getQuantidadeProdutos()+1, descricao, precoUnit);
            estoque.cadastraProduto(produto, qtdInicial);
            System.out.println("Produto cadastrado!");

            System.out.println("Deseja cadastrar outro produto? 1-Sim 2-Não");
            opcao = scanner.nextInt();
        }
    }

    //item 2
    private static void listarProdutos(){
        System.out.println(estoque.toString());
    }

    //item 3
    private static void reporProduto(){
        int opcao = 1;
        while (opcao == 1) {
            listarProdutos();
            System.out.println("Digite o código do produto:");
            int codProd = scanner.nextInt();
            while (estoque.getProduto(codProd) == null) {
                System.out.println("O código do produto não existe, digite um novo código:");
                codProd = scanner.nextInt();
            }

            System.out.println("Digite a quantidade de produtos:");
            int qtdReposicao = scanner.nextInt();

            //aumenta a quantidade de um produto no estoque
            estoque.reposicaoEstoque(codProd, qtdReposicao);

            System.out.println("Deseja repor outro produto? 1-Sim 2-Não");
            opcao = scanner.nextInt();
        }

    }


    //RELATÓRIOS

    //item 1
    public static void mostraFaturamento(){
        System.out.println("Faturamento Bruto: "+historicoVendas.getFaturamentoBruto());
        System.out.println("Faturamento Líquido: "+historicoVendas.getFaturamentoLiquido()+"\n");
    }

    //item 4
    public static void listarVendasCanceladas(){
        historicoVendas.listarVendasCanceladas();
    }






    //preenche o estoque com os produtos e sua quantidade
    private static void popularEstoque(){

        // Obtem o caminho para o diretório corrente
        String currDir = Paths.get("").toAbsolutePath().toString();
        // Monta o nome do arquivo
        String nameComplete = currDir+"\\"+"estoque.txt";
        // Cria acesso ao "diretorio" da mídia (disco)
        Path path = Paths.get(nameComplete);

        String linha = "";
        // Usa a classe scanner para fazer a leitura do arquivo
        try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))){
            linha = sc.nextLine();
        }catch (IOException x){
            System.err.format("Erro de E/S: %s%n", x);
        }
        String[] dados = linha.split(",");

        for(int i = 0; i < dados.length; i += 4) {
            Produto produto = new Produto(Integer.parseInt(dados[i]), dados[i + 1], (double)Integer.parseInt(dados[i + 2]));
            estoque.cadastraProduto(produto, Integer.parseInt(dados[i + 3]));
        }
    }


    /*private static void popularEstoque() {
        GerenciadorArquivos.leituraArquivo(linha -> {
            String[] dados = linha.split(",");
            estoque.cadastraProduto(new Produto(
                            Integer.parseInt(dados[0]),
                            dados[1],
                            Integer.parseInt(dados[2])),
                    Integer.parseInt(dados[3]));
        });
    }*/
}
