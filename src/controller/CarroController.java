package controller;

import java.util.Random;
import view.Formula1View;

public class CarroController implements Runnable {
    private final String nomePiloto;
    private final EquipeController equipe;
    private final TreinoController treino;
    private final Formula1View view;
    private double melhorVolta = Double.MAX_VALUE;
    private final Random random;

    public CarroController(String nomePiloto, EquipeController equipe, TreinoController treino, Formula1View view) {
        this.nomePiloto = nomePiloto;
        this.equipe = equipe;
        this.treino = treino;
        this.view = view;
        this.random = new Random();
    }

    public String getNomePiloto() {
        return nomePiloto;
    }

    public EquipeController getEquipe() {
        return equipe;
    }

    public double getMelhorVolta() {
        return melhorVolta;
    }

    @Override
    public void run() {
        try {
            if (treino.entrarNaPista(this)) {
                view.mostrarEntrouPista(nomePiloto, equipe.getNome());
                
                for (int volta = 1; volta <= 3; volta++) {
                    double tempoVolta = 60 + (30 * random.nextDouble());
                    Thread.sleep((long) (tempoVolta * 10));
                    
                    view.mostrarVolta(nomePiloto, equipe.getNome(), volta, tempoVolta);
                    
                    if (tempoVolta < melhorVolta) {
                        melhorVolta = tempoVolta;
                    }
                }
                
                view.mostrarMelhorVolta(nomePiloto, equipe.getNome(), melhorVolta);
            } else {
                view.mostrarEsperando(nomePiloto, equipe.getNome());
            }
        } catch (InterruptedException e) {
            view.mostrarInterrupcao(nomePiloto, equipe.getNome());
            Thread.currentThread().interrupt();
        } finally {
            treino.sairDaPista(this);
        }
    }
}