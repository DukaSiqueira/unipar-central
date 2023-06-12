package repositories;

import models.Pais;
import utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaisDAO {

    private static final String FIND_ALL =  "SELECT * FROM PAIS";

    private static final String INSERT = "INSERT INTO pais(id, nome, sigla, ra)" +
            "VALUES (?, ?, ?, ?)";

    public List<Pais> findAll() throws SQLException {
        ArrayList<Pais> response = new ArrayList<>();
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
                pais.setAbreviacao(rs.getString("SIGLA"));
                pais.setRegistroAcademico(rs.getString("RA"));

                response.add(pais);
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
            pstmt.setString(3, pais.getAbreviacao());
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
}