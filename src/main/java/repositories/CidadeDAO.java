package repositories;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import interfaces.CrudInterface;
import models.Cidade;
import utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAO implements CrudInterface<Cidade> {

    private static final String FIND_ALL = "SELECT * FROM CIDADE";
    private static final String FIND_BY_ID = "SELECT * FROM CIDADE WHERE ID = ?";
    private static final String INSERT = "INSERT INTO CIDADE (ID, NOME, ESTADO_ID, RA) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE CIDADE SET NOME = ?, ESTADO_ID = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM CIDADE WHERE ID = ?";

    @Override
    public List<Cidade> findAll() throws SQLException {
        ArrayList<Cidade> cidades = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setId(rs.getInt("ID"));
                cidade.setNome(rs.getString("NOME"));
                cidade.setRegistroAcademico(rs.getString("RA"));
                cidade.setEstado(new EstadoDAO().findById(rs.getInt("ESTADO_ID")));

                cidades.add(cidade);
            }
        } finally {
            if (rs != null)
                rs.close();

            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }

        return cidades;
    }

    @Override
    public Cidade findById(int id) throws SQLException {
        Cidade cidade = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                cidade = new Cidade();
                cidade.setId(rs.getInt("ID"));
                cidade.setNome(rs.getString("NOME"));
                cidade.setRegistroAcademico(rs.getString("RA"));
                cidade.setEstado(new EstadoDAO().findById(rs.getInt("ESTADO_ID")));
            }
        } finally {
            if (rs != null)
                rs.close();

            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }

        return cidade;
    }

    @Override
    public int insert(Cidade cidade) throws SQLException {
        int id = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, cidade.getId());
            pstmt.setString(2, cidade.getNome());
            pstmt.setInt(3, cidade.getEstado().getId());
            pstmt.setString(4, cidade.getRegistroAcademico());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            rs.next();
            id = rs.getInt("ID");
        } finally {
            if (rs != null)
                rs.close();

            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }

        return id;
    }

    @Override
    public void update(Cidade cidade) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setString(1, cidade.getNome());
            pstmt.setInt(2, cidade.getEstado().getId());
            pstmt.setInt(3, cidade.getId());

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
            pstmt = conn.prepareStatement(DELETE);
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
