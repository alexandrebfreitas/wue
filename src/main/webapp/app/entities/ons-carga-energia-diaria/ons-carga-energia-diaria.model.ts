import dayjs from 'dayjs/esm';

export interface IOnsCargaEnergiaDiaria {
  id: number;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  dinInstante?: dayjs.Dayjs | null;
  valCargaenergiamwmed?: number | null;
}

export type NewOnsCargaEnergiaDiaria = Omit<IOnsCargaEnergiaDiaria, 'id'> & { id: null };
