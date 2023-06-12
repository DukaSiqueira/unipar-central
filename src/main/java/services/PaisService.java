package services;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import models.Pais;
import repositories.PaisDAO;

import java.sql.SQLException;
import java.util.List;

public class PaisService {

    private void validar(Pais pais) throws SQLException, EntidadeNaoInformadaException, CampoNaoInformadoException,
            TamanhoCampoInvalidoException {
        if (pais == null) {
            throw new EntidadeNaoInformadaException("Pais");
        }

        if (pais.getId() == 0) {
            throw  new CampoNaoInformadoException("ID");
        }

        if (pais.getNome() == null || pais.getNome().isBlank() || pais.getNome().isEmpty()) {
            throw new CampoNaoInformadoException("NOME");
        }

        if (pais.getNome().length() > 60) {
            throw new TamanhoCampoInvalidoException("NOME", 60);
        }

        if (pais.getAbreviacao() == null || pais.getAbreviacao().isBlank() || pais.getAbreviacao().isEmpty()) {
            throw new CampoNaoInformadoException("SIGLA");
        }

        if (pais.getAbreviacao().length() > 2) {
            throw new TamanhoCampoInvalidoException("SIGLA", 2);
        }

        if (pais.getRegistroAcademico() == null) {
            throw new CampoNaoInformadoException("RA");
        }
    }

    public List<Pais> findAll() throws SQLException {
        PaisDAO paisDAO = new PaisDAO();
        List<Pais> response = paisDAO.findAll();

        return response;
    }

    public int insert(Pais pais) throws SQLException, EntidadeNaoInformadaException, CampoNaoInformadoException,
            TamanhoCampoInvalidoException{
        validar(pais);

        PaisDAO paisDAO = new PaisDAO();
        int id = paisDAO.insert(pais);

        return id;
    }

    public Pais findById(int id) throws SQLException, CampoNaoInformadoException, TamanhoCampoInvalidoException , Exception{
        if (id <= 0)
            throw new TamanhoCampoInvalidoException("id", 1);

        PaisDAO paisDAO = new PaisDAO();
        Pais response = paisDAO.findByid(id);

        if (response == null)
            throw new Exception("Não foi possível encontrar nenhum páis com o id " + id + "!");

        return response;
    }

}
