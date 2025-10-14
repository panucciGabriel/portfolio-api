package br.com.gabrielpanucci.meuportfolio.portfolio_api.repository;

import br.com.gabrielpanucci.meuportfolio.portfolio_api.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
