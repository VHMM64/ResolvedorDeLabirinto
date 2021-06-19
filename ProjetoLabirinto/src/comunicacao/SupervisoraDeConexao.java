package comunicacao;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.*;


public class SupervisoraDeConexao extends Thread {
    private double a = 0;

    private Parceiro cliente;
    private Socket conexao;

    private ArrayList<Parceiro> clientes;

    public SupervisoraDeConexao (Socket conexao, ArrayList<Parceiro> clientes) throws Exception {
        if (conexao == null)
            throw new Exception ("Conexão ausente");

        if (clientes == null)
            throw new Exception ("Clientes ausentes");

        this.conexao  = conexao;
        this.clientes = clientes;
    }

    public void run() {
        ObjectOutputStream transmissor;

        try {
            transmissor = new ObjectOutputStream(this.conexao.getOutputStream());
        }
        catch (Exception erro) {
            return;
        }
        
        ObjectInputStream receptor = null;

        try {
            receptor = new ObjectInputStream(this.conexao.getInputStream());
        }
        catch (Exception err0) {
            try {
                transmissor.close();
            }
            catch (Exception falha) {} // so tentando fechar antes de acabar a thread
            
            return;
        }

        try {
            this.cliente = new Parceiro(this.conexao, receptor, transmissor);
        }
        catch (Exception erro) {} // sei que passei os parametros corretos

        try {
            synchronized(this.clientes) {
                this.clientes.add(this.cliente);
                System.out.println("Novo cliente conectado!");
            }

            for(;;) {
                Comunicado comunicado = null;

                if (this.cliente != null) {
                    comunicado = this.cliente.envie();
                }
                else {
                    continue;
                }

                if (comunicado == null)
                    return;

                else if (comunicado instanceof EncerrarConexao) {
                	
                    synchronized (this.clientes) {
                        this.clientes.remove(this.cliente);
                    }

                    this.cliente.adeus();
                    System.out.println("Cliente desconectado!");

                    this.cliente = null;
                }
                
//                else if (comunicado instanceof X) {
//                	
//                    X
//                }
            }
        }
        catch (Exception erro) {
            try {
                transmissor.close();
                receptor.close();
                System.out.println("Programa Encerrado");
                System.err.println(erro);
            }
            catch (Exception falha) {}

            return;
        }
    }
}