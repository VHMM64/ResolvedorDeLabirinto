package projetolabirinto;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;

import comunicacao.Parceiro;
import comunicacao.TratadoraDeComunicadoDeDesligamento;

public class ResolvedorDeLabirintoUI extends JFrame {
    
    private JLabel lblAbrirArquivo = new JLabel("Escolha o arquivo:");
    private JButton btnAbrirArquivo = new JButton("Abrir");
    private JButton btnAbrirArquivoBD = new JButton("Abrir do Banco");
    private JButton btnSalvarArquivo = new JButton("Salvar");
    private JButton btnSalvarArquivoBD = new JButton("Salvar no Banco");
    private JTextArea txtLabirintoResolvido;  

    private BufferedReader leitorDeArquivo = null;
    
    private Parceiro servidor=null;
    
    public static final String HOST_PADRAO  = "localhost";
	public static final int    PORTA_PADRAO = 3000;

    public ResolvedorDeLabirintoUI() {
        super("Resolvedor De Labirinto UI");

        Socket conexao=null;
        
        try
        {
            String host = HOST_PADRAO;
            int    porta= PORTA_PADRAO;
            conexao = new Socket (host, porta);
        }
        catch (Exception erro)
        {
            System.err.println ("O Servidor não esta exencutando!\n");
            return;
        }
        
        ObjectOutputStream transmissor=null;
        try
        {
            transmissor =
            new ObjectOutputStream(
            conexao.getOutputStream());
        }
        catch (Exception erro)
        {
            System.err.println ("O Servidor não esta exencutando!\n");
            return;
        }

        ObjectInputStream receptor=null;
        try
        {
            receptor =
            new ObjectInputStream(
            conexao.getInputStream());
        }
        catch (Exception erro)
        {
            System.err.println ("O Servidor não esta exencutando!\n");
            return;
        }
        
        try
        {
            servidor =
            new Parceiro (conexao, receptor, transmissor);
        }
        catch (Exception erro)
        {
            System.err.println ("O Servidor não esta exencutando!\n");
            return;
        }

        TratadoraDeComunicadoDeDesligamento tratadoraDeComunicadoDeDesligamento = null;
        try
        {
			tratadoraDeComunicadoDeDesligamento = new TratadoraDeComunicadoDeDesligamento (servidor);
		}
		catch (Exception erro)
		{}
        
        btnAbrirArquivo.addActionListener(new AbrirLabirinto(this));
        btnSalvarArquivo.addActionListener(new SalvarLabirinto(this));
        
        btnAbrirArquivoBD.addActionListener(new AbrirLabirintoBanco(this));
        btnSalvarArquivoBD.addActionListener(new SalvarLabirintoBanco(this));
        
        //btnResolveLabirinto.addActionListener(new ResolveLabirinto(this));

        JPanel pnlBtnAbrirSalvarLocal = new JPanel();

        pnlBtnAbrirSalvarLocal.add(lblAbrirArquivo);
        pnlBtnAbrirSalvarLocal.add(btnAbrirArquivo);
        pnlBtnAbrirSalvarLocal.add(btnSalvarArquivo);
        
        JPanel pnlBtnAbrirSalvarBD = new JPanel();
        
        pnlBtnAbrirSalvarBD.add(btnAbrirArquivoBD);
        pnlBtnAbrirSalvarBD.add(btnSalvarArquivoBD);

        JPanel pnlLabirintoResolvido = new JPanel();

        //txtLabirintoEditor = new JTextArea("Digite aqui seu labirinto.", 20, 25);
        txtLabirintoResolvido = new JTextArea("O labirinto resolvido será mostrado aqui.", 20, 25);
        
//        Font font = new Font("Monospaced", Font.PLAIN, 14);
//        txtLabirintoResolvido.setFont(font);


        //pnlLabirintoResolvido.add(txtLabirintoEditor);
        pnlLabirintoResolvido.add(txtLabirintoResolvido);
        //pnlLabirintoResolvido.add(btnResolveLabirinto);

        Container cntForm = this.getContentPane();
        cntForm.setLayout(new BorderLayout());
        
        cntForm.add(pnlBtnAbrirSalvarLocal, BorderLayout.NORTH);
        cntForm.add(pnlLabirintoResolvido, BorderLayout.CENTER);
        cntForm.add(pnlBtnAbrirSalvarBD, BorderLayout.SOUTH);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
              System.exit(0);
            }
        });

        this.setSize(450, 450);
        this.setVisible(true);
    }
    
    public Parceiro getServidor() {
        return this.servidor;
    }

    public void setLeitorDeArquivo(BufferedReader leitor) {
        this.leitorDeArquivo = leitor;        
    }

    public BufferedReader getLeitorDeArquivo() {
        return this.leitorDeArquivo;
    }

    public void mostraLabirinto() {
        ResolvedorDeLabirinto resolvedorDeLabirinto = new ResolvedorDeLabirinto();        

        String labirintoResolvido = resolvedorDeLabirinto.resolveLabirinto(this.leitorDeArquivo);
        txtLabirintoResolvido.setText(labirintoResolvido);
    }

	public JTextArea getTxtLabirintoResolvido() {
		return txtLabirintoResolvido;
	}
	
	public void mostraLabirintoBanco(String labirintoBanco) {
		this.txtLabirintoResolvido.setText(labirintoBanco);
	}
    	
}