package br.com.cortez.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.HttpStatus;
import br.com.cortez.model.Pessoa;
import br.com.cortez.repository.PessoaRepository;
import br.com.cortez.model.Endereco;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private final PessoaRepository repository;

    public PessoaController(PessoaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Pessoa> listar() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa criar(@RequestBody Pessoa pessoa) {
        return repository.save(pessoa);
    }

    @PutMapping("/{id}")
    public Pessoa atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        pessoa.setId(id);
        return repository.save(pessoa);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PostMapping("/{id}/endereco")
    public Pessoa adicionarEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
        Pessoa pessoa = repository.findById(id).get();
        pessoa.setEndereco(endereco);
        return repository.save(pessoa);
    }

    @GetMapping("/{id}/endereco")
    public List<Endereco> listarEnderecos(@PathVariable Long id) {
        Pessoa pessoa = repository.findById(id).get();
        return (List<Endereco>) pessoa.getEndereco();
    }
}
