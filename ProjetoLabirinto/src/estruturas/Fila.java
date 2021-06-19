package estruturas;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Fila<X> implements Cloneable
{

    private Object[] fila;
    private int tamanho = 0;
    private int total;

    public Fila(Integer total) throws Exception
    {
        if(total <= 0)
            throw new Exception("Tamanho invalido");

        this.total = total;
        this.fila = new Object[this.total];
    }

    public void push(Object item) throws Exception
    {
        if(this.tamanho == this.total)
            throw new Exception("A fila está cheia.");

        this.fila[this.tamanho] = this.getClone(item);
        this.tamanho++;
    }

    public X top() throws Exception
    {
        if(this.tamanho == 0)
            throw new Exception("A fila está vazia.");

        return this.getClone(this.fila[0]);
    }

    public X pop() throws Exception
    {
        if(this.tamanho == 0)
            throw new Exception("A fila está vazia.");
        
        X elementoRemovido = (X)this.getClone(this.fila[0]);

        for(int i = 0; i < this.tamanho; i++)
        {
            if(i == 0) continue;
                this.fila[i - 1] = this.fila[i];
        }

        this.tamanho--;
        
        return elementoRemovido;
    }

    public Fila(Fila<X> x) throws Exception
    {
        if (x == null)
            throw new Exception ("O modelo inserido é nulo.");

        this.fila = new Object[x.fila.length];

        for (int i = 0; i <= x.tamanho; i++)
            this.fila[i] = this.getClone(x.fila[i]);

        this.tamanho = x.tamanho;
    }

    public X getClone(Object item) throws Exception
    {
        if (item instanceof Cloneable)
        {
            Class classe            = item.getClass();
            Class<?>[] parmsFormais = null;
            Method metodo           = classe.getMethod("clone", parmsFormais);
            Object[] parmsReais     = null;
            return (X)metodo.invoke(item, parmsReais);
        }
        else
            return (X)item;
    }

    public Object clone ()
    {
        Fila<X> ret=null;

        try
        {
            ret = new Fila <X>(this);
        }
        catch (Exception erro)
        {}

        return ret;
    }

    public int getCount()
    {
        return this.tamanho;
    }

    public void printa()
    {
        if(this.tamanho > -1)
            for(int i = 0; i < this.tamanho; i++)
                System.out.println(this.fila[i].toString());
    }

    public String toString() {
        String vetStr = "";
        
        if(this.tamanho > -1)
            for(int i = 0; i < this.tamanho; i++)
                vetStr += this.fila[i].toString() + ";";
        
        return "Fila{" + "fila=" + vetStr + ", tamanho=" + tamanho + ", total=" + total + '}';
    }

    public int hashCode() {
        int hash = 3;
        hash = 666 * hash + Arrays.deepHashCode(this.fila);
        hash = 666 * hash + this.tamanho;
        hash = 666 * hash + this.total;
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
        final Fila<?> other = (Fila<?>) obj;
        if (this.tamanho != other.tamanho) {
            return false;
        }
        if (this.total != other.total) {
            return false;
        }
        if (!Arrays.deepEquals(this.fila, other.fila)) {
            return false;
        }
        return true;
    }
    
    
}