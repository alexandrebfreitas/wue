package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsDadosDisponibilidadeUsinasEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsDadosDisponibilidadeUsinasEntity getOnsDadosDisponibilidadeUsinasEntitySample1() {
        return new OnsDadosDisponibilidadeUsinasEntity()
            .id(1L)
            .idSubsistema("idSubsistema1")
            .nomSubsistema("nomSubsistema1")
            .idEstado("idEstado1")
            .nomEstado("nomEstado1")
            .nomUsina("nomUsina1")
            .idTipousina("idTipousina1")
            .nomTipocombustivel("nomTipocombustivel1")
            .idOns("idOns1")
            .ceg("ceg1");
    }

    public static OnsDadosDisponibilidadeUsinasEntity getOnsDadosDisponibilidadeUsinasEntitySample2() {
        return new OnsDadosDisponibilidadeUsinasEntity()
            .id(2L)
            .idSubsistema("idSubsistema2")
            .nomSubsistema("nomSubsistema2")
            .idEstado("idEstado2")
            .nomEstado("nomEstado2")
            .nomUsina("nomUsina2")
            .idTipousina("idTipousina2")
            .nomTipocombustivel("nomTipocombustivel2")
            .idOns("idOns2")
            .ceg("ceg2");
    }

    public static OnsDadosDisponibilidadeUsinasEntity getOnsDadosDisponibilidadeUsinasEntityRandomSampleGenerator() {
        return new OnsDadosDisponibilidadeUsinasEntity()
            .id(longCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString())
            .idEstado(UUID.randomUUID().toString())
            .nomEstado(UUID.randomUUID().toString())
            .nomUsina(UUID.randomUUID().toString())
            .idTipousina(UUID.randomUUID().toString())
            .nomTipocombustivel(UUID.randomUUID().toString())
            .idOns(UUID.randomUUID().toString())
            .ceg(UUID.randomUUID().toString());
    }
}
