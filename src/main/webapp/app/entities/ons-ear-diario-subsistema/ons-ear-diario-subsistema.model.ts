import dayjs from 'dayjs/esm';

export interface IOnsEarDiarioSubsistema {
  id: number;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  earData?: dayjs.Dayjs | null;
  earMaxSubsistema?: number | null;
  earVerifSubsistemaMwmes?: number | null;
  earVerifSubsistemaPercentual?: number | null;
}

export type NewOnsEarDiarioSubsistema = Omit<IOnsEarDiarioSubsistema, 'id'> & { id: null };
