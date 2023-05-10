package net.codejava;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;


public class InserimentoImmagine {

	public static void main(String[] args) {
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ristorante","root","Davide123@");
			PreparedStatement stmt = conn.prepareStatement("Replace INTO menu(id,categoria,portate,prezzo,foto) VALUES(7,'Secondi','Astice con pomodorini del vesuvio',40,?)");
			File immagine = new File("C:\\Users\\peppe\\Downloads\\astice2.jpg");
			FileInputStream fis = new FileInputStream(immagine);
			//stmt.setString(1, immagine.getName());
			stmt.setBinaryStream(1, fis, (int)immagine.length());
			stmt.executeUpdate();
			System.out.println("Immagine inserita correttamente nel database.");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Errore durante l'inserimento dell'immagine nel database.");
		}
	}
}
