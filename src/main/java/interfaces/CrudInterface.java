package interfaces;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import models.Pais;

import java.sql.SQLException;
import java.util.List;

public interface CrudInterface<Model> {

    List<Model> findAll() throws SQLException;

    Model findById(int id) throws TamanhoCampoInvalidoException, Exception;

    int insert(Model model) throws SQLException, EntidadeNaoInformadaException, CampoNaoInformadoException, TamanhoCampoInvalidoException;

    void update(Model model) throws SQLException, CampoNaoInformadoException, EntidadeNaoInformadaException, TamanhoCampoInvalidoException;

    void delete(int id) throws SQLException, CampoNaoInformadoException;
}
