package repositories;

import exceptions.TamanhoCampoInvalidoException;
import interfaces.CrudInterface;
import models.PessoaFisica;
import utils.DatabaseUtils;

import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO implements CrudInterface<PessoaFisica> {

    // Criar constantes para querys find_all, find_by_id, insert, update e delete
    private static final String FIND_ALL = "SELECT * FROM PESSOAFISICA";
    private static final String FIND_BY_ID = "SELECT * FROM PESSOAFISICA WHERE PESSOA_ID = ?";
    private static final String INSERT = "INSERT INTO PESSOAFISICA (NOME, CPF, RG, DATANASCIMENTO, PESSOA_ID)\n" +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE PESSOAFISICA SET NOME = ?, CPF = ?, RG = ?, DATANASCIMENTO = ?" +
            "WHERE PESSOA_ID = ?";
    private static final String DELETE = "DELETE FROM PESSOAFISICA WHERE PESSOA_ID = ?";

    @Override
    public List<PessoaFisica> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<PessoaFisica> pessoaFisicaList = new ArrayList<>();

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                PessoaFisica pessoaFisica = new PessoaFisica();
                pessoaFisica.setPessoa(new PessoaDAO().findById(rs.getInt("PESSOA_ID")));
                pessoaFisica.setNome(rs.getString("NOME"));
                pessoaFisica.setCpf(rs.getString("CPF"));
                pessoaFisica.setRg(rs.getString("RG"));
                pessoaFisica.setDataNascimento(rs.getDate("DATANASCIMENTO"));

                pessoaFisicaList.add(pessoaFisica);
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
        return pessoaFisicaList;
    }

    @Override
    public PessoaFisica findById(int id) throws TamanhoCampoInvalidoException, Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PessoaFisica pessoaFisica = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();

            while(rs.next()){
                pessoaFisica = new PessoaFisica();
                pessoaFisica.setPessoa(new PessoaDAO().findById(rs.getInt("PESSOA_ID")));
                pessoaFisica.setNome(rs.getString("NOME"));
                pessoaFisica.setCpf(rs.getString("CPF"));
                pessoaFisica.setRg(rs.getString("RG"));
                pessoaFisica.setDataNascimento(rs.getDate("DATANASCIMENTO"));
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
        return pessoaFisica;
    }

    public int insert(PessoaFisica pessoaFisica) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        int id = 0;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(INSERT,  Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, pessoaFisica.getNome());
            pstmt.setString(2, pessoaFisica.getCpf());
            pstmt.setString(3, pessoaFisica.getRg());
            pstmt.setDate(4, (Date) pessoaFisica.getDataNascimento());
            pstmt.setInt(5, pessoaFisica.getPessoa().getId());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt("ID");
            }

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

    public void update(PessoaFisica pessoaFisica) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setString(1, pessoaFisica.getNome());
            pstmt.setString(2, pessoaFisica.getCpf());
            pstmt.setString(3, pessoaFisica.getRg());
            pstmt.setDate(4, (Date) pessoaFisica.getDataNascimento());
            pstmt.setInt(5, pessoaFisica.getPessoa().getId());
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
            pstmt = conn.prepareStatement(DELETE);
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
