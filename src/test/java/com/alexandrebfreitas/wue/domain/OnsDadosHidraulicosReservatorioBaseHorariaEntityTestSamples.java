package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class OnsDadosHidraulicosReservatorioBaseHorariaEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsDadosHidraulicosReservatorioBaseHorariaEntity getOnsDadosHidraulicosReservatorioBaseHorariaEntitySample1() {
        return new OnsDadosHidraulicosReservatorioBaseHorariaEntity().id(1L);
    }

    public static OnsDadosHidraulicosReservatorioBaseHorariaEntity getOnsDadosHidraulicosReservatorioBaseHorariaEntitySample2() {
        return new OnsDadosHidraulicosReservatorioBaseHorariaEntity().id(2L);
    }

    public static OnsDadosHidraulicosReservatorioBaseHorariaEntity getOnsDadosHidraulicosReservatorioBaseHorariaEntityRandomSampleGenerator() {
        return new OnsDadosHidraulicosReservatorioBaseHorariaEntity().id(longCount.incrementAndGet());
    }
}
