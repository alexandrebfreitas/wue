package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class OnsEnaDiarioReservatorioEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsEnaDiarioReservatorioEntity getOnsEnaDiarioReservatorioEntitySample1() {
        return new OnsEnaDiarioReservatorioEntity().id(1L);
    }

    public static OnsEnaDiarioReservatorioEntity getOnsEnaDiarioReservatorioEntitySample2() {
        return new OnsEnaDiarioReservatorioEntity().id(2L);
    }

    public static OnsEnaDiarioReservatorioEntity getOnsEnaDiarioReservatorioEntityRandomSampleGenerator() {
        return new OnsEnaDiarioReservatorioEntity().id(longCount.incrementAndGet());
    }
}
