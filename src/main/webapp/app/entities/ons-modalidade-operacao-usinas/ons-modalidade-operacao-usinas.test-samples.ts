import { IOnsModalidadeOperacaoUsinas, NewOnsModalidadeOperacaoUsinas } from './ons-modalidade-operacao-usinas.model';

export const sampleWithRequiredData: IOnsModalidadeOperacaoUsinas = {
  id: 16637,
};

export const sampleWithPartialData: IOnsModalidadeOperacaoUsinas = {
  id: 3827,
  ceg: 'astride',
  nomModalidadeoperacao: 'aside tightly',
  idEstado: 'diver',
  nomEstado: 'whoa hundred filter',
};

export const sampleWithFullData: IOnsModalidadeOperacaoUsinas = {
  id: 12138,
  nomUsina: 'seemingly immaculate',
  ceg: 'after',
  nomModalidadeoperacao: 'preheat',
  valPotenciaautorizada: 23753.17,
  sglCentrooperacao: 'phew waft yahoo',
  nomPontoconexao: 'reassuringly',
  idEstado: 'airbrush drowse ajar',
  nomEstado: 'grouper',
  stsAneel: 'at amid trouser',
};

export const sampleWithNewData: NewOnsModalidadeOperacaoUsinas = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
