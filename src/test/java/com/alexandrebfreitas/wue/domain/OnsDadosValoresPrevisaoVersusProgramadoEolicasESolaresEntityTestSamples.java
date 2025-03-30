package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntitySample1() {
        return new OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity()
            .id(1L)
            .numPatamar(1)
            .codUsinapdp("codUsinapdp1")
            .nomUsinapdp("nomUsinapdp1");
    }

    public static OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntitySample2() {
        return new OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity()
            .id(2L)
            .numPatamar(2)
            .codUsinapdp("codUsinapdp2")
            .nomUsinapdp("nomUsinapdp2");
    }

    public static OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntityRandomSampleGenerator() {
        return new OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity()
            .id(longCount.incrementAndGet())
            .numPatamar(intCount.incrementAndGet())
            .codUsinapdp(UUID.randomUUID().toString())
            .nomUsinapdp(UUID.randomUUID().toString());
    }
}
