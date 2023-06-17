package services;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import models.Agencia;
import repositories.AgenciaDAO;

import java.sql.SQLException;
import java.util.List;

public class AgenciaService {

    public void validar (Agencia agencia) throws CampoNaoInformadoException, TamanhoCampoInvalidoException, EntidadeNaoInformadaException {

        if (agencia == null){
            throw new EntidadeNaoInformadaException("agencia");
        }
        if (agencia.getId() <= 0){
            throw new CampoNaoInformadoException("ID");
        }
        if(agencia.getCodigo() == null ||
                agencia.getCodigo().isEmpty() ||
                agencia.getCodigo().isBlank()){
            throw new CampoNaoInformadoException("agencia(Codigo)");
        }
        if (agencia.getCodigo().length() > 10){
            throw new TamanhoCampoInvalidoException("agencia(Codigo)", 10);
        }

        if(agencia.getDigito() == null ||
                agencia.getCodigo().isEmpty() ||
                agencia.getCodigo().isBlank()){
            throw new CampoNaoInformadoException(("agencia(Digito)"));
        }
        if (agencia.getDigito().length() > 2){
            throw new TamanhoCampoInvalidoException("agencia(Digito)", 2);
        }
        if(agencia.getRazaoSocial() == null ||
                agencia.getRazaoSocial().isEmpty() ||
                agencia.getRazaoSocial().isBlank()){
            throw new CampoNaoInformadoException(("agencia(RazaoSocial)"));
        }
        if (agencia.getRazaoSocial().length() > 120){
            throw new TamanhoCampoInvalidoException("agencia(RazaoSocial)", 120);
        }

        if(agencia.getCnpj() == null ||
                agencia.getCnpj().isEmpty() ||
                agencia.getCnpj().isBlank()){
            throw new CampoNaoInformadoException(("agencia(CPNJ)"));
        }
        if (agencia.getCnpj().length() > 20){
            throw new TamanhoCampoInvalidoException("agencia(CPNJ)", 20);
        }
        if(agencia.getCnpj() == null ||
                agencia.getCnpj().isEmpty() ||
                agencia.getCnpj().isBlank()){
            throw new CampoNaoInformadoException(("agencia(RA)"));
        }
        if (agencia.getCnpj().length() > 8){
            throw new TamanhoCampoInvalidoException("agencia(RA)", 8);
        }

    }

    public void insert(Agencia agencia, int idBanco) throws Exception{
        validar(agencia);
        AgenciaDAO agenciaDAO = new AgenciaDAO();
        agenciaDAO.insert(agencia, idBanco);
    }

    public void update(Agencia agencia, int idBanco) throws Exception{
        validar(agencia);
        AgenciaDAO agenciaDAO = new AgenciaDAO();
        Agencia agenciaExistente = agenciaDAO.FIND_BY_ID(agencia.getId());
        if (agenciaExistente == null) {
            throw new Exception("Agencia não encontrada");
        }
        agenciaDAO.update(agencia, idBanco);
    }

    public void delete(int id) throws Exception{
        AgenciaDAO agenciaDAO = new AgenciaDAO();
        Agencia agenciaExistente = agenciaDAO.FIND_BY_ID(id);
        if (agenciaExistente == null) {
            throw new Exception("Agencia não encontrada");
        }
        agenciaDAO.delete(id);
    }

    public List<Agencia> findAll() throws Exception {
        AgenciaDAO agenciaDAO = new AgenciaDAO();
        List<Agencia> listaAgencia = agenciaDAO.FIND_ALL();
        if (listaAgencia.isEmpty()){
            throw new Exception("Não existem registros de Agencia");
        }
        return listaAgencia;
    }

    public Agencia findById(int id) throws Exception{
        if(id <= 0)
            throw  new TamanhoCampoInvalidoException("id",1);
        AgenciaDAO agenciaDAO = new AgenciaDAO();
        Agencia retorno = agenciaDAO.FIND_BY_ID(id);

        if (retorno == null){
            throw new Exception("Não existe Agencia");
        }
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
