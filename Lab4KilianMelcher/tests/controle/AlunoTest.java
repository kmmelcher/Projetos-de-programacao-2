package controle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    private Aluno alunoBase;

    @BeforeEach
    void preparaAluno() {
        alunoBase = new Aluno(120110391, "Kilian Melcher", "Ciência da Computação");
    }

    @Test
    void testNomeNulo() {
        try {
            Aluno a = new Aluno(120110391, null, "Computação");
            fail("Era esperado uma exceção por nome nulo");
        } catch (NullPointerException npe) {
            assertEquals("Nome nulo",npe.getMessage());
        }
    }

    @Test
    void testCursoNulo() {
        try {
            Aluno a = new Aluno(123, "Kilian", null);
            fail("Era esperado uma exceção por curso nulo");
        } catch (NullPointerException npe) {
            assertEquals("Curso nulo",npe.getMessage());
        }
    }

    @Test
    void testNomeVazio() {
        try {
            Aluno a = new Aluno(120110391, "", "Computação");
            fail("Era esperado uma exceção por nome vazio");
        } catch (IllegalArgumentException ilae) {
            assertEquals("Nome vazio",ilae.getMessage());
        }
    }

    @Test
    void testCursoVazio() {
        try {
            Aluno a = new Aluno(120110391, "Kilian", "");
            fail("Era esperado uma exceção por curso vazio");
        } catch (IllegalArgumentException ilae) {
            assertEquals("Curso vazio",ilae.getMessage());
        }
    }

    @Test
    void testToStringSimples() {
        String mensagemEsperada = "120110391 - Kilian Melcher - Ciência da Computação";
        assertEquals(mensagemEsperada, alunoBase.toString());
    }

    @Test
    void testComparaAlunosIguais() {
        Aluno aluno = new Aluno(120110391, "Kilian Melcher", "Ciência da Computação");
        assertTrue(aluno.equals(this.alunoBase));
    }

    @Test
    void testComparaAlunosDiferentes() {
        Aluno aluno = new Aluno(250, "Gabriel Reyes", "Computação");
        assertFalse(aluno.equals(this.alunoBase));
    }

    @Test
    void testHashCodeAlunosIguais() {
        Aluno aluno = new Aluno(120110391, "Kilian Melcher", "Ciência da Computação");
        assertEquals(aluno.hashCode(), alunoBase.hashCode());
    }

    @Test
    void testHashCodeAlunosDiferentes() {
        Aluno aluno = new Aluno(250, "Gabriel Reyes", "Computação");
        assertNotEquals(aluno.hashCode(), alunoBase.hashCode());
    }

}