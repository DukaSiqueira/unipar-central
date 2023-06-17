package services;

import com.mycompany.unipar.central.exceptions.*;
import com.mycompany.unipar.central.models.Transacao;
import com.mycompany.unipar.central.repositories.TransacaoDAO;

import java.sql.SQLException;
import java.util.List;

public class TransacaoService {

    private RAService raService;

    public TransacaoService(){
        this.raService = new RAService();
    }

    public void validar (Transacao transacao) throws CampoNaoInformadoException, CampoLimiteTamanhoException, EntidadeNaoInformadaException, ValorInformadoException, ValorInformadoRAException {
        raService.validarRA(transacao.getRa());
        if (transacao == null){
            throw new EntidadeNaoInformadaException("Transacao");
        }
        if(transacao.getValor() <= 0){
            throw new ValorInformadoException(transacao.getValor(),"Valor");
        }
        if (transacao.getContaOrigem() == null || transacao.getContaOrigem().getId() <= 0){
            throw new ValorInformadoException(transacao.getContaOrigem().getId(), "Conta_origem");
        }
        if (transacao.getContaDestino() == null || transacao.getContaDestino().getId() <= 0){
            throw new ValorInformadoException(transacao.getContaOrigem().getId(), "Conta_origem");
        }
    }

    public List<Transacao> findAll() throws SQLException, FindRetornadoException {
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        List<Transacao> listaTransacao = transacaoDAO.findAll();
        if (listaTransacao.isEmpty()) {
            throw new FindRetornadoException("Consulta com todos as transações referente a essa conta");
        }
        return listaTransacao;
    }

    public Transacao findByContaOrigem(int id) throws Exception {
        if (id <= 0)
            throw new CampoLimiteTamanhoException("id", "1");
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        List<Transacao> listaTransacao = transacaoDAO.findByContaOrigem(id);

        if (listaTransacao.isEmpty()) {
            throw new FindRetornadoException("Conta_origem", id);
        }
        return (Transacao) listaTransacao;
    }
    public Transacao findByContaDestino(int id) throws Exception {
        if (id <= 0)
            throw new CampoLimiteTamanhoException("id", "1");
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        List<Transacao> listaTransacao = transacaoDAO.findByContaDestino(id);

        if (listaTransacao.isEmpty()) {
            throw new FindRetornadoException("conta_destino", id);
        }
        return (Transacao) listaTransacao;
    }

    public Transacao findByContaOrigemDestino(int id) throws Exception {
        if (id <= 0)
            throw new CampoLimiteTamanhoException("id", "1");
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        List<Transacao> listaTransacao = transacaoDAO.findByTodasMinha(id);

        if (listaTransacao.isEmpty()) {
            throw new FindRetornadoException("Consulta com todos as transações referente a essa conta", id);
        }
        return (Transacao) listaTransacao;
    }

    public void insert(Transacao transacao) throws Exception{
        validar(transacao);
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        transacaoDAO.insert(transacao);
    }
}
