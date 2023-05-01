package net.codejava;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Carrello extends JFrame{
	
	ArrayList<Integer> libri = new ArrayList<Integer>();
	
	private static final String DB_URL = "jdbc:mysql://localhost/GestioneLibri";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "password";	
	
	private JLabel libroDaAggiungereLabel;
	private JTextField libroDaAggiungereField;
	private JButton aggiungiButton, totaleButton;
	
	public Carrello() {
		libroDaAggiungereLabel = new JLabel("Aggiungi Libro(ID)");
		libroDaAggiungereField = new JTextField();
		
		aggiungiButton = new JButton("Aggiungi al carrello");
		totaleButton = new JButton("Visualizza Somma");
		
		JTextArea area = new JTextArea();
		JTextArea area2 = new JTextArea();
		
		JPanel panel = new JPanel(new GridLayout(6,4));
		
		setTitle("Carrello");
		setSize(500,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.add(libroDaAggiungereLabel);
		panel.add(libroDaAggiungereField);
		panel.add(aggiungiButton);
		panel.add(totaleButton);
		panel.add(area);
		panel.add(area2);
		
		aggiungiButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Connection conn = null;
		        try {
		            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

		            Statement stmt = conn.createStatement();
		            int idLibro = Integer.parseInt(libroDaAggiungereField.getText());
		            String sql = "SELECT titolo, prezzo FROM libri WHERE id=" + idLibro;

		            ResultSet rs = stmt.executeQuery(sql);
		            
		            if (rs.next()) {
		                String titolo = rs.getString("titolo");
		                int prezzo = rs.getInt("prezzo");
		                libri.add(prezzo);
		                area.append("Titolo: " + titolo + ",Prezzo: €" + prezzo + "\n");
		                boolean libroTrovato = false;
		            } else {
		                area.append("Nessun libro trovato con l'id " + idLibro + "\n");
		                area.append("Nessun prezzo trovato per il libro con l'id" + idLibro + "\n");
		            }

		            rs.close();
		            stmt.close();
		            conn.close();

		            

		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		    }
		});

		
		totaleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					int somma = 0;
					for(int libro: libri) {
						somma += libro;
					}
					area2.append(somma + " euro + IVA");
					
				}catch(Exception e3) {
					e3.printStackTrace();
				}
			}
		}
				);
		
		add(panel);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Carrello();
	}
	
}

