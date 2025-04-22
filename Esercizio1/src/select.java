// programma per acquisire dati da una tabella usando JDBC

import java.sql.*;

public class select {
	
	// url che punta al database mysql
    static final String url = "jdbc:mysql://localhost:3306/edicola";
	
	public static void main (String args[]) {
		
		try {
			// metodo usato per la registrazione del driver utilizzato			
			Class.forName("com.mysql.cj.jdbc.Driver");	
			
			// getConnection() stabilisce una connessione, passando l'url che punta al database, lo username e la password
			Connection conn = DriverManager.getConnection (url, "root", "");
			
			// Creiamo un oggetto Statement per poter interrogare il db
			Statement cmd = conn.createStatement();
       
			// executeQuery(String query) invia la richiesta di acquisizione al database e ne salva il return in un oggett ResultSet
			ResultSet res = cmd.executeQuery("SELECT * FROM quotidiani");
			
			// Stampiamone i risultati riga per riga
			while (res.next()) {
				System.out.println(res.getString("id"));
				System.out.println(res.getString("nome"));
				System.out.println(res.getString("prezzo"));
				System.out.println(res.getString("agio"));
				System.out.println(res.getString("cricevute"));
				System.out.println(res.getString("cvendute"));
			}					
				
			conn.close();

		}
		catch (SQLException | ClassNotFoundException e) { }
		
	}
}