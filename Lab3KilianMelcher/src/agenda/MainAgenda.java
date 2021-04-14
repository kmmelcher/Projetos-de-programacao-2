package agenda;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentNavigableMap;

/**
 * Interface com menus texto para manipular uma agenda de contatos.
 *
 * @author Nazareno Andrade e Kilian Melcher
 *
 */
public class MainAgenda {

    public static void main(String[] args) {
        Agenda agenda = new Agenda();

        System.out.println("Carregando agenda inicial");
        try {
            /*
             * Essa é a maneira de lidar com possíveis erros por falta do arquivo.
             */
            carregaAgenda("agenda_inicial.csv", agenda);
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro lendo arquivo: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String escolha = "";
        while (true) {
            escolha = menu(scanner);
            comando(escolha, agenda, scanner);
        }

    }

    /**
     * Lê uma agenda de um arquivo csv.
     *
     * @param arquivoContatos O caminho para o arquivo.
     * @param agenda A agenda que deve ser populada com os dados.
     * @throws IOException Caso o arquivo não exista ou não possa ser lido.
     */
    private static void carregaAgenda(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
        LeitorDeAgenda leitor = new LeitorDeAgenda();

        int carregados =  leitor.carregaContatos(arquivoContatos, agenda);
        System.out.println("Carregamos " + carregados + " contatos.");
    }

    /**
     * Exibe o menu e captura a escolha do/a usuário/a.
     *
     * @param scanner Para captura da opção do usuário.
     * @return O comando escolhido.
     */
    private static String menu(Scanner scanner) {
        System.out.print(
                "\n---\nMENU\n" +
                        "(C)adastrar Contato\n" +
                        "(L)istar Contatos\n" +
                        "(E)xibir Contato\n" +
                        "(P)esquisar Contatos\n" +
                        "(D)eletar Contato\n" +
                        "(F)avoritos\n" +
                        "(A)dicionar Favorito\n" +
                        "(R)emover Favorito\n" +
                        "(M)udar Telefone\n" +
                        "(S)air\n" +
                        "\n" +
                        "Opção> ");
        return scanner.next().toUpperCase();
    }

    /**
     * Interpreta a opção escolhida por quem está usando o sistema.
     *
     * @param opcao   Opção digitada.
     * @param agenda  A agenda que estamos manipulando.
     * @param scanner Objeto scanner para o caso do comando precisar de mais input.
     */
    private static void comando(String opcao, Agenda agenda, Scanner scanner) {
        switch (opcao) {
            case "C":
                cadastraContato(agenda, scanner);
                break;
            case "L":
                listaContatos(agenda);
                break;
            case "E":
                exibeContato(agenda, scanner);
                break;
            case "P":
                pesquisaContatos(agenda, scanner);
                break;
            case "D":
                deletaContato(agenda, scanner);
                break;
            case "F":
                favoritos(agenda);
                break;
            case "A":
                adicionaFavorito(agenda, scanner);
                break;
            case "R":
                removeFavorito(agenda, scanner);
                break;
            case "M":
                mudarTelefone(agenda, scanner);
                break;
            case "S":
                sai();
                break;
            default:
                System.out.println("OPÇÃO INVÁLIDA!");
        }
    }

    /**
     * Lê um valor inteiro do usuário para uma variável. Caso o usuário digite um valor que não seja (int)
     * será perguntado novamente até que a entrada seja válida.
     *
     * @param scanner Objeto scanner para receber o inteiro do usuário.
     * @param mensagem Contendo a informação requisitada do usuário.
     * @return entrada inteira do usuário.
     */
    private static int leInteiro(Scanner scanner, String mensagem) {
        int inteiro;
        while (true) {
            System.out.print("\n" + mensagem + "> ");
            try {
                inteiro = Integer.parseInt(scanner.next());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println(mensagem.toUpperCase() + " DEVE SER UM NÚMERO!");
            }
        }
        return inteiro;
    }

    /**
     * Cadastra um contato na agenda. 
     * O contato só será cadastrado se o contato ainda não existe na agenda e se a posição for válida.
     *
     * @param agenda A agenda.
     * @param scanner Scanner para pedir informações do contato.
     */
    private static void cadastraContato(Agenda agenda, Scanner scanner) {
        int posicao = leInteiro(scanner, "Posição");
        System.out.print("\nNome: ");
        String nome = scanner.next();
        System.out.print("\nSobrenome: ");
        String sobrenome = scanner.next();
        System.out.print("\nPrioritario: ");
        String prioritario = scanner.next();
        System.out.print("\nWhatsapp: ");
        String whatsapp = scanner.next();
        System.out.print("\nAdicional: ");
        scanner.nextLine();                    //Limpa a linha restante.
        String adicional = scanner.nextLine(); //Uso do nextLine para que o campo seja opcional;

        if (agenda.cadastraContato(posicao,nome, sobrenome, prioritario, whatsapp, adicional)) {
            System.out.println("CADASTRO REALIZADO");
        } else if (!agenda.posicaoValida(posicao)) {
            System.out.println("POSIÇÃO INVÁLIDA");
        } else if (agenda.contatoCadastrado(nome, sobrenome)) {
            System.out.println("CONTATO JA CADASTRADO");
        }
    }

    /**
     * Imprime lista de contatos da agenda.
     *
     * @param agenda A agenda sendo manipulada.
     */
    private static void listaContatos(Agenda agenda) {
        System.out.print(agenda.listaContatos());
    }

    /**
     * Imprime lista de favoritos da agenda.
     *
     * @param agenda A agenda sendo manipulada.
     */
    private static void favoritos(Agenda agenda) {
        System.out.print(agenda.listaFavoritos());
    }

    /**
     * Imprime os detalhes de um dos contatos da agenda.
     * Os detalhes só serão impressos se o contato existir na agenda.
     *
     * @param agenda A agenda.
     * @param scanner Scanner para capturar qual contato.
     */
    private static void exibeContato(Agenda agenda, Scanner scanner) {
        int contato = leInteiro(scanner, "Contato");

        if (agenda.existeContato(contato)) {
            System.out.println("\n" + agenda.formataContato(contato));
        } else {
            System.out.println("CONTATO INVÁLIDO!");
        }
    }

    /**
     * Pesquisa um contato na agenda por nome ou por sobrenome.
     *
     * @param agenda A agenda.
     * @param scanner Scanner para capturar a escolha da busca, que será feita e o nome ou sobrenome.
     */
    private static void pesquisaContatos(Agenda agenda, Scanner scanner) {
        int escolha = leInteiro(scanner, "Nome ou sobrenome (1 ou 2)");

        if (escolha == 1) {
            System.out.print("\nNome> ");
            String nome = scanner.next();
            System.out.print(agenda.pesquisaContatosPorNome(nome));
        } else if (escolha == 2) {
            System.out.print("\nSobrenome> ");
            String sobrenome = scanner.next();
            System.out.print(agenda.pesquisaContatosPorSobrenome(sobrenome));
        }
    }

    /**
     * Deleta contato da agenda.
     * O contato só será deletado se ele já existir na agenda.
     *
     * @param agenda A agenda.
     * @param scanner Scanner para capturar qual contato.
     */
    private static void deletaContato(Agenda agenda, Scanner scanner) {
        int contato = leInteiro(scanner,"Contato");

        if (agenda.deletaContato(contato)) {
            System.out.println("CONTATO DELETADO");
        } else {
            System.out.println("CONTATO INVÁLIDO!");
        }
    }

    /**
     * Favorita um contato da agenda.
     * O contato só será favoritado se ele não é favorito, se ele existir na agenda e se a posição for válida.
     *
     * @param agenda A agenda.
     * @param scanner Scanner para capturar qual contato e em que posição.
     */
    private static void adicionaFavorito(Agenda agenda, Scanner scanner) {
        int contato = leInteiro(scanner, "Contato");
        int posicao = leInteiro(scanner, "Posição");

        if (!agenda.adicionaFavorito(contato,posicao)) {
            System.out.println("CONTATO FAVORITADO NA POSIÇÃO " + posicao + "!");
        } else if (agenda.contatoFavoritado(contato)) {
            System.out.println("CONTATO JÁ FAVORITADO!");
        } else if (!agenda.existeContato(posicao)) {
            System.out.println("CONTATO INVÁLIDO!");
        } else if (!agenda.posicaoFavoritoValida(posicao)) {
            System.out.println("POSICÃO INVÁLIDA!");
        }
    }

    /**
     * Remove contato dos favoritos.
     * O contato só será removido se ele existir na agenda e se for um contato favoritado.
     *
     * @param agenda A agenda.
     * @param scanner Scanner para capturar qual contato e em que posição.
     */
    private static void removeFavorito(Agenda agenda, Scanner scanner) {
        int contato = leInteiro(scanner, "Contato");

        if (agenda.removeFavorito(contato)) {
            System.out.println("FAVORITO REMOVIDO");
        } else if (!agenda.existeContato(contato)) {
            System.out.println("CONTATO INVÁLIDO!");
        } else if (!agenda.contatoFavoritado(contato))  {
            System.out.println("FAVORITO INVÁLIDO!");
        }
    }

    /**
     * Muda o telefone prioritário do contato.
     * O telefone só será mudado se o contato for válido.
     *
     * @param agenda A agenda.
     * @param scanner Scanner para capturar qual contato.
     */
    private static void mudarTelefone(Agenda agenda, Scanner scanner) {
        int contato = leInteiro(scanner, "Contato");
        System.out.print("\nTelefone prioritário> ");
        String telefonePrioritario = scanner.next();

        if (agenda.mudaTelefonePrioritario(contato,telefonePrioritario)) {
            System.out.println("TELEFONE MUDADO");
        } else {
            System.out.println("CONTATO INVÁLIDO!");
        }
    }

    /**
     * Sai da aplicação.
     */
    private static void sai() {
        System.out.println("\nAGENDA FECHADA");
        System.exit(0);
    }


}
