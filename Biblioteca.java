package net.codejava;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

	public class Biblioteca extends JFrame{
		
		
		private JButton addButton, modifyButton, deleteButton,searchButton,carrelloButton,visualizzaButton;
		private JLabel titoloLabel, prezzoLabel, editoriLabel, nomeLabel, cognomeLabel,idLabel, idAutoreLabel,idEditoreLabel;
		private JTextField titoloField, prezzoField, editoriField, nomeField, cognomeField, idField, idAutoreField, idEditoreField;
		
	
		
		public Biblioteca() {
			
		
			
			//inizializzazione Label
			titoloLabel = new JLabel("Inserisci titolo libro ");
			prezzoLabel = new JLabel("Inserisci prezzo del libro ");
			editoriLabel = new JLabel("Inserisci l'editore del libro ");
			nomeLabel = new JLabel("Inserisci nome autore ");
			cognomeLabel = new JLabel("Inserisci cognome autore ");
			idLabel = new JLabel("Inserisci l'Id libro");
			idAutoreLabel = new JLabel("Inserisci Id autore");
			idEditoreLabel =new JLabel("Inserisci Id editore");
		//inizializzazione Field
			titoloField = new JTextField(50);
			prezzoField = new JTextField();
			editoriField = new JTextField(50);
			nomeField = new JTextField(50);
			cognomeField = new JTextField(50);
			idField = new JTextField();
			idAutoreField = new JTextField();
			idEditoreField = new JTextField();
		//inizializzazione bottoni
			addButton = new JButton("Aggiungi libro");
			modifyButton = new JButton("Modifica libro");
			deleteButton = new JButton("Elimina Libro");
			searchButton = new JButton("Cerca Libro");
			carrelloButton = new JButton("Vai al Carrello");
			visualizzaButton = new JButton("Visualizza");
			
			//panel
			JPanel panel = new JPanel(new GridLayout(12,10));
			
			setTitle("Aggiungi Libro");
			setSize(400,600);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			panel.add(titoloLabel);
			panel.add(titoloField);
			panel.add(idLabel);
			panel.add(idField);
			/*panel.add(idEditoreLabel);
			panel.add(idEditoreField);
			panel.add(idAutoreLabel);
			panel.add(idAutoreField);*/
			panel.add(prezzoLabel);
			panel.add(prezzoField);
			panel.add(editoriLabel);
			panel.add(editoriField);
			panel.add(nomeLabel);
			panel.add(nomeField);
			panel.add(cognomeLabel);
			panel.add(cognomeField);
			panel.add(addButton);
			panel.add(modifyButton);
			panel.add(deleteButton);
			panel.add(searchButton);
			panel.add(carrelloButton);
			panel.add(visualizzaButton);
			add(panel);
			setVisible(true);
			
			addButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					
					try {
						
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/GestioneLibri",
								"root", "Shake411+");
							String id = idField.getText();
							String id_autori = idField.getText();
							String id_editori = idField.getText();
	                           String titolo =  titoloField.getText(); 
	                           String prezzo = prezzoField.getText();
	                           String editori = editoriField.getText();
	                           String nome = nomeField.getText();
	                           String cognome = cognomeField.getText();
	                           
                           	String sql = "INSERT INTO libri(id, titolo, id_autori, id_editori, prezzo) VALUES(?, ?, ?,?,?)";
                           	PreparedStatement stmt =  conn.prepareStatement(sql);                      
                           
                           	String sql1 = "INSERT INTO editori(nome) VALUES(?)";
                           	PreparedStatement stmt1 = conn.prepareStatement(sql1);
                        		   
                        	String sql2 = "INSERT INTO autori(nome,cognome) VALUES(?,?)";	   
                        	PreparedStatement stmt2 = conn.prepareStatement(sql2);
                        	
                        	stmt.setString(1, id);
                        	stmt.setString(2, titolo);
                        	stmt.setString(3, id_autori);
                        	stmt.setString(4, id_editori);
                        	stmt.setString(5, prezzo);
                        	
                        	stmt1.setString(1, editori);
                        	
                        	stmt2.setString(1, nome);
                        	stmt2.setString(2, cognome);
                        	
                        	int riga1 = stmt.executeUpdate();
                        	int riga2 = stmt1.executeUpdate();
                        	int riga3 = stmt2.executeUpdate();
                        	
                        	JOptionPane.showConfirmDialog(panel, "Libro aggiunto correttamente.");
                        	titoloField.setText(" ");
                        	prezzoField.setText(" ");
                        	editoriField.setText(" ");
                        	nomeField.setText(" ");
                        	cognomeField.setText(" ");
                        	idField.setText(" ");
                        	
                        	
                        	
                    }catch(Exception  ex ) {
                        ex.printStackTrace();
                    }
					
			}
			}
				);
			
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Connection conn = null;
					PreparedStatement stmt = null;
					String sql = "DELETE FROM libri WHERE id = ?";

					try {


						String id_modifica = idField.getText().toString();
						
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/GestioneLibri",
								"root", "Shake411+");
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, id_modifica);
						stmt.executeUpdate();
						
						
						idField.setText(" ");
						
						JOptionPane.showMessageDialog(null, "Libro eliminato correttamente");
						

					}catch(SQLException ey) {
						ey.printStackTrace();
						JOptionPane.showMessageDialog(null, "Libro non eliminato");
					}
				}
			}
					);
			
			modifyButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String id = idField.getText();
                    String titolo =  titoloField.getText(); 
                    String prezzo = prezzoField.getText();
                    String editori = editoriField.getText();
                    String nome = nomeField.getText();
                    String cognome = cognomeField.getText();
					
					Connection conn = null;
					PreparedStatement stmt = null;
					String sql = "UPDATE libri SET titolo=?,prezzo=?  Where id=? ";					
					String sql1 = "UPDATE editori SET nome=? where id=?";
					String sql2 = "UPDATE autori SET nome=?, cognome=? where id=?";
					try {
						
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/GestioneLibri",
								"root", "Shake411+");
						stmt = conn.prepareStatement(sql);
						PreparedStatement stmt1 = conn.prepareStatement(sql1);
						PreparedStatement stmt2 = conn.prepareStatement(sql2);
						
						stmt.setString(1, titolo);
						stmt.setString(2, prezzo);
						stmt.setString(3, id);
						stmt1.setString(1, editori);
						stmt1.setString(2, id);
						stmt2.setString(1, nome);
						stmt2.setString(2, cognome);
						stmt2.setString(3, id);
						
						int riga = stmt.executeUpdate();
						int riga1 = stmt1.executeUpdate();
						int riga3 = stmt2.executeUpdate();
						
						titoloField.setText(" ");
                    	prezzoField.setText(" ");
                    	editoriField.setText(" ");
                    	nomeField.setText(" ");
                    	cognomeField.setText(" ");
                    	idField.setText(" ");
                    	
						
						JOptionPane.showMessageDialog(null, "Libro aggiornato correttamente");
					}catch(Exception ex) {						
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Libro non aggiornato correttamente");
					}
					
				}
			}
					);
			
			
			searchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Search();
				}
			}
					);
			
			carrelloButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Carrello();
				}
			}
					);
					
			visualizzaButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Visualizza();
				}
			}
					);
			
			
			
			
}
		
			
			public static void main(String[] args) {
				new Biblioteca();
			}
}
