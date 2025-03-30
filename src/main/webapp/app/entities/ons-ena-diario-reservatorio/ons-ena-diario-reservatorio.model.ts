export interface IOnsEnaDiarioReservatorio {
  id: number;
  enaBrutaResMwmed?: number | null;
  enaBrutaResPercentualmlt?: number | null;
  enaArmazenavelResMwmed?: number | null;
  enaArmazenavelResPercentualmlt?: number | null;
  enaQuedaBruta?: number | null;
  mltEna?: number | null;
}

export type NewOnsEnaDiarioReservatorio = Omit<IOnsEnaDiarioReservatorio, 'id'> & { id: null };
