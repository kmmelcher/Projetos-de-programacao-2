package controle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrupoTest {

    private Grupo grupoBase;
    private Aluno alunoBase;

    @BeforeEach
    void preparaGrupo() {
        this.grupoBase = new Grupo("Anatomia", "Medicina");
        this.alunoBase = new Aluno(202, "Angela Ziegler", "Medicina");
    }

    @Test
    void testNomeNulo() {
        try {
            Grupo g = new Grupo(null, "Medicina");
            fail("Era esperado uma exceção por nome nulo");
        } catch (NullPointerException npe) {
            assertEquals("Nome nulo",npe.getMessage());
        }
    }

    @Test
    void testRestricaoNula() {
        try {
            Grupo g = new Grupo("Anatomia", null);
            fail("Era esperado uma exceção por restrição nula");
        } catch (NullPointerException npe) {
            assertEquals("Restrição nula",npe.getMessage());
        }
    }

    @Test
    void testNomeVazio() {
        try {
            Grupo g = new Grupo("", "Medicina");
            fail("Era esperado uma exceção por nome vazio");
        } catch (IllegalArgumentException ilae) {
            assertEquals("Nome vazio",ilae.getMessage());
        }
    }

    @Test
    void testAdicionaAlunoSimples() {
        assertTrue(this.grupoBase.adiciona(this.alunoBase));
    }

    @Test
    void testAdicionaAlunoRepetido() {
        this.grupoBase.adiciona(this.alunoBase);
        assertFalse(this.grupoBase.adiciona(this.alunoBase));
    }

    @Test
    void testContemAlunoExistente() {
        this.grupoBase.adiciona(this.alunoBase);
        assertTrue(this.grupoBase.contem(this.alunoBase));
    }

    @Test
    void testContemAlunoInexistente() {
        assertFalse(this.grupoBase.contem(this.alunoBase));
    }

    @Test
    void testComparaGruposIguais() {
        Grupo grupo = new Grupo("Anatomia", "Medicina");
        assertTrue(this.grupoBase.equals(grupo));
    }

    @Test
    void testComparaGruposDiferentes() {
        Grupo grupo = new Grupo("Listas", "Computação");
        assertFalse(this.grupoBase.equals(grupo));
    }

    @Test
    void testHashCodeGruposIguais() {
        Grupo grupo = new Grupo("Anatomia", "Medicina");
        assertEquals(grupo.hashCode(), this.grupoBase.hashCode());
    }

    @Test
    void testHashCodeGruposDiferentes() {
        Grupo grupo = new Grupo("Listas", "Computação");
        assertNotEquals(grupo.hashCode(), this.grupoBase.hashCode());
    }

}