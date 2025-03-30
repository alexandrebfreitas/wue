package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024EntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024EntitySample1() {
        return new OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity()
            .id(1L)
            .datPdp("datPdp1")
            .codSubmercado("codSubmercado1")
            .sglTipousina("sglTipousina1")
            .codUsinapdp("codUsinapdp1")
            .nomUsinapdp("nomUsinapdp1")
            .numIntdespa(1)
            .valDespasup(1);
    }

    public static OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024EntitySample2() {
        return new OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity()
            .id(2L)
            .datPdp("datPdp2")
            .codSubmercado("codSubmercado2")
            .sglTipousina("sglTipousina2")
            .codUsinapdp("codUsinapdp2")
            .nomUsinapdp("nomUsinapdp2")
            .numIntdespa(2)
            .valDespasup(2);
    }

    public static OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024EntityRandomSampleGenerator() {
        return new OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity()
            .id(longCount.incrementAndGet())
            .datPdp(UUID.randomUUID().toString())
            .codSubmercado(UUID.randomUUID().toString())
            .sglTipousina(UUID.randomUUID().toString())
            .codUsinapdp(UUID.randomUUID().toString())
            .nomUsinapdp(UUID.randomUUID().toString())
            .numIntdespa(intCount.incrementAndGet())
            .valDespasup(intCount.incrementAndGet());
    }
}
