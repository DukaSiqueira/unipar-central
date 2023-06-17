package services;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import models.Conta;
import repositories.ContaDAO;

import java.sql.SQLException;
import java.util.List;

public class ContaService {

    private ContaDAO contaDAO;

    public ContaService() {
        this.contaDAO = new ContaDAO();
    }

    public void validar(Conta conta) throws EntidadeNaoInformadaException, CampoNaoInformadoException,
            TamanhoCampoInvalidoException {
        if (conta == null) {
            throw new EntidadeNaoInformadaException("Conta");
        }
        if (conta.getNumero() == null || conta.getNumero().isEmpty() || conta.getNumero().isBlank()) {
            throw new CampoNaoInformadoException("Conta(Numero)");
        }
        if (conta.getNumero().length() > 120) {
            throw new TamanhoCampoInvalidoException("Conta(Numero)", 120);
        }
    }

    public List<Conta> listarContas() throws SQLException {
        try {
            return contaDAO.findAll();
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar contas: " + e.getMessage());
        }
    }

    public Conta buscarContaPorId(int id) throws SQLException {
        try {
            Conta conta = contaDAO.findById(id);
            if (conta == null) {
                throw new IllegalArgumentException("Conta não encontrada");
            }
            return conta;
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar conta por ID: " + e.getMessage());
        }
    }

    public void criarConta(Conta conta) throws SQLException {
        try {
            contaDAO.insert(conta);
        } catch (SQLException e) {
            throw new SQLException("Erro ao criar conta: " + e.getMessage());
        }
    }

    public void atualizarConta(Conta conta) throws SQLException {
        try {
            Conta contaExistente = contaDAO.findById(conta.getId());
            if (contaExistente == null) {
                throw new IllegalArgumentException("Conta não encontrada");
            }
            contaDAO.update(conta);
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar conta: " + e.getMessage());
        }
    }

    public void excluirConta(int id) throws SQLException {
        try {
            Conta contaExistente = contaDAO.findById(id);
            if (contaExistente == null) {
                throw new IllegalArgumentException("Conta não encontrada");
            }
            contaDAO.deleteConta(id);
        } catch (SQLException e) {
            throw new SQLException("Erro ao excluir conta: " + e.getMessage());
        }
    }
}
