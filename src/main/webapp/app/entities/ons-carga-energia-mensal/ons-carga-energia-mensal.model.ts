import dayjs from 'dayjs/esm';

export interface IOnsCargaEnergiaMensal {
  id: number;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  dinInstante?: dayjs.Dayjs | null;
  valCargaenergiamwmed?: number | null;
}

export type NewOnsCargaEnergiaMensal = Omit<IOnsCargaEnergiaMensal, 'id'> & { id: null };
