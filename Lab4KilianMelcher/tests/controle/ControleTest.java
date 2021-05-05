package controle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControleTest {

    private Controle controleBase;

    @BeforeEach
    void preparaControle() {
        this.controleBase = new Controle();
        this.controleBase.cadastraAluno(250, "Gabriel Reyes", "Computação");
        this.controleBase.cadastraAluno(200, "Lili Camposh", "Computação");
        this.controleBase.cadastraAluno(202, "Angela Ziegler", "Medicina");
        this.controleBase.cadastraAluno(201, "Torbjorn Lindholm", "Engenharia Mecânica");
    }

    @Test
    void testCadastroSimples() {
        assertTrue(this.controleBase.cadastraAluno(100, "Kilian", "Computação"));
    }

    @Test
    void testCadastraMaisDeUmAluno() {
        this.controleBase.cadastraAluno(100, "Kilian", "Computação");
        assertTrue(this.controleBase.cadastraAluno(110, "Pedro", "Computação"));
    }

    @Test
    void testCadastroRepetido() {
        assertFalse(this.controleBase.cadastraAluno(250, "Gabriel Reyes", "Computação"));
    }

    @Test
    void testCadastraAlunosComMatriculasIguais() {
        assertFalse(this.controleBase.cadastraAluno(250, "Pedro", "Computação"));
    }

    @Test
    void testExibeAlunoSimples() {
        assertEquals("Aluno: 250 - Gabriel Reyes - Computação", this.controleBase.exibeAluno(250));
    }

    @Test
    void testCadastraGrupoSemRestricao() {
         assertTrue(this.controleBase.cadastraGrupo("Programação OO", ""));
    }

    @Test
    void testCadastraGrupoComRestricao() {
        assertTrue(this.controleBase.cadastraGrupo("Listas","Computação"));
    }

    @Test
    void testCadastraGrupoComNomeExistente() {
        this.controleBase.cadastraGrupo("Listas","Computação");
        //Cadastra grupo com nome existente e todas as letras iguais
        assertFalse(this.controleBase.cadastraGrupo("Listas","Computação"));
        //Cadastra grupo com nome existente e todas as letras maiusculas
        assertFalse(this.controleBase.cadastraGrupo("LISTAS","Computação"));
        //Cadastra grupo com nome existente e todas as letras minusculas
        assertFalse(this.controleBase.cadastraGrupo("listas","Computação"));
    }

    @Test
    void testAlocaAlunoSimples() {
        this.controleBase.cadastraGrupo("Programação OO", "");
        assertTrue(this.controleBase.alocaAlunoEmGrupo(200, "Programação OO"));
    }

    @Test
    void testAlocaMaisDeUmAluno() {
        this.controleBase.cadastraGrupo("Programação OO", "");
        this.controleBase.alocaAlunoEmGrupo(200, "Programação OO");
        assertTrue(this.controleBase.alocaAlunoEmGrupo(202, "Programação OO"));
    }

    @Test
    void testAlocaAlunoRepetido() {
        this.controleBase.cadastraGrupo("Programação OO", "");
        this.controleBase.alocaAlunoEmGrupo(200, "Programação OO");
        assertTrue(this.controleBase.alocaAlunoEmGrupo(200, "Programação OO"));
    }

    @Test
    void testAlocaAlunoInexistente() {
        this.controleBase.cadastraGrupo("Programação OO", "");
        assertFalse(this.controleBase.alocaAlunoEmGrupo(100, "Programação OO"));
    }

    @Test
    void testAlocaAlunoEmGrupoInexistente() {
        assertFalse(this.controleBase.alocaAlunoEmGrupo(250, "Anatomia"));
    }

    @Test
    void AlocaAlunoEmGrupoComRestricao() {
        this.controleBase.cadastraGrupo("Listas","Computação");
        //Aloca aluno não restrito ao grupo
        assertTrue(this.controleBase.alocaAlunoEmGrupo(250, "Listas"));
        //Aloca aluno restrito ao grupo
        assertFalse(this.controleBase.alocaAlunoEmGrupo(202, "Listas"));
    }

    @Test
    void testPertinenciaSimples() {
        this.controleBase.cadastraGrupo("Listas","Computação");
        this.controleBase.alocaAlunoEmGrupo(250, "Listas");
        this.controleBase.alocaAlunoEmGrupo(202, "Listas");

        //Aluno pertence ao grupo
        assertTrue(this.controleBase.alunoPertenceAoGrupo(250, "Listas"));
        //Aluno pertence ao grupo com letras maísculas e minusculas diferentes
        assertTrue(this.controleBase.alunoPertenceAoGrupo(250, "liSTaS"));
        //Aluno não pertence ao grupo
        assertFalse(this.controleBase.alunoPertenceAoGrupo(202, "Listas"));
    }

    @Test
    void testPertinenciaGrupoNaoCadastrado() {
        this.controleBase.alocaAlunoEmGrupo(200, "Anatomia");
        assertFalse(this.controleBase.alunoPertenceAoGrupo(200, "Anatomia"));
    }

    @Test
    void testPertinenciaAlunoNaoCadastrado() {
        this.controleBase.cadastraGrupo("Anatomia","Medicina");
        this.controleBase.alocaAlunoEmGrupo(100, "Anatomia");
        assertFalse(this.controleBase.alunoPertenceAoGrupo(100, "Anatomia"));
    }

    @Test
    void testContagemDeGruposComRestricao() {
        this.controleBase.cadastraGrupo("Listas","Computação");
        assertEquals(1, this.controleBase.contagemGruposRestritos("Computação"));
        assertEquals(0, this.controleBase.contagemGruposRestritos("Medicina"));
        assertEquals(0, this.controleBase.contagemGruposRestritos("Engenharia Mecânica"));
    }

    @Test
    void testRegistraAlunoQueRespondeuSimples() {
        assertTrue(this.controleBase.registraAlunoQueRespondeu(202));
    }

    @Test
    void testRegistraAlunoInexistenteQueRespondeu() {
        assertFalse(this.controleBase.registraAlunoQueRespondeu(100));
    }

    @Test
    void testImprimeAlunosQueResponderamSimples() {
        this.controleBase.registraAlunoQueRespondeu(200);
        this.controleBase.registraAlunoQueRespondeu(201);

        String esperado = "Alunos:" +
                          "\n1. 200 - Lili Camposh - Computação" +
                          "\n2. 201 - Torbjorn Lindholm - Engenharia Mecânica";
        assertEquals(esperado, this.controleBase.imprimeAlunosQueResponderam());
    }

    @Test
    void testImprimeAlunosQueResponderamRepetido() {
        this.controleBase.registraAlunoQueRespondeu(200);
        this.controleBase.registraAlunoQueRespondeu(200);

        String esperado = "Alunos:" +
                "\n1. 200 - Lili Camposh - Computação" +
                "\n2. 200 - Lili Camposh - Computação";
        assertEquals(esperado, this.controleBase.imprimeAlunosQueResponderam());
    }

}