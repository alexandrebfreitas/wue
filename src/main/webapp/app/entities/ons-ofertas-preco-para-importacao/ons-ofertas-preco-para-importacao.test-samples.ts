import dayjs from 'dayjs/esm';

import { IOnsOfertasPrecoParaImportacao, NewOnsOfertasPrecoParaImportacao } from './ons-ofertas-preco-para-importacao.model';

export const sampleWithRequiredData: IOnsOfertasPrecoParaImportacao = {
  id: 32764,
};

export const sampleWithPartialData: IOnsOfertasPrecoParaImportacao = {
  id: 14567,
  nomPais: 'expostulate',
  nomAgente: 'orientate better',
  nomBloco: 'of forenenst without',
  datIniciovalidade: dayjs('2025-03-29'),
  datFimvalidade: dayjs('2025-03-29'),
  valPreco: 5154.1,
};

export const sampleWithFullData: IOnsOfertasPrecoParaImportacao = {
  id: 27287,
  nomPais: 'anaesthetise',
  nomAgente: 'under enroll',
  nomBloco: 'modulo unknown',
  datIniciovalidade: dayjs('2025-03-29'),
  datFimvalidade: dayjs('2025-03-29'),
  valPreco: 10431.11,
};

export const sampleWithNewData: NewOnsOfertasPrecoParaImportacao = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
