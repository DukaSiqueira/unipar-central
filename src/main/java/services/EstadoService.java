package services;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import interfaces.CrudInterface;
import models.Estado;
import repositories.EstadoDAO;

import java.sql.SQLException;
import java.util.List;

public class EstadoService implements CrudInterface<Estado> {

    private void validar(Estado estado) throws EntidadeNaoInformadaException, CampoNaoInformadoException,
            TamanhoCampoInvalidoException{

        if (estado == null) {
            throw new EntidadeNaoInformadaException("Estado");
        }

        if (estado.getPais() == null) {
            throw new EntidadeNaoInformadaException("Pais");
        }

        if (estado.getPais().getId() == 0) {
            throw new CampoNaoInformadoException("PAIS_ID");
        }

        if (estado.getId() == 0) {
            throw new CampoNaoInformadoException("ID");
        }

        if (estado.getNome() == null || estado.getNome().isBlank() || estado.getNome().isBlank()) {
            throw new CampoNaoInformadoException("NOME");
        }

        if (estado.getNome().length() > 60) {
            throw  new TamanhoCampoInvalidoException("NOME", 60);
        }

        if (estado.getSigla() == null || estado.getSigla().isBlank() || estado.getSigla().isBlank()) {
            throw new CampoNaoInformadoException("SIGLA");
        }

        if (estado.getSigla().length() > 2) {
            throw new TamanhoCampoInvalidoException("SIGLA", 2);
        }
    }

    @Override
    public List<Estado> findAll() throws SQLException {
        EstadoDAO estadoDAO = new EstadoDAO();
        List<Estado> estados = estadoDAO.findAll();
        return estados;
    }

    @Override
    public Estado findById(int id) throws TamanhoCampoInvalidoException, Exception {
        EstadoDAO estadoDAO = new EstadoDAO();
        Estado estado = estadoDAO.findById(id);
        return estado;
    }

    @Override
    public int insert(Estado estado) throws SQLException, EntidadeNaoInformadaException, CampoNaoInformadoException, TamanhoCampoInvalidoException {
        validar(estado);

        EstadoDAO estadoDAO = new EstadoDAO();
        int id = estadoDAO.insert(estado);

        return id;
    }

    @Override
    public void update(Estado estado) throws SQLException, CampoNaoInformadoException, EntidadeNaoInformadaException, TamanhoCampoInvalidoException {
        validar(estado);

        EstadoDAO estadoDAO = new EstadoDAO();
        estadoDAO.update(estado);
    }

    @Override
    public void delete(int id) throws SQLException, CampoNaoInformadoException {
        if (id <= 0)
            throw  new CampoNaoInformadoException("ID");

        EstadoDAO estadoDAO = new EstadoDAO();
        estadoDAO.delete(id);
    }
}
