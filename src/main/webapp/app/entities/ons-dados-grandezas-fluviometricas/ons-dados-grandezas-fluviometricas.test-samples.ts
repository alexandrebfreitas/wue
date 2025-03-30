import dayjs from 'dayjs/esm';

import { IOnsDadosGrandezasFluviometricas, NewOnsDadosGrandezasFluviometricas } from './ons-dados-grandezas-fluviometricas.model';

export const sampleWithRequiredData: IOnsDadosGrandezasFluviometricas = {
  id: 9117,
};

export const sampleWithPartialData: IOnsDadosGrandezasFluviometricas = {
  id: 4883,
  idPostofluv: 'really',
  valLongitude: 22156.12,
  nomBacia: 'abaft',
  dinMedicao: dayjs('2025-03-29'),
  valVazaomedia: 26914.13,
};

export const sampleWithFullData: IOnsDadosGrandezasFluviometricas = {
  id: 19665,
  idPostofluv: 'oof replacement aw',
  nomPostofluviometrico: 'incidentally dissemble the',
  valLatitude: 20171.2,
  valLongitude: 1520.44,
  nomRio: 'small settle divert',
  nomBacia: 'institute utilized',
  dinMedicao: dayjs('2025-03-29'),
  valVazaomedia: 4156.12,
  valVazaomediaincr: 16480.08,
};

export const sampleWithNewData: NewOnsDadosGrandezasFluviometricas = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
