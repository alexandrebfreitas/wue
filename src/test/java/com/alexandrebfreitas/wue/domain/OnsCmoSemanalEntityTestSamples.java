package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsCmoSemanalEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsCmoSemanalEntity getOnsCmoSemanalEntitySample1() {
        return new OnsCmoSemanalEntity().id(1L).idSubsistema("idSubsistema1").nomSubsistema("nomSubsistema1");
    }

    public static OnsCmoSemanalEntity getOnsCmoSemanalEntitySample2() {
        return new OnsCmoSemanalEntity().id(2L).idSubsistema("idSubsistema2").nomSubsistema("nomSubsistema2");
    }

    public static OnsCmoSemanalEntity getOnsCmoSemanalEntityRandomSampleGenerator() {
        return new OnsCmoSemanalEntity()
            .id(longCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString());
    }
}
