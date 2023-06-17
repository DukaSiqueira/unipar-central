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

    private void validar(PessoaFisica pessoaFisica) throws TamanhoCampoInvalidoException, CampoNaoInformadoException, EntidadeNaoInformadaException {
        if (pessoaFisica == null) {
            throw new EntidadeNaoInformadaException("Pessoa Física");
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

        if (pessoaFisica.getCpf().length() != 11) {
            throw new TamanhoCampoInvalidoException("CPF", 11);
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
        return new PessoaFisicaDAO().findAll();
    }

    @Override
    public PessoaFisica findById(int id) throws TamanhoCampoInvalidoException, Exception {
        return new PessoaFisicaDAO().findById(id);
    }

    @Override
    public int insert(PessoaFisica pessoaFisica) throws SQLException, EntidadeNaoInformadaException, CampoNaoInformadoException, TamanhoCampoInvalidoException {
        validar(pessoaFisica);
        PessoaFisicaDAO  pessoaFisicaDAO = new PessoaFisicaDAO();
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

    }
}
