package net.codejava;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {
	private JLabel userLabel, passLabel;
	private JTextField userField;
	private JPasswordField passField;
	private JButton loginButton;
	
	
	public Login() {
		
		super("Controllo di accesso"); // TITOLO DELLA FINESTRA
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // TERMINA IL PROGRAMMA QUANDO LA FINESTRA VIENE CHIUSA
		setSize(400, 150); // DIMENSIONI DELLA FINESTRA
		setLayout(new GridLayout(3, 2)); // LAYOUT DELLA FINESTRA
		// CREA GLI ELEMENTI DELLA FINESTRA
		userLabel = new JLabel("Nome utente:");
		passLabel = new JLabel("Password:");
		userField = new JTextField();
		passField = new JPasswordField();
		loginButton = new JButton("Login");
		loginButton.addActionListener(this);
		// AGGIUNGE GLI ELEMENTI ALLA FINESTRA
		add(userLabel);
		add(userField);
		add(passLabel);
		add(passField);
		add(new JLabel(""));
		add(loginButton);
		setVisible(true); //MOSTRA LA FINESTRA
	}
	
	
	public void actionPerformed(ActionEvent e) {
		String user = userField.getText(); // OTTIENE IL NOME UTENTE INSERITO DALL'UTENTE
		String pass = new String(passField.getPassword()); // OTTIENE LA PASSWORD INSERITA DALL'UTENTEE
		
		
		try {
			// CARICA IL FILE JDBC PER IL DATABASE MySQL
			Class.forName("com.mysql.jdbc.Driver");
			// CONNETTE AL DATABASE DI MySQL
			Connection conn =
					DriverManager.getConnection("jdbc:mysql://localhost:3306/GestioneLibri",
							"root", "Shake411+");
			
			
			// CREA UN OGGETTO STATEMENT per ESEGUIRE LE QUERY SUL DATABASE
			Statement stmt = conn.createStatement();
			
			
			// ESEGUE LA QUERY PER SELEZIONARE L'UTENTE CON IL NOME UTENTE E LA PASSWORD INSERITI
			ResultSet rs = stmt.executeQuery("SELECT * FROM accesso WHERE nome='"
					+ user + "' AND password='" + pass + "'");
			
			
			// VERIFICA SE L'UTENTE ESISTE NEL DATABASE
			if (rs.next()) {
				JOptionPane.showMessageDialog(this, "Accesso consentito", "Login",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Nome utente o password errati", "Login",
						JOptionPane.ERROR_MESSAGE);
			}
			// CHIUDE LA CONNESSIONE AL DATABASE
			conn.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Errore di connessione al database", "Login",
					JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
		
			new Biblioteca();
		
		
	}
	
	
	public static void main(String[] args) {
		new Login(); //CREA UNA ISTANZA DELLA CLASSE Login PER AVVIARE L'APPLICAZIONE
	}
}