package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Estabelecimento;
import br.com.cwi.reset.tcc.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarEstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository repository;

    public Page<Estabelecimento> listarEstabelecimento(Pageable pageable){
        return repository.findAllByOrderByNomeFantasia(pageable);
    }
}
