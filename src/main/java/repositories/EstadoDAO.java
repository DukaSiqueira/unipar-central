package repositories;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import interfaces.CrudInterface;
import models.Estado;
import models.Pais;
import utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO implements CrudInterface<Estado> {

    private static final String FIND_ALL = "SELECT * FROM ESTADO";

    private static final String FIND_BY_ID = "SELECT * FROM ESTADO WHERE ID = ?";

    private static final String INSERT = "INSERT INTO ESTADO(ID, NOME, SIGLA, RA, PAIS_ID)" +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE ESTADO SET NOME = ?," +
            "SIGLA = ? WHERE ID = ?";

    private static  final String DELETE_BY_ID = "DELETE FROM ESTADO WHERE ID = ?";

    @Override
    public List<Estado> findAll() throws SQLException {
        ArrayList<Estado> estados = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Estado estado = new Estado();
                estado.setId(rs.getInt("ID"));
                estado.setNome(rs.getString("NOME"));
                estado.setSigla(rs.getString("SIGLA"));
                estado.setRegistroAcademico(rs.getString("RA"));
                System.out.println("Buscar País");
                estado.setPais(new PaisDAO().findById(rs.getInt("PAIS_ID")));

                estados.add(estado);
            }
        } finally {
            if (rs != null)
                rs.close();

            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }

        return estados;
    }

    @Override
    public Estado findById(int id) throws SQLException {
        Estado estado = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                estado = new Estado();
                estado.setId(rs.getInt("ID"));
                estado.setNome(rs.getString("NOME"));
                estado.setSigla(rs.getString("SIGLA"));
                estado.setPais(new PaisDAO().findById(rs.getInt("PAIS_ID")));
                estado.setRegistroAcademico(rs.getString("RA"));
            }

        } finally {
            if (rs != null)
                rs.close();

            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }
        return estado;
    }

    @Override
    public int insert(Estado estado) throws SQLException {
        int id = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, estado.getId());
            pstmt.setString(2, estado.getNome());
            pstmt.setString(3, estado.getSigla());
            pstmt.setString(4, estado.getRegistroAcademico());
            pstmt.setInt(5, estado.getPais().getId());

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
    public void update(Estado estado) throws SQLException, CampoNaoInformadoException, EntidadeNaoInformadaException, TamanhoCampoInvalidoException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setString(1, estado.getNome());
            pstmt.setString(2, estado.getSigla());
            pstmt.setInt(3, estado.getId());
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }
    }

    @Override
    public void delete(int id) throws SQLException, CampoNaoInformadoException {
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
