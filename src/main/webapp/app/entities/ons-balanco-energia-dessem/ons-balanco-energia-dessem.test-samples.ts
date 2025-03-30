import dayjs from 'dayjs/esm';

import { IOnsBalancoEnergiaDessem, NewOnsBalancoEnergiaDessem } from './ons-balanco-energia-dessem.model';

export const sampleWithRequiredData: IOnsBalancoEnergiaDessem = {
  id: 7343,
};

export const sampleWithPartialData: IOnsBalancoEnergiaDessem = {
  id: 6823,
  dinInstante: dayjs('2025-03-29'),
  valGeracaotermicamwed: 30668.13,
};

export const sampleWithFullData: IOnsBalancoEnergiaDessem = {
  id: 4029,
  idSubsistema: 'bouncy about',
  nomSubsistema: 'for',
  dinInstante: dayjs('2025-03-29'),
  valDemanda: 22559.71,
  valGeracaohidraulicamwmed: 8145.23,
  valGeracaopchmwmed: 12398.03,
  valGeracaotermicamwed: 19427.54,
  valGeracaopctmwmed: 13035.11,
  valGeracaoeolicamwmed: 3972.58,
  valGeracaofotovoltaicamwmed: 31390.61,
};

export const sampleWithNewData: NewOnsBalancoEnergiaDessem = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
