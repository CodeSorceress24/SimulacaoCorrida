package view;

import controller.CarroController;
import java.util.List;

public class Formula1View {
    
    public void mostrarCabecalho() {
        System.out.println("TREINO DE FORMULA 1 - AUTOMATIZADO");
        System.out.println("===================================");
        System.out.println("Regras:");
        System.out.println("- Maximo 5 carros na pista");
        System.out.println("- Apenas 1 carro por equipe");
        System.out.println("- 3 voltas por piloto");
        System.out.println("- 7 equipes, 14 carros no total");
        System.out.println("===================================");
        System.out.println();
    }
    
    public void mostrarEntrouPista(String piloto, String equipe) {
        System.out.println(piloto + " (" + equipe + ") entrou na pista");
    }
    
    public void mostrarEsperando(String piloto, String equipe) {
        System.out.println(piloto + " (" + equipe + ") aguardando - companheiro na pista");
    }
    
    public void mostrarVolta(String piloto, String equipe, int volta, double tempo) {
        System.out.println(piloto + " (" + equipe + ") - Volta " + volta + ": " + 
                          String.format("%.2f", tempo) + "s");
    }
    
    public void mostrarMelhorVolta(String piloto, String equipe, double tempo) {
        System.out.println(piloto + " (" + equipe + ") - Melhor volta: " + 
                          String.format("%.2f", tempo) + "s");
    }
    
    public void mostrarSaiuPista(String piloto, String equipe) {
        System.out.println(piloto + " (" + equipe + ") saiu da pista");
    }
    
    public void mostrarInterrupcao(String piloto, String equipe) {
        System.out.println(piloto + " (" + equipe + ") - Treino interrompido");
    }
    
    public void mostrarGridLargada(List<CarroController> grid) {
        System.out.println();
        System.out.println("GRID DE LARGADA - MELHORES TEMPOS");
        System.out.println("==================================");
        
        for (int i = 0; i < grid.size(); i++) {
            CarroController carro = grid.get(i);
            System.out.printf("%2dº - %s (%s) - %6.2fs%n",
                i + 1,
                carro.getNomePiloto(),
                carro.getEquipe().getNome(),
                carro.getMelhorVolta());
        }
    }
    
    public void mostrarEstatisticas() {
        System.out.println();
        System.out.println("Treino concluido com sucesso!");
    }
}