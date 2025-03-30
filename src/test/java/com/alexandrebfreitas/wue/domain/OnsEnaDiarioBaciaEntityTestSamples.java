package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class OnsEnaDiarioBaciaEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsEnaDiarioBaciaEntity getOnsEnaDiarioBaciaEntitySample1() {
        return new OnsEnaDiarioBaciaEntity().id(1L);
    }

    public static OnsEnaDiarioBaciaEntity getOnsEnaDiarioBaciaEntitySample2() {
        return new OnsEnaDiarioBaciaEntity().id(2L);
    }

    public static OnsEnaDiarioBaciaEntity getOnsEnaDiarioBaciaEntityRandomSampleGenerator() {
        return new OnsEnaDiarioBaciaEntity().id(longCount.incrementAndGet());
    }
}
