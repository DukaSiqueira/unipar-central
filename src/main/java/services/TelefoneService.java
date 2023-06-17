package services;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import models.Telefone;
import repositories.TelefoneDAO;

import java.sql.SQLException;
import java.util.List;

public class TelefoneService {
    public void validar(Telefone telefone) throws EntidadeNaoInformadaException, TamanhoCampoInvalidoException, CampoNaoInformadoException {
        if (telefone == null){
            throw new EntidadeNaoInformadaException(("Telefone"));
        }

        if(telefone.getNumero() == null ||
                telefone.getNumero().isEmpty() ||
                telefone.getNumero().isBlank()){
            throw new CampoNaoInformadoException(("Telefone(Numero)"));
        }

        if (telefone.getNumero().length() > 11){
            throw new TamanhoCampoInvalidoException("Telefone(Numero)", 11);
        }
    }


    public Telefone findById(int id) throws SQLException, TamanhoCampoInvalidoException, Exception{
        if(id <= 0)
            throw  new TamanhoCampoInvalidoException("id",1);
        TelefoneDAO telefoneDAO = new TelefoneDAO();
        Telefone retorno = telefoneDAO.findById(id);

        if (retorno == null)
            throw new Exception("Não foi possível encontrar um telefone" +
                    "com id " + id + " tente outro código.");
        return retorno;
    }

    public List<Telefone> findByPessoaAgencia(int idPessoa, int idAgencia) throws SQLException{
        TelefoneDAO telefoneDAO = new TelefoneDAO();
        List<Telefone> listaTelefone = telefoneDAO.findByPessoaAgencia(idPessoa, idAgencia);
        return listaTelefone;
    }

    public void update(Telefone telefone, int idPessoa, int idAgencia) throws SQLException, TamanhoCampoInvalidoException, EntidadeNaoInformadaException, Exception{
        validar(telefone);
        TelefoneDAO telefoneDAO = new TelefoneDAO();
        telefoneDAO.update(telefone, idPessoa, idAgencia);
    }

    public void delete(int id) throws SQLException, Exception{
        TelefoneDAO telefoneDAO = new TelefoneDAO();
        telefoneDAO.delete(id);
    }

    public void insert(Telefone telefone , int idPessoa, int idAgencia) throws SQLException, TamanhoCampoInvalidoException, EntidadeNaoInformadaException, Exception{
        validar(telefone);
        TelefoneDAO telefoneDAO = new TelefoneDAO();
        telefoneDAO.insert(telefone, idPessoa, idAgencia);
    }

}
