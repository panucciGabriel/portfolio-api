package br.com.gabrielpanucci.meuportfolio.portfolio_api.controller;

import br.com.gabrielpanucci.meuportfolio.portfolio_api.model.Projeto;
import br.com.gabrielpanucci.meuportfolio.portfolio_api.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Import geral para as anotações

import java.util.List;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoRepository projetoRepository;

    @GetMapping
    public List<Projeto> listarTodos() {
        return projetoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Projeto> criarProjeto(@RequestBody Projeto novoProjeto) {
        Projeto projetoSalvo = projetoRepository.save(novoProjeto);
        return new ResponseEntity<>(projetoSalvo, HttpStatus.CREATED);
    }

    // NOVO MÉTODO PARA ATUALIZAR (UPDATE)
    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizarProjeto(@PathVariable Long id, @RequestBody Projeto projetoAtualizado) {
        return projetoRepository.findById(id)
                .map(projetoExistente -> {
                    projetoExistente.setTitulo(projetoAtualizado.getTitulo());
                    projetoExistente.setDescricao(projetoAtualizado.getDescricao());
                    projetoExistente.setUrlImagem(projetoAtualizado.getUrlImagem());
                    projetoExistente.setUrlProjeto(projetoAtualizado.getUrlProjeto());
                    Projeto projetoSalvo = projetoRepository.save(projetoExistente);
                    return ResponseEntity.ok(projetoSalvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // NOVO MÉTODO PARA DELETAR (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProjeto(@PathVariable Long id) {
        return projetoRepository.findById(id)
                .map(projeto -> {
                    projetoRepository.delete(projeto);
                    // Esta linha garante o status 204
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}