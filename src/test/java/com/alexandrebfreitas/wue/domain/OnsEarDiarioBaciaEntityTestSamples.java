package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsEarDiarioBaciaEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsEarDiarioBaciaEntity getOnsEarDiarioBaciaEntitySample1() {
        return new OnsEarDiarioBaciaEntity().id(1L).nomCurto("nomCurto1");
    }

    public static OnsEarDiarioBaciaEntity getOnsEarDiarioBaciaEntitySample2() {
        return new OnsEarDiarioBaciaEntity().id(2L).nomCurto("nomCurto2");
    }

    public static OnsEarDiarioBaciaEntity getOnsEarDiarioBaciaEntityRandomSampleGenerator() {
        return new OnsEarDiarioBaciaEntity().id(longCount.incrementAndGet()).nomCurto(UUID.randomUUID().toString());
    }
}
