package repositories;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import interfaces.CrudInterface;
import models.PessoaFisica;
import utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO implements CrudInterface<PessoaFisica> {

    private static final String FIND_ALL = "SELECT * FROM PESSOAFISICA";
    private static final String FIND_BY_ID = "SELECT * FROM PESSOAFISICA WHERE PESSOA_ID = ?";
    private static final String INSERT = "INSERT INTO PESSOAFISICA (PESSOA_ID, NOME, CPF, RG, DATANASCIMENTO)" +
            "VALUES (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE PESSOAFISICA SET PESSOA_ID = ?, NOME = ?, CPF = ?, RG = ?, DATANASCIMENTO = ?" +
            "WHERE PESSOA_ID = ?";
    private static final String DELETE = "4";

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
    public PessoaFisica findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PessoaFisica pessoaFisica = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            System.out.println("Query executada");
            if (rs.next()) {
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

    @Override
    public int insert(PessoaFisica pessoaFisica) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet  rs = null;
        int id = 0;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, (int) pessoaFisica.getPessoa().getId());
            pstmt.setString(2, pessoaFisica.getNome());
            pstmt.setString(3, pessoaFisica.getCpf());
            pstmt.setString(4, pessoaFisica.getRg());
            pstmt.setDate(5, (java.sql.Date) pessoaFisica.getDataNascimento());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
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
        return id;
    }

    @Override
    public void update(PessoaFisica pessoaFisica) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            //PESSOA_ID = ?, NOME = ?, CPF = ?, RG = ?, DATANASCIMENTO
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setInt(1, pessoaFisica.getPessoa().getId());
            pstmt.setString(2, pessoaFisica.getNome());
            pstmt.setString(3, pessoaFisica.getCpf());
            pstmt.setString(4, pessoaFisica.getRg());
            pstmt.setDate(5, (java.sql.Date) pessoaFisica.getDataNascimento());
            pstmt.setInt(6, pessoaFisica.getPessoa().getId());
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
    public void delete(int id) throws SQLException {

    }
}
