package lab2;

/**
 * Representação do registro de tempo online de um estudante
 * em uma disciplina. O registro é responsável por administrar a
 * quantidade de horas que um aluno dedica na disciplina.
 *
 * @author Kilian Macedo Melcher
 */
public class RegistroTempoOnline {

    /**
     * Nome da disciplina relacionada ao registro.
     */
    private String nomeDisciplina;

    /**
     * Tempo online esperado, em horas, que o estudante se dedique
     * a disciplina. Geralmente é duas vezes a carga horária dela.
     */
    private int tempoOnlineEsperado;

    /**
     * Tempo online realmente gasto, em horas, por aquele estudante
     * na disciplina. Inicializado com zero para futuro incremento.
     */
    private int tempoOnlineGasto;

    /**
     * Constrói um registro a partir do nome da disciplina.
     * O registro de o tempo online começa com o tempo online
     * esperado com 120 horas e o tempo online gasto como zero.
     *
     * @param nomeDisciplina nome da disciplina relacionada com o registro.
     */
    public RegistroTempoOnline(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
        this.tempoOnlineEsperado = 120;
        this.tempoOnlineGasto = 0;
    }

    /**
     * Constrói um registro a partir do nome da disciplina e
     * do tempo online esperado.
     *  O registro de o tempo online começa com tempo online
     *  gasto como zero.
     *
     * @param nomeDisciplina nome da disciplina relacionada com o registro.
     * @param tempoOnlineEsperado tempo online esperado que um
     * aluno gaste na disciplina.
     */
    public RegistroTempoOnline(String nomeDisciplina, int tempoOnlineEsperado) {
        this.nomeDisciplina = nomeDisciplina;
        this.tempoOnlineEsperado = tempoOnlineEsperado;
        this.tempoOnlineGasto = 0;
    }

    /**
     * Incrementa o tempo online gasto.
     *
     * @param tempo tempo a ser incrementado em horas.
     */
    public void adicionaTempoOnline(int tempo) {
        this.tempoOnlineGasto += tempo;
    }

    /**
     * Checa se o tempo online gasto é maior ou igual ao tempo
     * online esperado.
     *
     * @return representação em Boolean se o tempo gasto é maior
     * ou igual ao tempo esperado
     */
    public boolean atingiuMetaTempoOnline() {
        return this.tempoOnlineGasto >= this.tempoOnlineEsperado;
    }

    /**
     * Retorna nome da disciplina associada ao registro de tempo
     * online.
     *
     * @return nome da disciplina.
     */
    public String getNomeDisciplina(){
        return this.nomeDisciplina;
    }

    /**
     * Retorna a String que representa o registro do tempo online.
     * A representação segue o formato
     * "[Nome da disciplina] [Tempo online gasto] [Tempo online esperado]"
     *
     * @return a representação em String de um registro de tempo online.
     */
    public String toString() {
        return this.nomeDisciplina + " " + this.tempoOnlineGasto + "/" + this.tempoOnlineEsperado;
    }

}
