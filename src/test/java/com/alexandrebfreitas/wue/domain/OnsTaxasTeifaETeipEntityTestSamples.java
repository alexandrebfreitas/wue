package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsTaxasTeifaETeipEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsTaxasTeifaETeipEntity getOnsTaxasTeifaETeipEntitySample1() {
        return new OnsTaxasTeifaETeipEntity()
            .id(1L)
            .nomUsina("nomUsina1")
            .codCeg("codCeg1")
            .idTipousina("idTipousina1")
            .nomTaxa("nomTaxa1");
    }

    public static OnsTaxasTeifaETeipEntity getOnsTaxasTeifaETeipEntitySample2() {
        return new OnsTaxasTeifaETeipEntity()
            .id(2L)
            .nomUsina("nomUsina2")
            .codCeg("codCeg2")
            .idTipousina("idTipousina2")
            .nomTaxa("nomTaxa2");
    }

    public static OnsTaxasTeifaETeipEntity getOnsTaxasTeifaETeipEntityRandomSampleGenerator() {
        return new OnsTaxasTeifaETeipEntity()
            .id(longCount.incrementAndGet())
            .nomUsina(UUID.randomUUID().toString())
            .codCeg(UUID.randomUUID().toString())
            .idTipousina(UUID.randomUUID().toString())
            .nomTaxa(UUID.randomUUID().toString());
    }
}
