package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class OnsDadosProgramadosElementosFluxoControladoEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static OnsDadosProgramadosElementosFluxoControladoEntity getOnsDadosProgramadosElementosFluxoControladoEntitySample1() {
        return new OnsDadosProgramadosElementosFluxoControladoEntity()
            .id(1L)
            .numPatamar(1)
            .nomElementofluxocontrolado("nomElementofluxocontrolado1")
            .dscElementofluxocontrolado("dscElementofluxocontrolado1")
            .tipTerminal(1)
            .codSubmercado("codSubmercado1");
    }

    public static OnsDadosProgramadosElementosFluxoControladoEntity getOnsDadosProgramadosElementosFluxoControladoEntitySample2() {
        return new OnsDadosProgramadosElementosFluxoControladoEntity()
            .id(2L)
            .numPatamar(2)
            .nomElementofluxocontrolado("nomElementofluxocontrolado2")
            .dscElementofluxocontrolado("dscElementofluxocontrolado2")
            .tipTerminal(2)
            .codSubmercado("codSubmercado2");
    }

    public static OnsDadosProgramadosElementosFluxoControladoEntity getOnsDadosProgramadosElementosFluxoControladoEntityRandomSampleGenerator() {
        return new OnsDadosProgramadosElementosFluxoControladoEntity()
            .id(longCount.incrementAndGet())
            .numPatamar(intCount.incrementAndGet())
            .nomElementofluxocontrolado(UUID.randomUUID().toString())
            .dscElementofluxocontrolado(UUID.randomUUID().toString())
            .tipTerminal(intCount.incrementAndGet())
            .codSubmercado(UUID.randomUUID().toString());
    }
}
