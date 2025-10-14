package br.com.gabrielpanucci.meuportfolio.portfolio_api.config;

import br.com.gabrielpanucci.meuportfolio.portfolio_api.model.Projeto;
import br.com.gabrielpanucci.meuportfolio.portfolio_api.model.Usuario; // IMPORT NECESSÁRIO
import br.com.gabrielpanucci.meuportfolio.portfolio_api.repository.ProjetoRepository;
import br.com.gabrielpanucci.meuportfolio.portfolio_api.repository.UsuarioRepository; // IMPORT NECESSÁRIO
import org.springframework.beans.factory.annotation.Autowired; // IMPORT NECESSÁRIO
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder; // IMPORT NECESSÁRIO

@Configuration
public class DataInitializerConfig {

    @Autowired
    private PasswordEncoder passwordEncoder; // 1. INJETANDO O CRIPTOGRAFADOR

    @Bean
    CommandLineRunner initDatabase(ProjetoRepository projetoRepository, UsuarioRepository usuarioRepository) { // 2. RECEBENDO O REPOSITÓRIO DE USUÁRIO
        return args -> {
            // --- Carregando Projetos de Exemplo ---
            System.out.println(">>> [DataInitializer] Carregando projetos de exemplo...");
            // (O código para criar os projetos continua aqui, sem alterações)
            Projeto p1 = new Projeto();
            p1.setTitulo("Meu Portfólio Pessoal (Backend)");
            p1.setDescricao("API REST feita com Spring Boot para gerenciar os projetos do meu portfólio.");
            p1.setUrlImagem("https://i.imgur.com/URL_DA_SUA_IMAGEM.png");
            p1.setUrlProjeto("https://github.com/seu-usuario/portfolio-api");
            projetoRepository.save(p1);
            System.out.println(">>> [DataInitializer] Projetos de exemplo carregados.");


            // --- Carregando Usuário Admin ---
            System.out.println(">>> [DataInitializer] Verificando e carregando usuário admin...");
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                // 3. CRIPTOGRAFANDO A SENHA ANTES DE SALVAR
                admin.setPassword(passwordEncoder.encode("admin123"));

                usuarioRepository.save(admin);
                System.out.println(">>> [DataInitializer] Usuário 'admin' criado com sucesso.");
            } else {
                System.out.println(">>> [DataInitializer] Usuário 'admin' já existe.");
            }
        };
    }
}