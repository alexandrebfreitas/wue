package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity getOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntitySample1() {
        return new OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity().id(1L);
    }

    public static OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity getOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntitySample2() {
        return new OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity().id(2L);
    }

    public static OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity getOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityRandomSampleGenerator() {
        return new OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity().id(longCount.incrementAndGet());
    }
}
