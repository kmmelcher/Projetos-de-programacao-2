package agenda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContatoTest {

    private Contato contatoBase;
    private String nome;
    private String sobrenome;
    private String prioritario;
    private String whatsapp;
    private String adicional;

    @BeforeEach
    void preparaContatos() {
        this.nome = "Matheus";
        this.sobrenome = "Gaudencio";
        this.prioritario = "(83) 99999-0000";
        this.whatsapp = "(83) 99999-00010";
        this.adicional = "(83) 99999-0002";
        this.contatoBase = new Contato(this.nome,this.sobrenome,this.prioritario, this.whatsapp,this.adicional);
    }

    @Test
    void testeAtributoNulo() {
        this.criaContatoNulo(null,this.sobrenome,this.prioritario, this.whatsapp, this.adicional,
                "Nome nulo");
        this.criaContatoNulo(this.nome,null,this.prioritario, this.whatsapp, this.adicional,
                "Sobrenome nulo");
        this.criaContatoNulo(this.nome,this.sobrenome,null, this.whatsapp, this.adicional,
                "Prioritario nulo");
        this.criaContatoNulo(this.nome,this.sobrenome,this.prioritario, null, this.adicional,
                "Whatsapp nulo");
        this.criaContatoNulo(this.nome,this.sobrenome,this.prioritario, this.whatsapp, null,
                "Adicional nulo");
    }

    private void criaContatoNulo(String nome, String sobrenome, String prioritario, String whatsapp, String adicional,
                                 String mensagemEsperada) {
        try {
            Contato contatoInvalido = new Contato(nome,sobrenome,prioritario, whatsapp,adicional);
            fail("Era esperado exceção ao passar atributo nulo");
        } catch (NullPointerException npe) {
            assertEquals(mensagemEsperada,npe.getMessage());
        }
    }

    @Test
    void testeAtributoVazio() {
        this.criaContatoVazio("",this.sobrenome,this.prioritario, this.whatsapp, "Nome vazio");
        this.criaContatoVazio(this.nome,"",this.prioritario, this.whatsapp, "Sobrenome vazio");
        this.criaContatoVazio(this.nome,this.sobrenome,"", this.whatsapp, "Prioritario vazio");
        this.criaContatoVazio(this.nome,this.sobrenome,this.prioritario, "", "Whatsapp vazio");
    }

    private void criaContatoVazio(String nome, String sobrenome, String prioritario, String whatsapp,
                                  String mensagemEsperada) {
        try {
            Contato contatoInvalido = new Contato(nome,sobrenome,prioritario, whatsapp,this.adicional);
            fail("Era esperado exceção ao passar atributo vazio");
        } catch (IllegalArgumentException iae) {
            assertEquals(mensagemEsperada, iae.getMessage());
        }
    }

    @Test
    void testeEquals() {
        assertTrue(contatoBase.equals(contatoBase));
        Contato contatoNulo = null;
        assertFalse(contatoBase.equals(contatoNulo));
        Contato contatoIgual = new Contato("Matheus","Gaudencio","(83) 0000",
                "(83) 33333","(83) 22222");
        assertTrue(contatoBase.equals(contatoIgual));
        Contato contatoDiferente = new Contato("Kilian","Melcher","(83) 0000",
                "(83) 33333","(83) 22222");
        assertFalse(contatoBase.equals(contatoDiferente));
    }

    @Test
    void testeNomeCompleto() {
        assertEquals("Matheus Gaudencio",this.contatoBase.nomeCompleto());
    }

    @Test
    void testeToString() {
        String contatoComAdicional = "Matheus Gaudencio\n" +
                                     "(83) 99999-0000 (Prioritário)\n" +
                                     "(83) 99999-00010 (Whatsapp)\n" +
                                     "(83) 99999-0002 (Adicional)";
        assertEquals(contatoComAdicional, this.contatoBase.toString());
        String contatoSemAdicional = "Matheus Gaudencio\n" +
                                     "(83) 99999-0000 (Prioritário)\n" +
                                     "(83) 99999-00010 (Whatsapp)";
        Contato novoContato = new Contato("Matheus","Gaudencio","(83) 99999-0000",
                "(83) 99999-00010","");
        assertEquals(contatoSemAdicional, novoContato.toString());
    }
}