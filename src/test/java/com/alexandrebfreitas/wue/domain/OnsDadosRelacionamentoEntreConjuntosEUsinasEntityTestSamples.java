package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class OnsDadosRelacionamentoEntreConjuntosEUsinasEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static OnsDadosRelacionamentoEntreConjuntosEUsinasEntity getOnsDadosRelacionamentoEntreConjuntosEUsinasEntitySample1() {
        return new OnsDadosRelacionamentoEntreConjuntosEUsinasEntity()
            .id(1L)
            .idSubsistema("idSubsistema1")
            .nomSubsistema("nomSubsistema1")
            .estadId("estadId1")
            .nomEstado("nomEstado1")
            .idTipousina("idTipousina1")
            .idConjuntousina(1)
            .idOnsConjunto("idOnsConjunto1")
            .idOnsUsina("idOnsUsina1")
            .nomConjunto("nomConjunto1")
            .nomUsina("nomUsina1")
            .ceg("ceg1");
    }

    public static OnsDadosRelacionamentoEntreConjuntosEUsinasEntity getOnsDadosRelacionamentoEntreConjuntosEUsinasEntitySample2() {
        return new OnsDadosRelacionamentoEntreConjuntosEUsinasEntity()
            .id(2L)
            .idSubsistema("idSubsistema2")
            .nomSubsistema("nomSubsistema2")
            .estadId("estadId2")
            .nomEstado("nomEstado2")
            .idTipousina("idTipousina2")
            .idConjuntousina(2)
            .idOnsConjunto("idOnsConjunto2")
            .idOnsUsina("idOnsUsina2")
            .nomConjunto("nomConjunto2")
            .nomUsina("nomUsina2")
            .ceg("ceg2");
    }

    public static OnsDadosRelacionamentoEntreConjuntosEUsinasEntity getOnsDadosRelacionamentoEntreConjuntosEUsinasEntityRandomSampleGenerator() {
        return new OnsDadosRelacionamentoEntreConjuntosEUsinasEntity()
            .id(longCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString())
            .estadId(UUID.randomUUID().toString())
            .nomEstado(UUID.randomUUID().toString())
            .idTipousina(UUID.randomUUID().toString())
            .idConjuntousina(intCount.incrementAndGet())
            .idOnsConjunto(UUID.randomUUID().toString())
            .idOnsUsina(UUID.randomUUID().toString())
            .nomConjunto(UUID.randomUUID().toString())
            .nomUsina(UUID.randomUUID().toString())
            .ceg(UUID.randomUUID().toString());
    }
}
