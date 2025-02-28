package Exceptions;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Benvenuto!");
		
		boolean exit = false;
		
		while (true) {
			System.out.println("Inserici un numero oppure \"q\" per uscire");
			String input = scanner.nextLine();
			
			if (input.equalsIgnoreCase("q")) {
				exit = true;
                break;
			}
			
			try {
			
				double number = Double.parseDouble(input);
				
				double divisionResult = 10 / number;
				
				if (number == 0) {
					throw new ArithmeticException("Divisione per zero non è consentita.");
				}
				
				System.out.println("Il risultato della division è: " + divisionResult);

			} catch (ArithmeticException | InputMismatchException e) {
				System.out.println("Eccezione specifica di tipo: " + e.getClass().getName() + " attivata.");

				if (e instanceof ArithmeticException) {
					System.out.println("Espressione aritmetica fallita.");
				} else {
					System.out.println("Input non valido. Devi inserire un numero intero.");
				}
			}
			catch (Exception e) {
				System.out.println("Eccezione generale: " + e.getMessage());
			} finally {
				// System.out.println("Inserici un numero oppure \"q\" per uscire");
			}
		}
		System.out.println("Sei uscito dal programma. Arrivederci!");
		scanner.close();
	}

}
