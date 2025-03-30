import dayjs from 'dayjs/esm';

import {
  IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
  NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
} from './ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.model';

export const sampleWithRequiredData: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas = {
  id: 25663,
};

export const sampleWithPartialData: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas = {
  id: 15138,
  idSubsistema: 'kinase shinny friendly',
  nomSubsistema: 'wilted yet wilted',
  idEstado: 'via igloo aha',
  nomEstado: 'boohoo form',
  ceg: 'wherever',
  dinInstante: dayjs('2025-03-29'),
  valGeracao: 24164.82,
  valGeracaolimitada: 11533.63,
  valDisponibilidade: 18614.95,
  valGeracaoreferencia: 32720.84,
  codRazaorestricao: 'uh-huh',
  codOrigemrestricao: 'paralyse incidentally joyously',
};

export const sampleWithFullData: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas = {
  id: 12958,
  idSubsistema: 'swear concrete now',
  nomSubsistema: 'lace creamy',
  idEstado: 'um cake',
  nomEstado: 'hence shrilly piglet',
  nomUsina: 'epic amidst',
  idOns: 'psst mid',
  ceg: 'char',
  dinInstante: dayjs('2025-03-30'),
  valGeracao: 7335.1,
  valGeracaolimitada: 29701.13,
  valDisponibilidade: 22634.15,
  valGeracaoreferencia: 1314.39,
  valGeracaoreferenciafinal: 30037.84,
  codRazaorestricao: 'pro',
  codOrigemrestricao: 'quaintly convalesce innovate',
};

export const sampleWithNewData: NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
