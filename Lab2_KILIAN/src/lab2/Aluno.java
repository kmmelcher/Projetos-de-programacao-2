package lab2;

import java.util.ArrayList;

/**
 * Aluno representa um aluno do curso de Ciência da Computação
 * da UFCG.
 *
 * @author Kilian Macedo Melcher
 */
public class Aluno {

    /**
     * Lista de disciplinas que o aluno está matriculado.
     */
    private ArrayList<Disciplina> disciplinas;

    /**
     * Lista de registros de tempo online para cada disciplina
     * matriculada.
     */
    private ArrayList<RegistroTempoOnline> registrosOnline;

    /**
     * Registro de finanças do aluno.
     */
    private RegistroFinancas registroFinancas;

    /**
     * Saúde do aluno.
     */
    private Saude saude;

    /**
     * Constrói um aluno. Cada aluno começa com uma lista de
     * disciplinas, uma lista de registros de tempo online, um
     * registro de finanças e uma saúde.
     */
    public Aluno () {
        this.disciplinas = new ArrayList<>();
        this.registrosOnline = new ArrayList<>();
        this.registroFinancas = new RegistroFinancas(0);
        this.saude = new Saude();
    }

    /**
     * Cria um novo registro de tempo online para uma disciplina,
     * a partir do nome da disciplina.
     * Esse registro é adicionado a lista de registros.
     *
     * @param nomeDisciplina nome da disciplina associada ao registo.
     */
    public void registroTempoOnline(String nomeDisciplina) {
        RegistroTempoOnline registroOnline = new RegistroTempoOnline(nomeDisciplina);
        this.registrosOnline.add(registroOnline);
    }

    /**
     * Cria um novo registro de tempo online para uma disciplina,
     * a partir do nome da disciplina e do tempo online esperado.
     * Esse registro é adicionado a lista de registros.
     *
     * @param nomeDisciplina nome da disciplina associada ao registo.
     * @param tempoOnlineEsperado tempo online esperado para essa disciplina.
     */
    public void registroTempoOnline(String nomeDisciplina, int tempoOnlineEsperado) {
        RegistroTempoOnline registroOnline = new RegistroTempoOnline(nomeDisciplina, tempoOnlineEsperado);
        this.registrosOnline.add(registroOnline);
    }

    /**
     * Retorna um novo registro de tempo online, cujo nome da
     * disciplina é igual ao nome dado.
     *
     * @param nomeDisciplina nome da disciplina associada ao registo.
     * @return representação em objeto do registro de tempo online.
     */
    public RegistroTempoOnline getRegistroOnline(String nomeDisciplina) {
        RegistroTempoOnline registroTempoOnline = null;
        for (int i = 0; i < this.registrosOnline.size(); i++) {
            if(this.registrosOnline.get(i).getNomeDisciplina().equals(nomeDisciplina)){
                registroTempoOnline = this.registrosOnline.get(i);
            }
        }
        return registroTempoOnline;
    }

    /**
     * Adiciona tempo online, em horas, ao registro de tempo online
     * associado à disciplina.
     *
     * @param nomeDisciplina nome da disciplina associada ao registo.
     * @param tempo tempo em horas a ser adicionado.
     */
    public void adicionaTempoOnline(String nomeDisciplina, int tempo) {
        this.getRegistroOnline(nomeDisciplina).adicionaTempoOnline(tempo);
    }

    /**
     * Retorna se o aluno atingiu a meta de tempo online para a
     * disciplina.
     *
     * @param nomeDisciplina nome da disciplina a ser checada.
     * @return representação booleana se atingiu a meta ou não.
     */
    public boolean atingiuMetaOnline(String nomeDisciplina) {
        return this.getRegistroOnline(nomeDisciplina).atingiuMetaTempoOnline();
    }

    /**
     * Retorna a representação em String do registro de tempo
     * online do aluno na disciplina dada.
     *
     * @param nomeDisciplina nome da disciplina a ser representada.
     * @return representaçao em String do registro de tempo online
     * da disciplina.
     */
    public String registroOnlineToString(String nomeDisciplina) {
        return this.getRegistroOnline(nomeDisciplina).toString();
    }

