package services;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import models.Banco;
import repositories.BancoDAO;

import java.sql.SQLException;
import java.util.List;

public class BancoService {

    private BaseAbstractService baseAbstractService;

    public BancoService (){
        this.baseAbstractService = new BaseAbstractService();
    }

    public void validar (Banco banco) throws CampoNaoInformadoException, TamanhoCampoInvalidoException, EntidadeNaoInformadaException, SQLException {

        if (banco == null){
            throw new EntidadeNaoInformadaException("Banco");
        }
        if(banco.getNome() == null ||
                banco.getNome().isEmpty() ||
                banco.getNome().isBlank()){
            throw new CampoNaoInformadoException(("Banco(Nome)"));
        }
        if (banco.getNome().length() > 120){
            throw new TamanhoCampoInvalidoException("Banco(Nome)", 120);
        }
        baseAbstractService.validarRA(banco.getRegistroAcademico());
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
            throw new Exception("Banco não encontrado!");
        }
        bancoDAO.update(banco);
    }

    public void delete(int id) throws Exception{
        BancoDAO bancoDAO = new BancoDAO();
        Banco bancoExistente = bancoDAO.findById(id);
        if (bancoExistente == null) {
            throw new Exception("Banco não encontrado!");
        }
        bancoDAO.deleteById(id);
    }

    public List<Banco> findAll() throws SQLException, Exception {
        BancoDAO bancoDAO = new BancoDAO();
        List<Banco> listaBanco = bancoDAO.findAll();
        if (listaBanco.isEmpty()){
            throw new Exception("Não existem registros de Banco");
        }
        return listaBanco;
    }


    public Banco findById(int id) throws Exception{
        if(id <= 0)
            throw  new TamanhoCampoInvalidoException("id",1);
        BancoDAO bancoDAO = new BancoDAO();
        Banco retorno = bancoDAO.findById(id);

        if (retorno == null){
            throw new Exception("Banco não encontrado!");
        }
        return retorno;
    }

    public int findExiste (int id) throws Exception {
        if(id <= 0)
            throw  new CampoNaoInformadoException("id");
        BancoDAO bancoDAO = new BancoDAO();
        int count = bancoDAO.findExiste(id);
        return count;
    }

}
