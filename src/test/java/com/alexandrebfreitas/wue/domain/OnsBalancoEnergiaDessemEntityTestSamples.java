package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsBalancoEnergiaDessemEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsBalancoEnergiaDessemEntity getOnsBalancoEnergiaDessemEntitySample1() {
        return new OnsBalancoEnergiaDessemEntity().id(1L).idSubsistema("idSubsistema1").nomSubsistema("nomSubsistema1");
    }

    public static OnsBalancoEnergiaDessemEntity getOnsBalancoEnergiaDessemEntitySample2() {
        return new OnsBalancoEnergiaDessemEntity().id(2L).idSubsistema("idSubsistema2").nomSubsistema("nomSubsistema2");
    }

    public static OnsBalancoEnergiaDessemEntity getOnsBalancoEnergiaDessemEntityRandomSampleGenerator() {
        return new OnsBalancoEnergiaDessemEntity()
            .id(longCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString());
    }
}
