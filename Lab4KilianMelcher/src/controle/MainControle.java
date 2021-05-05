package controle;

import java.util.Scanner;

/**
 * Interface com menus texto para manipular um controle de alunos.
 * É responsável por tratar de toda entrada e saída com o usuário.
 *
 * @author Kilian Melcher
 *
 */
public class MainControle {

    public static void main(String[] args) {
        Controle controle = new Controle();

        Scanner scanner = new Scanner(System.in);
        String opcao;
        while (true) {
            opcao = menu(scanner);
            comando(opcao, controle, scanner);
        }
    }

    /**
     * Exibe o menu e captura a escolha do/a usuário/a.
     *
     * @param scanner Para captura da opção do usuário.
     * @return O comando escolhido em letra maíuscula.
     */
    public static String menu(Scanner scanner) {
        System.out.print(
                        "\n---\nMENU\n" +
                        "(C)adastrar Aluno\n" +
                        "(E)xibir Aluno\n" +
                        "(N)ovo Grupo\n" +
                        "(A)locar Aluno no Grupo e Verificar pertinência a Grupos\n" +
                        "(R)egistrar Aluno que Respondeu\n" +
                        "(I)mprimir Alunos que Responderam\n" +
                        "(O)xe, e a contagem dos grupos com restrição de curso?\n" +
                        "(S)im, quero fechar o programa!\n" +
                        "\n" +
                        "Opção> ");
        return scanner.next().toUpperCase();
    }

