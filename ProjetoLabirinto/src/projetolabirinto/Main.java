package projetolabirinto;

import java.io.BufferedReader;

/*
 * Projeto Resolvedor de Labirintos - Client-Servidor e Integracao com Banco de Dados MySQL
 * Bryan / Gustavo / Victor Hugo
 */

public class Main {
    // Main
    public static void main(String[] args) {
        
        // resolvendo pelo Console
        
        // ResolvedorDeLabirinto resolvedorDeLabirinto = new ResolvedorDeLabirinto();
        // BufferedReader leitorDeArquivo = resolvedorDeLabirinto.getArquivoPeloConsole();
        // String labirintoResolvido = resolvedorDeLabirinto.resolveLabirinto(leitorDeArquivo);
        // System.out.println(labirintoResolvido);


        // resolvendo pela tela

        new ResolvedorDeLabirintoUI();
    }
}


