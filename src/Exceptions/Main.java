package Exceptions;

import java.util.Scanner;

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
				
				System.out.println("Il risultato della divisione è: " + divisionResult);

			} catch (ArithmeticException | NumberFormatException e) {
				System.out.println("Eccezione di tipo: " + e.getClass().getName() + " lanciata.");

				if (e instanceof ArithmeticException) {
					System.out.println("Espressione aritmetica fallita.");
				} else {
					System.out.println("Input non valido. Devi inserire un numero.");
				}
			}
			catch (Exception e) {
				System.out.println("Eccezione generale: " + e.getMessage());
			} finally {
				System.out.println("\n");
			}
		}
		System.out.println("Sei uscito dal programma. Arrivederci!");
		scanner.close();
	}

}
