package net.codejava;

public class EpsonOS {
	private boolean on;
	private boolean inStampa;
	private int livelloInchiostro;
	
	public EpsonOS() {
		this.on = false;
		this.inStampa = false;
		this.livelloInchiostro = 100;
	}
	//accendi la stampante
	public void powerOn() {
		this.on = true;	
	}
	//spegni la stampante
	public void powerOff() {
		this.on = false;
	}
	
	public boolean on() {
		return on;
	}
	
	public boolean stampa() {
		return inStampa;
	}
	
	public int getLivelloInchiostro() {
		return livelloInchiostro;
	}
	//funzione della stampante: la stampante se è accesa e all'interno ce inchiostro stampa, ogni volta che stampa leva il 10% di inchiostro
	public void inStampa(String documento) {
		if(on == true && livelloInchiostro > 0) {
			inStampa = true;
		 System.out.println("IN STAMPA: " + documento);
		 livelloInchiostro -= 10;
		 inStampa = false;
		} else {
			System.out.println("Inchiostro Insufficiente o Stampante non accesa correttamente.");
			System.out.println();
			System.out.println("Si Prega di controllare.");
		}	
		
	}
	//ricaricare l'inchiostro
	public void ricaricaInchiostro() {
		 livelloInchiostro = 100;
	}
	
	
	
	
	
}
