package controle;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Um controle que manipula alunos, grupos de alunos e alunos que responderam a questões no quadro.
 *
 * @author Kilian Melcher
 */
public class Controle {

    /**
     * Mapa que guarda alunos associados a sua matrícula.
     */
    private HashMap<Integer,Aluno> alunos;

    /**
     * Mapa que guarda grupos assosciados ao seu nome.
     */
    private HashMap<String,Grupo> grupos;

    /**
     * Lista dos alunos que responderam a questões no quadro.
     */
    private ArrayList<String> alunosQueResponderam;

    /**
     * Cria um controle.
     * Esse controle é inicializado com um mapa de alunos e grupos e uma lista de alunos que responderam
     * a questões no quadro.
     */
    public Controle() {
        this.alunos = new HashMap<>();
        this.grupos = new HashMap<>();
        this.alunosQueResponderam = new ArrayList<>();
    }

    /**
     * Cadastra um aluno no controle.
     * O cadastrado é feito a partir da matrícula, nome e seu curso.
     * Ele só será efetuado se o aluno não estiver cadastrado no controle.
     *
     * @param matricula matrícula do aluno.
     * @param nome nome do aluno.
     * @param curso curso do aluno.
     * @return Representação booleana se o aluno foi cadastrado.
     */
    public boolean cadastraAluno(int matricula, String nome, String curso) {
        if (!this.alunoCadastrado(matricula)) {
            this.alunos.put(matricula, new Aluno(matricula, nome, curso));
            return true;
        }
        return false;
    }

    /**
     * Checa se o aluno está cadastrado no controle.
     *
     *
     * @param matricula matrícula do aluno.
     * @return Representação booleana se o aluno está cadastrado.
     */
    public boolean alunoCadastrado(int matricula) {
        return this.alunos.containsKey(matricula);
    }

    /**
     * Exibe os detalhes de um aluno.
     *
     * @param matricula matricula do aluno a ser exibido.
     * @return Representação em String do aluno.
     */
    public String exibeAluno(int matricula) {
        return "Aluno: " + this.alunos.get(matricula);
    }

    /**
     * Cadastra um grupo no controle.
     * O grupo só será cadastrado se já não houver um cadastro do grupo.
     *
     * @param nomeGrupo nome do grupo a ser cadastrado.
     * @param restricao restrição de curso que os alunos podem participar desse grupo.
     * @return Representação boolena se o grupo foi cadastrado.
     */
    public boolean cadastraGrupo(String nomeGrupo, String restricao) {
        if (!this.grupoCadastrado(nomeGrupo)) {
            this.grupos.put(nomeGrupo.toUpperCase(), new Grupo(nomeGrupo, restricao));
            return true;
        }
        return false;
    }

    /**
     * Checa se um grupo está cadastrado no controle.
     *
     * @param nome nome do grupo a ser checado.
     * @return Representação booleana se o grupo está cadastrado.
     */
    public boolean grupoCadastrado(String nome) {
        return this.grupos.containsKey(nome.toUpperCase());
    }

    /**
     * Aloca aluno em grupo.
     * O aluno só será alocado se o grupo e o aluno estiverem cadastrado no controle e se o aluno
     * for restrito ao grupo.
     *
     * @param matricula matricula do aluno a ser alocado.
     * @param nomeGrupo nome do grupo em que o aluno será alocado.
     * @return Representação booleana se o aluno foi alocado.
     */
    public boolean alocaAlunoEmGrupo(int matricula, String nomeGrupo) {
        if (this.alunoCadastrado(matricula) && this.grupoCadastrado(nomeGrupo)) {
            if (this.alunoRestritoAoGrupo(matricula, nomeGrupo)) {
                Grupo grupo = this.grupos.get(nomeGrupo.toUpperCase());
                Aluno aluno = this.alunos.get(matricula);
                grupo.adiciona(aluno);
                return true;
            }
        }
        return false;
    }

    /**
     * Checa se o aluno está restrito ao grupo.
     * Um aluno será restrito ao grupo se o seu curso for igual a restrição do grupo.
     * Caso não haja restrição no grupo todos os alunos são restritos.
     *
     * @param matricula matricula do aluno a ser checado.
     * @param nomeGrupo nome do grupo a ser checado.
     * @return Representação booleana se o aluno é restrito ao grupo.
     */
    public boolean alunoRestritoAoGrupo(int matricula, String nomeGrupo) {
        Aluno aluno = this.alunos.get(matricula);
        Grupo grupo = this.grupos.get(nomeGrupo.toUpperCase());
        if (grupo.getRestricao().isBlank()) {
            return true;
        }
        return aluno.getCurso().equals(grupo.getRestricao());
    }

    /**
     * Checa se o aluno pertence ao grupo.
     * Caso o aluno ou o grupo não estejam cadastrados no controle resultará em falso.
     *
     * @param matricula matrícula do aluno a ser checado.
     * @param nomeGrupo nome do grupo a ser checado.
     * @return Representação booleana se o aluno pertence ao grupo.
     */
    public boolean alunoPertenceAoGrupo(int matricula, String nomeGrupo) {
        if (this.alunoCadastrado(matricula) && this.grupoCadastrado(nomeGrupo)) {
            Grupo grupo = this.grupos.get(nomeGrupo.toUpperCase());
            Aluno aluno = this.alunos.get(matricula);
            return grupo.contem(aluno);
        }
        return false;
    }

    /**
     * Registra aluno que respondeu a questões no quadro.
     * O registro só será efetuado se o aluno estiver cadastrado no controle.
     *
     * @param matricula matricula do aluno a ser registrado.
     * @return Representação booleana se o aluno foi registrado.
     */
    public boolean registraAlunoQueRespondeu(int matricula) {
        if (this.alunoCadastrado(matricula)) {
            Aluno aluno = this.alunos.get(matricula);
            this.alunosQueResponderam.add(aluno.toString());
            return true;
        }
        return false;
    }

    /**
     * Gera um texto contendo a lista de alunos que responderam a questões no quadro,
     * no formato: "[índice]. [representação em texto de um aluno]"
     *
     * @return Representação em String da lista de alunos.
     */
    public String imprimeAlunosQueResponderam() {
        String listaDeAlunos = "Alunos:";
        for (int i = 0; i < this.alunosQueResponderam.size(); i++) {
            listaDeAlunos += "\n" + (i+1) + ". " + this.alunosQueResponderam.get(i);
        }
        return listaDeAlunos;
    }

    /**
     * Conta quantos grupos existem com a restrição de um curso.
     *
     * @param curso curso que corresponde a restrição do grupo.
     * @return Representação inteira de quantos grupos existem com a restrição do curso.
     */
    public int contagemGruposRestritos(String curso) {
        int contagem = 0;
        for (Grupo grupo: this.grupos.values() ) {
            if (grupo.getRestricao().equals(curso)) {
                contagem++;
            }
        }
        return contagem;
    }
}
