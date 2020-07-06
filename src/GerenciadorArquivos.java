import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class GerenciadorArquivos {

    private static final String arquivo = "estoque";

    public void leituraArquivo(Consumer<String> consumidor) {
        leituraArquivo(arquivo, consumidor);
    }

    public void leituraArquivo(String arquivo, Consumer<String> consumidor) {
        List<String> linhas = new ArrayList<>();
        try {
            //Procura o arquivo na pasta resources
            Path path = Paths.get(Paths.get(ClassLoader.getSystemResource(arquivo).toURI()).toString());

            //Le e percorre cada item do arquivo adicionando ele na lista de linhas
            Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8));
            sc.forEachRemaining(linhas::add);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException("Erro ao ler arquivo [" + arquivo + "]");
        }
        //Aplica a funcão recebida por parametro para cada item na lista
        linhas.forEach(consumidor);
    }
}
