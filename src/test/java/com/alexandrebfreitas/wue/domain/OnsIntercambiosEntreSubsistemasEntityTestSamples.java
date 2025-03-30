package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsIntercambiosEntreSubsistemasEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsIntercambiosEntreSubsistemasEntity getOnsIntercambiosEntreSubsistemasEntitySample1() {
        return new OnsIntercambiosEntreSubsistemasEntity()
            .id(1L)
            .idSubsistemaOrigem("idSubsistemaOrigem1")
            .nomSubsistemaOrigem("nomSubsistemaOrigem1")
            .idSubsistemaDestino("idSubsistemaDestino1")
            .nomSubsistemaDestino("nomSubsistemaDestino1");
    }

    public static OnsIntercambiosEntreSubsistemasEntity getOnsIntercambiosEntreSubsistemasEntitySample2() {
        return new OnsIntercambiosEntreSubsistemasEntity()
            .id(2L)
            .idSubsistemaOrigem("idSubsistemaOrigem2")
            .nomSubsistemaOrigem("nomSubsistemaOrigem2")
            .idSubsistemaDestino("idSubsistemaDestino2")
            .nomSubsistemaDestino("nomSubsistemaDestino2");
    }

    public static OnsIntercambiosEntreSubsistemasEntity getOnsIntercambiosEntreSubsistemasEntityRandomSampleGenerator() {
        return new OnsIntercambiosEntreSubsistemasEntity()
            .id(longCount.incrementAndGet())
            .idSubsistemaOrigem(UUID.randomUUID().toString())
            .nomSubsistemaOrigem(UUID.randomUUID().toString())
            .idSubsistemaDestino(UUID.randomUUID().toString())
            .nomSubsistemaDestino(UUID.randomUUID().toString());
    }
}
