package dao;

import bean.Quotidiani;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuotidianiDAO {
    private final String url = "jdbc:mysql://localhost:3306/edicola";
    private final String user = "root";
    private final String password = "";

    public List<Quotidiani> selectAll() throws SQLException {
        List<Quotidiani> quotidiani = new ArrayList<>();
        String sql = "SELECT * FROM quotidiani";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                quotidiani.add(new Quotidiani(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("prezzo"),
                        rs.getInt("aggio"),
                        rs.getInt("cricevute"),
                        rs.getInt("cvendute")
                ));
            }
        }
        return quotidiani;
    }

    public Quotidiani selectQuotidianiById(int id) throws SQLException {
    String sql = "SELECT * FROM quotidiani WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); 

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Quotidiani(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("prezzo"),
                        rs.getInt("aggio"),
                        rs.getInt("cricevute"),
                        rs.getInt("cvendute")
                    );
                }
            }
        }

        return null; 
    }

    
    public void delete(int id) throws SQLException {

        String sql = "DELETE FROM quotidiani WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }


    public void updateAll(Quotidiani quotidiani ) throws SQLException {
        String sql = "UPDATE quotidiani SET nome=?,prezzo=?,aggio=?,cricevute=?,cvendute=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            
            stmt.setString(1, quotidiani.getNome());
            stmt.setDouble(2, quotidiani.getPrezzo());
            stmt.setInt(3, quotidiani.getAggio());
            stmt.setInt(4,  quotidiani.getCricevute());
            stmt.setInt(5,  quotidiani.getCvendute());
            stmt.setInt(6, quotidiani.getId());
            stmt.executeUpdate();
        }
    }

    public void insertQuotidiani(Quotidiani quotidiani) throws SQLException {
        String sql = "INSERT INTO quotidiani(nome,prezzo,aggio) VALUES (?,?,?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, quotidiani.getNome());
                stmt.setDouble(2, quotidiani.getPrezzo());
                stmt.setInt(3, quotidiani.getAggio());
                stmt.executeUpdate();
        }
    }

    public void getQuotidianiVenduti(Connection conn) throws SQLException{
        String sql = "SELECT nome,cvendute FROM quotidiani";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("Copie vendute:");
        while(rs.next()){
            System.out.println("Nome: "+ rs.getString(1));
            System.out.println("Copie Vendute: "+  rs.getInt(2));
        }
    }
    

    public void getQuotidianiRicevuti(Connection conn) throws SQLException{
        String sql = "SELECT nome,cricevute FROM quotidiani";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("Copie ricevute:");
        while(rs.next()){
            System.out.println("Nome: "+ rs.getString(1));
            System.out.println("Copie Ricevute: "+  rs.getInt(2));
        }
    }

    public void updateCopieRicevute(Quotidiani quotidiani ) throws SQLException {
        String sql = "UPDATE quotidiani SET cricevute=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, quotidiani.getCricevute());
                stmt.setInt(2, quotidiani.getId());
                stmt.executeUpdate();
            }
    }


    public void updateCopieVendute(Quotidiani quotidiani ) throws SQLException {
        String sql = "UPDATE quotidiani SET cvendute=cvendute+1 WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
                if(quotidiani.getCricevute() <= quotidiani.getCvendute() ){
                    System.out.println("non puoi vendere piu' copie di quelle nel magazzino..");
                }else{
                    stmt.setInt(1, quotidiani.getId());
                    stmt.executeUpdate();
                }
            }
    }


    public void updatePrezzoCopertina(Quotidiani quotidiani) throws SQLException{
        String sql = "UPDATE quotidiani SET prezzo=? WHERE id=?";
        try(Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql)){
                if(quotidiani.getCvendute() != 0){
                    System.out.println("non puoi modificare il prezzo di un quotidiano che stai vendendo..");
                }else{
                    stmt.setDouble(1, quotidiani.getPrezzo());
                    stmt.setInt(2, quotidiani.getId());
                    stmt.executeUpdate();
                }
            }

    }


    public void updateAggioCopertina(Quotidiani quotidiani) throws SQLException{
        String sql = "UPDATE quotidiani SET aggio=? WHERE id=?";
        try(Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql)){
                if(quotidiani.getCvendute() != 0){
                    System.out.println("non puoi modificare l'aggio di un quotidiano che stai vendendo..");
                }else{
                    stmt.setDouble(1, quotidiani.getAggio());
                    stmt.setInt(2, quotidiani.getId());
                    stmt.executeUpdate();
                }
            }

    }

    public void resetGiornaliero(Quotidiani quotidiani) throws SQLException{
        String sql = "UPDATE quotidiani SET cvendute = 0, cricevute = 0";
        try(Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, quotidiani.getCvendute());
                stmt.setInt(2, quotidiani.getCricevute());
                stmt.executeUpdate();
            }
    }

    public void getResocontoGiornaliero(Connection conn) throws SQLException{
        String sql = "SELECT nome,prezzo,aggio,cricevute,cvendute FROM quotidiani";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("Resoconto Giornaliero:");
        while(rs.next()){
            System.out.println("Nome: "+ rs.getString(1));
            System.out.println("Prezzo: "+  rs.getDouble(2));
            System.out.println("Aggio: "+ rs.getInt(3));
            System.out.println("Differenza: "+(rs.getInt(4) - rs.getInt(5)));
        }
    }
}


