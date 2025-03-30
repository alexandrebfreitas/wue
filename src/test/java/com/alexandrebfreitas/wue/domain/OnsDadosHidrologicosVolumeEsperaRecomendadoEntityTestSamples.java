package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class OnsDadosHidrologicosVolumeEsperaRecomendadoEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsDadosHidrologicosVolumeEsperaRecomendadoEntity getOnsDadosHidrologicosVolumeEsperaRecomendadoEntitySample1() {
        return new OnsDadosHidrologicosVolumeEsperaRecomendadoEntity().id(1L);
    }

    public static OnsDadosHidrologicosVolumeEsperaRecomendadoEntity getOnsDadosHidrologicosVolumeEsperaRecomendadoEntitySample2() {
        return new OnsDadosHidrologicosVolumeEsperaRecomendadoEntity().id(2L);
    }

    public static OnsDadosHidrologicosVolumeEsperaRecomendadoEntity getOnsDadosHidrologicosVolumeEsperaRecomendadoEntityRandomSampleGenerator() {
        return new OnsDadosHidrologicosVolumeEsperaRecomendadoEntity().id(longCount.incrementAndGet());
    }
}
