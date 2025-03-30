import dayjs from 'dayjs/esm';

export interface IOnsTaxasTeifaETeip {
  id: number;
  nomUsina?: string | null;
  codCeg?: string | null;
  idTipousina?: string | null;
  dinMes?: dayjs.Dayjs | null;
  nomTaxa?: string | null;
  valTaxa?: number | null;
  numVersao?: number | null;
  dinInstante?: dayjs.Dayjs | null;
}

export type NewOnsTaxasTeifaETeip = Omit<IOnsTaxasTeifaETeip, 'id'> & { id: null };
