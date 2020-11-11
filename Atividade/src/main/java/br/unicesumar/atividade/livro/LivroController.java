package br.unicesumar.atividade.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    @Cacheable(cacheNames = Livro.CACHE_NAME, key = "#root.method.name")
    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(cacheNames = Livro.CACHE_NAME, key="#a0")
    public Livro findById(String id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("id Not Found: " + id));
    }

    @PostMapping("/api/livro")
    @CacheEvict(cacheNames = Livro.CACHE_NAME, allEntries = true)
    public Livro create(@RequestBody Livro livro) {
        return livroRepository.save(livro);
    }

    @DeleteMapping("/deletar/{id}")
    @CacheEvict(cacheNames = Livro.CACHE_NAME, key="#id")
    public void delete(@PathVariable String id) {
        if(id == null) {
            throw new EntityNotFoundException("Empty id");
        }
        
        livroRepository.deleteById(id);
    }
}