package repositories;

import enums.TipoTransacaoEnum;
import models.Transacao;
import utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {

    public static final String INSERT = "INSERT INTO TRANSACAO (DATAHORA, VALOR, TIPO, RA, CONTA_ORIGEM, CONTA_DESTINO) VALUES (?, ?, ?, ?, ?, ?)";
    
    public static final String FIND_ALL = "SELECT ID, DATAHORA, VALOR, TIPO, RA, CONTA_ORIGEM, CONTA_DESTINO FROM TRANSACAO";
    public static final String FIND_BY_CONTA_ORIGEM = "SELECT ID, DATAHORA, VALOR, TIPO, RA, CONTA_ORIGEM, CONTA_DESTINO FROM TRANSACAO WHERE CONTA_ORIGEM = ?";

    private static final String FIND_BY_CONTA_DESTINO = "SELECT ID, NUMERO, DIGITO, SALDO, TIPO, AGENCIA, PESSOA, RA FROM CONTA WHERE CONTA_DESTINO = ?";

    private static final String FIND_BY_CONTA_MINHA = "SELECT ID, NUMERO, DIGITO, SALDO, TIPO, AGENCIA, PESSOA, RA FROM CONTA WHERE CONTA_ORIGEM = ? or  CONTA_DESTINO = ?";

    public void insert(Transacao transacao) throws SQLException{

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
                conn =  new DatabaseUtils().getConnection();
                pstm = conn.prepareStatement(INSERT);
                pstm.setString(1, transacao.getDataHora());
                pstm.setDouble(2, transacao.getValor());
                pstm.setInt(3, transacao.getTipoTransacao().getId());
                pstm.setString(4, transacao.getRegistroAcademico());
                pstm.setInt(5, transacao.getContaOrigem().getId());
                pstm.setInt(6, transacao.getContaDestino().getId());
        } finally {
            if (conn != null){
                conn.close();
            }
            if (pstm != null){
                pstm.close();
            }
        }
    }
    
    public List<Transacao> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Transacao retorno = null;
        List<Transacao> transacoes = new ArrayList<>();

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while(rs.next()){
                retorno = new Transacao();
                retorno.setId(rs.getInt("ID"));
                retorno.setDataHora(rs.getDate("DATAHORA"));
                retorno.setValor(rs.getDouble("VALOR"));
                if (rs.getInt("TIPO") == 1){
                    retorno.setTipoTransacao(TipoTransacaoEnum.PIX);
                } else if (rs.getInt("TIPO") == 2) {
                    retorno.setTipoTransacao(TipoTransacaoEnum.TED);
                } else if (rs.getInt("TIPO") == 3) {
                    retorno.setTipoTransacao(TipoTransacaoEnum.SAQUE);
                } else if (rs.getInt("TIPO") == 4) {
                    retorno.setTipoTransacao(TipoTransacaoEnum.DEPOSITO);
                } else {
                    retorno.setTipoTransacao(TipoTransacaoEnum.TIPO_DESCONHECIDO);
                }
                retorno.setRegistroAcademico(rs.getString("RA"));
                retorno.setContaOrigem(new ContaDAO().findById(rs.getInt("CONTA_ORIGEM")));
                retorno.setContaDestino(new ContaDAO().findById(rs.getInt(rs.getInt("CONTA_DESTINO"))));
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
        return transacoes;
    }

    public List<Transacao> findByContaOrigem(int contaOrigem) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Transacao> transacoes = new ArrayList<>();

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_CONTA_ORIGEM);
            pstmt.setInt(1,contaOrigem);
            rs = pstmt.executeQuery();

            while(rs.next()){
                Transacao retorno = new Transacao();
                retorno.setId(rs.getInt("ID"));
                retorno.setDataHora(rs.getDate("DATAHORA"));
                retorno.setValor(rs.getDouble("VALOR"));
                if (rs.getInt("TIPO") == 1){
                    retorno.setTipoTransacao(TipoTransacaoEnum.PIX);
                } else if (rs.getInt("TIPO") == 2) {
                    retorno.setTipoTransacao(TipoTransacaoEnum.TED);
                } else if (rs.getInt("TIPO") == 3) {
                    retorno.setTipoTransacao(TipoTransacaoEnum.SAQUE);
                } else if (rs.getInt("TIPO") == 4) {
                    retorno.setTipoTransacao(TipoTransacaoEnum.DEPOSITO);
                } else {
                    retorno.setTipoTransacao(TipoTransacaoEnum.TIPO_DESCONHECIDO);
                }
                retorno.setRegistroAcademico(rs.getString("RA"));
                retorno.setContaOrigem(new ContaDAO().findById(rs.getInt("CONTA_ORIGEM")));
                retorno.setContaDestino(new ContaDAO().findById(rs.getInt(rs.getInt("CONTA_DESTINO"))));
                transacoes.add(retorno);
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
        return transacoes;
    }

    public List<Transacao> findByContaDestino(int contaDestino) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Transacao retorno = null;
        List<Transacao> transacoes = new ArrayList<>();

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_CONTA_DESTINO);
            pstmt.setInt(1,contaDestino);
            rs = pstmt.executeQuery();

            while(rs.next()){
                retorno = new Transacao();
                retorno.setId(rs.getInt("ID"));
                retorno.setDataHora(rs.getDate("DATAHORA"));
                retorno.setValor(rs.getDouble("VALOR"));
                if (rs.getInt("TIPO") == 1){
                    retorno.setTipoTransacao(TipoTransacaoEnum.PIX);
                } else if (rs.getInt("TIPO") == 2) {
                    retorno.setTipoTransacao(TipoTransacaoEnum.TED);
                } else if (rs.getInt("TIPO") == 3) {
                    retorno.setTipoTransacao(TipoTransacaoEnum.SAQUE);
                } else if (rs.getInt("TIPO") == 4) {
                    retorno.setTipoTransacao(TipoTransacaoEnum.DEPOSITO);
                } else {
                    retorno.setTipoTransacao(TipoTransacaoEnum.TIPO_DESCONHECIDO);
                }
                retorno.setRegistroAcademico(rs.getString("RA"));
                retorno.setContaOrigem(new ContaDAO().findById(rs.getInt("CONTA_ORIGEM")));
                retorno.setContaDestino(new ContaDAO().findById(rs.getInt(rs.getInt("CONTA_DESTINO"))));
                transacoes.add(retorno);
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
        return transacoes;
    }

    public List<Transacao> findByTodasMinha(int conta) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Transacao retorno = null;
        List<Transacao> transacoes = new ArrayList<>();

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_CONTA_MINHA);
            pstmt.setInt(1,conta);
            pstmt.setInt(2,conta);
            rs = pstmt.executeQuery();

            while(rs.next()){
                retorno = new Transacao();
                retorno.setId(rs.getInt("ID"));
                retorno.setDataHora(rs.getDate("DATAHORA"));
                retorno.setValor(rs.getDouble("VALOR"));
                if (rs.getInt("TIPO") == 1){
                    retorno.setTipoTransacao(TipoTransacaoEnum.PIX);
                } else if (rs.getInt("TIPO") == 2) {
                    retorno.setTipoTransacao(TipoTransacaoEnum.TED);
                } else if (rs.getInt("TIPO") == 3) {
                    retorno.setTipoTransacao(TipoTransacaoEnum.SAQUE);
                } else if (rs.getInt("TIPO") == 4) {
                    retorno.setTipoTransacao(TipoTransacaoEnum.DEPOSITO);
                } else {
                    retorno.setTipoTransacao(TipoTransacaoEnum.TIPO_DESCONHECIDO);
                }
                retorno.setRegistroAcademico(rs.getString("RA"));
                retorno.setContaOrigem(new ContaDAO().findById(rs.getInt("CONTA_ORIGEM")));
                retorno.setContaDestino(new ContaDAO().findById(rs.getInt(rs.getInt("CONTA_DESTINO"))));
                transacoes.add(retorno);
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
        return transacoes;
    }

}
