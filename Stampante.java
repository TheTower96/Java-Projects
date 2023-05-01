package net.codejava;

import java.util.*;

public class Stampante {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		EpsonOS epson = new EpsonOS();
		
		//dichiarazione variabile di scelta
		int scelta = 0;
		
		//Interfaccia della Stampante con le varie opzioni
		
		do {
			System.out.println("Benvenuto, seleziona un'opzione: ");
			System.out.println("1. Accendi stampante");
			System.out.println("2. Stampa Documento");
			System.out.println("3. Controlla il livello di inchiostro");
			System.out.println("4. Ricarica l'inchiostro");
			System.out.println("5. Spegni la stampante");
			System.out.println("6. Esci");
			
			scelta = scanner.nextInt();
			
			switch(scelta) {
			case 1: 
				epson.powerOn();
				System.out.println("La Stampante Epson è accesa.");
				System.out.println();
				break;
			case 2:
				if(epson.stampa()) {
					System.out.println("La stampante sta già stampando il documento");
					System.out.println();
				} else if(epson.getLivelloInchiostro() <= 0) {
					System.out.println("La stampante non ha inchiostro, controlla il livello d'inchiostro.");
					System.out.println();
				} else {
					epson.inStampa("Documento");
					System.out.println();
				}
				break;
			case 3: 
				System.out.println("Il livello di inchiostro è: " + epson.getLivelloInchiostro());
				System.out.println();
				break;
			case 4: 
				epson.ricaricaInchiostro();
				System.out.println("Il livello d'inchiostro è stato ricaricato");
				System.out.println();
				break;
			case 5:
				epson.powerOff();
				System.out.println("La Stampante Epson è spenta.");
				System.out.println();
				break;
			case 6:
				System.out.println("Arrivederci!");
				System.out.println();
				break;
				default:
					System.out.println("Opzione non valida, scegli un opzione valida.");
					System.out.println();
			}
			
		}while(scelta != 6);


	}

}
