package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsCargaEnergiaMensalEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsCargaEnergiaMensalEntity getOnsCargaEnergiaMensalEntitySample1() {
        return new OnsCargaEnergiaMensalEntity().id(1L).idSubsistema("idSubsistema1").nomSubsistema("nomSubsistema1");
    }

    public static OnsCargaEnergiaMensalEntity getOnsCargaEnergiaMensalEntitySample2() {
        return new OnsCargaEnergiaMensalEntity().id(2L).idSubsistema("idSubsistema2").nomSubsistema("nomSubsistema2");
    }

    public static OnsCargaEnergiaMensalEntity getOnsCargaEnergiaMensalEntityRandomSampleGenerator() {
        return new OnsCargaEnergiaMensalEntity()
            .id(longCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString());
    }
}
