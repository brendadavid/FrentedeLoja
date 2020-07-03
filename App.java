public class App{
    public static void main(String args[]) {
        // Instancia o controle de estoque
        Estoque e = new Estoque();

        // Cadastra um produto
        e.cadastraProduto(new Produto(10, "iPhone", 4000.0), 100);

        // Obtem um produto
        Produto p = e.getProduto(10);
        System.out.println(p);

        // Imprime quantidade do produto em estoque
        System.out.println("Quantidade de produdos: " + e.getQuantidadeProdutos());

        // Imprime quantidade de itens de um dado produto em estoque
        System.out.println("Quantidade de itens em estoque: " + e.getQuantidadeEstoque(10));
    }
}