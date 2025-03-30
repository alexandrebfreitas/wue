import dayjs from 'dayjs/esm';

export interface IOnsDescontinuadoPrecipitacaoDiariaObservada {
  id: number;
  codEstacao?: string | null;
  valLatitude?: number | null;
  valLongitude?: number | null;
  valMedida?: number | null;
  datObservada?: dayjs.Dayjs | null;
}

export type NewOnsDescontinuadoPrecipitacaoDiariaObservada = Omit<IOnsDescontinuadoPrecipitacaoDiariaObservada, 'id'> & { id: null };
