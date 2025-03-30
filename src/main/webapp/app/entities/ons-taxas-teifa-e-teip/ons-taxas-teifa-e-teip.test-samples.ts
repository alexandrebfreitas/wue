import dayjs from 'dayjs/esm';

import { IOnsTaxasTeifaETeip, NewOnsTaxasTeifaETeip } from './ons-taxas-teifa-e-teip.model';

export const sampleWithRequiredData: IOnsTaxasTeifaETeip = {
  id: 15375,
};

export const sampleWithPartialData: IOnsTaxasTeifaETeip = {
  id: 0,
  codCeg: 'lobster',
  nomTaxa: 'incidentally pertinent',
};

export const sampleWithFullData: IOnsTaxasTeifaETeip = {
  id: 1181,
  nomUsina: 'mummify',
  codCeg: 'clumsy really',
  idTipousina: 'on effector barring',
  dinMes: dayjs('2025-03-29'),
  nomTaxa: 'woeful beautifully',
  valTaxa: 19995.13,
  numVersao: 24351.96,
  dinInstante: dayjs('2025-03-29'),
};

export const sampleWithNewData: NewOnsTaxasTeifaETeip = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
