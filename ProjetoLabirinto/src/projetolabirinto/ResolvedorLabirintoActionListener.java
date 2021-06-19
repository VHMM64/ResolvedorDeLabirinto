package projetolabirinto;

import java.awt.event.*;

public abstract class ResolvedorLabirintoActionListener implements ActionListener { 
    protected ResolvedorDeLabirintoUI resolvedorDeLabirintoUI;
    
    public ResolvedorLabirintoActionListener(ResolvedorDeLabirintoUI resolvedorDeLabirintoUI) {
        this.resolvedorDeLabirintoUI = resolvedorDeLabirintoUI;
    }
}
