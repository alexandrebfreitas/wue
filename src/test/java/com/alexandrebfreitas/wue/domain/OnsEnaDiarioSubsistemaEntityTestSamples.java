package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class OnsEnaDiarioSubsistemaEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsEnaDiarioSubsistemaEntity getOnsEnaDiarioSubsistemaEntitySample1() {
        return new OnsEnaDiarioSubsistemaEntity().id(1L);
    }

    public static OnsEnaDiarioSubsistemaEntity getOnsEnaDiarioSubsistemaEntitySample2() {
        return new OnsEnaDiarioSubsistemaEntity().id(2L);
    }

    public static OnsEnaDiarioSubsistemaEntity getOnsEnaDiarioSubsistemaEntityRandomSampleGenerator() {
        return new OnsEnaDiarioSubsistemaEntity().id(longCount.incrementAndGet());
    }
}
