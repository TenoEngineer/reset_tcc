package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Estabelecimento;
import br.com.cwi.reset.tcc.dominio.HorarioFuncionamento;
import br.com.cwi.reset.tcc.exceptions.CnpjExistenteException;
import br.com.cwi.reset.tcc.exceptions.HorarioException;
import br.com.cwi.reset.tcc.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarEstabelecimentoService {
    
    @Autowired
    private EstabelecimentoRepository repository;
    
    public Estabelecimento salvar(final Estabelecimento estabelecimento){
        estabelecimento.setId(null);
        validarEstabelecimento(estabelecimento);
        return repository.save(estabelecimento);
    }

    private void validarEstabelecimento(final Estabelecimento estabelecimento){
        if(repository.existsByCnpj(estabelecimento.getCnpj())){
            throw new CnpjExistenteException();
        }

        for(HorarioFuncionamento horarioFuncionamento : estabelecimento.getHorariosFuncionamento()){
            if (horarioFuncionamento.getDiaSemana() == null || horarioFuncionamento.getHorarioAbertura() == null ||
                    horarioFuncionamento.getHorarioFechamento() == null){
                throw new HorarioException();
            }
        }
    }
}
