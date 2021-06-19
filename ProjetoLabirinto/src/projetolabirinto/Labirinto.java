package projetolabirinto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * Projeto Resolvedor de Labirintos - Client-Servidor e Integracao com Banco de Dados MySQL
 * Bryan / Gustavo / Victor
 */

public class Labirinto {
	
    // Matriz
    private char[][] matrizLabirinto;
    
    // Linhas e colunas do labirinto
    private int linhas = 0;
    private int colunas = 0;
    
    // Entrada e saida do labirinto
    Coordenada entrada;
    Coordenada saida;
    
    /*
     * Construtor
     */
    
    public Labirinto(String caractere) throws Exception
    {
        this.loadFromString(caractere);
    }
    
    /*
     * Preenche a matriz
     */
    
    private void loadFromString(String caractere) throws Exception
    {
        // Divide a string em pedacos (token)
        StringTokenizer token = new StringTokenizer(caractere, "\n", false);
        
        // Calcula quantidade de linhas do labirinto
        int linhas = 0;
        String nextToken = token.nextToken();
        
        try
        {
            linhas = Integer.parseInt(nextToken);
        }
        
        catch(NumberFormatException e)
        {
            System.out.println("Uma falha ocorreu durante a leitura do labirinto (Numero incorreto de linhas)");
        }
        
        // Calcula quantidade de colunas do labirinto
        int colunas = 0;
        nextToken = token.nextToken();
        
        try
        {
            colunas = Integer.parseInt(nextToken);
        } catch(NumberFormatException e)
        {
            System.out.println("Uma falha ocorreu durante a leitura do labirinto (Numero incorreto de colunas)");
        }
        
        if(linhas == 0 || colunas == 0)
            throw new Exception("Uma falha ocorreu durante a leitura do labirinto (formato invalido)");
        
        // Armazenamento de linhas
        int i = 0;
        String[] tempLinhas = new String[linhas];
        while(token.hasMoreTokens())
        {
            String linha = token.nextToken();
            tempLinhas[i] = linha;
            i++;
        }
        
        // Nova matriz - com as linhas
        this.instanciarMatriz(linhas, colunas);
        this.popularMatriz(tempLinhas);
    }
    
    /*
     * instancia Matriz
     */
    private void instanciarMatriz(int linhas, int colunas) throws Exception
    {
        if(linhas <= 0 || colunas <= 0)
            throw new Exception("O numero de linhas ou coluna e invalido (menor ou igual a zero)");
        
        this.matrizLabirinto = new char[linhas][colunas];
        this.linhas = linhas;
        this.colunas = colunas;
        
        for(int i = 0; i < linhas; i++)
            for(int j = 0; j < colunas; j++)
                this.matrizLabirinto[i][j] = ' ';
    }
    
    /*
     * Preenche matriz
     */
    private void popularMatriz(String[] linhas) throws Exception
    {
        for(int l = 0; l < this.linhas; l++)
        {
            for(int c = 0; c < this.colunas; c++)
            {
                char caractere = linhas[l].charAt(c);
                
                Coordenada cords = new Coordenada(l, c);
                this.setCaractere(caractere, cords);
            }
        }
        
        if(this.getEntrada() == null)
            throw new Exception("Labirinto nao tem entrada");
        
        if(this.getSaida() == null)
            throw new Exception("Labirinto nao tem saida");
    }
    
    /*
     * Valida caractere de entrada
     */
    private boolean isCaractereValido(char caractere)
    {
        switch(caractere)
        {
            case ' ':
                return true;
            case '#':
                return true;
            case 'E':
                return true;
            case 'S':
                return true;
            case '*':
                return true;
            default:
                return false;
        }
    }
    
    /*
     * Altera o caractere de uma posicao
     */
    
    public void setCaractere(char caractere, Coordenada coordenada)
            throws Exception
    {
        if(!this.isCaractereValido(caractere))
            throw new Exception("ha um caractere inválido");
        
        if(caractere == 'E')
            if(this.entrada == null)
                this.entrada = coordenada;
            else
                throw new Exception("existem duas entradas");
        else if(caractere == 'S')
            if(this.saida == null)
                this.saida = coordenada;
            else
                throw new Exception("existem duas saidas");
        
        this.matrizLabirinto[coordenada.getX()][coordenada.getY()] = caractere;
    }
    
