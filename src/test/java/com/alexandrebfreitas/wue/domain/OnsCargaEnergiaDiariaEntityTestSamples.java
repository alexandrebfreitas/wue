package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsCargaEnergiaDiariaEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsCargaEnergiaDiariaEntity getOnsCargaEnergiaDiariaEntitySample1() {
        return new OnsCargaEnergiaDiariaEntity().id(1L).idSubsistema("idSubsistema1").nomSubsistema("nomSubsistema1");
    }

    public static OnsCargaEnergiaDiariaEntity getOnsCargaEnergiaDiariaEntitySample2() {
        return new OnsCargaEnergiaDiariaEntity().id(2L).idSubsistema("idSubsistema2").nomSubsistema("nomSubsistema2");
    }

    public static OnsCargaEnergiaDiariaEntity getOnsCargaEnergiaDiariaEntityRandomSampleGenerator() {
        return new OnsCargaEnergiaDiariaEntity()
            .id(longCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString());
    }
}
