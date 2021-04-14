package lab2;

/**
 * Representação de uma disciplina, especificamente do curso
 * ciência da computação da UFCG. Toda disciplina possui
 * notas que determinam se um aluno foi aprovado ou não.
 *
 * @author Kilian Macedo Melcher
 */
public class Disciplina {

    /**
     * Nome da disciplina.
     */
    private String nomeDisciplina;

    /**
     * Horas de estudo do aluno dedicadas a essa disciplina.
     */
    private int horasDeEstudo;

    /**
     * Notas atribuídas a disciplina.
     */
    private double[] notas;

    /**
     * Media ponderada das notas sem arredondamento.
     */
    private double media;

    /**
     * Peso de cada nota.
     */
    private int[] pesos;

    /**
     * Constrói uma disciplina a partir do nome da disciplina.
     * Por padrão, a disciplina começa com: 4 notas, zero horas de
     * estudo, zero na média e com cada nota com peso 1.
     *
     * @param nomeDisciplina nome da disciplina
     */
    public Disciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
        this.notas = new double[4];
        this.horasDeEstudo = 0;
        this.media = 0;
        this.inicializaPesos();
    }

    /**
     * Constrói uma disciplina a partir do nome da disciplina
     * e da quantidade de notas.
     * Por padrão, a disciplina começa com: zero horas de estudo,
     * zero na média e com cada nota com peso 1.
     *
     * @param nomeDisciplina nome da disciplina
     * @param qntNotas quantidade de notas da disciplina
     */
    public Disciplina(String nomeDisciplina, int qntNotas) {
        this.nomeDisciplina = nomeDisciplina;
        this.notas = new double[qntNotas];
        this.horasDeEstudo = 0;
        this.media = 0;
        this.inicializaPesos();
    }

    /**
     * Constrói uma disciplina a partir do nome da disciplina, da
     * quantidade de notas e dos pesos de cada nota.
     * Por padrão, a disciplina começa com: zero horas de estudo e
     * zero na média.
     *
     * @param nomeDisciplina nome da disciplina
     * @param qntNotas quantidade de notas da disciplina
     * @param pesos peso de cada nota.
     */
    public Disciplina(String nomeDisciplina, int qntNotas, int[] pesos) {
        this.nomeDisciplina = nomeDisciplina;
        this.notas = new double[qntNotas];
        this.pesos = pesos;
        this.horasDeEstudo = 0;
        this.media = 0;
    }

    /**
     * Inicializa pesos com valor 1 para cada nota.
     */
    public void inicializaPesos() {
        this.pesos = new int[this.notas.length];
        for (int i = 0; i < this.pesos.length; i++) {
            this.pesos[i] = 1;
        }
    }

    /**
     * Calcula o somatório do total de pesos.
     *
     * @return somatório de pesos em inteiro.
     */
    public int calculaTotalPesos() {
        int total = 0;
        for (int peso: this.pesos) {
            total += peso;
        }
        return total;
    }

    /**
     * Incrementa o valor de horas de estudo na disciplina.
     *
     * @param horas horas de estudo a serem incrementadas
     */
    public void cadastraHoras(int horas) {
        this.horasDeEstudo += horas;
    }

    /**
     * Calcula a média ponderada das notas e atribui o resultado
     * para a média.
     */
    private void calculaMedia() {
        double soma = 0;
        for (int i = 0; i < this.notas.length; i++) {
            soma += this.notas[i] * this.pesos[i];
        }
        this.media = soma/this.calculaTotalPesos();
    }

    /**
     * Atribui novo valor à uma nota e, em seguida,
     * recalcula a média.
     *
     * @param nota índice da nota
     * @param valorNota valor para ser atribuido à nota
     */
    public void cadastraNota(int nota, double valorNota) {
        this.notas[nota-1] = valorNota;
        this.calculaMedia();
    }

    /**
     * Checa se o aluno possui uma média superior ou igual a 7.0.
     * Se sim, ele está aprovado, caso contrário reprovado.
     *
     * @return representação em Boolean se o aluno foi aprovado ou não
     */
    public boolean aprovado() {
        return this.media >= 7.0;
    }

    /**
     * Converte array de notas em uma String no formato
     * "[Nota1, ..., NotaN]"
     *
     * @return representação em String das 4 notas
     */
    public String notasToString() {
        String notasEmTexto = "[";
        for (int i = 0; i < this.notas.length-1; i++) {
            notasEmTexto += this.notas[i] + ", ";
        }
        notasEmTexto += this.notas[notas.length-1] + "]";
        return notasEmTexto;
    }

    /**
     * Retorna nome da disciplina.
     *
     * @return nome da disciplina.
     */
    public String getNomeDisciplina(){
        return this.nomeDisciplina;
    }

    /**
     * Retorna a String que representa a disciplina.
     * A representação segue o formato
     * "[Nome da disciplina] [Horas de estudo] [Media] [N1, N2, ..., Nx]"
     *
     * @return representação em String da disciplina
     */
    public String toString() {
        String infoDisciplina = "";
        infoDisciplina += this.nomeDisciplina + " ";
        infoDisciplina += this.horasDeEstudo + " ";
        infoDisciplina += this.media + " ";
        infoDisciplina += this.notasToString();
        return infoDisciplina;
    }

}
