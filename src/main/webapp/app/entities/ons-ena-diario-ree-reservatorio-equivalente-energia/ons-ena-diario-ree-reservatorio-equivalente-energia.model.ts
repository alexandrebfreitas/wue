export interface IOnsEnaDiarioReeReservatorioEquivalenteEnergia {
  id: number;
  enaArmazenavelReePercentualmlt?: number | null;
}

export type NewOnsEnaDiarioReeReservatorioEquivalenteEnergia = Omit<IOnsEnaDiarioReeReservatorioEquivalenteEnergia, 'id'> & { id: null };
