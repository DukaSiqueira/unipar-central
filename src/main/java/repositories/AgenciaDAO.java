package repositories;

import models.Agencia;
import utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgenciaDAO {
    private static final String INSERT = "INSERT INTO (CODIGO, DIGITO, RAZAOSOCIAL, CNPJ, RA, BANCO_ID) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String FIND_ALL = "SELECT ID, CODIGO, DIGITO, RAZAOSOCIAL, CNPJ, RA, BANCO_ID FROM AGENCIA";

    private static final String FIND_BY_ID = "SELECT ID, CODIGO, DIGITO, RAZAOSOCIAL, CNPJ, RA, BANCO_ID FROM AGENCIA " +
            "WHERE ID = ?";

    private static final String DELETE_BY_ID = "DELETE AGENCIA WHERE ID = ?";

    private static final String UPDATE = "UPDATE AGENCIA SET CODIGO = ?, DIGITO = ?, RAZAOSOCIAL = ?, CNPJ = ?, RA = ?, BANCO_ID = ? WHERE ID = ?";

    private static final String FIND_EXISTE = "SELECT COUNT(*) FROM banco WHERE id = ?";

    public void insert(Agencia agencia, int idBanco) throws SQLException{

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            if (agencia.getId() != 0){
                conn =  new DatabaseUtils().getConnection();
                pstm = conn.prepareStatement(INSERT);
                pstm.setString(1, agencia.getCodigo());
                pstm.setString(2, agencia.getDigito());
                pstm.setString(3, agencia.getRazaoSocial());
                pstm.setString(4, agencia.getCnpj());
                pstm.setString(5, agencia.getRegistroAcademico());
                pstm.setInt(6, idBanco);
            }
        } finally {
            if (conn != null){
                conn.close();
            }
            if (pstm != null){
                pstm.close();
            }
        }
    }

    public List<Agencia> FIND_ALL() throws SQLException{

        List<Agencia> agencias = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Agencia agencia = null;

        try{
            conn = new DatabaseUtils().getConnection();
            pstm = conn.prepareStatement(FIND_ALL);
            rs = pstm.executeQuery();
            while(rs.next()){
                agencia = new Agencia();
                agencia.setId(rs.getInt("ID"));
                agencia.setCodigo(rs.getString("CODIGO"));
                agencia.setDigito(rs.getString("DIGITO"));
                agencia.setRazaoSocial(rs.getString("RAZAOSOCIAL"));
                agencia.setCnpj(rs.getString("CNPJ"));
                agencia.setRegistroAcademico(rs.getString("RA"));
                agencia.setBanco(rs.getInt("BANCO_ID"));
                agencias.add(agencia);
            }
        } finally {
            if (conn != null){
                conn.close();
            }
            if (pstm != null){
                pstm.close();
            }
            if (rs != null){
                rs.close();
            }
        }
        return agencias;
    }

    public Agencia FIND_BY_ID(int id) throws SQLException{

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Agencia agencia = null;

        try{
            conn = new DatabaseUtils().getConnection();
            pstm = conn.prepareStatement(FIND_BY_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while(rs.next()){
                agencia = new Agencia();
                agencia.setId(rs.getInt("ID"));
                agencia.setCodigo(rs.getString("CODIGO"));
                agencia.setDigito(rs.getString("DIGITO"));
                agencia.setRazaoSocial(rs.getString("RAZAOSOCIAL"));
                agencia.setCnpj(rs.getString("CNPJ"));
                agencia.setRegistroAcademico(rs.getString("RA"));
                agencia.setBanco(rs.getInt("BANCO_ID"));
            }
        } finally {
            if (conn != null){
                conn.close();
            }
            if (pstm != null){
                pstm.close();
            }
            if (rs != null){
                rs.close();
            }
        }
        return agencia;
    }

    public void delete (int id) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try{
            conn = new DatabaseUtils().getConnection();
            pstm = conn.prepareStatement(DELETE_BY_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
        } finally {

            if (conn != null){
                conn.close();
            }
            if (pstm != null){
                pstm.close();
            }
        }

    }

    public void update(Agencia agencia, int idBanco) throws SQLException{
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn =  new DatabaseUtils().getConnection();
            pstm = conn.prepareStatement(UPDATE);
            pstm.setString(1, agencia.getCodigo());
            pstm.setString(2, agencia.getDigito());
            pstm.setString(3, agencia.getRazaoSocial());
            pstm.setString(4, agencia.getCnpj());
            pstm.setString(5, agencia.getRegistroAcademico());
            pstm.setInt(6, idBanco);
            pstm.executeUpdate();

        } finally {
            if (pstm != null) {
                pstm.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }

    public int findExiste(long id) throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_EXISTE);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return count;
    }

}
