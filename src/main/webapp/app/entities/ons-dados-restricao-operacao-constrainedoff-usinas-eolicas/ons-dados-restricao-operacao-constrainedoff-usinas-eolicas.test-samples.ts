import dayjs from 'dayjs/esm';

import {
  IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
  NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
} from './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas.model';

export const sampleWithRequiredData: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas = {
  id: 23150,
};

export const sampleWithPartialData: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas = {
  id: 1506,
  idSubsistema: 'badly amongst',
  nomEstado: 'up only',
  nomUsina: 'blond',
  ceg: 'until',
  valDisponibilidade: 3980.58,
  valGeracaoreferenciafinal: 31548.95,
};

export const sampleWithFullData: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas = {
  id: 26767,
  idSubsistema: 'triumphantly below provided',
  nomSubsistema: 'altruistic warped',
  idEstado: 'license opposite gracefully',
  nomEstado: 'deliberately frightfully kinase',
  nomUsina: 'woot',
  idOns: 'puff excepting',
  ceg: 'that er',
  dinInstante: dayjs('2025-03-29'),
  valGeracao: 27990.11,
  valGeracaolimitada: 16071.43,
  valDisponibilidade: 4203.46,
  valGeracaoreferencia: 16093.81,
  valGeracaoreferenciafinal: 18025.37,
  codRazaorestricao: 'gah statement into',
  codOrigemrestricao: 'mid outlandish',
};

export const sampleWithNewData: NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
