package services;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import interfaces.CrudInterface;
import models.Cidade;
import repositories.CidadeDAO;

import java.sql.SQLException;
import java.util.List;

public class CidadeService implements CrudInterface<Cidade> {

    private void validar(Cidade cidade) throws EntidadeNaoInformadaException, CampoNaoInformadoException,
            TamanhoCampoInvalidoException {
        if (cidade == null) {
            throw new EntidadeNaoInformadaException("Cidade");
        }

        if (cidade.getEstado() == null) {
            throw new EntidadeNaoInformadaException("Estado");
        }

        if (cidade.getEstado().getId() == 0) {
            throw new CampoNaoInformadoException("ESTADO_ID");
        }

        if (cidade.getId() == 0) {
            throw new CampoNaoInformadoException("ID");
        }

        if (cidade.getNome() == null || cidade.getNome().isBlank() || cidade.getNome().isBlank()) {
            throw new CampoNaoInformadoException("NOME");
        }

        if (cidade.getNome().length() > 60) {
            throw  new TamanhoCampoInvalidoException("NOME", 60);
        }

    }

    @Override
    public List<Cidade> findAll() throws SQLException {
        CidadeDAO cidadeDAO = new CidadeDAO();
        List<Cidade> cidades = cidadeDAO.findAll();
        return cidades;
    }

    @Override
    public Cidade findById(int id) throws TamanhoCampoInvalidoException, Exception {
        if (id <= 0)
            throw new TamanhoCampoInvalidoException("ID", 1);

        CidadeDAO cidadeDAO = new CidadeDAO();
        return cidadeDAO.findById(id);
    }

    @Override
    public int insert(Cidade cidade) throws SQLException, EntidadeNaoInformadaException, CampoNaoInformadoException, TamanhoCampoInvalidoException {
        validar(cidade);

        CidadeDAO cidadeDAO = new CidadeDAO();
        int id = cidadeDAO.insert(cidade);

        return id;
    }

    @Override
    public void update(Cidade cidade) throws SQLException, CampoNaoInformadoException, EntidadeNaoInformadaException, TamanhoCampoInvalidoException {
        validar(cidade);

        CidadeDAO cidadeDAO = new CidadeDAO();
        cidadeDAO.update(cidade);
    }

    @Override
    public void delete(int id) throws SQLException, CampoNaoInformadoException {
        CidadeDAO cidadeDAO = new CidadeDAO();
        cidadeDAO.delete(id);
    }
}
