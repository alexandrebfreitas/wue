import dayjs from 'dayjs/esm';

import {
  IOnsDadosIntercambioEnergiaModalidade,
  NewOnsDadosIntercambioEnergiaModalidade,
} from './ons-dados-intercambio-energia-modalidade.model';

export const sampleWithRequiredData: IOnsDadosIntercambioEnergiaModalidade = {
  id: 8794,
};

export const sampleWithPartialData: IOnsDadosIntercambioEnergiaModalidade = {
  id: 21684,
  dinInstante: dayjs('2025-03-29'),
  valModalidadecontratual: 27215.13,
  valModalidadeemergencial: 26948.95,
  valModalidadeoportunidade: 9595.94,
  valModalidadeteste: 17065.1,
};

export const sampleWithFullData: IOnsDadosIntercambioEnergiaModalidade = {
  id: 17580,
  nomConversora: 'limited apricot recommendation',
  dinInstante: dayjs('2025-03-29'),
  valModalidadecontratual: 9658.65,
  valModalidadeemergencial: 16816.68,
  valModalidadeoportunidade: 20522.82,
  valModalidadeteste: 24979.49,
  valModalidadeexcepcional: 7809,
};

export const sampleWithNewData: NewOnsDadosIntercambioEnergiaModalidade = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
