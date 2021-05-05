package controle;

import java.util.Objects;

/**
 * Aluno pertencente ao controle.
 * Cada aluno possui um nome, curso e uma matrícula.
 * Um aluno é identificado unicamente pela sua matrícula.
 *
 * @author Kilian Melcher
 */
public class Aluno {

    /**
     * Matrícula do aluno. Esse valor é um número inteiro que único de cada aluno.
     */
    private int matricula;

    /**
     * Nome do aluno.
     */
    private String nome;

    /**
     * Curso em que o aluno está matrículado.
     */
    private String curso;

    /**
     * Cria um aluno, a partir de sua matrícula, nome e curso.
     * Caso o aluno seja criado com nome ou curso nulo ou vazio um exceção será jogada e o programa
     * irá fechar.
     *
     * @param matricula matrícula do aluno.
     * @param nome nome do aluno.
     * @param curso curso do aluno.
     */
    public Aluno(int matricula,String nome, String curso) {
        this.checaAtributoNull(nome, curso);
        this.checaAtributoVazio(nome, curso);
        this.matricula = matricula;
        this.nome = nome;
        this.curso = curso;
    }

    /**
     * Checa se o nome ou o curso do aluno é nulo.
     * Caso sim uma exceção com uma mensagem do erro será jogada e o programa fechará.
     *
     * @param nome nome do aluno.
     * @param curso curso do aluno.
     */
    private void checaAtributoNull(String nome, String curso) {
        if (nome == null) {
            throw new NullPointerException("Nome nulo");
        } else if ( curso == null) {
            throw new NullPointerException("Curso nulo");
        }
    }

    /**
     * Checa se o nome ou o curso do aluno é vazio.
     * Caso sim uma exceção com uma mensagem do erro será jogada e o programa fechará.
     *
     * @param nome nome do aluno.
     * @param curso curso do aluno.
     */
    private void checaAtributoVazio(String nome, String curso) {
        if (nome.isBlank()) {
            throw new IllegalArgumentException("Nome vazio");
        } else if (curso.isBlank()) {
            throw new IllegalArgumentException("Curso vazio");
        }
    }

    /**
     * Retorna o curso do aluno.
     *
     * @return Representação em String do curso do aluno.
     */
    public String getCurso(){
        return this.curso;
    }


    /**
     * Gera uma representação em texto de um aluno, no formato: "[matricula] - [nome] - [curso]".
     *
     * @return
     */
    @Override
    public String toString() {
        return this.matricula + " - " + this.nome + " - " + this.curso;
    }

    /**
     * Compara se um aluno é igual a outro a partir da matrícula.
     *
     * @param o Objeto a ser comparado.
     * @return Representação booleana se os alunos são iguais.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aluno)) return false;
        Aluno aluno = (Aluno) o;
        return this.matricula == aluno.matricula;
    }

    /**
     * Cria um código hash a partir da matrícula do aluno.
     *
     * @return Representação em String do código hash da matrícula.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.matricula);
    }
}
