import dayjs from 'dayjs/esm';

export interface IOnsCargaEnergiaProgramada {
  id: number;
  codAreacarga?: string | null;
  datReferencia?: dayjs.Dayjs | null;
  dinReferenciautc?: dayjs.Dayjs | null;
  valCargaglobalprogramada?: number | null;
}

export type NewOnsCargaEnergiaProgramada = Omit<IOnsCargaEnergiaProgramada, 'id'> & { id: null };
