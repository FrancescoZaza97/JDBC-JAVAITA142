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


    public void update(Quotidiani quotidiani ) throws SQLException {
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

    public void insert(Quotidiani quotidiani) throws SQLException {
        String sql = "INSERT INTO quotidiani(nome,prezzo,aggio,cricevute,cvendute) VALUES (?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, quotidiani.getNome());
            stmt.setDouble(2, quotidiani.getPrezzo());
            stmt.setInt(3, quotidiani.getAggio());
            stmt.setInt(4,  quotidiani.getCricevute());
            stmt.setInt(5,  quotidiani.getCvendute());
            stmt.executeUpdate();
        }
    }

    public void getQuotidianiVenduti(Quotidiani quotidiani) throws SQLException{
        String sql = "SELECT nome,cvendute FROM quotidiani";
        try (Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, quotidiani.getNome());
                stmt.setInt(2,  quotidiani.getCvendute());
                stmt.executeUpdate();
            }

    }

    
    public void getQuotidianiRicevuti(Quotidiani quotidiani) throws SQLException{
        String sql = "SELECT nome,cricevute FROM quotidiani";
        try (Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, quotidiani.getNome());
                stmt.setInt(2,  quotidiani.getCricevute());
                stmt.executeUpdate();
            }

    }
}


