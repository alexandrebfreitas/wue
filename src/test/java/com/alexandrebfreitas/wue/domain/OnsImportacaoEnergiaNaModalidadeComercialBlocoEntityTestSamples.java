package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsImportacaoEnergiaNaModalidadeComercialBlocoEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity getOnsImportacaoEnergiaNaModalidadeComercialBlocoEntitySample1() {
        return new OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity()
            .id(1L)
            .nomPais("nomPais1")
            .nomAgente("nomAgente1")
            .nomBloco("nomBloco1")
            .codBloco("codBloco1");
    }

    public static OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity getOnsImportacaoEnergiaNaModalidadeComercialBlocoEntitySample2() {
        return new OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity()
            .id(2L)
            .nomPais("nomPais2")
            .nomAgente("nomAgente2")
            .nomBloco("nomBloco2")
            .codBloco("codBloco2");
    }

    public static OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity getOnsImportacaoEnergiaNaModalidadeComercialBlocoEntityRandomSampleGenerator() {
        return new OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity()
            .id(longCount.incrementAndGet())
            .nomPais(UUID.randomUUID().toString())
            .nomAgente(UUID.randomUUID().toString())
            .nomBloco(UUID.randomUUID().toString())
            .codBloco(UUID.randomUUID().toString());
    }
}
