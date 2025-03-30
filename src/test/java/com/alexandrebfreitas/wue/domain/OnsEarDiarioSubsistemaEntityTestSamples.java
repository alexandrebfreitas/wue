package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsEarDiarioSubsistemaEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsEarDiarioSubsistemaEntity getOnsEarDiarioSubsistemaEntitySample1() {
        return new OnsEarDiarioSubsistemaEntity().id(1L).idSubsistema("idSubsistema1").nomSubsistema("nomSubsistema1");
    }

    public static OnsEarDiarioSubsistemaEntity getOnsEarDiarioSubsistemaEntitySample2() {
        return new OnsEarDiarioSubsistemaEntity().id(2L).idSubsistema("idSubsistema2").nomSubsistema("nomSubsistema2");
    }

    public static OnsEarDiarioSubsistemaEntity getOnsEarDiarioSubsistemaEntityRandomSampleGenerator() {
        return new OnsEarDiarioSubsistemaEntity()
            .id(longCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString());
    }
}
