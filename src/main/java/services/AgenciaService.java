package services;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import models.Agencia;
import repositories.AgenciaDAO;

import java.sql.SQLException;
import java.util.List;

public class AgenciaService {

    private RAService raService;

    public void RAService(){
        this.raService = new RAService();
    }

    public void validar (Agencia agencia) throws CampoNaoInformadoException, TamanhoCampoInvalidoException, EntidadeNaoInformadaException, SQLException {

        raService.validarRA(agencia.getRegistroAcademico());

        if (agencia == null){
            throw new EntidadeNaoInformadaException("agencia");
        }
        if(agencia.getCodigo() == null ||
                agencia.getCodigo().isEmpty() ||
                agencia.getCodigo().isBlank()){
            throw new CampoNaoInformadoException("agencia(Codigo)");
        }
        if(agencia.getDigito() == null ||
                agencia.getCodigo().isEmpty() ||
                agencia.getCodigo().isBlank()){
            throw new CampoNaoInformadoException(("agencia(Digito)"));
        }
        if(agencia.getRazaoSocial() == null ||
                agencia.getRazaoSocial().isEmpty() ||
                agencia.getRazaoSocial().isBlank()){
            throw new CampoNaoInformadoException(("agencia(RazaoSocial)"));
        }
        if(agencia.getCnpj() == null ||
                agencia.getCnpj().isEmpty() ||
                agencia.getCnpj().isBlank()){
            throw new CampoNaoInformadoException(("agencia(CPNJ)"));
        }
        if(agencia.getCnpj() == null ||
                agencia.getCnpj().isEmpty() ||
                agencia.getCnpj().isBlank()){
            throw new CampoNaoInformadoException(("agencia(RA)"));

    }

    public void insert(Agencia agencia, int idBanco) throws Exception {
        validar(agencia);
        AgenciaDAO agenciaDAO = new AgenciaDAO();
        agenciaDAO.insert(agencia, idBanco);
    }

    public void delete(int id) throws Exception {
        AgenciaDAO agenciaDAO = new AgenciaDAO();
        Agencia agenciaExistente = agenciaDAO.FIND_BY_ID(id);
        if (agenciaExistente == null) {
            throw new NaoExisteDatabaseException("ID", "Agencia");
        }
        agenciaDAO.delete(id);
    }

    public List<Agencia> findAll() throws SQLException, FindRetornadoException {
        AgenciaDAO agenciaDAO = new AgenciaDAO();
        List<Agencia> listaAgencia = agenciaDAO.FIND_ALL();
        if (listaAgencia.isEmpty()){
            throw new FindRetornadoException("Agencia");
        }
        return listaAgencia;
    }

    public Agencia findById(int id) throws Exception{
        if(id <= 0)
            throw  new TamanhoCampoInvalidoException("id",1);
        AgenciaDAO agenciaDAO = new AgenciaDAO();
        Agencia retorno = agenciaDAO.FIND_BY_ID(id);

        return retorno;
    }

    public int findExiste (int id) throws Exception {
        if(id <= 0)
            throw  new TamanhoCampoInvalidoException("id",1);
        AgenciaDAO agenciaDAO = new AgenciaDAO();
        int count = agenciaDAO.findExiste(id);
        return count;
    }

}
