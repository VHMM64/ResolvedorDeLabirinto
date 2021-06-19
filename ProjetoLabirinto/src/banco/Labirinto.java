package banco;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Labirinto implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    private String nome;
    private String cliente_ip;
	private LocalDateTime create_date;
	private LocalDateTime modified_date;
    private String labirinto;
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCliente_ip() {
		return cliente_ip;
	}
	public void setCliente_ip(String cliente_ip) {
		this.cliente_ip = cliente_ip;
	}
	public LocalDateTime getCreate_date() {
		return create_date;
	}
	public void setCreate_date(LocalDateTime create_date) {
		this.create_date = create_date;
	}
	public LocalDateTime getModified_date() {
		return modified_date;
	}
	public void setModified_date(LocalDateTime modified_date) {
		this.modified_date = modified_date;
	}
	public String getLabirinto() {
		return labirinto;
	}
	public void setLabirinto(String labirinto) {
		this.labirinto = labirinto;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
