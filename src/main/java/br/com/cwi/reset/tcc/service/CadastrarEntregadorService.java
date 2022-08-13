package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Entregador;
import br.com.cwi.reset.tcc.exceptions.CpfInvalidoException;
import br.com.cwi.reset.tcc.repository.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarEntregadorService {

    @Autowired
    private EntregadorRepository repository;

    public Entregador salvar(final Entregador entregador) {
        entregador.setId(null);
        validarEntregador(entregador);
        return repository.save(entregador);
    }

    public void validarEntregador(final Entregador entregador) {
        if(repository.existsByCpf(entregador.getCpf())){
            throw new CpfInvalidoException();
        }
    }
}
