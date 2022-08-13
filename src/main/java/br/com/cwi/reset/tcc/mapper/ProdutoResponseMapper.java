package br.com.cwi.reset.tcc.mapper;

import br.com.cwi.reset.tcc.dominio.Produto;
import br.com.cwi.reset.tcc.response.ProdutoResponse;
import org.springframework.stereotype.Component;


@Component
public class ProdutoResponseMapper {

    public ProdutoResponse mapear(final Produto produto){

        return new ProdutoResponse(produto.getId(), produto.getTitulo(), produto.getDescricao(), produto.getValor(),
                produto.getUrlFoto(), produto.getCategoria(), produto.getTempoPreparo(),
                produto.getEstabelecimento().getCnpj());

    }
}
