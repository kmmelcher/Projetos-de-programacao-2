package agenda;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {

    private Agenda agendaBase;

    @BeforeEach
    void preparaAgendas() {
        this.agendaBase = new Agenda();
    }

    private boolean cadastraMatheus(int posicao) {
        return this.agendaBase.cadastraContato(posicao,"Matheus","Gaudêncio","(83) 99999-0000",
                "(83) 99999-0001","(83) 99999-0002");
    }

    private boolean cadastraPedro(int posicao) {
        return this.agendaBase.cadastraContato(posicao,"Pedro", "Silva", "(84) 98888-1111",
                "(84) 98888-1112", "(84) 98888-1113");
    }

    private boolean cadastraPedro(int posicao, String adicional) {
        return this.agendaBase.cadastraContato(posicao,"Pedro", "Silva", "(84) 98888-1111",
                "(84) 98888-1112", adicional);
    }

    @Test
    void testNormalCadastraContato() {
        //Cadastrar um novo contato em posição vazia
        assertTrue(this.cadastraMatheus(1));
        //Cadastrar um novo contato com nome e sobrenome já cadastrados em outra posição
        assertFalse(this.cadastraMatheus(3));
        //Cadastrar um novo contato em posição existente
        assertTrue(this.cadastraPedro(1));
    }

    @Test
    void testCadastraTelefoneVazio() {
        assertTrue(this.cadastraPedro(1, ""));
    }

    @Test
    void testExtremoCadastraContato() {
        //Cadastrar um novo contato em uma posição acima do limite
        assertFalse(this.cadastraMatheus(101));
        //Cadastrar um novo contato em uma posição abaixo do limite
        assertFalse(this.cadastraMatheus(0));
        //Cadastrar um novo contato na posição limite
        assertTrue(this.cadastraMatheus(100));
    }

    @Test
    void testFormataContato() {
        //Exibir um contato cadastrado com todos os dados
        String representacao;
        this.cadastraMatheus(1);
        representacao = "Matheus Gaudêncio\n" +
                        "(83) 99999-0000 (Prioritário)\n" +
                        "(83) 99999-0001 (Whatsapp)\n" +
                        "(83) 99999-0002 (Adicional)";
        assertEquals(representacao, this.agendaBase.formataContato(1));
        //Exibir um contato cadastrado sem um dos telefones
        this.cadastraPedro(2,"");
        representacao = "Pedro Silva\n" +
                        "(84) 98888-1111 (Prioritário)\n" +
                        "(84) 98888-1112 (Whatsapp)";
        assertEquals(representacao, this.agendaBase.formataContato(2));
        //Exibir um contato favoritado
        this.agendaBase.adicionaFavorito(1,1);
        representacao = "❤ Matheus Gaudêncio\n" +
                        "(83) 99999-0000 (Prioritário)\n" +
                        "(83) 99999-0001 (Whatsapp)\n" +
                        "(83) 99999-0002 (Adicional)";
        assertEquals(representacao, this.agendaBase.formataContato(1));
    }

    @Test
    void testExisteContato() {
        //Exibir um contato em uma posição sem contato
        assertFalse(this.agendaBase.existeContato(100));
        //Exibir um contato em uma posição inválida (limite inferior)
        assertFalse(this.agendaBase.existeContato(0));
        //Exibir um contato em uma posição inválida (limite superior)
        assertFalse(this.agendaBase.existeContato(101));
    }

    @Test
    void testListaContatos() {
        this.cadastraMatheus(1);
        this.cadastraPedro(2);
        this.agendaBase.cadastraContato(3,"Fábio", "Morais", "98790-1323",
                "3231-8383", "");
        this.agendaBase.cadastraContato(4, "Nazareno", "Andrade", "99979-6564",
                "99875-3210", "");
        String representacao;
        representacao = "1 - Matheus Gaudêncio\n" +
                        "2 - Pedro Silva\n" +
                        "3 - Fábio Morais\n" +
                        "4 - Nazareno Andrade\n";
        assertEquals(representacao, this.agendaBase.listaContatos());
    }

    @Test
    void testPesquisaContatosPorNome() {
        this.cadastraMatheus(1);
        String mensagemEsperada = "1 - Matheus Gaudêncio\n";
        assertEquals(mensagemEsperada, this.agendaBase.pesquisaContatosPorNome("Matheus"));
    }

    @Test
    void testPesquisaContatosPorSobrenome() {
        this.cadastraMatheus(1);
        String mensagemEsperada = "1 - Matheus Gaudêncio\n";
        assertEquals(mensagemEsperada, this.agendaBase.pesquisaContatosPorSobrenome("Gaudêncio"));
    }

    @Test
    void testDeletaContato() {
        this.cadastraMatheus(1);
        //Deleta contato existente
        assertTrue(this.agendaBase.deletaContato(1));
        //Deleta contato inexistente
        assertFalse(this.agendaBase.deletaContato(1));
    }

    @Test
    void testMudaTelefonePrioritario() {
        this.cadastraMatheus(1);
        //Muda telefone prioritário de contato existente
        assertTrue(this.agendaBase.mudaTelefonePrioritario(1,"(83) 1234-1234"));
        //Muda telefone prioritário de contato inexistente
        assertFalse(this.agendaBase.mudaTelefonePrioritario(2,"(83) 1234-1234"));
    }

    @Test
    void testNormalAdicionaFavorito() {
        //Favorita contato inexistente
        assertFalse(this.agendaBase.adicionaFavorito(1,3));
        //Favorita contato existente
        this.cadastraMatheus(1);
        assertTrue(this.agendaBase.adicionaFavorito(1,3));
        //Favorita contato favoritado em outra posição
        assertFalse(this.agendaBase.adicionaFavorito(1,7));
    }

    @Test
    void testExtremoAdicionaFavorito() {
        this.cadastraMatheus(1);
        this.cadastraPedro(2);

        //Favorita contato em posição abaixo do limite
        assertFalse(this.agendaBase.adicionaFavorito(1,-1));
        //Favorita contato em posição no limite mínimo
        assertTrue(this.agendaBase.adicionaFavorito(1,1));
        //Favorita contato em posição no limite máximo
        assertTrue(this.agendaBase.adicionaFavorito(2,10));
        //Favorita contato em posição acima do limite máximo
        assertFalse(this.agendaBase.adicionaFavorito(1,101));
    }

    @Test
    void testListaFavoritos() {
        this.cadastraMatheus(1);
        this.cadastraPedro(2);
        this.agendaBase.adicionaFavorito(1,1);
        this.agendaBase.adicionaFavorito(2,2);
        String representacao;
        representacao = "1 - Matheus Gaudêncio\n" +
                        "2 - Pedro Silva\n";
        assertEquals(representacao, this.agendaBase.listaFavoritos());
    }

    @Test
    void testRemoveFavorito() {
        this.cadastraMatheus(1);
        //Remove contato inexistente e não favoritado
        assertFalse(this.agendaBase.removeFavorito(2));
        //Remove contato não favoritado
        assertFalse(this.agendaBase.removeFavorito(1));
        //Remove contato favoritado
        this.agendaBase.adicionaFavorito(1,1);
        assertTrue(this.agendaBase.removeFavorito(1));
    }

}