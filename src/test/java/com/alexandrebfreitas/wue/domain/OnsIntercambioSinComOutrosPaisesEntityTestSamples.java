package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsIntercambioSinComOutrosPaisesEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsIntercambioSinComOutrosPaisesEntity getOnsIntercambioSinComOutrosPaisesEntitySample1() {
        return new OnsIntercambioSinComOutrosPaisesEntity().id(1L).nomPaisdestino("nomPaisdestino1");
    }

    public static OnsIntercambioSinComOutrosPaisesEntity getOnsIntercambioSinComOutrosPaisesEntitySample2() {
        return new OnsIntercambioSinComOutrosPaisesEntity().id(2L).nomPaisdestino("nomPaisdestino2");
    }

    public static OnsIntercambioSinComOutrosPaisesEntity getOnsIntercambioSinComOutrosPaisesEntityRandomSampleGenerator() {
        return new OnsIntercambioSinComOutrosPaisesEntity().id(longCount.incrementAndGet()).nomPaisdestino(UUID.randomUUID().toString());
    }
}
