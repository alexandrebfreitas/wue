import dayjs from 'dayjs/esm';

export interface IOnsDadosGrandezasFluviometricas {
  id: number;
  idPostofluv?: string | null;
  nomPostofluviometrico?: string | null;
  valLatitude?: number | null;
  valLongitude?: number | null;
  nomRio?: string | null;
  nomBacia?: string | null;
  dinMedicao?: dayjs.Dayjs | null;
  valVazaomedia?: number | null;
  valVazaomediaincr?: number | null;
}

export type NewOnsDadosGrandezasFluviometricas = Omit<IOnsDadosGrandezasFluviometricas, 'id'> & { id: null };
