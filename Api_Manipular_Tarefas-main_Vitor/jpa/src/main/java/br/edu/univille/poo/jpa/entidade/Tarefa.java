package br.edu.univille.poo.jpa.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Tarefa {
    @Id
    @GeneratedValue
    private long id;
    private String titulo;
    @Column(length = 10000)
    private String descricao;
    @Column(nullable = false)
    private boolean finalizado;
    @Column(nullable = false)
    private LocalDate dataprevistadefinalizacao;
    private LocalDate datafinalizacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public LocalDate getDataprevistadefinalizacao() {
        return dataprevistadefinalizacao;
    }

    public void setDataprevistadefinalizacao(LocalDate dataprevistadefinalizacao) {
        this.dataprevistadefinalizacao = dataprevistadefinalizacao;
    }

    public LocalDate getDatafinalizacao() {
        return datafinalizacao;
    }

    public void setDatafinalizacao(LocalDate datafinalizacao) {
        this.datafinalizacao = datafinalizacao;
    }
}

