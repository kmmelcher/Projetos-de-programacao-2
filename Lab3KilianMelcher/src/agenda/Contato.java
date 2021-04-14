package agenda;

import java.util.Objects;

/**
 * Um contato da agenda.
 *
 * @author Kilian Melcher
 */
public class Contato {

    /**
     * Nome do contato.
     */
    private String nome;

    /**
     * Sobrenome do contato.
     */
    private String sobrenome;

    /**
     * Número do telefone prioritário do contato.
     */
    private String numeroPrioritario;

    /**
     * Número do Whatsapp do contato.
     */
    private String numeroWhatsapp;

    /**
     * Número do telefone adicional do contato. Esse número é opcional.
     */
    private String numeroAdicional;

    /**
     * Cria contato a partir do nome, sobrenome, número prioritário, número do Whatsapp e número adicional.
     * O contato só será criado se nenhum atributo for nulo e se todos os atributos, exceto o adicional,
     * não forem vazios.
     *
     * @param nome Nome do contato.
     * @param sobrenome Sobrenome do contato.
     * @param numeroPrioritario Número do telefone prioritário do contato.
     * @param numeroWhatsapp Número do Whatsapp do contato.
     * @param adicional Número do telefone adicional do contato.
     */
    public Contato(String nome, String sobrenome, String numeroPrioritario, String numeroWhatsapp, String adicional) {
        this.checaNull(nome,sobrenome,numeroPrioritario,numeroWhatsapp,adicional);
        this.checaAtributoVazio(nome,sobrenome,numeroPrioritario,numeroWhatsapp);

        this.nome = nome;
        this.sobrenome = sobrenome;
        this.numeroPrioritario = numeroPrioritario;
        this.numeroWhatsapp = numeroWhatsapp;
        this.numeroAdicional = adicional;
    }

    /**
     * Checa se algum dos atributos do contato é nulo.
     * Caso algum seja, joga uma exceção, com a mensagem adequada, e a aplicação para.
     *
     * @param nome Nome do contato.
     * @param sobrenome Sobrenome do contato.
     * @param numeroPrioritario Número do telefone prioritário do contato.
     * @param numeroWhatsapp Número do Whatsapp do contato.
     * @param adicional Número do telefone adicional do contato.
     */
    private void checaNull(String nome, String sobrenome, String numeroPrioritario, String numeroWhatsapp,
                           String adicional) {
        if (nome == null) {
            throw new NullPointerException("Nome nulo");
        } else if (sobrenome == null) {
            throw new NullPointerException("Sobrenome nulo");
        } else if (numeroPrioritario == null) {
            throw new NullPointerException("Prioritario nulo");
        } else if (numeroWhatsapp == null) {
            throw new NullPointerException("Whatsapp nulo");
        } else if (adicional == null) {
            throw new NullPointerException("Adicional nulo");
        }
    }

    /**
     * Checa se algum dos atributos do contato é vazio.
     * Caso algum seja, joga uma exceção, com a mensagem adequada, e a aplicação para.
     *
     * @param nome Nome do contato.
     * @param sobrenome Sobrenome do contato.
     * @param numeroPrioritario Número do telefone prioritário do contato.
     * @param numeroWhatsapp Número do Whatsapp do contato.
     */
    private void checaAtributoVazio(String nome, String sobrenome, String numeroPrioritario, String numeroWhatsapp) {
        if (nome.isBlank()) {
            throw new IllegalArgumentException("Nome vazio");
        }else if (sobrenome.isBlank()) {
            throw new IllegalArgumentException("Sobrenome vazio");
        } else if (numeroPrioritario.isBlank()) {
            throw new IllegalArgumentException("Prioritario vazio");
        } else if (numeroWhatsapp.isBlank()) {
            throw new IllegalArgumentException("Whatsapp vazio");
        }
    }

    /**
     * Retorna o nome completo do contato.
     *
     * @return Representação em String do nome completo do usuário.
     */
    public String nomeCompleto() {
        return this.nome + " " + this.sobrenome;
    }

    /**
     * Checa se um objeto da classe Contato é igual a outro objeto.
     *
     * @param o Objeto a ser checado.
     * @return Representação booleana se o objeto é igual.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contato contato = (Contato) o;
        return nome.equals(contato.nome) && sobrenome.equals(contato.sobrenome);
    }

    /**
     * Retorna representação do contato. no formato:
     * "[Nome] [Sobrenome]
     *  [Número prioritário] (Prioritário)
     *  [Número Whatsapp] (Whatsapp)
     *  [Número adicional] (Adicional)".
     *  O número adicional não aparecerá se for vazio.
     *
     * @return Representação em String do contato.
     */
    @Override
    public String toString() {
        String adicional = "";
        if (!numeroAdicional.isBlank()) {
            adicional = "\n" + this.numeroAdicional + " (Adicional)";
        }
        return this.nome + " " + this.sobrenome + "\n"
               + this.numeroPrioritario + " (Prioritário)" + "\n"
               + this.numeroWhatsapp +    " (Whatsapp)"
               + adicional;
    }

    /**
     * Atribuí um valor ao número de telefone prioritário.
     *
     * @param telefonePrioritario número de telefone prioritário a ser atribuído.
     */
    public void setNumeroPrioritario(String telefonePrioritario) {
        this.numeroPrioritario = telefonePrioritario;
    }

    /**
     * Retorna nome do contato.
     *
     * @return Nome do contato em String.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Retorna sobrenome do contato.
     *
     * @return Sobrenome do contato em String.
     */
    public String getSobrenome() {
        return this.sobrenome;
    }
}
