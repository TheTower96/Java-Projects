package net.codejava;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import javax.swing.*;



import java.io.*;
import java.sql.*;
import java.util.Properties;
import javax.swing.*;



public class Visualizza extends JFrame{
	
	

	public Visualizza() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/GestioneLibri",
					"root", "password");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT libri.id, libri.titolo, libri.prezzo FROM libri ");
			
			PrintWriter out = new PrintWriter("libri.html");
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Elenco Libri</title>");
			out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css\">");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"container\">");
			out.println("<h1 class=\"text-center\">Elenco Libri</h1>");
			out.println("<table class=\"table\">");
			out.println("<thead class=\"thead-dark\"><tr><th scope=\"col\">ID</th><th scope=\"col\">Titolo</th><th  scope=\"col\">Prezzo</th></tr></thead>");
			out.println("<tdoby>");
			while(rs.next()) {
				if(true) {
					int id = rs.getInt("libri.id");
					String titolo = rs.getString("libri.titolo");
					/*String nomeAutore = rs.getString("autori.nome");
					String cognomeAutore = rs.getString("autori.cognome");
					String nomeEditore = rs.getString("editori.nome");*/
					double prezzo = rs.getDouble("libri.prezzo");
				
				
				
				
				out.println("<tr><td>" + id + "</td><td>" + titolo + "</td><td>"   + prezzo +"</td></tr>");
				}
			}
			out.println("</tbody>");
			out.println("</table>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
			out.close();
			
			JOptionPane.showMessageDialog(null, "Tabella creata");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		

	}
	public static void main(String[] args) {
		new Visualizza();
	}
}



