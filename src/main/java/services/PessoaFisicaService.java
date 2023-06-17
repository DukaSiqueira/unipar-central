package services;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import interfaces.CrudInterface;
import models.PessoaFisica;
import repositories.PessoaFisicaDAO;

import java.sql.SQLException;
import java.util.List;

public class PessoaFisicaService implements CrudInterface<PessoaFisica> {

    private void validar(PessoaFisica pessoaFisica) throws EntidadeNaoInformadaException, CampoNaoInformadoException {

        if (pessoaFisica == null) {
            throw new EntidadeNaoInformadaException("Pessoa Fisica");
        }

        if (pessoaFisica.getPessoa() == null) {
            throw new EntidadeNaoInformadaException("Pessoa");
        }

        if (pessoaFisica.getNome() == null || pessoaFisica.getNome().isEmpty() || pessoaFisica.getNome().isBlank()) {
            throw new CampoNaoInformadoException("Nome");
        }

        if (pessoaFisica.getCpf() == null || pessoaFisica.getCpf().isEmpty() || pessoaFisica.getCpf().isBlank()) {
            throw new CampoNaoInformadoException("CPF");
        }

        if (pessoaFisica.getRg() == null || pessoaFisica.getRg().isEmpty() || pessoaFisica.getRg().isBlank()) {
            throw new CampoNaoInformadoException("RG");
        }


        if (pessoaFisica.getDataNascimento() == null) {
            throw new CampoNaoInformadoException("Data de Nascimento");
        }

    }

    @Override
    public List<PessoaFisica> findAll() throws SQLException {
        PessoaFisicaDAO  pessoaFisicaDAO = new PessoaFisicaDAO();
        List<PessoaFisica> pessoaFisicaList = pessoaFisicaDAO.findAll();
        if (pessoaFisicaList.isEmpty()) {
            throw new SQLException("Nenhuma pessoa fisica encontrada");
        }
        return pessoaFisicaList;
    }

    @Override
    public PessoaFisica findById(int id) throws TamanhoCampoInvalidoException, Exception {
        if (id <= 0) {
            throw new TamanhoCampoInvalidoException("ID", 1);
        }
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        return pessoaFisicaDAO.findById(id);
    }

    @Override
    public int insert(PessoaFisica pessoaFisica) throws SQLException, EntidadeNaoInformadaException, CampoNaoInformadoException, TamanhoCampoInvalidoException {
        validar(pessoaFisica);
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        return pessoaFisicaDAO.insert(pessoaFisica);
    }

    @Override
    public void update(PessoaFisica pessoaFisica) throws SQLException, CampoNaoInformadoException, EntidadeNaoInformadaException, TamanhoCampoInvalidoException {
        validar(pessoaFisica);
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        pessoaFisicaDAO.update(pessoaFisica);
    }

    @Override
    public void delete(int id) throws SQLException, CampoNaoInformadoException {
        if (id <= 0) {
            throw new CampoNaoInformadoException("ID");
        }
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        pessoaFisicaDAO.delete(id);
    }
}
