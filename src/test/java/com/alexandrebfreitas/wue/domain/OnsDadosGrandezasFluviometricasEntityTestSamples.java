package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsDadosGrandezasFluviometricasEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsDadosGrandezasFluviometricasEntity getOnsDadosGrandezasFluviometricasEntitySample1() {
        return new OnsDadosGrandezasFluviometricasEntity()
            .id(1L)
            .idPostofluv("idPostofluv1")
            .nomPostofluviometrico("nomPostofluviometrico1")
            .nomRio("nomRio1")
            .nomBacia("nomBacia1");
    }

    public static OnsDadosGrandezasFluviometricasEntity getOnsDadosGrandezasFluviometricasEntitySample2() {
        return new OnsDadosGrandezasFluviometricasEntity()
            .id(2L)
            .idPostofluv("idPostofluv2")
            .nomPostofluviometrico("nomPostofluviometrico2")
            .nomRio("nomRio2")
            .nomBacia("nomBacia2");
    }

    public static OnsDadosGrandezasFluviometricasEntity getOnsDadosGrandezasFluviometricasEntityRandomSampleGenerator() {
        return new OnsDadosGrandezasFluviometricasEntity()
            .id(longCount.incrementAndGet())
            .idPostofluv(UUID.randomUUID().toString())
            .nomPostofluviometrico(UUID.randomUUID().toString())
            .nomRio(UUID.randomUUID().toString())
            .nomBacia(UUID.randomUUID().toString());
    }
}
