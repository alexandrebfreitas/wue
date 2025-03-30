package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsDescontinuadoPrecipitacaoDiariaObservadaEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsDescontinuadoPrecipitacaoDiariaObservadaEntity getOnsDescontinuadoPrecipitacaoDiariaObservadaEntitySample1() {
        return new OnsDescontinuadoPrecipitacaoDiariaObservadaEntity().id(1L).codEstacao("codEstacao1");
    }

    public static OnsDescontinuadoPrecipitacaoDiariaObservadaEntity getOnsDescontinuadoPrecipitacaoDiariaObservadaEntitySample2() {
        return new OnsDescontinuadoPrecipitacaoDiariaObservadaEntity().id(2L).codEstacao("codEstacao2");
    }

    public static OnsDescontinuadoPrecipitacaoDiariaObservadaEntity getOnsDescontinuadoPrecipitacaoDiariaObservadaEntityRandomSampleGenerator() {
        return new OnsDescontinuadoPrecipitacaoDiariaObservadaEntity()
            .id(longCount.incrementAndGet())
            .codEstacao(UUID.randomUUID().toString());
    }
}
