package estruturas;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Pilha<X> implements Cloneable {

    private Object[] vetor;
    private int topo;
    private int maximo;

    public Pilha(int tamanho) throws Exception
    {
        if(tamanho <= 0)
            throw new Exception("Tamanho de pilha invalido");
            
        this.vetor = new Object[tamanho];
        this.maximo = tamanho;
        this.topo = -1;
    }

    public void push(Object item) throws Exception {
        if((this.topo + 1) == this.maximo)
            throw new Exception("Pilha cheia");
            
        this.topo++;
        this.vetor[this.topo] = this.getClone(item);
    }

    public X pop() throws Exception {
        if(this.topo < 0)
            throw new Exception("A pilha esta vazia");
           
        X itemTopo = (X)this.vetor[this.topo];
        this.vetor[this.topo] = null;
        this.topo--;
        
        return this.getClone(itemTopo);
    }

    public X top() throws Exception {
        if(this.topo < 0)
            throw new Exception("A pilha esta vazia");
           
        X itemTopo = (X)this.vetor[this.topo];
        return this.getClone(itemTopo);
    }

    public int getCount() {
        return this.topo + 1;
    }

    public String printa()
    {
        String retorno = "";
        if(this.topo > -1)
            for(int i = 0; i < (this.topo + 1); i++)
                //System.out.println(this.vetor[i].toString());
                retorno += this.vetor[i].toString() + "\n";

        return retorno;
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

    public Pilha(Pilha<X> x) throws Exception
    {
        if (x == null)
            throw new Exception("null");

        this.vetor = new Object[x.vetor.length];

        for (int i = 0; i <= x.maximo; i++)
            this.vetor[i] = this.getClone(x.vetor[i]);
        
        this.maximo = x.maximo;
        this.topo = x.topo;
    }

    public Object clone()
    {
        Pilha<X> ret=null;

        try
        {
            ret = new Pilha<X>(this);
        }
        catch (Exception erro)
        {}

        return ret;
    }

    public int hashCode() {
        int hash = 7;
        hash = 666 * hash + Arrays.deepHashCode(this.vetor);
        hash = 666 * hash + this.topo;
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
        final Pilha<?> other = (Pilha<?>) obj;
        if (this.topo != other.topo) {
            return false;
        }
        if (!Arrays.deepEquals(this.vetor, other.vetor)) {
            return false;
        }
        return true;
    }

    public String toString() {
        String vetStr = "";
        
        if(this.topo > -1)
            for(int i = 0; i < (this.topo + 1); i++)
                vetStr += this.vetor[i].toString() + ";";
        
        return "Pilha{" + "vetor=" + vetStr + ", topo=" + topo + '}';
    }
    
}
