import dayjs from 'dayjs/esm';

import {
  IOnsImportacaoEnergiaNaModalidadeComercialBloco,
  NewOnsImportacaoEnergiaNaModalidadeComercialBloco,
} from './ons-importacao-energia-na-modalidade-comercial-bloco.model';

export const sampleWithRequiredData: IOnsImportacaoEnergiaNaModalidadeComercialBloco = {
  id: 452,
};

export const sampleWithPartialData: IOnsImportacaoEnergiaNaModalidadeComercialBloco = {
  id: 17024,
  nomPais: 'liberalize red midst',
  nomAgente: 'now meh',
  nomBloco: 'pushy memorise loftily',
  codBloco: 'amid lest',
  valImportacaoprogramada: 24575.11,
  valImportacaodespachada: 15050.3,
  valImportacaoverificada: 20602.91,
};

export const sampleWithFullData: IOnsImportacaoEnergiaNaModalidadeComercialBloco = {
  id: 9572,
  nomPais: 'meh',
  nomAgente: 'politely lest meanwhile',
  nomBloco: 'out',
  codBloco: 'obedience arrange',
  dinInstante: dayjs('2025-03-29'),
  valImportacaoprogramada: 31058.15,
  valImportacaodespachada: 14358.5,
  valImportacaoverificada: 21679.14,
  valPreco: 26933.68,
};

export const sampleWithNewData: NewOnsImportacaoEnergiaNaModalidadeComercialBloco = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
