import dayjs from 'dayjs/esm';

export interface IOnsIntercambioSinComOutrosPaises {
  id: number;
  dinInstante?: dayjs.Dayjs | null;
  nomPaisdestino?: string | null;
  valIntercambiomwmed?: number | null;
}

export type NewOnsIntercambioSinComOutrosPaises = Omit<IOnsIntercambioSinComOutrosPaises, 'id'> & { id: null };
