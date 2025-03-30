import dayjs from 'dayjs/esm';

export interface IOnsEarDiarioReeReservatorioEquivalenteEnergia {
  id: number;
  nomRee?: string | null;
  earData?: dayjs.Dayjs | null;
  earMaxRee?: number | null;
  earVerifReeMwmes?: number | null;
  earVerifReePercentual?: number | null;
}

export type NewOnsEarDiarioReeReservatorioEquivalenteEnergia = Omit<IOnsEarDiarioReeReservatorioEquivalenteEnergia, 'id'> & { id: null };
