package repositories;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import interfaces.CrudInterface;
import models.Pais;
import utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaisDAO implements CrudInterface<Pais> {

    private static final String FIND_ALL =  "SELECT * FROM PAIS";

    private static final String FIND_BY_ID = "SELECT * FROM PAIS WHERE ID = ?";

    private static final String INSERT = "INSERT INTO PAIS(ID, NOME, SIGLA, RA) VALUES (?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE PAIS SET NOME = ?, SIGLA = ? WHERE ID = ?";

    private static final String DELETE_BY_ID = "DELE FROM PAIS WHERE ID = ?";

    @Override
    public List<Pais> findAll() throws SQLException {
        ArrayList<Pais> paises = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            conn = new DatabaseUtils().getConnection();

            pstmt = conn.prepareStatement(FIND_ALL);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Pais pais = new Pais();
                pais.setId(rs.getInt("ID"));
                pais.setNome(rs.getString("NOME"));
                pais.setSigla(rs.getString("SIGLA"));
                pais.setRegistroAcademico(rs.getString("RA"));

                paises.add(pais);
            }

        } finally {
            if (rs != null)
                rs.close();

            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }

        return paises;
    }

    @Override
    public int insert(Pais pais) throws SQLException {
        int id = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, pais.getId());
            pstmt.setString(2, pais.getNome());
            pstmt.setString(3, pais.getSigla());
            pstmt.setString(4, pais.getRegistroAcademico());

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
    public Pais findById(int id) throws SQLException {
        Pais response = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                response = new Pais();
                response.setId(rs.getInt("ID"));
                response.setNome(rs.getString("NOME"));
                response.setSigla(rs.getString("SIGLA"));
                response.setRegistroAcademico(rs.getString("RA"));
            }

        } finally {
            if (rs != null)
                rs.close();

            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }

        return response;
    }

    @Override
    public void update(Pais pais) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setString(1, pais.getNome());
            pstmt.setString(2, pais.getSigla());
            pstmt.setInt(3, pais.getId());
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
