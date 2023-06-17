package main;

import models.*;
import services.*;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Todos os deletes são realizados no final do arquivo
            // Operações de País
            PaisService paisService = new PaisService();
            Pais pais = new Pais();
            // Create País
            // pais.setId(11022);
            // pais.setNome("Novo Teste - País");
            // pais.setSigla("NT");
            // pais.setRegistroAcademico("00207623");
            // Para criar o registro basta descomentar a linha de baixo
            // System.out.println("País id: " + paisService.insert(pais) + " inserido com sucesso!");

            // Update País
            // pais.setId(11022);
            // pais.setNome("Novo Teste Update - País");
            // pais.setSigla("NT");
            // pais.setRegistroAcademico("207623");

            // paisService.update(pais);
            // System.out.println("País editado com sucesso!");

            // Find All País
            // List<Pais> paises = paisService.findAll();
            // System.out.println("Lista de Países: " + paises + "\n"
            //         + "Total de Países: " + paises.size());

            // Find By Id País
            // pais = paisService.findById(11022);
            // System.out.println("País: " + pais);

            // Operações de Estado
            EstadoService estadoService = new EstadoService();
            Estado estado = new Estado();

            // Create Estado
            // estado.setId(22011);
            // estado.setNome("Novo Teste - Estado");
            // estado.setSigla("NT");
            // estado.setPais(paisService.findById(11022));
            // estado.setRegistroAcademico("00207623");
            // Para criar o registro basta descomentar a linha de baixo
            // System.out.println("Estado id: " + estadoService.insert(estado) + " inserido com sucesso!");

            // Update Estado
            // estado.setId(22011);
            // estado.setNome("Novo Teste Update - Estado");
            // estado.setSigla("NT");
            // estado.setPais(paisService.findById(11022));
            // estado.setRegistroAcademico("207623");
            // estadoService.update(estado);
            // System.out.println("Estado editado com sucesso!");

            // Find All Estado
            // CONSULTA DEMORADA
            // List<Estado> estados = estadoService.findAll();
            // System.out.println("Lista de Estados: " + estados + "\n"
            //         + "Total de Estados: " + estados.size());

            // Find By Id Estado
            // estado = estadoService.findById(22011);
            // System.out.println("Estado: " + estado);

            // Operações de Cidade
            CidadeService cidadeService = new CidadeService();
            Cidade cidade = new Cidade();

            // Create Cidade
            // cidade.setId(22011);
            // cidade.setNome("Novo Teste - Cidade");
            // cidade.setRegistroAcademico("00207623");
            // cidade.setEstado(estadoService.findById(22011));

            // cidadeService.insert(cidade);
            // System.out.println("Cidade id: " + cidade.getId() + " inserido com sucesso!");

            // Update Cidade
            // cidade.setId(22011);
            // cidade.setNome("Novo Teste Update - Cidade");
            // cidade.setRegistroAcademico("207623");
            // cidade.setEstado(estadoService.findById(22011));
            // cidadeService.update(cidade);
            // System.out.println("Cidade editada com sucesso!");

            // Find All Cidade
            // CONSULTA DEMORADA
            // List<Cidade> cidades = cidadeService.findAll();
            // System.out.println("Lista de Cidades: " + cidades + "\n"
            //         + "Total de Cidades: " + cidades.size());

            // Find By Id Cidade
            // cidade = cidadeService.findById(22011);
            // System.out.println("Cidade: " + cidade);

            // Operações de Pessoa
            PessoaService pessoaService = new PessoaService();
            Pessoa pessoa = new Pessoa();

            // Create Pessoa
            // pessoa.setId(22011);
            // pessoa.setEmail("XXXXXXXXXXXXXXX");
            // pessoa.setRegistroAcademico("00207623");

            // System.out.println("Pessoa id: " + pessoaService.insert(pessoa) + " inserida com sucesso!");

            // Update Pessoa
            // pessoa.setId(22011);
            // pessoa.setEmail("email@teste.com");
            // pessoa.setRegistroAcademico("207623");
            // pessoaService.update(pessoa);
            // System.out.println("Pessoa editada com sucesso!");

            // Find All Pessoa
            // List<Pessoa> pessoas = pessoaService.findAll();
            // System.out.println("Lista de Pessoas: " + pessoas + "\n"
            //         + "Total de Pessoas: " + pessoas.size());

            // Find By Id Pessoa
            // pessoa = pessoaService.findById(22011);
            // System.out.println("Pessoa: " + pessoa);

            // Operações de PessoaJuridica
            PessoaJuridicaService pessoaJuridicaService = new PessoaJuridicaService();
            PessoaJuridica  pessoaJuridica = new PessoaJuridica();

            // Create PessoaJuridica
            // Obs.: Diz que o atributo passado para razao social é int, exibe mensagem de erro, porém, é inserido
            // no banco de dados. Não achei o motivo do ocorrido.
            // pessoaJuridica.setPessoa(pessoaService.findById(22011));
            // pessoaJuridica.setRazaoSocial("Pessoa Jurídica Teste");
            // pessoaJuridica.setCnpj("12345678901234");
            // pessoaJuridica.setCnaePrincipal("123456");
            // pessoaJuridica.setNomeFantasia("Nome Fantasia Teste");
            // System.out.println("PessoaJurídica id: " + pessoaJuridicaService.insert(pessoaJuridica) + " inserida com sucesso!");

            // Update PessoaJuridica
            // pessoaJuridica.setPessoa(pessoaService.findById(22011));
            // pessoaJuridica.setRazaoSocial("Pessoa Jurídica Teste Update");
            // pessoaJuridica.setCnpj("12345678901234");
            // pessoaJuridica.setCnaePrincipal("123456");
            // pessoaJuridica.setNomeFantasia("Nome Fantasia Teste Update");
            // pessoaJuridicaService.update(pessoaJuridica);
            // System.out.println("PessoaJurídica editada com sucesso!");

            // Find All PessoaJuridica
            // List<PessoaJuridica> pessoaJuridicas = pessoaJuridicaService.findAll();
            // System.out.println("Lista de PessoaJurídicas: " + pessoaJuridicas + "\n"
            //         + "Total de PessoaJurídicas: " + pessoaJuridicas.size());

            // Find By Id PessoaJuridica
            // pessoaJuridica = pessoaJuridicaService.findById(22011);
            // System.out.println("PessoaJurídica: " + pessoaJuridica);

            // Operações de PessoaFisica
            PessoaFisicaService pessoaFisicaService = new PessoaFisicaService();
            PessoaFisica pessoaFisica = new PessoaFisica();
            // Create PessoaFisica
            // pessoaFisica.setPessoa(pessoaService.findById(22011));
            // pessoaFisica.setNome("Pessoa Física Teste");
            // pessoaFisica.setCpf("12345678901");
            // pessoaFisica.setRg("123456789");
            // pessoaFisica.setDataNascimento(new java.sql.Date(new java.util.Date().getTime()));
            // System.out.println("Pessoa Fisíca id: " + pessoaFisicaService.insert(pessoaFisica) + " inserida com sucesso!");

            // Update PessoaFisica
            // pessoaFisica.setPessoa(pessoaService.findById(22011));
            // pessoaFisica.setNome("Pessoa Física Teste Update");
            // pessoaFisica.setCpf("12345678901");
            // pessoaFisica.setRg("123456789");
            // pessoaFisica.setDataNascimento(new java.sql.Date(new java.util.Date().getTime()));
            // pessoaFisicaService.update(pessoaFisica);
            // System.out.println("Pessoa Fisíca editada com sucesso!");

            // Find All PessoaFisica
            // List<PessoaFisica> pessoaFisicas = pessoaFisicaService.findAll();
            // System.out.println("Lista de PessoaFisicas: " + pessoaFisicas + "\n"
            //         + "Total de PessoaFisicas: " + pessoaFisicas.size());

            // Find By Id PessoaFisica
            // pessoaFisica = pessoaFisicaService.findById(22011);
            // System.out.println("PessoaFisica: " + pessoaFisica);

            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}