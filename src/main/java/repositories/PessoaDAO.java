package repositories;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import interfaces.CrudInterface;
import models.Estado;
import models.Pessoa;
import utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO implements CrudInterface<Pessoa> {

    private static final String FIND_ALL = "SELECT * FROM PESSOA";

    private static final String FIND_BY_ID = "SELECT * FROM PESSOA WHERE ID = ?";

    private static final String INSERT = "INSERT INTO PESSOA(ID, EMAIL, RA)" +
            "VALUES (?, ?, ?)";

    private static final String UPDATE = "UPDATE PESSOA SET EMAIL = ?," +
            "RA = ? WHERE ID = ?";

    private static  final String DELETE_BY_ID = "DELETE FROM PESSOA WHERE ID = ?";

    @Override
    public List<Pessoa> findAll() throws SQLException {
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("ID"));
                pessoa.setEmail(rs.getString("EMAIL"));
                pessoa.setRegistroAcademico(rs.getString("RA"));

                pessoas.add(pessoa);
            }
        } finally {
            if (rs != null)
                rs.close();

            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }

        return pessoas;
    }

    @Override
    public Pessoa findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Pessoa pessoa = null;

        try {

            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setId(rs.getInt("ID"));
                pessoa.setEmail(rs.getString("EMAIL"));
                pessoa.setRegistroAcademico(rs.getString("RA"));
            }
        } finally {
            if (rs != null)
                rs.close();

            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }

        return pessoa;
    }

    @Override
    public int insert(Pessoa pessoa) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int id = 0;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, pessoa.getId());
            pstmt.setString(2, pessoa.getEmail());
            pstmt.setString(3, pessoa.getRegistroAcademico());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } finally {
            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }

        return id;
    }

    @Override
    public void update(Pessoa pessoaDAO) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setString(1, pessoaDAO.getEmail());
            pstmt.setString(2, pessoaDAO.getRegistroAcademico());
            pstmt.setInt(3, pessoaDAO.getId());
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(DELETE_BY_ID);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }
    }
}
