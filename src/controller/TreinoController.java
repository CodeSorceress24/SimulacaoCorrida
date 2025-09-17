package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Semaphore;

public class TreinoController {
    private final Semaphore semaforoPista = new Semaphore(5);
    private final List<CarroController> gridLargada = Collections.synchronizedList(new ArrayList<>());

    public boolean entrarNaPista(CarroController carro) throws InterruptedException {
        if (!carro.getEquipe().tentarEntrar()) {
            return false;
        }
        
        semaforoPista.acquire();
        return true;
    }

    public void sairDaPista(CarroController carro) {
        carro.getEquipe().sair();
        semaforoPista.release();
        
        synchronized (gridLargada) {
            gridLargada.add(carro);
        }
    }

    public List<CarroController> getGridLargada() {
        synchronized (gridLargada) {
            gridLargada.sort(Comparator.comparingDouble(CarroController::getMelhorVolta));
            return new ArrayList<>(gridLargada);
        }
    }
}