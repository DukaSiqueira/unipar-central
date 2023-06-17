package repositories;

import enums.TipoContaEnum;
import models.Conta;
import utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {
    private static final String INSERT = "INSERT INTO CONTA(NUMERO, DIGITO, SALDO, RA, TIPO, AGENCIA, PESSOA) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_ALL = "SELECT ID, NUMERO, DIGITO, SALDO, TIPO, AGENCIA, PESSOA, RA FROM CONTA";
    private static final String FIND_BY_ID = "SELECT ID, NUMERO, DIGITO, SALDO, TIPO, AGENCIA, PESSOA, RA FROM CONTA WHERE ID = ?";
    private static final String DELETE_BY_ID = "DELETE FROM CONTA WHERE ID = ?";
    private static final String UPDATE = "UPDATE CONTA SET NUMERO = ?, DIGITO = ?, SALDO = ?, TIPO = ?, AGENCIA = ?, PESSOA = ?, RA = ? WHERE ID = ?";

    public List<Conta> findAll() throws SQLException {
        ArrayList<Conta> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        
     
        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Conta conta = new Conta();
                conta.setId(rs.getInt("ID"));
                conta.setNumero(rs.getString("NUMERO"));
                conta.setDigito(rs.getString("DIGITO"));
                conta.setSaldo(rs.getDouble("SALDO"));

                int tipoContaId = rs.getInt("TIPO");
                TipoContaEnum tipoConta;
                if (tipoContaId == 1) {
                    tipoConta = TipoContaEnum.POUPANCA;
                } else if (tipoContaId == 2) {
                    tipoConta = TipoContaEnum.CORRENTE;
                } else if (tipoContaId == 3) {
                    tipoConta = TipoContaEnum.CONTASALARIO;
                } else {
                    tipoConta = TipoContaEnum.TIPO_DESCONHECIDO;
                }
                conta.setTipoConta(tipoConta);

                conta.setAgencia(new AgenciaDAO().FIND_BY_ID(rs.getInt("AGENCIA_ID")));
                conta.setPessoa(new PessoaDAO().findById(rs.getInt("PESSOA_ID")));

                conta.setRegistroAcademico(rs.getString("RA"));

                retorno.add(conta);
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

    public Conta findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Conta retorno = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                retorno = new Conta();
                retorno.setId(rs.getInt("ID"));
                retorno.setNumero(rs.getString("NUMERO"));
                retorno.setDigito(rs.getString("DIGITO"));
                retorno.setSaldo(rs.getDouble("SALDO"));

                int tipoContaId = rs.getInt("TIPO");
                TipoContaEnum tipoConta;
                if (tipoContaId == 1) {
                    tipoConta = TipoContaEnum.POUPANCA;
                } else if (tipoContaId == 2) {
                    tipoConta = TipoContaEnum.CORRENTE;
                } else if (tipoContaId == 3) {
                    tipoConta = TipoContaEnum.CONTASALARIO;
                } else {
                   tipoConta = TipoContaEnum.TIPO_DESCONHECIDO;
                }
                retorno.setTipoConta(tipoConta);

                retorno.setAgencia(new AgenciaDAO().FIND_BY_ID(rs.getInt("AGENCIA_ID")));
                retorno.setPessoa(new PessoaDAO().findById(rs.getInt("PESSOA_ID")));

                retorno.setRegistroAcademico(rs.getString("RA"));
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
     

   

    public void insert(Conta conta) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(INSERT);
            pstmt.setString(1, conta.getNumero());
            pstmt.setString(2, conta.getDigito());
            pstmt.setDouble(3, conta.getSaldo());

            pstmt.setString(4, conta.getTipoConta().toString());
            pstmt.setInt(5, conta.getAgencia().getId());
            pstmt.setInt(6, conta.getPessoa().getId());
            pstmt.setString(7, conta.getRegistroAcademico());


            pstmt.setInt(4, conta.getTipoConta().getId());
            pstmt.setString(5, conta.getRegistroAcademico());
            pstmt.setInt(6, conta.getAgencia().getId());
            pstmt.setInt(7, conta.getPessoa().getId());

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

    public void update(Conta conta) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setString(1, conta.getNumero());
            pstmt.setString(2, conta.getDigito());
            pstmt.setDouble(3, conta.getSaldo());

            pstmt.setString(4, conta.getTipoConta().toString());
            pstmt.setInt(5, conta.getAgencia().getId());
            pstmt.setInt(6, conta.getPessoa().getId());
            pstmt.setString(7, conta.getRegistroAcademico());
            pstmt.setInt(8, conta.getId());

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

    public void deleteConta(int id) throws SQLException {
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
