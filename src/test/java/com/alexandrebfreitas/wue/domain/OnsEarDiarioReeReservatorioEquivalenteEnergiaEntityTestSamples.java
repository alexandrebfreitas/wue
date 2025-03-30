package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsEarDiarioReeReservatorioEquivalenteEnergiaEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity getOnsEarDiarioReeReservatorioEquivalenteEnergiaEntitySample1() {
        return new OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity().id(1L).nomRee("nomRee1");
    }

    public static OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity getOnsEarDiarioReeReservatorioEquivalenteEnergiaEntitySample2() {
        return new OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity().id(2L).nomRee("nomRee2");
    }

    public static OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity getOnsEarDiarioReeReservatorioEquivalenteEnergiaEntityRandomSampleGenerator() {
        return new OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity()
            .id(longCount.incrementAndGet())
            .nomRee(UUID.randomUUID().toString());
    }
}
