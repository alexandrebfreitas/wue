package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsEarDiarioReservatorioEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsEarDiarioReservatorioEntity getOnsEarDiarioReservatorioEntitySample1() {
        return new OnsEarDiarioReservatorioEntity()
            .id(1L)
            .idSubsistemaJusante("idSubsistemaJusante1")
            .nomSubsistemaJusante("nomSubsistemaJusante1");
    }

    public static OnsEarDiarioReservatorioEntity getOnsEarDiarioReservatorioEntitySample2() {
        return new OnsEarDiarioReservatorioEntity()
            .id(2L)
            .idSubsistemaJusante("idSubsistemaJusante2")
            .nomSubsistemaJusante("nomSubsistemaJusante2");
    }

    public static OnsEarDiarioReservatorioEntity getOnsEarDiarioReservatorioEntityRandomSampleGenerator() {
        return new OnsEarDiarioReservatorioEntity()
            .id(longCount.incrementAndGet())
            .idSubsistemaJusante(UUID.randomUUID().toString())
            .nomSubsistemaJusante(UUID.randomUUID().toString());
    }
}
