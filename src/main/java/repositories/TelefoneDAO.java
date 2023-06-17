package repositories;

import enums.OperadoraEnum;
import models.Telefone;
import utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelefoneDAO {
    private static final String INSERT = "INSERT INTO TELEFONE (NUMERO, OPERADORA, RA, AGENCIA_ID, PESSOA_ID)" +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT ID, NUMERO, OPERADORA, RA, AGENCIA_ID, PESSOA_ID FROM TELEFONE WHERE ID = ?";
    private static final String FIND_BY_PESSOA = "SELECT TELEFONE.ID," +
                                                       " TELEFONE.NUMERO," +
                                                       " TELEFONE.OPERADORA," +
                                                       " TELEFONE.RA," +
                                                    " FROM TELEFONE," +
                                                         " PESSOA" +
                                                    "WHERE TELEFONE.PESSOA_ID = PESSOA.ID" +
                                                    "  AND PESSOA.ID = ?";
    private static final String FIND_BY_AGENCIA = "SELECT TELEFONE.ID," +
                                                " TELEFONE.NUMERO," +
                                                " TELEFONE.OPERADORA," +
                                                " TELEFONE.RA," +
                                                " FROM TELEFONE, AGENCIA" +
                                                "WHERE TELEFONE.AGENCIA_ID = AGENCIA.ID" +
                                                "  AND AGENCIA.ID = ?";

    private static final String DELETE_BY_ID = "DELETE FROM TELEFONE WHERE ID = ?";
    private static final String UPDATE = "UPDATE TELEFONE SET NUMERO = ?, OPERADORA = ?, RA = ?, AGENCIA_ID = ?, PESSOA_ID = ?  WHERE ID = ?";


    public Telefone findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Telefone retorno = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();

            while(rs.next()){
                retorno = new Telefone();
                retorno.setId(rs.getInt("ID"));
                retorno.setNumero(rs.getString("NUMERO"));
                retorno.setRegistroAcademico(rs.getString("RA"));
                if (rs.getInt("OPERADORA") == 15){
                    retorno.setOperadoraEnum(OperadoraEnum.VIVO);
                } else if (rs.getInt("OPERADORA") == 21) {
                    retorno.setOperadoraEnum(OperadoraEnum.CLARO);
                } else if (rs.getInt("OPERADORA") == 41) {
                    retorno.setOperadoraEnum(OperadoraEnum.TIM);
                } else if (rs.getInt("OPERADORA") == 12) {
                    retorno.setOperadoraEnum(OperadoraEnum.ALGAR);
                } else if (rs.getInt("OPERADORA") == 43) {
                    retorno.setOperadoraEnum(OperadoraEnum.SERCOMTEL);
                } else if (rs.getInt("OPERADORA") == 10) {
                    retorno.setOperadoraEnum(OperadoraEnum.GENTE);
                } else if (rs.getInt("OPERADORA") == 17) {
                    retorno.setOperadoraEnum(OperadoraEnum.TRANSIT);
                } else if (rs.getInt("OPERADORA") == 64) {
                    retorno.setOperadoraEnum(OperadoraEnum.NEOTELECOM);
                }

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
        return retorno;
    }

    public List<Telefone> findByPessoaAgencia(int idPessoa, int idAgencia) throws SQLException {
        ArrayList<Telefone> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = new DatabaseUtils().getConnection();

            if (idPessoa != 0) {
                pstmt = conn.prepareStatement(FIND_BY_PESSOA);
                pstmt.setInt(1, idPessoa);
            }
            if (idAgencia != 0){
                pstmt = conn.prepareStatement(FIND_BY_AGENCIA);
                pstmt.setInt(1, idAgencia);
            }
            rs = pstmt.executeQuery();
            while(rs.next()){
                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("ID"));
                telefone.setNumero(rs.getString("NUMERO"));
                telefone.setRegistroAcademico(rs.getString("RA"));
                if (rs.getInt("OPERADORA") == 15){
                    telefone.setOperadoraEnum(OperadoraEnum.VIVO);
                } else if (rs.getInt("OPERADORA") == 21) {
                    telefone.setOperadoraEnum(OperadoraEnum.CLARO);
                } else if (rs.getInt("OPERADORA") == 41) {
                    telefone.setOperadoraEnum(OperadoraEnum.TIM);
                } else if (rs.getInt("OPERADORA") == 12) {
                    telefone.setOperadoraEnum(OperadoraEnum.ALGAR);
                } else if (rs.getInt("OPERADORA") == 43) {
                    telefone.setOperadoraEnum(OperadoraEnum.SERCOMTEL);
                } else if (rs.getInt("OPERADORA") == 10) {
                    telefone.setOperadoraEnum(OperadoraEnum.GENTE);
                } else if (rs.getInt("OPERADORA") == 17) {
                    telefone.setOperadoraEnum(OperadoraEnum.TRANSIT);
                } else if (rs.getInt("OPERADORA") == 64) {
                    telefone.setOperadoraEnum(OperadoraEnum.NEOTELECOM);
                }
                retorno.add(telefone);
            }
        }finally {
            if(rs != null){
                rs.close();
            }

            if(pstmt != null){
                pstmt.close();
            }

            if(conn != null){
                conn.close();
            }
        }
        return retorno;
    }



    public void insert(Telefone telefone, int idPessoa, int idAgencia) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(INSERT);
            pstmt.setString(1, telefone.getNumero());
            pstmt.setInt(2, telefone.getOperadoraEnum().getCodigo());
            pstmt.setString(3, telefone.getRegistroAcademico());
            if (idAgencia != 0 ) {
                pstmt.setInt(4, idAgencia);
                pstmt.setNull(5, Types.INTEGER);
            }
            if (idPessoa != 0){
                pstmt.setNull(4, Types.INTEGER);
                pstmt.setInt(5, idPessoa);
            }
            pstmt.executeUpdate();

        } finally {

            if (pstmt != null) {
                pstmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }

    public void update(Telefone telefone, int idPessoa, int idAgencia) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setString(1, telefone.getNumero());
            pstmt.setInt(2, telefone.getOperadoraEnum().getCodigo());
            pstmt.setString(3, telefone.getRegistroAcademico());
            if (idAgencia != 0 ) {
                pstmt.setInt(4, idAgencia);
                pstmt.setNull(5, Types.INTEGER);
            }
            if (idPessoa != 0){
                pstmt.setNull(4, Types.INTEGER);
                pstmt.setInt(5, idPessoa);
            }
            pstmt.executeUpdate();


        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }

    public void delete(int id) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(DELETE_BY_ID);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }
}
