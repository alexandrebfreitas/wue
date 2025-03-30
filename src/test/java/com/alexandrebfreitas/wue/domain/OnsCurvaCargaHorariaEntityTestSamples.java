package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsCurvaCargaHorariaEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsCurvaCargaHorariaEntity getOnsCurvaCargaHorariaEntitySample1() {
        return new OnsCurvaCargaHorariaEntity().id(1L).idSubsistema("idSubsistema1").nomSubsistema("nomSubsistema1");
    }

    public static OnsCurvaCargaHorariaEntity getOnsCurvaCargaHorariaEntitySample2() {
        return new OnsCurvaCargaHorariaEntity().id(2L).idSubsistema("idSubsistema2").nomSubsistema("nomSubsistema2");
    }

    public static OnsCurvaCargaHorariaEntity getOnsCurvaCargaHorariaEntityRandomSampleGenerator() {
        return new OnsCurvaCargaHorariaEntity()
            .id(longCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString());
    }
}