    /**
     * Cadastra o aluno em uma nova disciplina, a partir do nome
     * da disciplina e adiciona ela a lista de disciplinas.
     *
     * @param nomeDisciplina nome da disciplina a ser cadastrada.
     */
    public void cadastraDisciplina(String nomeDisciplina) {
        Disciplina disciplina = new Disciplina(nomeDisciplina);
        this.disciplinas.add(disciplina);
    }

    /**
     * Retorna a disciplina, cujo nome é igual ao nome dado.
     *
     * @param nomeDisciplina nome da disciplina a ser retornada.
     * @return representação em objeto da disciplina.
     */
    public Disciplina getDisciplina(String nomeDisciplina) {
        Disciplina disciplina = null;
        for (int i = 0; i < this.disciplinas.size(); i++) {
            if(this.disciplinas.get(i).getNomeDisciplina().equals(nomeDisciplina)){
                disciplina = this.disciplinas.get(i);
            }
        }
        return disciplina;
    }

    /**
     * Cadastra horas em uma disciplina.
     *
     * @param nomeDisciplina nome da disciplina para cadastrar horas.
     * @param horas horas a serem cadastradas.
     */
    public void cadastraHoras(String nomeDisciplina, int horas) {
        this.getDisciplina(nomeDisciplina).cadastraHoras(horas);
    }

    /**
     * Cadasta nota em uma disciplina.
     *
     * @param nomeDisciplina nome da disciplina para cadastrar a nota.
     * @param nota índice da nota a ser cadastrada.
     * @param valorNota valor a ser atribuído a nota.
     */
    public void cadastraNota(String nomeDisciplina, int nota, double valorNota) {
        this.getDisciplina(nomeDisciplina).cadastraNota(nota,valorNota);
    }

    /**
     * Retorna se o aluno foi aprovado ou não na disciplina.
     *
     * @param nomeDisciplina nome da disciplina para checagem.
     * @return representação booleana se o aluno foi aprovado.
     */
    public boolean aprovado(String nomeDisciplina) {
        return this.getDisciplina(nomeDisciplina).aprovado();
    }

    /**
     * Retorna representação da disciplina em String.
     *
     * @param nomeDisciplina nome da disciplina a ser representada.
     * @return representação em String da disciplina.
     */
    public String disciplinaToString(String nomeDisciplina) {
        return this.getDisciplina(nomeDisciplina).toString();
    }

    /**
     * Cadastra um novo registro de finanças do aluno, a partir
     * de uma receita inicial.
     *
     * @param receitaInicial receita inicial a ser adicionada no
     * registro de finanças.
     */
    public void cadastraFinancas(int receitaInicial) {
        this.registroFinancas = new RegistroFinancas(receitaInicial);
    }

    /**
     * Aumenta receita do aluno.
     *
     * @param valorCentavos valor em centavos para ser incrementado
     * na receita.
     * @param tipoFonte tipo da fonte da receita.
     */
    public void aumentaReceita(int valorCentavos, int tipoFonte) {
        this.registroFinancas.aumentaReceita(valorCentavos,tipoFonte);
    }

    /**
     * Paga despesa do aluno.
     *
     * @param valorCentavos valor em centavos a ser pago.
     */
    public void pagaDespesa(int valorCentavos) {
        this.registroFinancas.pagaDespesa(valorCentavos);
    }

    /**
     * Retorna String representando as fontes de renda do aluno.
     *
     * @return representação em String dos valores associados as
     * fontes de rendas do aluno.
     */
    public String exibeFontes() {
        return this.registroFinancas.exibeFontes();
    }

    /**
     * Retorna String representando as finanças do aluno.
     *
     * @return representação em String das finanças do aluno.
     */
    public String financasToString() {
        return this.registroFinancas.toString();
    }

    /**
     * Define a saúde mental do aluno.
     *
     * @param valor valor, em String, a ser definido à saúde
     * mental.
     */
    public void defineSaudeMental(String valor) {
        this.saude.defineSaudeMental(valor);
    }

    /**
     * Define a saúde física do aluno.
     *
     * @param valor valor, em String, a ser definido à saúde
     * física.
     */
    public void defineSaudeFisica(String valor) {
        this.saude.defineSaudeFisica(valor);
    }

    /**
     * Retorna a representação em String da saúde geral
     * do aluno.
     *
     * @return representação em String da saúde geral.
     */
    public String getStatusGeral() {
        return this.saude.getStatusGeral();
    }

}
