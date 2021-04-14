package lab2;

/**
 * COISA é o Controle Institucional da Situaçao Acadêmica.
 * Esse sistema é responsável por gerenciar o uso dos laboratórios
 * do curso de Ciência da Computação da UFCG e a vida acadêmica dos
 * estudantes.
 *
 * @author Kilian Macedo Melcher
 */
public class Coisa {

    public static void main(String[] args) {

        Aluno kilian = new Aluno();

        //Registro tempo online
        kilian.registroTempoOnline("LP2",30);
        kilian.adicionaTempoOnline("LP2",10);
        System.out.println(kilian.atingiuMetaOnline("LP2"));
        kilian.adicionaTempoOnline("LP2",10);
        kilian.adicionaTempoOnline("LP2",10);
        System.out.println(kilian.atingiuMetaOnline("LP2"));
        kilian.adicionaTempoOnline("LP2",2);
        System.out.println(kilian.atingiuMetaOnline("LP2"));
        System.out.println(kilian.registroOnlineToString("LP2"));

        //Disciplina
        kilian.cadastraDisciplina("PROGRAMACAO 2");
        kilian.cadastraHoras("PROGRAMACAO 2",4);
        kilian.cadastraNota("PROGRAMACAO 2",1, 5.0);
        kilian.cadastraNota("PROGRAMACAO 2",2, 6.0);
        kilian.cadastraNota("PROGRAMACAO 2",3, 7.0);
        System.out.println(kilian.aprovado("PROGRAMACAO 2"));
        kilian.cadastraNota("PROGRAMACAO 2",4,10.0);
        System.out.println(kilian.aprovado("PROGRAMACAO 2"));
        System.out.println(kilian.disciplinaToString("PROGRAMACAO 2"));

        //Registro de finanças
        kilian.cadastraFinancas(100000);
        kilian.aumentaReceita(12000,1);
        kilian.aumentaReceita(72100,2);
        kilian.pagaDespesa(20000);
        System.out.println(kilian.exibeFontes());
        System.out.println(kilian.financasToString());

        //Saude
        System.out.println(kilian.getStatusGeral());
        kilian.defineSaudeMental("boa");
        kilian.defineSaudeFisica("boa");
        System.out.println(kilian.getStatusGeral());
        kilian.defineSaudeMental("fraca");
        kilian.defineSaudeFisica("fraca");
        System.out.println(kilian.getStatusGeral());
        kilian.defineSaudeMental("boa");
        kilian.defineSaudeFisica("fraca");
        System.out.println(kilian.getStatusGeral());

    }
}
