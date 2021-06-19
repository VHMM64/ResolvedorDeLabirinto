package projetolabirinto;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AbrirLabirinto extends ResolvedorLabirintoActionListener {
    
    public AbrirLabirinto(ResolvedorDeLabirintoUI resolvedorDeLabirintoUI) {
        super(resolvedorDeLabirintoUI);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text");
        fc.setFileFilter(filter);
        
        try {
            int returnVal = fc.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                BufferedReader leitor = new BufferedReader(new FileReader(file));
                resolvedorDeLabirintoUI.setLeitorDeArquivo(leitor);
                resolvedorDeLabirintoUI.mostraLabirinto();
            }
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro na abertura do arquivo:", "",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    
}
