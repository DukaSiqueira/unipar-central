package services;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import interfaces.CrudInterface;
import models.Pessoa;
import repositories.PessoaDAO;
import java.sql.SQLException;
import java.util.List;

public class PessoaService implements CrudInterface<Pessoa> {

    private void validar(Pessoa pessoa) throws TamanhoCampoInvalidoException, EntidadeNaoInformadaException, CampoNaoInformadoException {
        if (pessoa == null) {
            throw new EntidadeNaoInformadaException("PESSOA");
        }
        if (pessoa.getId()  <= 0) {
            throw new CampoNaoInformadoException("ID");
        }
        if (pessoa.getEmail() == null || pessoa.getEmail().isBlank() || pessoa.getEmail().isEmpty()) {
            throw new CampoNaoInformadoException("EMAIL");
        }
        if (pessoa.getRegistroAcademico() == null || pessoa.getRegistroAcademico().isBlank() || pessoa.getRegistroAcademico().isEmpty()) {
            throw new CampoNaoInformadoException("REGISTRO ACADEMICO");
        }

    }
    @Override
    public List<Pessoa> findAll() throws SQLException {
        PessoaDAO pessoaDAO = new PessoaDAO();
        List<Pessoa> pessoas = pessoaDAO.findAll();
        if (pessoas.isEmpty()) {
            throw new SQLException("Nenhuma pessoa encontrada");
        }
        return pessoas;
    }

    @Override
    public Pessoa findById(int id) throws TamanhoCampoInvalidoException, Exception {
        if (id <= 0) {
            throw new TamanhoCampoInvalidoException("id", 1);
        }

        PessoaDAO pessoaDAO = new PessoaDAO();
        Pessoa pessoa = pessoaDAO.findById(id);

        if (pessoa == null) {
            throw new Exception("Pessoa não encontrada");
        }

        return pessoa;
    }

    @Override
    public int insert(Pessoa pessoa) throws SQLException, EntidadeNaoInformadaException, CampoNaoInformadoException, TamanhoCampoInvalidoException {
        validar(pessoa);
        PessoaDAO pessoaDAO = new PessoaDAO();
        return pessoaDAO.insert(pessoa);
    }

    @Override
    public void update(Pessoa pessoa) throws SQLException, CampoNaoInformadoException, EntidadeNaoInformadaException, TamanhoCampoInvalidoException {
        validar(pessoa);
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.update(pessoa);
    }

    @Override
    public void delete(int id) throws SQLException, CampoNaoInformadoException {
        if (id <= 0) {
            throw new CampoNaoInformadoException("ID");
        }
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.delete(id);
    }
}
