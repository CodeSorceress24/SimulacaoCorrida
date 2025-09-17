package controller;

import java.util.concurrent.Semaphore;

public class EquipeController {
    private final String nome;
    private final Semaphore semaforoEquipe;

    public EquipeController(String nome) {
        this.nome = nome;
        this.semaforoEquipe = new Semaphore(1);
    }

    public String getNome() {
        return nome;
    }

    public boolean tentarEntrar() {
        return semaforoEquipe.tryAcquire();
    }

    public void sair() {
        semaforoEquipe.release();
    }
}