package services;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import interfaces.CrudInterface;
import models.Endereco;
import repositories.EnderecoDAO;

import java.sql.SQLException;
import java.util.List;

public class EnderecoService implements CrudInterface<Endereco> {
    public void validar(Endereco endereco) throws EntidadeNaoInformadaException, TamanhoCampoInvalidoException, CampoNaoInformadoException {
        if (endereco == null){
            throw new EntidadeNaoInformadaException(("Endereco"));
        }
        if (endereco.getCidade() == null){
            throw new EntidadeNaoInformadaException(("Endereco(Cidade)"));
        }

        if(endereco.getLogradouro() == null ||
                endereco.getLogradouro().isEmpty() ||
                endereco.getLogradouro().isBlank()){
            throw new CampoNaoInformadoException(("Endereco(Logradouro)"));
        }

        if(endereco.getBairro() == null ||
                endereco.getBairro().isEmpty() ||
                endereco.getBairro().isBlank()){
            throw new CampoNaoInformadoException(("Endereco(Bairro)"));
        }
        if(endereco.getNumero() == null ||
                endereco.getNumero().isEmpty() ||
                endereco.getNumero().isBlank()){
            throw new CampoNaoInformadoException(("Endereco(Numero)"));
        }
        if(endereco.getCep() == null ||
                endereco.getCep().isEmpty() ||
                endereco.getCep().isBlank()){
            throw new CampoNaoInformadoException(("Endereco(Cep)"));
        }
        if (endereco.getCep().length() > 8){
            throw new TamanhoCampoInvalidoException("Endereco(Cep)", 8);
        }
    }


    @Override
    public List<Endereco> findAll() throws SQLException {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        return enderecoDAO.findAll();
    }

    @Override
    public Endereco findById(int id) throws SQLException, TamanhoCampoInvalidoException, Exception{
        if(id <= 0)
            throw  new TamanhoCampoInvalidoException("id",1);
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        Endereco retorno = enderecoDAO.findById(id);

        if (retorno == null)
            throw new Exception("Não foi possível encontrar um endereco" +
                    "com id " + id + " tente outro código.");
        return retorno;
    }
    @Override
    public int insert(Endereco endereco) throws SQLException, TamanhoCampoInvalidoException, EntidadeNaoInformadaException, CampoNaoInformadoException {
        validar(endereco);
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        return enderecoDAO.insert(endereco);
    }
    @Override
    public void update(Endereco endereco) throws SQLException, TamanhoCampoInvalidoException, EntidadeNaoInformadaException, CampoNaoInformadoException {
        validar(endereco);
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        enderecoDAO.update(endereco);
    }

    @Override
    public void delete(int id) throws SQLException{
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        enderecoDAO.delete(id);
    }
}
