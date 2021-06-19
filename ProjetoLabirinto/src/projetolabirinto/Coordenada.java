package projetolabirinto;

public class Coordenada implements Cloneable {

    private int x;
    private int y;

    // Construtores
    public Coordenada()
    {
        this.x = -1;
        this.y = -1;
    }

    public Coordenada(int x, int y) throws Exception
    {
        this.setX(x);
        this.setY(y);
    }
    
    // Getters / Setters
    public int getX() throws Exception {
        if(this.x < 0)
            throw new Exception("Coordenada X invalida");
        
        return x;
    }

    public void setX(int x) throws Exception {
        if(x < 0)
            throw new Exception("Coordenada X invalida");
        
        this.x = x;
    }

    public int getY() throws Exception {
        if(this.y < 0)
            throw new Exception("Coordenada Y invalida");
        
        return y;
    }

    public void setY(int y) throws Exception {
        if(y < 0)
            throw new Exception("Coordenada Y invalida");
        
        this.y = y;
    }

    public void setPosicao(int x, int y) throws Exception {
        this.setX(x);
        this.setY(y);
    }

    public int hashCode() {
        int hash = 11;
        hash = 666 * hash + this.x;
        hash = 666 * hash + this.y;
        return hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Coordenada other = (Coordenada) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "(" + y + ", " + x + ')';
    }

    public Object clone()
    {
        Coordenada ret = null;
        try 
        {
            return (Coordenada) new Coordenada(this.getX(), this.getY());
        }
        catch(Exception e) {}
        
        return ret;
    }
    
}
