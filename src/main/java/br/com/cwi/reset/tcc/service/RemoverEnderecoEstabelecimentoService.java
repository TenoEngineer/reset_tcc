package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Endereco;
import br.com.cwi.reset.tcc.dominio.Estabelecimento;
import br.com.cwi.reset.tcc.exceptions.EnderecoNaoCadastradoException;
import br.com.cwi.reset.tcc.exceptions.IdNuloException;
import br.com.cwi.reset.tcc.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoverEnderecoEstabelecimentoService {

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private BuscarEstabelecimentoService buscarEstabelecimento;

    public void remover(final Long id,final Long idEndereco) {

        if (id == null || idEndereco == null) {
            throw new IdNuloException();
        }

        Estabelecimento estabelecimento = buscarEstabelecimento.buscar(id);
        Endereco endereco = repository.findById(idEndereco).orElse(null);

        if (endereco == null) {
            throw new EnderecoNaoCadastradoException();
        }

        if (estabelecimento.getEnderecos().contains(endereco)) {
            estabelecimento.getEnderecos().remove(endereco);
            repository.delete(endereco);
        } else {
            throw new EnderecoNaoCadastradoException();
        }
    }

}