    /*
     * Retorna o caractere de determinada posição
     */
    public char getCaractere(Coordenada coordenada) throws Exception
    {
        if(coordenada.getX() < 0 || coordenada.getY() < 0)
            throw new Exception("posição de linha ou coluna menor do que zero");
        
        if(coordenada.getX() > this.linhas || coordenada.getY() > this.colunas)
            throw new Exception("posição de linha ou coluna maior do que zero");
        
        return this.matrizLabirinto[coordenada.getX()][coordenada.getY()];
    }
    
    /*
     * Verifica parede
     */
    public boolean isParede(Coordenada coordenada) throws Exception
    {
        return (this.getCaractere(coordenada) == '#');
    }
    
    /*
     * Verifica entrada
     */
    public boolean isEntrada(Coordenada coordenada) throws Exception
    {
        return (this.getCaractere(coordenada) == 'E');
    }
    
    /*
     * Verifica saida
     */
    public boolean isSaida(Coordenada coordenada) throws Exception
    {
        return (this.getCaractere(coordenada) == 'S');
    }
    
    /*
     * Verifica caminho
     */
    public boolean isCaminho(Coordenada coordenada) throws Exception
    {
        return (this.getCaractere(coordenada) == ' ' || this.getCaractere(coordenada) == 'S');
    }
    
    public int getLinhas()
    {
        return this.linhas;
    }
    
    public int getColunas()
    {
        return this.colunas;
    }
    
    public int getTamanho()
    {
        return this.linhas * this.colunas;
    }
    
    //Retorna a entrada do labirinto.
    public Coordenada getEntrada()
    {
        return this.entrada;
    }
    
    //Retorna a saida do labirinto
    public Coordenada getSaida()
    {
        return this.saida;
    }
    
    // Retorna caminhos validos
    public Coordenada[] getCaminhos(Coordenada percurso) throws Exception
    {
        Coordenada[] ret = new Coordenada[3];
        
        int i = 0;
        int x = percurso.getX();
        int y = percurso.getY();
        
        if(x > 0)
        {
            Coordenada esquerda = new Coordenada(x - 1, y);
            if(this.isCaminho(esquerda))
            {
                ret[i] = esquerda;
                i++;
            }
        }
        
        if(x < this.getLinhas())
        {
            Coordenada direita = new Coordenada(x + 1, y);
            if(this.isCaminho(direita))
            {
                ret[i] = direita;
                i++;
            }
        }
        
        if(y > 0)
        {
            Coordenada cima = new Coordenada(x, y - 1);
            if(this.isCaminho(cima))
            {
                ret[i] = cima;
                i++;
            }
        }
        
        if(y < this.getColunas())
        {
            Coordenada baixo = new Coordenada(x, y + 1);
            if(this.isCaminho(baixo))
            {
                ret[i] = baixo;
                i++;
            }
        }
        
        return ret;
    }
    
    // Verifica o caminho percorrido
    public void setAndou(Coordenada percurso) throws Exception
    {
        this.setCaractere('*', percurso);
    }

    public String toString()
    {
        String ret = "";
        
        for(int i = 0; i < this.linhas; i++)
        {
            for(int j = 0; j < this.colunas; j++)
            {
                try {
                    ret += this.getCaractere(new Coordenada(i, j));
                } catch(Exception e) {}
            }
            
            ret += "\n";
        }
        
        return ret;
    }

    public int hashCode() {
        int hash = 7;
        hash = 666 * hash + Arrays.deepHashCode(this.matrizLabirinto);
        hash = 666 * hash + this.linhas;
        hash = 666 * hash + this.colunas;
        return hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Labirinto other = (Labirinto) obj;
        if (this.linhas != other.linhas) {
            return false;
        }
        if (this.colunas != other.colunas) {
            return false;
        }
        if (!Arrays.deepEquals(this.matrizLabirinto, other.matrizLabirinto)) {
            return false;
        }
        return true;
    }

    public Labirinto(BufferedReader leitor) throws Exception
    {
        String labStr = "";
        String linha = null;
        
        try {
            while(leitor.ready())
                labStr += leitor.readLine() + "\n";
            
            leitor.close();
            this.loadFromString(labStr);
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

	public Labirinto() {
		// TODO Auto-generated constructor stub
	}
    
}
