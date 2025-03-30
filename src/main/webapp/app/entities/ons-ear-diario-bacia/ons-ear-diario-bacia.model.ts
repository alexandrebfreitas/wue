import dayjs from 'dayjs/esm';

export interface IOnsEarDiarioBacia {
  id: number;
  nomCurto?: string | null;
  earData?: dayjs.Dayjs | null;
  earMaxBacia?: number | null;
  earVerifBaciaMwmes?: number | null;
  earVerifBaciaPercentual?: number | null;
}

export type NewOnsEarDiarioBacia = Omit<IOnsEarDiarioBacia, 'id'> & { id: null };
