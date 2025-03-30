import dayjs from 'dayjs/esm';

import { IOnsIntercambioSinComOutrosPaises, NewOnsIntercambioSinComOutrosPaises } from './ons-intercambio-sin-com-outros-paises.model';

export const sampleWithRequiredData: IOnsIntercambioSinComOutrosPaises = {
  id: 8621,
};

export const sampleWithPartialData: IOnsIntercambioSinComOutrosPaises = {
  id: 17534,
};

export const sampleWithFullData: IOnsIntercambioSinComOutrosPaises = {
  id: 15245,
  dinInstante: dayjs('2025-03-29'),
  nomPaisdestino: 'because yahoo',
  valIntercambiomwmed: 29855.48,
};

export const sampleWithNewData: NewOnsIntercambioSinComOutrosPaises = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
