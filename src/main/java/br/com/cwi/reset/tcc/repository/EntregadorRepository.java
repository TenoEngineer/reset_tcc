package br.com.cwi.reset.tcc.repository;

import br.com.cwi.reset.tcc.dominio.Entregador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, Long> {

    Boolean existsByCpf(String cpf);

    Page<Entregador> findAllByOrderByNome(Pageable pageable);
}
