package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsCargaEnergiaProgramadaEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsCargaEnergiaProgramadaEntity getOnsCargaEnergiaProgramadaEntitySample1() {
        return new OnsCargaEnergiaProgramadaEntity().id(1L).codAreacarga("codAreacarga1");
    }

    public static OnsCargaEnergiaProgramadaEntity getOnsCargaEnergiaProgramadaEntitySample2() {
        return new OnsCargaEnergiaProgramadaEntity().id(2L).codAreacarga("codAreacarga2");
    }

    public static OnsCargaEnergiaProgramadaEntity getOnsCargaEnergiaProgramadaEntityRandomSampleGenerator() {
        return new OnsCargaEnergiaProgramadaEntity().id(longCount.incrementAndGet()).codAreacarga(UUID.randomUUID().toString());
    }
}
