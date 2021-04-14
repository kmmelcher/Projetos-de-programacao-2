package agenda;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Lógica para ler de arquivos csv os dados necessários e povoar uma agenda.
 *
 * @author Nazareno Andrade e Kilian Melcher
 *
 */
public class LeitorDeAgenda {

    private static final int COLUNA_POSICAO = 0;
    private static final int COLUNA_NOME = 1;
    private static final int COLUNA_SOBRENOME = 2;
    private static final int COLUNA_TELEFONE1 = 3;
    private static final int COLUNA_TELEFONE2 = 4;
    private static final int COLUNA_TELEFONE3 = 5;
    private static final int COLUNA_TELEFONE_PRIORITARIO = 6;
    private static final int COLUNA_TELEFONE_WHATSAPP = 7;


    /**
     * Lê contatos de um arquivo CSV e os coloca em uma agenda.
     * @param arquivoContatos Caminho para arquivo contendo contatos.
     * @param agenda A agenda a manipular.
     * @return O número de contatos adicionados à agenda.
     * @throws IOException Caso não tenhamos permissão de ler o arquivo.
     * @throws FileNotFoundException Caso o arquivo não exista.
     */
    public int carregaContatos(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
        int carregados = -1;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoContatos))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                carregados += 1;
                if (carregados == 0) {
                    // pulamos a primeira linha, o cabeçalho
                    continue;
                }
                String[] campos = linha.split(",");
                processaLinhaCsvContatos(campos, agenda);
            }
        }

        return carregados;
    }


    /**
     * Coloca na agenda os dados de uma linha do arquivo de agenda inicial.
     *
     * @param campos As informações lidas do csv.
     * @param agenda A agenda a manipular.
     */
    private void processaLinhaCsvContatos(String[] campos, Agenda agenda) {
        int posicao = Integer.parseInt(campos[COLUNA_POSICAO]);

        String nome = campos[COLUNA_NOME].trim();
        String sobrenome = campos[COLUNA_SOBRENOME].trim();

        String[] telefones = new String[3];
        telefones[0] = campos[COLUNA_TELEFONE1].trim();
        telefones[1] = campos[COLUNA_TELEFONE2].trim();
        telefones[2] = campos[COLUNA_TELEFONE3].trim();

        int telefonePrioritario = Integer.parseInt(campos[COLUNA_TELEFONE_PRIORITARIO].trim());
        int telefoneWhatsapp = Integer.parseInt(campos[COLUNA_TELEFONE_WHATSAPP].trim());

        String prioritario = telefones[telefonePrioritario-1];
        String whatsapp = telefones[telefoneWhatsapp-1];
        String adicional = telefones[2];

        agenda.cadastraContato(posicao, nome, sobrenome, prioritario, whatsapp, adicional);
    }

}
