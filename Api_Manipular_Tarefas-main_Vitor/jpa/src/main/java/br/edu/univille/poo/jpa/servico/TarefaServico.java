package br.edu.univille.poo.jpa.servico;

import br.edu.univille.poo.jpa.entidade.Tarefa;
import br.edu.univille.poo.jpa.repositorio.TarefaRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaServico {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> obterTodos() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> obterPeloId(Long id) {
        return tarefaRepository.findById(id);
    }

    public List<Tarefa> obterfinalizadasfs() {return tarefaRepository.findByFinalizadoFalse();}

    public List<Tarefa> obterfinalizadastr() {return tarefaRepository.findByFinalizadoTrue();}

    public List<Tarefa> obterdataprevistafinalizacaobf(LocalDate dataAtual) {return tarefaRepository.findByFinalizadoFalseAndDataPrevistaFinalizacaoBefore(dataAtual);}

    public List<Tarefa> obterdataprevistafinalizacaobeforebt(LocalDate inicio, LocalDate fim) {return tarefaRepository.findByFinalizadoFalseAndDataPrevistaFinalizacaoBetween(inicio, fim);}

    public Tarefa incluir(Tarefa tarefa) {
        tarefa.setId(0);
        if(Strings.isBlank(tarefa.getTitulo())){
            throw new RuntimeException("Título não informado.");
        }
        if(tarefa.getDatafinalizacao() == null){
            throw new RuntimeException("Data não informada.");
        }
        if (tarefa.getTitulo().length() < 5){
            throw new RuntimeException("Título menor de 5 caractéres.");
        }
        tarefa = tarefaRepository.save(tarefa);
        return tarefa;
    }

    public Tarefa atualizar(Tarefa tarefa) {
        Tarefa antigo = tarefaRepository.findById(tarefa.getId()).orElse(null);
        if(antigo == null){
            throw new RuntimeException("Tarefa não foi encontrada.");
        }
        antigo.setDatafinalizacao(tarefa.getDatafinalizacao());
        antigo.setDescricao(tarefa.getDescricao());
        antigo.setId(tarefa.getId());
        antigo.setTitulo(tarefa.getTitulo());
        antigo.setFinalizado(tarefa.isFinalizado());
        antigo.setDataprevistadefinalizacao(tarefa.getDataprevistadefinalizacao());

        if(Strings.isBlank(antigo.getTitulo())){
            throw new RuntimeException("Título não informado.");
        }
        if(antigo.getDatafinalizacao() == null){
            throw new RuntimeException("Data não informada.");
        }
        if (antigo.getTitulo().length() < 5){
            throw new RuntimeException("Título menor de 5 caractéres.");
        }
        if (antigo.isFinalizado())
            throw new RuntimeException("A tarefa está finalizada");

        return tarefaRepository.save(antigo);
    }

    public void excluir(Tarefa tarefa) {
        var antigo = tarefaRepository.findById(tarefa.getId()).orElse(null);
        if(antigo == null){
            throw new RuntimeException("Tarefa não encontrada.");
        }
        if (antigo.isFinalizado())
            throw new RuntimeException("A tarefa está finalizada");

        tarefaRepository.delete(antigo);
    }

    public void finalizartarefa(Tarefa tarefa) {
        var antigo = tarefaRepository.findById(tarefa.getId()).orElse(null);
        if(antigo == null){
            throw new RuntimeException("Tarefa não encontrada.");
        }
        antigo.setFinalizado(true);
        tarefaRepository.save(antigo);
    }
}
