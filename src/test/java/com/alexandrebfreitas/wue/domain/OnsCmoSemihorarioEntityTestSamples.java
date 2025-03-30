package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsCmoSemihorarioEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsCmoSemihorarioEntity getOnsCmoSemihorarioEntitySample1() {
        return new OnsCmoSemihorarioEntity().id(1L).idSubsistema("idSubsistema1").nomSubsistema("nomSubsistema1");
    }

    public static OnsCmoSemihorarioEntity getOnsCmoSemihorarioEntitySample2() {
        return new OnsCmoSemihorarioEntity().id(2L).idSubsistema("idSubsistema2").nomSubsistema("nomSubsistema2");
    }

    public static OnsCmoSemihorarioEntity getOnsCmoSemihorarioEntityRandomSampleGenerator() {
        return new OnsCmoSemihorarioEntity()
            .id(longCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString());
    }
}
