import dayjs from 'dayjs/esm';

export interface IOnsCargaEnergiaVerificada {
  id: number;
  codAreacarga?: string | null;
  datReferencia?: dayjs.Dayjs | null;
  dinReferenciautc?: dayjs.Dayjs | null;
  valCargaglobal?: number | null;
  valCargaglobalsmmg?: number | null;
  valCargammgd?: number | null;
  valCargaglobalcons?: number | null;
  valConsistencia?: number | null;
  valCargasupervisionada?: number | null;
  valCarganaosupervisionada?: number | null;
}

export type NewOnsCargaEnergiaVerificada = Omit<IOnsCargaEnergiaVerificada, 'id'> & { id: null };
