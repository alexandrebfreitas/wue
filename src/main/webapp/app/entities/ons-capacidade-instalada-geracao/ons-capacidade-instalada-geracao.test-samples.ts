import dayjs from 'dayjs/esm';

import { IOnsCapacidadeInstaladaGeracao, NewOnsCapacidadeInstaladaGeracao } from './ons-capacidade-instalada-geracao.model';

export const sampleWithRequiredData: IOnsCapacidadeInstaladaGeracao = {
  id: 5328,
};

export const sampleWithPartialData: IOnsCapacidadeInstaladaGeracao = {
  id: 30703,
  idEstado: 'summary microblog',
  nomEstado: 'phooey',
  nomAgenteproprietario: 'mutate necklace',
  ceg: 'who diligently',
  nomUnidadegeradora: 'meanwhile where',
  nomCombustivel: 'analyse',
  valPotenciaefetiva: 2401.69,
};

export const sampleWithFullData: IOnsCapacidadeInstaladaGeracao = {
  id: 14662,
  idSubsistema: 'marathon',
  nomSubsistema: 'so',
  idEstado: 'convalesce boo righteously',
  nomEstado: 'excitedly aboard',
  nomModalidadeoperacao: 'kindheartedly who',
  nomAgenteproprietario: 'gosh innovate pixellate',
  nomTipousina: 'awkwardly',
  nomUsina: 'until certainly',
  ceg: 'after hairy',
  nomUnidadegeradora: 'furthermore atrium bah',
  codEquipamento: 'vamoose complication',
  numUnidadegeradora: 'indeed',
  nomCombustivel: 'shirk whereas',
  datEntradateste: dayjs('2025-03-30'),
  datEntradaoperacao: dayjs('2025-03-29'),
  datDesativacao: dayjs('2025-03-29'),
  valPotenciaefetiva: 365.26,
};

export const sampleWithNewData: NewOnsCapacidadeInstaladaGeracao = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
