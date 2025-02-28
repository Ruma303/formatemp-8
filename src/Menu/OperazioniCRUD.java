package Menu;

import java.util.Scanner;

public class OperazioniCRUD {

	private static Dipendente[] dipendenti = new Dipendente[1];
	private static int count = 0;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {

			mostraMenu();
			String scelta = scanner.nextLine();

			switch (scelta) {
			case "1":
				aggiungiDipendente(scanner);
				break;
			case "2":
				elencaDipendenti();
				break;
			case "3":
				cercaDipendente(scanner);
				break;
			case "4":
				aggiornaDipendente(scanner);
				break;
			case "5":
				eliminaDipendente(scanner);
				break;
			case "6":
				System.out.println("\nTermine programma!\n");
				scanner.close();
				System.exit(0);
				break;
			default:
				System.out.println("\nScelta non valida!\n");
				break;
			}
		}
	}

	private static void mostraMenu() {
		System.out.println("\n******** Menu ********\n");
		System.out.println("1. Aggiungi dipendente");
		System.out.println("2. Elenca dipendenti");
		System.out.println("3. Cerca dipendente");
		System.out.println("4. Elimina dipendente");
		System.out.println("5. Termine programma");
		System.out.println("6. Aggiorna dipendente");
		System.out.println("\n======================\n");
	}

	private static void aggiungiDipendente(Scanner scanner) {
		if (count == dipendenti.length) {

			// Array iniziale con capacità 2 (può essere anche 1 o un altro valore)
			// essendo gli array un dato statico, non possono essere modificati dopo la
			// creazione.
			// Possiamo però ricopiare i dipendenti che creeremo in un nuovoDipendente array
			// più capiente quando è pieno.

			Dipendente[] nuovoDipendenteArray = new Dipendente[dipendenti.length * 2];

			// Questa istruzione copia tutti gli elementi dal vecchio array dipendenti a
			// partire dall'indice 0,
			// fino alla lunghezza totale, dentro il nuovoDipendente array
			// nuovoDipendenteArray, a partire anch'esso dall'indice 0.
			// In questo modo, tutti i dipendenti già inseriti vengono preservati.

			System.arraycopy(dipendenti, 0, nuovoDipendenteArray, 0, dipendenti.length);

			// Il vecchio array dipendenti viene punta al nuovoDipendente array
			// nuovoDipendenteArray.
			// Questo significa che, da questo momento in poi, tutte le operazioni (come
			// aggiungere nuovi
			// dipendenti) verranno effettuate su questo array ampliato.

			dipendenti = nuovoDipendenteArray;
		}

		Dipendente nuovoDipendente = creaDipendente(scanner);

		// Se creaDipendente() ha restituito null, significa che esiste già un
		// dipendente
		if (nuovoDipendente == null) {
			System.out.println("Operazione annullata: dipendente già esistente!");
			return;
		}

		dipendenti[count] = nuovoDipendente;
		count++;
		System.out.println("Nuovo dipendente \"" + nuovoDipendente.getNome() + "\" aggiunto con successo!");
	}

	private static Dipendente creaDipendente(Scanner scanner) {
		System.out.println("Inserisci il nome del dipendente:");
		String nome = scanner.nextLine();
		System.out.println("Inserisci il cognome del dipendente:");
		String cognome = scanner.nextLine();
		System.out.println("Inserisci l'email del dipendente:");
		String email = scanner.nextLine();

		// Utilizziamo la funzione di ricerca per controllare duplicati
		if (cercaDipendente(nome, cognome, email) != null) {
			System.out.println("Dipendente già esistente!");
			return null;
		}
		return new Dipendente(nome, cognome, email);
	}

	private static void elencaDipendenti() {
		if (!esistonoDipendenti()) return;
		System.out.println("I dipendenti inseriti sono:");
		System.out.println("\t# | Nome Cognome | Email");
		for (int i = 0; i < count; i++) {
			System.out.println("\t" + (i + 1) + ": " + dipendenti[i].getNome() + " " + dipendenti[i].getCognome()
					+ " | " + dipendenti[i].getEmail());
		}
	}

	private static void cercaDipendente(Scanner scanner) {
		if (!esistonoDipendenti()) return;

		System.out.println("Cerca dipendente per nome, cognome o email:");
		String input = scanner.nextLine().toLowerCase();
		boolean trovato = false;

		for (int i = 0; i < count; i++) {
			Dipendente d = dipendenti[i];
			if (input.equalsIgnoreCase(d.getNome()) || input.equalsIgnoreCase(d.getCognome())
					|| input.equalsIgnoreCase(d.getEmail())) {

				System.out.println("\t# " + i + ": " + d.getNome() + " " + d.getCognome() + " | " + d.getEmail());
				trovato = true;
			}
		}

		if (!trovato) {
			System.out.println("\nNessun dipendente trovato con questi valori. Visualizza i dipendenti premendo 2.");
		}
	}

	// Metodo di ricerca (overload) che controlla se esiste un dipendente con lo
	// stesso nome, cognome o email
	private static Dipendente cercaDipendente(String nome, String cognome, String email) {
		if (!esistonoDipendenti()) return null;
		for (int i = 0; i < count; i++) {
			Dipendente d = dipendenti[i];

			// Decideremo se controllare solo l'email (solitamente unica) oppure anche nome
			// e cognome.
			// Ad esempio, controlliamo se la email è già presente oppure se (nome e
			// cognome) coincidono.

			if (d.getEmail().equalsIgnoreCase(email)
					|| (d.getNome().equalsIgnoreCase(nome) && d.getCognome().equalsIgnoreCase(cognome))) {
				return d;
			}
		}
		return null;
	}

	private static void aggiornaDipendente(Scanner scanner) {
		if (!esistonoDipendenti()) return;
		System.out.println("Inserisci l'indice del dipendente da aggiornare:");
		int indice;
		try {
			indice = Integer.parseInt(scanner.nextLine()) - 1;
		} catch (NumberFormatException e) {
			System.out.println("Inserisci un numero valido!");
			return;
		}
		if (indice < 0 || indice >= count) {
			System.out.println("Indice non valido!");
			return;
		}
		Dipendente aggiornato = creaDipendente(scanner);
		if (aggiornato == null) {
			System.out.println("Operazione annullata: dipendente già esistente con quei dati!");
			return;
		}
		dipendenti[indice] = aggiornato;
		System.out.println("Dipendente aggiornato con successo!");
	}

	private static void eliminaDipendente(Scanner scanner) {
		
		if (!esistonoDipendenti()) return;
		
		System.out.println("Inserisci l'indice del dipendente da eliminare:");
		int indice;
		try {
			indice = Integer.parseInt(scanner.nextLine()) - 1;
		} catch (NumberFormatException e) {
			System.out.println("Inserisci un numero valido!");
			return;
		}
		if (indice < 0 || indice >= count) {
			System.out.println("Indice non valido!");
			return;
		}
		// Spostiamo tutti gli elementi dopo l'indice verso sinistra
		for (int i = indice; i < count - 1; i++) {
			dipendenti[i] = dipendenti[i + 1];
		}
		dipendenti[count - 1] = null; // Aiuta il garbage collector
		count--;
		System.out.println("Dipendente eliminato con successo!");
	}

	
	public static Boolean esistonoDipendenti() {
		if (count == 0) {
			System.out.println("\nNessun dipendente esistente!\n");
			return false;
		} else {
			return true;
		}
	}
}