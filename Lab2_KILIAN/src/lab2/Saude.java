package lab2;

/**
 * Representação da saúde do estudante do
 * curso de ciência da computação da UFCG.
 *
 * @author Kilian Macedo Melcher
 */
public class Saude {

    /**
     * Saúde física do aluno.
     * Ela pode ser "boa" ou "fraca".
     */
    private String saudeFisica;

    /**
     * Saúde mental do aluno.
     * Ela pode ser "boa" ou "fraca".
     */
    private String saudeMental;

    /**
     * Emoji que representa o sentimento do aluno.
     * Ele pode ser ":(", "*_*", ":o)","<(^_^<)", "¯\_(ツ)_/¯",...
     */
    private String emoji;

    /**
     * Constrói a saúde do aluno.
     * A Saúde começa com a saúde física "boa", saúde mental "boa" e
     * sem emoji.
     */
    public Saude() {
        this.saudeFisica = "boa";
        this.saudeMental = "boa";
        this.emoji = "";
    }

    /**
     * Atribui um novo valor à saúde mental e retira o emoji.
     *
     * @param valor Novo valor correspondente a saúde mental do aluno
     */
    public void defineSaudeMental(String valor) {
        this.saudeMental = valor;
        this.emoji = "";
    }

    /**
     * Atribui um novo valor à saúde física e retira o emoji.
     *
     * @param valor Novo valor correspondente a saúde física do aluno
     */
    public void defineSaudeFisica(String valor) {
        this.saudeFisica = valor;
        this.emoji = "";
    }

    /**
     * Define um novo emoji.
     *
     * @param emoji emoji que representa o sentimento do aluno.
     */
    public void defineEmoji(String emoji) {
        this.emoji = emoji;
    }

    /**
     * Retorna a saúde geral do aluno. Ela pode será
     * "boa" quando a saúde física e mental for "boa";
     * "ok" quando ou a saúde física ou a saúde mental for "boa";
     * "fraca" quando a saúde física e mental for "fraca";
     * Se houver um emoji, ele será adicionado ao final do status.
     *
     * @return representação em String da saúde geral do aluno.
     */
    public String getStatusGeral() {
        String saudeGeral;
        if (this.saudeFisica.equals("boa") && this.saudeMental.equals("boa")) {
            saudeGeral = "boa";
        } else if (this.saudeFisica.equals("boa") || this.saudeMental.equals("boa")) {
            saudeGeral = "ok";
        } else {
            saudeGeral = "fraca";
        }
        if (!this.emoji.isEmpty()) {
            saudeGeral += " " + this.emoji;
        }
        return saudeGeral;
    }

}
