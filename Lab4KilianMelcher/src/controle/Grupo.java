package controle;

import java.util.HashSet;
import java.util.Objects;

/**
 * Grupo de alunos pertencente ao controle.
 * Cada grupo possui um nome, restrição de curso e um conjunto de alunos associados a ele.
 * Um grupo é identificado unicamente pelo seu nome, e esse não difencia maísculas e minúsculas.
 *
 * @author Kilian Melcher
 */
public class Grupo {

    /**
     * Nome do grupo.
     */
    private String nome;

    /**
     * Restrição de curso. Somente alunos com esse curso poderão entrar nesse grupo.
     */
    private String restricao;

    /**
     * Alunos que estão no grupo.
     */
    private HashSet<Aluno> alunos;

    /**
     * Cria um grupo, a partir do nome e da restrição.
     * O grupo só será criado se o nome do grupo e for não nulo e não vazio e se a restrição for
     * não nula. O nome será passado para letras maísculas para ignorar "case-sensitive".
     *
     * @param nomeGrupo nome do grupo.
     * @param restricao restrição de curso.
     */
    public Grupo(String nomeGrupo, String restricao) {
        this.checaAtributoNull(nomeGrupo, restricao);
        this.checaAtributoVazio(nomeGrupo);
        this.nome = nomeGrupo.toUpperCase();
        this.restricao = restricao;
        this.alunos = new HashSet<>();
    }

    /**
     * Checa se os atributos do grupo são nulos.
     * Caso sim uma exceção com uma mensagem do erro será jogada e o programa fechará.
     *
     * @param nome nome do grupo.
     * @param restricao restrição do grupo.
     */
    private void checaAtributoNull(String nome, String restricao) {
        if (nome == null) {
            throw new NullPointerException("Nome nulo");
        } else if ( restricao == null) {
            throw new NullPointerException("Restrição nula");
        }
    }

    /**
     * Checa se o nome do grupo é vazio.
     * Caso sim uma exceção com uma mensagem do erro será jogada e o programa fechará.
     *
     * @param nome nome do grupo.
     */
    private void checaAtributoVazio(String nome) {
        if (nome.isBlank()) {
            throw new IllegalArgumentException("Nome vazio");
        }
    }

    /**
     * Retorna a restrição de curso do grupo.
     *
     * @return Representação em String da restrição do curso em letras maíusculas.
     */
    public String getRestricao() {
        return this.restricao;
    }

    /**
     * Adiciona a matrícula do aluno no grupo.
     * O matrícula só será adicionada se ainda não existir no grupo.
     *
     * @param aluno aluno a ser adicionada.
     * @return Representação booleana se o aluno foi adicionado.
     */
    public boolean adiciona(Aluno aluno) {
        return this.alunos.add(aluno);
    }

    /**
     * Checa o aluno está contido no grupo.
     *
     * @param aluno aluno a ser checado.
     * @return Representação booleana se o aluno está contido.
     */
    public boolean contem(Aluno aluno) {
        return this.alunos.contains(aluno);
    }

    /**
     * Compara se um grupo é igual a outro a partir do seu nome.
     *
     * @param o Objeto a ser comparado.
     * @return Representação booleana se os grupos são iguais.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grupo)) return false;
        Grupo grupo = (Grupo) o;
        return this.nome.equals(grupo.nome);
    }

    /**
     * Cria um código hash a partir do nome do grupo.
     *
     * @return Representação em String do código hash do nome.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.nome);
    }
}
