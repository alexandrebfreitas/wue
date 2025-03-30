package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class OnsDadosValoresProgramacaoDiariaEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static OnsDadosValoresProgramacaoDiariaEntity getOnsDadosValoresProgramacaoDiariaEntitySample1() {
        return new OnsDadosValoresProgramacaoDiariaEntity()
            .id(1L)
            .numPatamar(1)
            .codExibicaousina("codExibicaousina1")
            .nomUsina("nomUsina1")
            .tipGeracao("tipGeracao1")
            .nomModalidadeoperacao("nomModalidadeoperacao1")
            .idSubsistema("idSubsistema1")
            .nomSubsistema("nomSubsistema1")
            .idEstado("idEstado1")
            .nomEstado("nomEstado1");
    }

    public static OnsDadosValoresProgramacaoDiariaEntity getOnsDadosValoresProgramacaoDiariaEntitySample2() {
        return new OnsDadosValoresProgramacaoDiariaEntity()
            .id(2L)
            .numPatamar(2)
            .codExibicaousina("codExibicaousina2")
            .nomUsina("nomUsina2")
            .tipGeracao("tipGeracao2")
            .nomModalidadeoperacao("nomModalidadeoperacao2")
            .idSubsistema("idSubsistema2")
            .nomSubsistema("nomSubsistema2")
            .idEstado("idEstado2")
            .nomEstado("nomEstado2");
    }

    public static OnsDadosValoresProgramacaoDiariaEntity getOnsDadosValoresProgramacaoDiariaEntityRandomSampleGenerator() {
        return new OnsDadosValoresProgramacaoDiariaEntity()
            .id(longCount.incrementAndGet())
            .numPatamar(intCount.incrementAndGet())
            .codExibicaousina(UUID.randomUUID().toString())
            .nomUsina(UUID.randomUUID().toString())
            .tipGeracao(UUID.randomUUID().toString())
            .nomModalidadeoperacao(UUID.randomUUID().toString())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString())
            .idEstado(UUID.randomUUID().toString())
            .nomEstado(UUID.randomUUID().toString());
    }
}
