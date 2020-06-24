package br.mack.ps2.dao;

import br.mack.ps2.api.Covid19;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Covid19DAOMySQL implements Covid19Dao {
    private String createSQL = "INSERT INTO covid19 (date, value) VALUES (?,?)";
    private String readSQL = "SELECT * FROM covid19";
    private String updateSQL= "UPDATE covid19 SET date = ?, value = ? WHERE id = ?";
    private String deleteSQL = "DELETE FROM covid19 WHERE id = ?";

    private final MySQLConnection mysql = new MySQLConnection();


    @Override
    public boolean create(Covid19 covid19) {
        Connection conexao = mysql.getConnection();
        try{
            PreparedStatement stm = conexao.prepareStatement(createSQL);

            stm.setDate(1,new java.sql.Date(covid19.getDate().getTime()));
            stm.setInt(2, covid19.getValue());
            int registro =stm.executeUpdate();
            return(registro>0);
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        finally {
            try{
                conexao.close();
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Covid19> read() {
        Connection conexao = mysql.getConnection();

        List<Covid19> covid19= new ArrayList();
        try {
            PreparedStatement stm = conexao.prepareStatement(readSQL);
            ResultSet registro = stm.executeQuery();
            while (registro.next()) {
                Covid19 corona = new Covid19();
                corona.setId(registro.getInt("id"));
                corona.setDate(registro.getDate("date"));
                corona.setValue(registro.getInt("value"));

                covid19.add(corona);
            }
            return covid19;

        } catch (final SQLException throwables) {
            throwables.printStackTrace();
        } catch (final Exception ex){
            ex.printStackTrace();
        }
        finally {
            try {
                conexao.close();
            } catch (final SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return covid19;

    }

    @Override
    public boolean update(Covid19 covid19) {
        Connection conexao = mysql.getConnection();
        int registros = -1;
        try {
            PreparedStatement stm = conexao.prepareStatement(updateSQL);

            stm.setDate(1,new java.sql.Date(covid19.getDate().getTime()));
            stm.setInt(2, covid19.getValue());
            stm.setInt(3, covid19.getId());

            registros = stm.executeUpdate();



        } catch (final SQLException e){
            System.out.println("Falha de conexão com a base de dados!");
            e.printStackTrace();
        } finally {
            try{
                conexao.close();
            } catch (final Exception e){
                e.printStackTrace();
            }
        }
        return registros > 0;


    }

    @Override
    public boolean delete(Covid19 covid19) {
        Connection conexao = mysql.getConnection();
        try{
            PreparedStatement stm = conexao.prepareStatement(deleteSQL);
            stm.setInt(1, covid19.getId());
            int registros = stm.executeUpdate();
            return registros > 0;

        } catch (SQLException e){
            System.out.println("Falha de conexão com a base de dados!");
            e.printStackTrace();
        } catch(final Exception e){
            e.printStackTrace();
        } finally {
            try{
                conexao.close();
            } catch (final Exception e){
                e.printStackTrace();
            }
        }

        return false;


    }
}
