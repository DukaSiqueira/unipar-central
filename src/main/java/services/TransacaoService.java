package services;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import models.Transacao;
import repositories.TransacaoDAO;

import java.sql.SQLException;
import java.util.List;

public class TransacaoService {

    public void validar (Transacao transacao) throws CampoNaoInformadoException, TamanhoCampoInvalidoException, EntidadeNaoInformadaException {
        if (transacao == null){
            throw new EntidadeNaoInformadaException("Transacao");
        }
        if(transacao.getValor() <= 0){
            throw new CampoNaoInformadoException("Valor");
        }
        if (transacao.getContaOrigem() == null || transacao.getContaOrigem().getId() <= 0){
            throw new CampoNaoInformadoException("Conta_origem");
        }
        if (transacao.getContaDestino() == null || transacao.getContaDestino().getId() <= 0){
            throw new CampoNaoInformadoException("Conta_origem");
        }
    }

    public List<Transacao> findAll() throws SQLException, Exception {
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        List<Transacao> listaTransacao = transacaoDAO.findAll();
        if (listaTransacao.isEmpty()) {
            throw new Exception("Não existem transaçães cadastradas");
        }
        return listaTransacao;
    }

    public Transacao findByContaOrigem(int id) throws Exception {
        if (id <= 0)
            throw new TamanhoCampoInvalidoException("id", 1);
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        List<Transacao> listaTransacao = transacaoDAO.findByContaOrigem(id);

        if (listaTransacao.isEmpty()) {
            throw new Exception("Não existem transaçães cadastradas para essa conta");
        }
        return (Transacao) listaTransacao;
    }
    public Transacao findByContaDestino(int id) throws Exception {
        if (id <= 0)
            throw new TamanhoCampoInvalidoException("id", 1);
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        List<Transacao> listaTransacao = transacaoDAO.findByContaDestino(id);

        if (listaTransacao.isEmpty()) {
            throw new Exception("Não existem transaçães cadastradas para essa conta");
        }

        if (listaTransacao.isEmpty()) {
            throw new Exception("Não existem transaçães cadastradas para essa conta");
        }
        return (Transacao) listaTransacao;
    }

    public Transacao findByContaOrigemDestino(int id) throws Exception {
        if (id <= 0)
            throw new TamanhoCampoInvalidoException("id", 1);
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        List<Transacao> listaTransacao = transacaoDAO.findByTodasMinha(id);

        if (listaTransacao.isEmpty()) {
            throw new Exception("Consulta com todos as transações referente a essa conta");
        }
        return (Transacao) listaTransacao;
    }

    public void insert(Transacao transacao) throws Exception{
        validar(transacao);
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        transacaoDAO.insert(transacao);
    }
}
