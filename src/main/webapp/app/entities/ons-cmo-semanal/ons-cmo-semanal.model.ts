import dayjs from 'dayjs/esm';

export interface IOnsCmoSemanal {
  id: number;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  dinInstante?: dayjs.Dayjs | null;
  valCmomediasemanal?: number | null;
  valCmoleve?: number | null;
  valCmomedia?: number | null;
  valCmopesada?: number | null;
}

export type NewOnsCmoSemanal = Omit<IOnsCmoSemanal, 'id'> & { id: null };
