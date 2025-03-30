package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsDadosIntercambioEnergiaModalidadeEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsDadosIntercambioEnergiaModalidadeEntity getOnsDadosIntercambioEnergiaModalidadeEntitySample1() {
        return new OnsDadosIntercambioEnergiaModalidadeEntity().id(1L).nomConversora("nomConversora1");
    }

    public static OnsDadosIntercambioEnergiaModalidadeEntity getOnsDadosIntercambioEnergiaModalidadeEntitySample2() {
        return new OnsDadosIntercambioEnergiaModalidadeEntity().id(2L).nomConversora("nomConversora2");
    }

    public static OnsDadosIntercambioEnergiaModalidadeEntity getOnsDadosIntercambioEnergiaModalidadeEntityRandomSampleGenerator() {
        return new OnsDadosIntercambioEnergiaModalidadeEntity().id(longCount.incrementAndGet()).nomConversora(UUID.randomUUID().toString());
    }
}
