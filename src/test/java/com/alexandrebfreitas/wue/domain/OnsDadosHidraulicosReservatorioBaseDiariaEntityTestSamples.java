package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class OnsDadosHidraulicosReservatorioBaseDiariaEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsDadosHidraulicosReservatorioBaseDiariaEntity getOnsDadosHidraulicosReservatorioBaseDiariaEntitySample1() {
        return new OnsDadosHidraulicosReservatorioBaseDiariaEntity().id(1L);
    }

    public static OnsDadosHidraulicosReservatorioBaseDiariaEntity getOnsDadosHidraulicosReservatorioBaseDiariaEntitySample2() {
        return new OnsDadosHidraulicosReservatorioBaseDiariaEntity().id(2L);
    }

    public static OnsDadosHidraulicosReservatorioBaseDiariaEntity getOnsDadosHidraulicosReservatorioBaseDiariaEntityRandomSampleGenerator() {
        return new OnsDadosHidraulicosReservatorioBaseDiariaEntity().id(longCount.incrementAndGet());
    }
}
