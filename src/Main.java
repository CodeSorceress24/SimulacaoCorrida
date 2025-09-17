import controller.CarroController;
import controller.EquipeController;
import controller.TreinoController;
import view.Formula1View;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Formula1View view = new Formula1View();
        view.mostrarCabecalho();
        
        TreinoController treino = new TreinoController();
        
        EquipeController[] equipes = {
            new EquipeController("Ferrari"), new EquipeController("Mercedes"),
            new EquipeController("Red Bull"), new EquipeController("McLaren"),
            new EquipeController("Alpine"), new EquipeController("Aston Martin"),
            new EquipeController("Williams")
        };
        
        ExecutorService executor = Executors.newFixedThreadPool(14);
        
        for (int i = 0; i < equipes.length; i++) {
            CarroController carro1 = new CarroController("Piloto " + (i*2+1), equipes[i], treino, view);
            CarroController carro2 = new CarroController("Piloto " + (i*2+2), equipes[i], treino, view);
            
            executor.execute(carro1);
            executor.execute(carro2);
            
            try { Thread.sleep(100); } catch (InterruptedException e) { break; }
        }
        
        executor.shutdown();
        
        try {
            if (!executor.awaitTermination(2, TimeUnit.MINUTES)) {
                System.out.println("Tempo limite excedido. Forcando finalizacao...");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        
        view.mostrarGridLargada(treino.getGridLargada());
        view.mostrarEstatisticas();
    }
}