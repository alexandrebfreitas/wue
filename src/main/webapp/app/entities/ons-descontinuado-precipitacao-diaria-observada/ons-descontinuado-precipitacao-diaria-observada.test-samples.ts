import dayjs from 'dayjs/esm';

import {
  IOnsDescontinuadoPrecipitacaoDiariaObservada,
  NewOnsDescontinuadoPrecipitacaoDiariaObservada,
} from './ons-descontinuado-precipitacao-diaria-observada.model';

export const sampleWithRequiredData: IOnsDescontinuadoPrecipitacaoDiariaObservada = {
  id: 1293,
};

export const sampleWithPartialData: IOnsDescontinuadoPrecipitacaoDiariaObservada = {
  id: 10561,
  valLongitude: 19475.77,
  datObservada: dayjs('2025-03-29'),
};

export const sampleWithFullData: IOnsDescontinuadoPrecipitacaoDiariaObservada = {
  id: 26092,
  codEstacao: 'override about',
  valLatitude: 322.57,
  valLongitude: 13166.15,
  valMedida: 3723.01,
  datObservada: dayjs('2025-03-30'),
};

export const sampleWithNewData: NewOnsDescontinuadoPrecipitacaoDiariaObservada = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
