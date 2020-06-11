package gerador;

import java.util.List;

public class GeraTeste {

    public static void main(String[] Args){

        String localArquivo = "/home/marceloeler/gdrive/Projects/IntelliJ/GeradorDadosTeste/src/gerador/exemplorestricoes.txt";

        LeitorDeArquivo leitor = new LeitorDeArquivo();
        List<String> linhas = leitor.leArquivoTexto(localArquivo);

        for (String linha: linhas){
            System.out.println(linha);
        }

    }

}
