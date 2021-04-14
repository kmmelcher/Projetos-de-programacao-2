package agenda;

import java.util.Arrays;

/**
 * Uma agenda que mantém uma lista de contatos com posições.
 * Podem existir 100 contatos e 10 favoritos.
 *
 * @author Nazareno Andrade e Kilian Melcher
 */
public class Agenda {

    /**
     * Tamanho disponível para contatos na agenda.
     */
    private static final int TAMANHO_AGENDA = 100;

    /**
     * Contatos da agenda.
     */
    private Contato[] contatos;

    /**
     * Tamanho disponível para favoritos na agenda.
     */
    private static final int TAMANHO_FAVORITOS = 10;

    /**
     * Contatos favoritados da agenda.
     */
    private Contato[] favoritos;

    /**
     * Cria uma agenda.
     */
    public Agenda() {
        this.contatos = new Contato[TAMANHO_AGENDA];
        this.favoritos = new Contato[TAMANHO_FAVORITOS];
    }

    /**
     * Acessa os dados de um contato específico.
     *
     * @param posicao Posição do contato na agenda.
     * @return Dados do contato. Null se não há contato na posição.
     */
    private Contato getContato(int posicao) {
        return this.contatos[posicao-1];
    }

    /**
     * Cadastra um contato em uma posição.
     * Um cadastro em uma posição que já existe sobrescreve o anterior.
     *
     * @param posicao Posição do contato.
     * @param nome    Nome do contato.
     */
    public boolean cadastraContato(int posicao, String nome, String sobrenome, String prioritario, String whatsapp,
                                String adicional) {
        if (posicaoValida(posicao) && !contatoCadastrado(nome,sobrenome)) {
            this.contatos[posicao-1] = new Contato(nome, sobrenome, prioritario, whatsapp, adicional);
            return true;
        }
        return false;
    }

