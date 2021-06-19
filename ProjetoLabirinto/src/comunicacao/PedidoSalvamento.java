package comunicacao;

import projetolabirinto.Labirinto;

public class PedidoSalvamento implements Comunicado {

    private static final long serialVersionUID = 242220071685655080L;
    
    private Labirinto labirinto;
    
    public PedidoSalvamento (Labirinto lab)
    {
        this.labirinto = lab;
    }

    public Labirinto getLabirinto ()
    {
        return this.labirinto;
    }
}