package projetolabirinto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import estruturas.Fila;
import estruturas.Pilha;

/*
 * Projeto LPOO 2021
 * @author Bryan / Gustavo / Victor
 */

/*
 * Arquivos de tipos de labirintos inclusos na pasta do projeto
 * 
 */

public class ResolvedorDeLabirinto {
 
    public String resolveLabirinto(BufferedReader leitorDeArquivo) {
        boolean completo = false;
        String labirintoResolvido = "";
        
        try {            
            Labirinto labirinto = new Labirinto(leitorDeArquivo);            
            Pilha<Coordenada> caminho = new Pilha<Coordenada>(labirinto.getTamanho());
            Pilha<Fila<Coordenada>> possibilidades = new Pilha<Fila<Coordenada>>(labirinto.getTamanho());
            Coordenada atual = labirinto.getEntrada();
            
            while(!completo)
            {
                Fila<Coordenada> fila = new Fila<Coordenada>(3);
                Coordenada[] caminhos = labirinto.getCaminhos(atual);
                for(int i = 0; i < 3; i++)
                {
                    // Busca a proxima coordenada
                    Coordenada possibilidade = caminhos[i];
                    if(possibilidade != null)
                    {
                        // Insere a coordenada na fila
                        fila.push(possibilidade);
                    }
                }
                
                while(fila.getCount() == 0)
                {
                    atual = caminho.pop();
                    labirinto.setCaractere(' ', atual);
                    fila = possibilidades.pop();
                }
                
                atual = fila.pop();
                
                if(labirinto.isSaida(atual))
                {
                    completo = true;
                    Pilha<Coordenada> inverso = new Pilha<Coordenada>(caminho.getCount());
                    
                    while(caminho.getCount() > 0)
                    {
                        Coordenada c = caminho.pop();
                        inverso.push(c);
                    }
                    
                    
                    //System.out.println(labirinto.toString());
                    //inverso.printa();
                    labirintoResolvido = labirinto.toString() + "\n";
                    labirintoResolvido += inverso.printa();
                    
                    break;
                }
                
                labirinto.setAndou(atual);
                caminho.push(atual);
                possibilidades.push(fila);
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return labirintoResolvido;
    }

    public BufferedReader getArquivoPeloConsole() {
        try {
            System.out.println("Digite o nome do arquivo:");
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            String arquivo = teclado.readLine();
            BufferedReader leitorDeArquivo = null;
            
            try {
                leitorDeArquivo = new BufferedReader(new FileReader(arquivo));
            } catch(FileNotFoundException e)
            {
                System.out.println("O arquivo nao existe ou o caminho indicado pode estar errado");
            }
            
            // if(leitorDeArquivo == null)
            //     return;
            
            return leitorDeArquivo;        
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;        
    }
}