    /**
     * Checa se um contato está cadastrado na agenda a partir do nome e do sobrenome.
     *
     * @param nome nome do contato.
     * @param sobrenome sobrenome do contato.
     * @return representação booleana se o contato está cadastrado.
     */
    public boolean contatoCadastrado(String nome, String sobrenome) {
        String nomeCompleto = nome + " " + sobrenome;
        for (int i = 0; i < TAMANHO_AGENDA; i++) {
            if (this.contatos[i] != null) {
                if (nomeCompleto.equals(this.contatos[i].nomeCompleto())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checa se a posição é maior ou igual a um ou menor igual que o tamanho da agenda.
     *
     * @param posicao posição a ser checada.
     * @return representação booleana se a posição é válida.
     */
    public static boolean posicaoValida(int posicao) {
        return posicao >= 1 && posicao <= TAMANHO_AGENDA;
    }

    /**
     * Checa se o contato existe a partir da posição.
     * Se a posição for válida e o contato não for nulo, então ele existe, caso contrário não.
     *
     * @param posicao posição a ser checada.
     * @return representação booleana se o contato existe.
     */
    public boolean existeContato(int posicao) {
        return posicaoValida(posicao) && this.getContato(posicao) != null;
    }

    /**
     * Formata contato a partir da sua posição.
     * A formatação é feita a partir do toString do contato. Caso esse contato seja favoritado,
     * ele receberá um coração antes do seu nome.
     *
     * @param posicao Posição do contato a ser formatado.
     * @return Representação em String do contato formatado.
     */
    public String formataContato(int posicao) {
        String coracao = "";
        if (this.contatoFavoritado(posicao)) {
            coracao = "❤ ";
        }
        return coracao + this.getContato(posicao).toString();
    }

    /**
     * Retorna uma lista de contatos, no formato: "[Posição] - [Nome] [Sobrenome]".
     *
     * @return Representação em String da lista de contatos.
     */
    public String listaContatos() {
        String listaDeContatos = "";
        for (int i = 0; i < TAMANHO_AGENDA; i++) {
            if (this.existeContato(i+1)) {
                listaDeContatos += (i+1) + " - " +  this.contatos[i].nomeCompleto() + "\n";
            }
        }
        return listaDeContatos;
    }

    /**
     * Retorna uma lista de contatos pesquisados pelo nome,
     * no formato: "[Posição] - [Nome] [Sobrenome]".
     *
     * @param nome Nome, ao qual devem ser pesquisados os contatos.
     * @return Representação em String da lista de contatos pesquisados.
     */
    public String pesquisaContatosPorNome(String nome) {
        String listaDeContatos = "";
        for (int i = 0; i < TAMANHO_AGENDA; i++) {
            if (this.existeContato(i+1) && this.contatos[i].getNome().equals(nome)) {
                listaDeContatos += (i+1) + " - " +  this.contatos[i].nomeCompleto() + "\n";
            }
        }
        return listaDeContatos;
    }

    /**
     *  Retorna uma lista de contatos pesquisados pelo sobrenome,
     *  no formato: "[Posição] - [Nome] [Sobrenome]".
     *
     * @param sobrenome Sobrenome, ao qual devem ser pesquisados os contatos.
     * @return Representação em String da lista de contatos pesquisados.
     */
    public String pesquisaContatosPorSobrenome(String sobrenome) {
        String listaDeContatos = "";
        for (int i = 0; i < TAMANHO_AGENDA; i++) {
            if (this.existeContato(i+1) && this.contatos[i].getSobrenome().equals(sobrenome)) {
                listaDeContatos += (i+1) + " - " +  this.contatos[i].nomeCompleto() + "\n";
            }
        }
        return listaDeContatos;
    }

    /**
     * Deleta um contato da agenda.
     * O contato só será deletado se ele já existir na agenda.
     *
     * @param contato contato a ser deletado.
     * @return Representação booleana se o contato foi deletado.
     */
    public boolean deletaContato(int contato) {
        if (this.existeContato(contato)) {
            this.removeFavorito(contato);
            this.contatos[contato-1] = null;
            return true;
        }
        return false;
    }

    /**
     * Checa se o contato está favoritado a partir do contato.
     *
     * @param contato Contato a ser checado.
     * @return Representação booleana se o contato está favoritado.
     */
    public boolean contatoFavoritado(int contato) {
        if (this.existeContato(contato)) {
            for (int i = 0; i < TAMANHO_FAVORITOS; i++) {
                if (this.favoritos[i] != null && this.favoritos[i].equals(this.getContato(contato))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checa se a posição é maior ou igual a um, ou menor ou igual ao tamanho da lista de favoritos.
     *
     * @param posicao Posição a ser checada.
     * @return Representação booleana se a posição é válida.
     */
    public boolean posicaoFavoritoValida(int posicao) {
        return posicao>=1 && posicao<=TAMANHO_FAVORITOS;
    }

    /**
     * Adiciona um contato aos favoritos.
     * O contato só será adicionado se o contato existir na agenda, não for favorito e se
     * a posição na lista de favoritos for válida.
     *
     * @param contato Contato a ser favoritado.
     * @param posicao Posição que o contato vai ser adicionado na lista de favoritos.
     * @return Representção booelana se o contato foi adicionado aos favoritos.
     */
    public boolean adicionaFavorito(int contato, int posicao) {
        if (this.existeContato(contato) && !this.contatoFavoritado(contato) && posicaoFavoritoValida(posicao)) {
            this.favoritos[posicao-1] = this.getContato(contato);
            return true;
        }
        return false;
    }

    /**
     * Retorna a lista de favoritos, no formato: "[Posição] - [Nome] [Sobrenome]".
     *
     * @return Representação em String da lista de favoritos.
     */
    public String listaFavoritos() {
        String listaDeFavoritos = "";
        for (int i = 0; i < TAMANHO_FAVORITOS; i++) {
            if (this.favoritos[i] != null) {
                listaDeFavoritos += (i+1) + " - " +  this.favoritos[i].nomeCompleto() + "\n";
            }
        }
        return listaDeFavoritos;
    }

    /**
     * Muda o telefone prioritário de um contato.
     * O telefone só será mudado se o contato existir na agenda.
     *
     * @param contato Contato para mudar o telefone.
     * @param telefonePrioritario Novo telefone prioritário do contato.
     * @return Representação booleana se o telefone foi mudado.
     */
    public boolean mudaTelefonePrioritario(int contato, String telefonePrioritario) {
        if (this.existeContato(contato)) {
            this.getContato(contato).setNumeroPrioritario(telefonePrioritario);
            return true;
        }
        return false;
    }

    /**
     * Remove contato dos favoritos.
     * O contato só será removido se ele existir na agenda e se ele estiver favoritado.
     *
     * @param contato contato a ser removido dos favoritos.
     * @return Representação booleana se o contato foi removido dos favoritos.
     */
    public boolean removeFavorito(int contato) {
        if (this.existeContato(contato) && this.contatoFavoritado(contato)) {
            for (int i = 0; i < TAMANHO_FAVORITOS; i++) {
                if (this.favoritos[i] != null) {
                    if (this.favoritos[i].nomeCompleto().equals(this.getContato(contato).nomeCompleto())) {
                        this.favoritos[i] = null;
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
