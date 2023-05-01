package net.codejava;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class Search extends JFrame implements ActionListener{

	    private JTextField searchField;
	    private JTextArea resultArea;
	    private JButton searchButton;

	    public Search() {
	        super("Search");

	        // Crea i componenti dell'interfaccia grafica
	        searchField = new JTextField(20);
	        resultArea = new JTextArea(20, 40);
	        searchButton = new JButton("Cerca");

	        // Aggiungi i componenti al frame
	        JPanel panel = new JPanel();
	        panel.add(new JLabel("Cerca: "));
	        panel.add(searchField);
	        panel.add(searchButton);
	        JScrollPane scrollPane = new JScrollPane(resultArea);
	        add(panel, BorderLayout.NORTH);
	        add(scrollPane, BorderLayout.CENTER);

	        // Aggiungi il listener al bottone di ricerca
	        searchButton.addActionListener(this);

	        // Imposta le proprietà del frame
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        pack();
	        setLocationRelativeTo(null);
	        setVisible(true);
	    }


	    @Override
	    public void actionPerformed(ActionEvent e) {
	        // Connessione al database MySQL
	        String url = "jdbc:mysql://localhost:3306/GestioneLibri";
	        String user = "root";
	        String password = "Shake411+";
	        try (Connection conn = DriverManager.getConnection(url, user, password)) {
	            // Query di ricerca dei titoli dei libri
	            String searchQuery = "SELECT titolo FROM libri WHERE titolo LIKE ?";
	            String searchTerm = "%" + searchField.getText() + "%";
	            PreparedStatement stmt = conn.prepareStatement(searchQuery);
	            stmt.setString(1, searchTerm);
	            ResultSet rs = stmt.executeQuery();
	            
	            //query di ricerca  del prezzo dei libri
	            String searchPrezzo = "SELECT prezzo FROM libri WHERE prezzo LIKE ?";
	            String searchTerms = "%" + searchField.getText() + "%";
	            PreparedStatement stmt1 = conn.prepareStatement(searchPrezzo);
	             stmt1.setString(1, searchTerms);
	             ResultSet rs1 = stmt1.executeQuery();
	            

	            // Mostra i risultati nella JTextArea
	            resultArea.setText("");
	           while(rs.next()) {       	 
	                String title = rs.getString("titolo");               
	                resultArea.append(title + "\n");
	            } 
	            
	           
	            
	            
	           
	            
	            
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
		

