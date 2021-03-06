package servidor;

import java.util.*;


import comunicacao.AceitadoraDeConexao;
import comunicacao.EncerrarConexao;
import comunicacao.Parceiro;

/*
 * Projeto Resolvedor de Labirintos - Client-Servidor e Integracao com Banco de Dados MySQL
 * Bryan / Gustavo / Victor Hugo
 */

public class Servidor
{
	public static String PORTA_PADRAO = "3000";
	
    public static void main (String[] args)
    {
        if (args.length>1)
        {
            System.err.println ("Uso esperado: java Servidor [PORTA]\n");
            return;
        }

        String porta=Servidor.PORTA_PADRAO;
        
        if (args.length==1)
            porta = args[0];

        ArrayList<Parceiro> usuarios =
        new ArrayList<Parceiro> ();

        AceitadoraDeConexao aceitadoraDeConexao=null;
        try
        {
            aceitadoraDeConexao =
            new AceitadoraDeConexao (porta, usuarios);
            aceitadoraDeConexao.start();
        }
        catch (Exception erro)
        {
            System.err.println ("Escolha uma porta apropriada e liberada para uso!\n");
            return;
        }
    }
}
