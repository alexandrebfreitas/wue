package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsCargaEnergiaVerificadaEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsCargaEnergiaVerificadaEntity getOnsCargaEnergiaVerificadaEntitySample1() {
        return new OnsCargaEnergiaVerificadaEntity().id(1L).codAreacarga("codAreacarga1");
    }

    public static OnsCargaEnergiaVerificadaEntity getOnsCargaEnergiaVerificadaEntitySample2() {
        return new OnsCargaEnergiaVerificadaEntity().id(2L).codAreacarga("codAreacarga2");
    }

    public static OnsCargaEnergiaVerificadaEntity getOnsCargaEnergiaVerificadaEntityRandomSampleGenerator() {
        return new OnsCargaEnergiaVerificadaEntity().id(longCount.incrementAndGet()).codAreacarga(UUID.randomUUID().toString());
    }
}
