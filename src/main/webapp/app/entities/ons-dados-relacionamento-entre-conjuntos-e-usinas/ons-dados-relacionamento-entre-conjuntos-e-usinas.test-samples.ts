import dayjs from 'dayjs/esm';

import {
  IOnsDadosRelacionamentoEntreConjuntosEUsinas,
  NewOnsDadosRelacionamentoEntreConjuntosEUsinas,
} from './ons-dados-relacionamento-entre-conjuntos-e-usinas.model';

export const sampleWithRequiredData: IOnsDadosRelacionamentoEntreConjuntosEUsinas = {
  id: 2247,
};

export const sampleWithPartialData: IOnsDadosRelacionamentoEntreConjuntosEUsinas = {
  id: 7389,
  nomEstado: 'fooey finally',
  idConjuntousina: 2625,
  idOnsConjunto: 'gah eek',
  nomUsina: 'pillory slimy fundraising',
};

export const sampleWithFullData: IOnsDadosRelacionamentoEntreConjuntosEUsinas = {
  id: 19147,
  idSubsistema: 'knitting',
  nomSubsistema: 'break once',
  estadId: 'outside whoever whenever',
  nomEstado: 'merge',
  idTipousina: 'arrogantly',
  idConjuntousina: 23443,
  idOnsConjunto: 'pfft',
  idOnsUsina: 'including between',
  nomConjunto: 'entire refer',
  nomUsina: 'important',
  ceg: 'secret',
  datIniciorelacionamento: dayjs('2025-03-29'),
  datFimrelacionamento: dayjs('2025-03-29'),
};

export const sampleWithNewData: NewOnsDadosRelacionamentoEntreConjuntosEUsinas = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
