package services;

import exceptions.CampoNaoInformadoException;
import exceptions.EntidadeNaoInformadaException;
import exceptions.TamanhoCampoInvalidoException;
import interfaces.CrudInterface;
import models.PessoaJuridica;
import repositories.PessoaDAO;
import repositories.PessoaJuridicaDAO;

import java.sql.SQLException;
import java.util.List;

public class PessoaJuridicaService implements CrudInterface<PessoaJuridica> {

    private void validar(PessoaJuridica pessoaJuridica) throws TamanhoCampoInvalidoException, EntidadeNaoInformadaException, CampoNaoInformadoException
    {
        if (pessoaJuridica == null) {
            throw new EntidadeNaoInformadaException("Pessoa Jurídica");
        }

        if (pessoaJuridica.getPessoa() == null) {
            throw new EntidadeNaoInformadaException("Pessoa");
        }

        if (pessoaJuridica.getRazaoSocial() == null || pessoaJuridica.getRazaoSocial().isEmpty() || pessoaJuridica.getRazaoSocial().isBlank()) {
            throw new CampoNaoInformadoException("Razão Social");
        }

        if (pessoaJuridica.getCnpj() == null || pessoaJuridica.getCnpj().isEmpty() || pessoaJuridica.getCnpj().isBlank()) {
            throw new CampoNaoInformadoException("CNPJ");
        }

        if (pessoaJuridica.getCnaePrincipal() == null || pessoaJuridica.getCnaePrincipal().isEmpty() || pessoaJuridica.getCnaePrincipal().isBlank()) {
            throw new CampoNaoInformadoException("CNAE Principal");
        }

        if (pessoaJuridica.getNomeFantasia() == null || pessoaJuridica.getNomeFantasia().isEmpty() || pessoaJuridica.getNomeFantasia().isBlank()) {
            throw new CampoNaoInformadoException("Nome Fantasia");
        }
    }
    @Override
    public List<PessoaJuridica> findAll() throws SQLException {
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        List<PessoaJuridica> pessoaJuridicaList = pessoaJuridicaDAO.findAll();
        if (pessoaJuridicaList.size() == 0) {
            throw new SQLException("Não há pessoas jurídicas cadastradas.");
        }
        return pessoaJuridicaList;
    }

    @Override
    public PessoaJuridica findById(int id) throws TamanhoCampoInvalidoException, Exception {
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.findById(id);
        if (pessoaJuridica == null) {
            throw new Exception("Pessoa Jurídica não encontrada.");
        }
        return pessoaJuridica;
    }

    @Override
    public int insert(PessoaJuridica pessoaJuridica) throws SQLException, EntidadeNaoInformadaException, CampoNaoInformadoException, TamanhoCampoInvalidoException {
        validar(pessoaJuridica);
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        return pessoaJuridicaDAO.insert(pessoaJuridica);
    }

    @Override
    public void update(PessoaJuridica pessoaJuridica) throws SQLException, CampoNaoInformadoException, EntidadeNaoInformadaException, TamanhoCampoInvalidoException {
        validar(pessoaJuridica);
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        pessoaJuridicaDAO.update(pessoaJuridica);
    }

    @Override
    public void delete(int id) throws SQLException, CampoNaoInformadoException {
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        pessoaJuridicaDAO.delete(id);
    }
}
