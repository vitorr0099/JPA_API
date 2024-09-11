package br.edu.univille.poo.jpa.repositorio;

import
        br.edu.univille.poo.jpa.entidade.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findAll();
    Optional<Tarefa> findById(Long id);
    List<Tarefa> findByFinalizadoFalse();
    List<Tarefa> findByFinalizadoTrue();
    List<Tarefa> findByFinalizadoFalseAndDataPrevistaFinalizacaoBefore(LocalDate dataAtual);
    List<Tarefa> findByFinalizadoFalseAndDataPrevistaFinalizacaoBetween(LocalDate inicio, LocalDate fim);

}
