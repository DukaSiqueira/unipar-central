package repositories;

import interfaces.CrudInterface;
import models.Endereco;
import utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO implements CrudInterface<Endereco> {
    private static final String INSERT = "INSERT INTO ENDERECO ("
            + "LOGRADOURO, NUMERO, BAIRRO, CEP, COMPLEMENTO, RA, PESSOA_ID, CIDADE_ID)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_ALL = "SELECT ID, LOGRADOURO, NUMERO, BAIRRO, "
            + "CEP, COMPLEMENTO, RA, PESSOA_ID, CIDADE_ID"
            + "FROM ENDERECO";

    private static final String FIND_BY_ID = "SELECT LOGRADOURO, NUMERO, BAIRRO, "
            + "CEP, COMPLEMENTO, RA, PESSOA_ID, CIDADE_ID"
            + "FROM ENDERECO"
            + "WHERE ID = ?, LOGRADOURO = ? AND NUMERO = ? AND BAIRRO = ? AND "
            + "CEP = ? AND COMPLEMENTO = ? AND RA = ? AND "
            + "PESSOA_ID = ? AND CIDADE_ID = ?";

    private static final String DELETE_BY_ID = "DELETE FROM ENDERECO WHERE ID, ? AND LOGRADOURO = ? "
            + "AND NUMERO = ? AND BAIRRO = ? AND "
            + "CEP = ? AND COMPLEMENTO = ? AND RA = ? AND "
            + "PESSOA_ID = ? AND CIDADE_ID = ?";

    private static final String UPDATE = "UPDATE ENDERECO SET LOGRADOURO = ?, NUMERO = ?, BAIRRO = ?AND "
            + "CEP = ? AND COMPLEMENTO = ? AND RA = ? AND "
            + "PESSOA_ID = ? AND CIDADE_ID = ?"
            + "WHERE ID = ? AND LOGRADOURO = ? "
            + "AND NUMERO = ? AND BAIRRO = ? AND "
            + "CEP = ? AND COMPLEMENTO = ? AND RA = ? " +
            " AND CIDADE_ID = ?";

    @Override
    public List<Endereco> findAll() throws SQLException{
        ArrayList<Endereco> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while(rs.next()){
                Endereco endereco = new Endereco();
                endereco.setLogradouro(rs.getString("LOGRADOURO"));
                endereco.setNumero(rs.getString("NUMERO"));
                endereco.setBairro(rs.getString("BAIRRO"));
                endereco.setCep(rs.getString("CEP"));
                endereco.setComplemento(rs.getString("COMPLEMENTO"));
                endereco.setRegistroAcademico(rs.getString("RA"));
                endereco.setPessoa(new PessoaDAO().findById(rs.getInt("PESSOA_ID")));
                endereco.setCidade(new CidadeDAO().findById(rs.getInt("CIDADE_ID")));
                retorno.add(endereco);
            }

        } finally {
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

    @Override
    public Endereco findById(int id) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Endereco endereco = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while(rs.next()){
                endereco = new Endereco();
                endereco.setLogradouro(rs.getString("LOGRADOURO"));
                endereco.setNumero(rs.getString("NUMERO"));
                endereco.setBairro(rs.getString("BAIRRO"));
                endereco.setCep(rs.getString("CEP"));
                endereco.setComplemento(rs.getString("COMPLEMENTO"));
                endereco.setRegistroAcademico(rs.getString("RA"));
                endereco.setPessoa(new PessoaDAO().findById(rs.getInt("PESSOA_ID")));
                endereco.setCidade(new CidadeDAO().findById(rs.getInt("CIDADE_ID")));

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
        return endereco;
    }

    @Override
    public int insert(Endereco endereco) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        int id = 0;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, endereco.getLogradouro());
            pstmt.setString(2, endereco.getNumero());
            pstmt.setString(3, endereco.getBairro());
            pstmt.setString(4, endereco.getCep());
            pstmt.setString(5, endereco.getComplemento());
            pstmt.setString(6 ,endereco.getRegistroAcademico());
            pstmt.setInt(7 , endereco.getPessoa().getId());
            pstmt.setInt(8, endereco.getCidade().getId());
            pstmt.executeUpdate();

            id = pstmt.getGeneratedKeys().getInt(1);

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
        return id;
    }


    @Override
    public void update(Endereco endereco) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setString(1, endereco.getLogradouro());
            pstmt.setString(2, endereco.getNumero());
            pstmt.setString(3, endereco.getBairro());
            pstmt.setString(4, endereco.getCep());
            pstmt.setString(5, endereco.getComplemento());
            pstmt.setString(6 ,endereco.getRegistroAcademico());
            pstmt.setInt(7, endereco.getCidade().getId());
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

    @Override
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
