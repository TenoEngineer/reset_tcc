package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Estabelecimento;
import br.com.cwi.reset.tcc.exceptions.EstabelecimentoNaoEncontradoException;
import br.com.cwi.reset.tcc.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarEstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository repository;

    public Estabelecimento buscar(final Long id){

        Estabelecimento estabelecimento = repository.findById(id).orElse(null);

        if(estabelecimento == null){
            throw new EstabelecimentoNaoEncontradoException();
        }

        return estabelecimento;
    }
}
