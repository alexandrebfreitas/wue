package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsBalancoEnergiaNosSubsistemasEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsBalancoEnergiaNosSubsistemasEntity getOnsBalancoEnergiaNosSubsistemasEntitySample1() {
        return new OnsBalancoEnergiaNosSubsistemasEntity().id(1L).idSubsistema("idSubsistema1").nomSubsistema("nomSubsistema1");
    }

    public static OnsBalancoEnergiaNosSubsistemasEntity getOnsBalancoEnergiaNosSubsistemasEntitySample2() {
        return new OnsBalancoEnergiaNosSubsistemasEntity().id(2L).idSubsistema("idSubsistema2").nomSubsistema("nomSubsistema2");
    }

    public static OnsBalancoEnergiaNosSubsistemasEntity getOnsBalancoEnergiaNosSubsistemasEntityRandomSampleGenerator() {
        return new OnsBalancoEnergiaNosSubsistemasEntity()
            .id(longCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString());
    }
}
