import dayjs from 'dayjs/esm';

import { IOnsIntercambiosEntreSubsistemas, NewOnsIntercambiosEntreSubsistemas } from './ons-intercambios-entre-subsistemas.model';

export const sampleWithRequiredData: IOnsIntercambiosEntreSubsistemas = {
  id: 25335,
};

export const sampleWithPartialData: IOnsIntercambiosEntreSubsistemas = {
  id: 20776,
  dinInstante: dayjs('2025-03-29'),
  idSubsistemaOrigem: 'ape physically',
  idSubsistemaDestino: 'like',
  valIntercambiomwmed: 20726.95,
};

export const sampleWithFullData: IOnsIntercambiosEntreSubsistemas = {
  id: 14061,
  dinInstante: dayjs('2025-03-29'),
  idSubsistemaOrigem: 'anenst',
  nomSubsistemaOrigem: 'willow cautiously properly',
  idSubsistemaDestino: 'ugh reconstitute furthermore',
  nomSubsistemaDestino: 'signature zowie',
  valIntercambiomwmed: 10283.39,
};

export const sampleWithNewData: NewOnsIntercambiosEntreSubsistemas = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
