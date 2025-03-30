import dayjs from 'dayjs/esm';

export interface IOnsCurvaCargaHoraria {
  id: number;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  dinInstante?: dayjs.Dayjs | null;
  valCargaenergiahomwmed?: number | null;
}

export type NewOnsCurvaCargaHoraria = Omit<IOnsCurvaCargaHoraria, 'id'> & { id: null };
