package br.edu.univille.poo.jpa.controller;

import br.edu.univille.poo.jpa.entidade.Tarefa;
import br.edu.univille.poo.jpa.servico.TarefaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController()
@RequestMapping("api/tarefa")
public class TarefaRestController {

    @Autowired
    private TarefaServico tarefaServico;

    @GetMapping
    public List<Tarefa> obterTodos(){
        return tarefaServico.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> obterPeloId(@PathVariable Long id){
        var opt = tarefaServico.obterPeloId(id);
        return opt.map(tarefa -> new ResponseEntity<>(tarefa, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> incluir(@RequestBody Tarefa tarefa){
        try {
            tarefa = tarefaServico.incluir(tarefa);
            return new ResponseEntity<>(tarefa, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody Tarefa tarefa){
        try {
            tarefa = tarefaServico.atualizar(tarefa);
            return new ResponseEntity<>(tarefa, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> excluir(@RequestBody Tarefa tarefa){
        try{
            tarefaServico.excluir(tarefa);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public List<Tarefa> consultatarefasnaofinalizadas(){
        return tarefaServico.obterfinalizadasfs();
        }

    @GetMapping
    public List<Tarefa> consultatarefasfinalizadas(){
        return tarefaServico.obterfinalizadastr();
    }

    @GetMapping
    public List<Tarefa> consultartarefastarasadas(@RequestBody LocalDate dataatual){
        return tarefaServico.obterdataprevistafinalizacaobf(dataatual);
    }

    @GetMapping
    public List<Tarefa> consultartarefasentreduasdatas(@RequestBody LocalDate inicio, @RequestBody LocalDate fim){
        return tarefaServico.obterdataprevistafinalizacaobeforebt(inicio, fim);
    }

    @PutMapping
    public ResponseEntity<?> finalizartarefa(@RequestBody Tarefa tarefa){
        try {
            tarefaServico.finalizartarefa(tarefa);
            return new ResponseEntity<>(tarefa, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}





