// Programma per eliminare record in una tabella usando JDBC

import java.sql.*;

public class delete {

	// url che punta al database mysql
	static final String url =  "jdbc:mysql://localhost:3306/rubrica";

	public static void main(String[] args) throws ClassNotFoundException {

        try {
            // metodo usato per la registrazione del driver utilizzato
            Class.forName("com.mysql.cj.jdbc.Driver");

            // getConnection() stabilisce una connessione, passando l'url che punta al database, lo username e la password
            Connection conn = DriverManager.getConnection(url, "root", "");

            // create.Statement() crea l'oggetto responsabile dell'esecuzione delle query nella tabella
            Statement stmt = conn.createStatement();

            // executeUpdate() è usato per comandi di INSERT, UPDATE, DELETE, resistiusce il numero di righe su cui è intervenuto con successo
            int result = stmt.executeUpdate("DELETE FROM anagrafica_clienti WHERE id_anagrafica=2");

            // se result è maggiore di 0, il record è stato aggiunto, altrimenti qualcosa è andato storto
            if (result > 0) {
                System.out.println("Record eliminato correttamente");
			} else {
				System.out.println("Record non eliminato. Verificare funzione");
			}
			
            // chiusura connessione
            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}