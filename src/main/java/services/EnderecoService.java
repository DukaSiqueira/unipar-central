package services;

import com.mycompany.unipar.central.exceptions.CampoLimiteTamanhoException;
import com.mycompany.unipar.central.exceptions.CampoNaoInformadoException;
import com.mycompany.unipar.central.exceptions.EntidadeNaoInformadaException;
import com.mycompany.unipar.central.models.Endereco;
import com.mycompany.unipar.central.repositories.EnderecoDAO;
import java.sql.SQLException;

public class EnderecoService {
    public void validar(Endereco endereco) throws EntidadeNaoInformadaException, CampoLimiteTamanhoException, CampoNaoInformadoException {
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
            throw new CampoLimiteTamanhoException("Endereco(Cep)", "8");
        }
    }
    
    public Endereco findById(int id) throws SQLException, CampoLimiteTamanhoException, Exception{
        if(id <= 0)
            throw  new CampoLimiteTamanhoException("id","1");
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        Endereco retorno = enderecoDAO.findById(id);

        if (retorno == null)
            throw new Exception("Não foi possível encontrar um endereco" +
                    "com id " + id + " tente outro código.");
        return retorno;
   }

   public void insert(Endereco endereco, int idPessoa) throws SQLException, CampoLimiteTamanhoException, EntidadeNaoInformadaException, Exception{
        validar(endereco);
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        enderecoDAO.insert(endereco, idPessoa);
   }
   public void update(Endereco endereco) throws SQLException, CampoLimiteTamanhoException, EntidadeNaoInformadaException, Exception{
        validar(endereco);
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        enderecoDAO.update(endereco);
   }

   public void delete(int id) throws SQLException, Exception{
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        enderecoDAO.delete(id);
   }
}
