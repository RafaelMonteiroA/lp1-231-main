package semana24;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CardápioDAO {

    public Cardápio create(Cardápio cardapio) throws SQLException {
        String sql = """
            INSERT INTO Cardápio (id, preco, sabor_pizza)
            VALUES (?, ?, ?);
        """;
        
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
                .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            
            statement.setDouble(1, cardapio.getId());
            statement.setString(2, cardapio.getPreco());
            statement.setString(3, cardapio.getsaborPizza());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                cardapio.setId(rs.getInt(1));
            }

            rs.close();

            return cardapio;
            
        }
        
    }

    public Cardápio update(Cardápio cardapio) throws SQLException {
        String sql = """
            UPDATE Cardápio
            SET preco = ?, sabor_pizza = ?
            WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, cardapio.getPreco());
            statement.setString(2, cardapio.getsaborPizza());
            statement.setInt(3, cardapio.getId());
            
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return cardapio;
            }
            
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM Cardápio WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Cardápio cardapio) {
        delete(cardapio.getId());
    }

    public Cardápio findById(Integer id) {
        String sql = "SELECT * FROM Cardápio WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToCardápio(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public List<Cardápio> findAll() throws SQLException {
        String sql = "SELECT * FROM Cardápio;";
        List<Cardápio> cardapios = new ArrayList<>();

        try (
            Connection connection = Conexao.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while(rs.next()) {
                cardapios.add(resultSetToCardápio(rs));
            }

            return cardapios;
        
        }
        
    }

    private Cardápio resultSetToCardápio(ResultSet rs) throws SQLException {
        return new Cardápio(
            rs.getInt("id"),
            rs.getString("preco"),
            rs.getString("sabor_pizza")
        );
    }
}