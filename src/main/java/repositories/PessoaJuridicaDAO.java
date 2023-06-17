package repositories;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import interfaces.CrudInterface;
import models.Pessoa;
import models.PessoaJuridica;
import utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PessoaJuridicaDAO implements CrudInterface<PessoaJuridica> {

    private static final String FIND_ALL = "SELECT * FROM PESSOA_JURIDICA";

    private static final String FIND_BY_ID = "SELECT * FROM PESSOAJURIDICA WHERE PESSOA_ID = ?";

    private static final String INSERT = "INSERT INTO PESSOAJURIDICA (RAZAOSOCIAL, CNPJ, CNAEPRINCIPAL, FANTASIA, PESSOA_ID)" +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE PESSOAJURIDICA SET PESSOA_ID = ?, RAZAOSOCIAL = ? , CNPJ = ?," +
            "CNAEPRINCIPAL = ?, FANTASIA = ? WHERE PESSOA_ID = ?";

    private static final String DELETE = "DELETE FROM PESSOAJURIDICA WHERE PESSOA_ID = ?";

    @Override
    public List<PessoaJuridica> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<PessoaJuridica> pessoaJuridicas = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                PessoaJuridica pessoaJuridica = new PessoaJuridica();
                pessoaJuridica.setRazaoSocial(rs.getString("RAZAOSOCIAL"));
                pessoaJuridica.setCnpj(rs.getString("CNPJ"));
                pessoaJuridica.setCnaePrincipal(rs.getString("CNAEPRINCIPAL"));
                pessoaJuridica.setNomeFantasia(rs.getString("FANTASIA"));
                pessoaJuridica.setPessoa(new PessoaDAO().findById(rs.getInt("PESSOA_ID")));

                pessoaJuridicas.add(pessoaJuridica);
            }
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return pessoaJuridicas;
    }

    @Override
    public PessoaJuridica findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PessoaJuridica pessoaJuridica = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                pessoaJuridica = new PessoaJuridica();
                pessoaJuridica.setPessoa(new PessoaDAO().findById(rs.getInt("PESSOA_ID")));
                pessoaJuridica.setRazaoSocial(rs.getString("RAZAOSOCIAL"));
                pessoaJuridica.setCnpj(rs.getString("CNPJ"));
                pessoaJuridica.setCnaePrincipal(rs.getString("CNAEPRINCIPAL"));
                pessoaJuridica.setNomeFantasia(rs.getString("FANTASIA"));

            }
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return pessoaJuridica;
    }

    @Override
    public int insert(PessoaJuridica pessoaJuridica) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int id = 0;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, pessoaJuridica.getRazaoSocial());
            pstmt.setString(2, pessoaJuridica.getCnpj());
            pstmt.setString(3, pessoaJuridica.getCnaePrincipal());
            pstmt.setString(4, pessoaJuridica.getNomeFantasia());
            pstmt.setInt(5, pessoaJuridica.getPessoa().getId());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
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

    @Override
    public void update(PessoaJuridica pessoaJuridica) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setInt(1, pessoaJuridica.getPessoa().getId());
            pstmt.setString(2, pessoaJuridica.getRazaoSocial());
            pstmt.setString(3, pessoaJuridica.getCnpj());
            pstmt.setString(4, pessoaJuridica.getCnaePrincipal());
            pstmt.setString(5, pessoaJuridica.getNomeFantasia());
            pstmt.setInt(6, pessoaJuridica.getPessoa().getId());
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
