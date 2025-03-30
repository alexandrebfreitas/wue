import dayjs from 'dayjs/esm';

export interface IOnsCmoSemihorario {
  id: number;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  dinInstante?: dayjs.Dayjs | null;
  valCmo?: number | null;
}

export type NewOnsCmoSemihorario = Omit<IOnsCmoSemihorario, 'id'> & { id: null };
