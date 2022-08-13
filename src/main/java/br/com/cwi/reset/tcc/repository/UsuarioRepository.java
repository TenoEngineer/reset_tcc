package br.com.cwi.reset.tcc.repository;

import br.com.cwi.reset.tcc.dominio.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    Page<Usuario> findAllByOrderByNome(Pageable pageable);

}
