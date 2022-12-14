package br.com.cwi.reset.tcc.repository;

import br.com.cwi.reset.tcc.dominio.Estabelecimento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {

    boolean existsByCnpj(String cnpj);

    Estabelecimento findAllById(Long idEstabelecimento);

    Page<Estabelecimento> findAllByOrderByNomeFantasia(Pageable pageable);

}
