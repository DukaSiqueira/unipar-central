package services;

import com.mycompany.unipar.central.exceptions.*;
import com.mycompany.unipar.central.models.Banco;
import com.mycompany.unipar.central.repositories.BancoDAO;

import java.sql.SQLException;
import java.util.List;

public class BancoService {

    private RAService raService;

    public BancoService (){
        this.raService = new RAService();
    }

    public void validar (Banco banco) throws CampoNaoInformadoException, CampoLimiteTamanhoException, EntidadeNaoInformadaException, ValorInformadoRAException, NaoExisteDatabaseException, SQLException {

        if (banco == null){
            throw new EntidadeNaoInformadaException("Banco");
        }
        if(banco.getNome() == null ||
                banco.getNome().isEmpty() ||
                banco.getNome().isBlank()){
            throw new CampoNaoInformadoException(("Banco(Nome)"));
        }
        if (banco.getNome().length() > 120){
            throw new CampoLimiteTamanhoException("Banco(Nome)", "120");
        }
        raService.validarRA(banco.getRa());
    }

    public void insert(Banco banco) throws Exception{
        validar(banco);
        BancoDAO bancoDAO = new BancoDAO();
        bancoDAO.insert(banco);
    }

    public void update(Banco banco) throws Exception{
        validar(banco);
        BancoDAO bancoDAO = new BancoDAO();
        Banco bancoExistente = bancoDAO.findById(banco.getId());
        if (bancoExistente == null) {
            throw new NaoExisteDatabaseException("ID", "Banco");
        }
        bancoDAO.update(banco);
    }

    public void delete(int id) throws Exception{
        BancoDAO bancoDAO = new BancoDAO();
        Banco bancoExistente = bancoDAO.findById(id);
        if (bancoExistente == null) {
            throw new NaoExisteDatabaseException("ID", "Banco");
        }
        bancoDAO.deleteById(id);
    }

    public List<Banco> findAll() throws SQLException, FindRetornadoException {
        BancoDAO bancoDAO = new BancoDAO();
        List<Banco> listaBanco = bancoDAO.findAll();
        if (listaBanco.isEmpty()){
            throw new FindRetornadoException("Banco");
        }
        return listaBanco;
    }


    public Banco findById(int id) throws Exception{
        if(id <= 0)
            throw  new CampoLimiteTamanhoException("id","1");
        BancoDAO bancoDAO = new BancoDAO();
        Banco retorno = bancoDAO.findById(id);

        if (retorno == null){
            throw new FindRetornadoException("Banco");
        }
        return retorno;
    }

    public int findExiste (int id) throws Exception {
        if(id <= 0)
            throw  new CampoLimiteTamanhoException("id","1");
        BancoDAO bancoDAO = new BancoDAO();
        int count = bancoDAO.findExiste(id);
        return count;
    }

}
