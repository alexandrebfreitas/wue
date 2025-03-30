package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsOfertasPrecoParaImportacaoEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsOfertasPrecoParaImportacaoEntity getOnsOfertasPrecoParaImportacaoEntitySample1() {
        return new OnsOfertasPrecoParaImportacaoEntity().id(1L).nomPais("nomPais1").nomAgente("nomAgente1").nomBloco("nomBloco1");
    }

    public static OnsOfertasPrecoParaImportacaoEntity getOnsOfertasPrecoParaImportacaoEntitySample2() {
        return new OnsOfertasPrecoParaImportacaoEntity().id(2L).nomPais("nomPais2").nomAgente("nomAgente2").nomBloco("nomBloco2");
    }

    public static OnsOfertasPrecoParaImportacaoEntity getOnsOfertasPrecoParaImportacaoEntityRandomSampleGenerator() {
        return new OnsOfertasPrecoParaImportacaoEntity()
            .id(longCount.incrementAndGet())
            .nomPais(UUID.randomUUID().toString())
            .nomAgente(UUID.randomUUID().toString())
            .nomBloco(UUID.randomUUID().toString());
    }
}
