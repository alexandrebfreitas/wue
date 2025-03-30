import dayjs from 'dayjs/esm';

export interface IOnsIntercambiosEntreSubsistemas {
  id: number;
  dinInstante?: dayjs.Dayjs | null;
  idSubsistemaOrigem?: string | null;
  nomSubsistemaOrigem?: string | null;
  idSubsistemaDestino?: string | null;
  nomSubsistemaDestino?: string | null;
  valIntercambiomwmed?: number | null;
}

export type NewOnsIntercambiosEntreSubsistemas = Omit<IOnsIntercambiosEntreSubsistemas, 'id'> & { id: null };
