package Menu;

public class Dipendente {
	
	private String nome = "";
	private String cognome = "";
	private String email = "";

	public Dipendente(String nome, String cognome, String email) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getEmail() {
		return email;
	}
}
