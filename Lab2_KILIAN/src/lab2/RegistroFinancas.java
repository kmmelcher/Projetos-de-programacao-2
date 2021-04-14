package lab2;

/**
 * Representação de registro de finanças de um estudante do
 * curso de ciência da computação da UFCG.
 *
 * @author Kilian Macedo Melcher
 */
public class RegistroFinancas {

    /**
     * A receita é o somatório, em centavos, das rendas de um aluno.
     */
    private int receita;

    /**
     * A despesa é o somatório, em centavos, dos gastos de um aluno.
     */
    private int despesa;

    /**
     * Existem 4 fontes de renda de um aluno que geram sua receita.
     * Fonte 1 - família
     * Fonte 2 - projetos institucionais com bolsa
     * Fonte 3 - auxílio institucional
     * Fonte 4 - doações externas
     * Cada fonte é representada em código pelo seu índice menos 1.
     */
    private int[] fontesDeRenda;

    /**
     * Ultimos 5 detalhes sobre despesas pagas.
     */
    private String[] ultimosDetalhes;

    /**
     * índice correspondente ao número de detalhes adicionados.
     */
    private int indiceDetalhes;

    /**
     *  Constrói um registro de finanças de um aluno a partir
     *  da receita inicial do tipo 1.
     *  O registro começa com 4 fontes de renda, a fonte de renda 1
     *  com a receita inicial do tipo 1, a receita igual a receita
     *  inicial, a despesa igual a zero, 5 últimos detalhes que
     *  serão salvos e com zero detalhes adicionados.
     *
     * @param receitaInicialDoTipo1 valor em centavos
     * correspondente a receita inicial do tipo 1.
     */
    public RegistroFinancas(int receitaInicialDoTipo1) {
        this.fontesDeRenda = new int[4];
        this.fontesDeRenda[0] = receitaInicialDoTipo1;
        this.receita = receitaInicialDoTipo1;
        this.despesa = 0;
        this.ultimosDetalhes = new String[5];
        this.indiceDetalhes = 0;
    }

    /**
     * Incrementa a receita de um dos tipos e o valor do
     * somatório total da receita
     *
     * @param valorCentavos Valor em centavos para ser
     * incrementado na receita
     * @param tipoFonte Indice que representa o tipo da fonte
     */
    public void aumentaReceita(int valorCentavos, int tipoFonte) {
        this.fontesDeRenda[tipoFonte-1] += valorCentavos;
        this.receita += valorCentavos;
    }

    /**
     * Incrementa o valor da despesa.
     *
     * @param valorCentavos Valor em centavos a ser incrementado
     * a despesa.
     */
    public void pagaDespesa(int valorCentavos) {
        this.despesa += valorCentavos;
    }

    /**
     * Incrementa o valor da despesa e registra detalhes da despesa.
     *
     * @param valorCentavos Valor em centavos a ser incrementado
     * a despesa.
     * @param detalhes Detalhes da origem da despesa.
     */
    public void pagaDespesa(int valorCentavos, String detalhes) {
        this.despesa += valorCentavos;
        this.adicionaDetalhes(detalhes);
    }

    /**
     * Atribuí um novo novo detalhe até a quinta atribuição.
     * Caso novos detalhes sejam adicionados, eles serão inseridos
     * na última posição.
     *
     * @param detalhes detalhes a serem adicionados
     */
    private void adicionaDetalhes(String detalhes) {
        if (this.indiceDetalhes < 5) {
            this.ultimosDetalhes[this.indiceDetalhes] = detalhes;
            this.indiceDetalhes++;
        } else {
            this.insereNaUltimaPosicao(detalhes);
        }
    }

    /**
     * Cada detalhe "sobe" uma posição para que o novo
     * detalhe seja inserido. Assim, o detalhe mais antigo é
     * descartado e o mais novo é inserido.
     *
     * @param detalhes detalhes a serem inseridos.
     */
    private void insereNaUltimaPosicao(String detalhes) {
        for (int i = 0; i < 4; i++) {
            this.ultimosDetalhes[i] = this.ultimosDetalhes[i+1];
        }
        this.ultimosDetalhes[4] = detalhes;
    }

    /**
     * Retorna String correspondente a lista dos últimos cinco
     * detalhes de despesas fornecidos pelo aluno, um em cada linha.
     * Valores nulos não serão retornados e o último valor não nulo
     * não deve quebrar a linha.
     *
     * @return últimos cinco detalhes das despesas.
     */
    public String listarDetalhes() {
        String ultimosCincoDetalhes = "";
        for (int i = 0; i < 5; i++) {
            if (i<4 && this.ultimosDetalhes[i]!=null && this.ultimosDetalhes[i+1]!=null) {
                ultimosCincoDetalhes += i+1 + " - " + this.ultimosDetalhes[i] + "\n";
            } else if (this.ultimosDetalhes[i]!=null) {
                ultimosCincoDetalhes += i+1 + " - " + this.ultimosDetalhes[i];
            }
        }
        return ultimosCincoDetalhes;
    }

    /**
     * Retorna uma String que representa o valor das 4 rendas
     * no seguinte formato:
     * "1 - Receita do tipo 1
     *  2 - Receita do tipo 2
     *  3 - Receita do tipo 3
     *  4 - Receita do tipo 4"
     *
     * @return representação em string das fontes de renda
     */
    public String exibeFontes() {
        String fontes = "";
        for (int i = 0; i < this.fontesDeRenda.length-1; i++) {
            fontes +=  (i+1) + " - " + this.fontesDeRenda[i] + "\n";
        }
        fontes +=  4 + " - " + this.fontesDeRenda[3];
        return fontes;
    }

    /**
     * Calcula a diferença entre a receita e despesa.
     *
     * @return representação inteira do resultado da
     * diferença em centavos.
     */
    public int calculaReceitaAtual() {
        return this.receita - this.despesa;
    }

    /**
     * Retorna a representação do registro de finanças em String,
     * no formato:
     * "Receita total: R, Receita atual: RA, Despesas totais: D"
     * No qual R represta a receita, RA a receita atual e D a despesa.
     *
     * @return representação em String do registro de finanças
     */
    public String toString() {
        String receitaTotal = "Receita total: " + this.receita;
        String receitaAtual = "Receita atual: " + this.calculaReceitaAtual();
        String despesasTotais = "Despesas totais: " + this.despesa;
        return receitaTotal + ", " + receitaAtual + ", " + despesasTotais;
    }

}