    /**
     * Interpreta a opção escolhida por quem está usando o sistema.
     *
     * @param opcao Opção digitada.
     * @param controle O controle que estamos manipulando.
     * @param scanner Objeto scanner para o caso do comando precisar de mais input.
     */
    public static void comando(String opcao, Controle controle, Scanner scanner) {
        switch (opcao) {
            case "C":
                cadastraAluno(controle, scanner);
                break;
            case "E":
                exibeAluno(controle, scanner);
                break;
            case "N":
                cadastraGrupo(controle, scanner);
                break;
            case "A":
                alocaOuVerificaAlunoNoGrupo(controle, scanner);
                break;
            case "R":
                registraAlunoQueRespondeu(controle, scanner);
                break;
            case "I":
                imprimeAlunosQueResponderam(controle);
                break;
            case "O":
                contagemGruposRestritos(controle, scanner);
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
    private static int lerInteiro(Scanner scanner, String mensagem) {
        int inteiro;
        while (true) {
            System.out.print("\n" + mensagem + ": ");
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
     * Cadastra um aluno no controle, a partir do nome e curso.
     * O aluno só será cadastrado se o aluno ainda não existe no controle.
     *
     * @param controle O controle.
     * @param scanner Scanner para pedir informações do aluno.
     */
    private static void cadastraAluno(Controle controle, Scanner scanner) {
        int matricula = lerInteiro(scanner, "Matricula");
        System.out.print("\nNome: ");
        String nome = scanner.next();
        System.out.print("\nCurso: ");
        String curso = scanner.next();

        if (controle.cadastraAluno(matricula, nome, curso)) {
            System.out.println("CADASTRO REALIZADO!");
        } else {
            System.out.println("MATRÍCULA JÁ CADASTRADA!");
        }
    }

    /**
     * Imprime os detalhes de um aluno do controle, a partir da sua matrícula.
     * Os detalhes só serão impressos se o aluno estiver cadastrado no controle.
     *
     * @param controle O controle.
     * @param scanner Scanner para capturar qual aluno.
     */
    private static void exibeAluno(Controle controle, Scanner scanner) {
        int matricula = lerInteiro(scanner, "Matricula");

        if (controle.alunoCadastrado(matricula)) {
            System.out.println(controle.exibeAluno(matricula));
        } else {
            System.out.println("Aluno não cadastrado.");
        }
    }

    /**
     * Cadastra um grupo de alunos no controle, a partir do nome e da restrição de curso.
     * O grupo só será cadastrado se ainda não estiver cadastrado no controle.
     *
     * @param controle O controle.
     * @param scanner Scanner para pedir informações do grupo.
     */
    private static void cadastraGrupo(Controle controle, Scanner scanner) {
        System.out.print("\nGrupo: ");
        String nomeGrupo = scanner.next();
        System.out.print("\nRestrição? ");
        scanner.nextLine(); //Limpa buffer
        String restricao = scanner.nextLine();

        if (controle.cadastraGrupo(nomeGrupo, restricao)) {
            System.out.println("CADASTRO REALIZADO!");
        } else {
            System.out.println("GRUPO JÁ CADASTRADO!");
        }
    }

    /**
     * Aloca ou verifica a pertinência de um aluno em um Grupo.
     * É perguntado ao usuário(a) se ele deseja alocar ou verificar a pertinência do aluno.
     *
     * @param controle O controle.
     * @param scanner Scanner para capturar qual aluno.
     */
    private static void alocaOuVerificaAlunoNoGrupo(Controle controle, Scanner scanner) {
        System.out.print("\n(A)locar Aluno ou (P)ertinência a Grupo? ");
        String escolha = scanner.next().toUpperCase();

        if (escolha.equals("A")) {
            alocaNoGrupo(controle, scanner);
        } else if (escolha.equals("P")) {
            verificaPertinenciaNoGrupo(controle, scanner);
        }
    }

    /**
     * Aloca um aluno em um grupo.
     * O aluno só será alocado no grupo se o aluno e o grupo estiverem cadastrados no controle e
     * se ele não for retrito ao grupo.
     *
     * @param controle O controle.
     * @param scanner Scanner para capturar qual grupo.
     */
    private static void alocaNoGrupo(Controle controle, Scanner scanner) {
        int matricula = lerInteiro(scanner, "Matricula");
        System.out.print("\nGrupo: ");
        String nomeGrupo = scanner.next();

        if (controle.alocaAlunoEmGrupo(matricula, nomeGrupo)) {
            System.out.println("ALUNO ALOCADO");
        } else if (!controle.alunoCadastrado(matricula)) {
            System.out.println("AlUNO NÃO CADASTRADO.");
        } else if (!controle.grupoCadastrado(nomeGrupo)) {
            System.out.println("GRUPO NÃO CADASTRADO.");
        } else if (!controle.alunoRestritoAoGrupo(matricula, nomeGrupo)) {
            System.out.println("GRUPO COM RESTRIÇÃO DE CURSO");
        }
    }

    /**
     * Verifica pertinência do aluno no grupo.
     *
     * @param controle O controle.
     * @param scanner Scanner para capturar qual grupo.
     */
    private static void verificaPertinenciaNoGrupo(Controle controle, Scanner scanner) {
        System.out.print("\nGrupo: ");
        String nomeGrupo = scanner.next();
        int aluno = lerInteiro(scanner, "Aluno");

        if (controle.alunoPertenceAoGrupo(aluno, nomeGrupo)) {
            System.out.println("ALUNO PERTENCE AO GRUPO");
        } else if (!controle.alunoCadastrado(aluno)) {
            System.out.println("AlUNO NÃO CADASTRADO.");
        } else if (controle.grupoCadastrado(nomeGrupo)) {
            System.out.println("GRUPO NÃO CADASTRADO");
        } else {
            System.out.println("ALUNO NÃO PERTENCE AO GRUPO");
        }
    }

    /**
     * Registra aluno que respondeu uma questão no quadro.
     * O aluno só será registrado se ele estiver cadastrado no controle.
     *
     * @param controle O controle.
     * @param scanner Scanner para capturar qual aluno.
     */
    private static void registraAlunoQueRespondeu(Controle controle, Scanner scanner) {
        int matricula = lerInteiro(scanner, "Matricula");

        if (controle.registraAlunoQueRespondeu(matricula)) {
            System.out.println("ALUNO REGISTRADO!");
        } else {
            System.out.println("ALUNO NÃO CADASTRADO");
        }
    }

    /**
     * Imprime alunos que responderam questões no quadro na ordem de inserção.
     *
     * @param controle O controle.
     */
    private static void imprimeAlunosQueResponderam(Controle controle) {
        System.out.println(controle.imprimeAlunosQueResponderam());
    }

    /**
     * Conta quantos grupos restritos ao curso inserido pelo usuário(a) existem.
     *
     * @param controle O controle.
     * @param scanner Scanner para capturar qual curso.
     */
    private static void contagemGruposRestritos(Controle controle, Scanner scanner) {
        System.out.print("\nCurso: ");
        String curso = scanner.next();

        System.out.println(curso + " " +  controle.contagemGruposRestritos(curso));
    }

    /**
     * Sai do programa.
     */
    private static void sai() {
        System.out.println("Programa fechado.");
        System.exit(0);
    }

}
