package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntitySample1() {
        return new OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity()
            .id(1L)
            .dscAgregacao("dscAgregacao1")
            .dscCaracteristica("dscCaracteristica1")
            .numNprcConcluidas(1)
            .numNprpProgramadas(1)
            .numNpratAtrasadas(1)
            .numNpraAntecipadas(1)
            .numNprcpConcluidasPrazo(1);
    }

    public static OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntitySample2() {
        return new OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity()
            .id(2L)
            .dscAgregacao("dscAgregacao2")
            .dscCaracteristica("dscCaracteristica2")
            .numNprcConcluidas(2)
            .numNprpProgramadas(2)
            .numNpratAtrasadas(2)
            .numNpraAntecipadas(2)
            .numNprcpConcluidasPrazo(2);
    }

    public static OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntityRandomSampleGenerator() {
        return new OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity()
            .id(longCount.incrementAndGet())
            .dscAgregacao(UUID.randomUUID().toString())
            .dscCaracteristica(UUID.randomUUID().toString())
            .numNprcConcluidas(intCount.incrementAndGet())
            .numNprpProgramadas(intCount.incrementAndGet())
            .numNpratAtrasadas(intCount.incrementAndGet())
            .numNpraAntecipadas(intCount.incrementAndGet())
            .numNprcpConcluidasPrazo(intCount.incrementAndGet());
    }
}
