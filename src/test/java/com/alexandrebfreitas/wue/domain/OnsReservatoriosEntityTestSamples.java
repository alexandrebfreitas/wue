package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsReservatoriosEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsReservatoriosEntity getOnsReservatoriosEntitySample1() {
        return new OnsReservatoriosEntity().id(1L).nomRee("nomRee1").valTipoperda("valTipoperda1").idReservatorio("idReservatorio1");
    }

    public static OnsReservatoriosEntity getOnsReservatoriosEntitySample2() {
        return new OnsReservatoriosEntity().id(2L).nomRee("nomRee2").valTipoperda("valTipoperda2").idReservatorio("idReservatorio2");
    }

    public static OnsReservatoriosEntity getOnsReservatoriosEntityRandomSampleGenerator() {
        return new OnsReservatoriosEntity()
            .id(longCount.incrementAndGet())
            .nomRee(UUID.randomUUID().toString())
            .valTipoperda(UUID.randomUUID().toString())
            .idReservatorio(UUID.randomUUID().toString());
    }
}
