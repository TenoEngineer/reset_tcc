package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Endereco;
import br.com.cwi.reset.tcc.dominio.Estabelecimento;
import br.com.cwi.reset.tcc.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdicionarEnderecoEstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository repository;

    @Autowired
    private BuscarEstabelecimentoService buscarEstabelecimento;

    public Estabelecimento adicionar(final Estabelecimento estabelecimento, final Endereco endereco) {

        Estabelecimento estabelecimentoEncontrado = buscarEstabelecimento.buscar(estabelecimento.getId());
        estabelecimentoEncontrado.getEnderecos().add(endereco);
        return repository.save(estabelecimentoEncontrado);

    }
}
