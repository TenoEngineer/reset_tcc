package br.com.cwi.reset.tcc.repository;

import br.com.cwi.reset.tcc.dominio.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
