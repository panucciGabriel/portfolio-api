package br.com.gabrielpanucci.meuportfolio.portfolio_api.repository;

import br.com.gabrielpanucci.meuportfolio.portfolio_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
}
