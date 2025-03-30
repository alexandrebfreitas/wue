import dayjs from 'dayjs/esm';

import { IOnsDadosValoresProgramacaoDiaria, NewOnsDadosValoresProgramacaoDiaria } from './ons-dados-valores-programacao-diaria.model';

export const sampleWithRequiredData: IOnsDadosValoresProgramacaoDiaria = {
  id: 19894,
};

export const sampleWithPartialData: IOnsDadosValoresProgramacaoDiaria = {
  id: 31987,
  numPatamar: 28251,
  tipGeracao: 'gadzooks past',
  nomModalidadeoperacao: 'however huddle wonderfully',
  idSubsistema: 'without until',
  nomEstado: 'ew',
  valGeracaoprogramada: 21781.57,
  valOrdemmerito: 3307.4,
  valInflexibilidade: 17603.47,
  valUc: 27887.74,
  valGesubgsub: 21415.56,
  valExportacao: 12742.51,
  valReposicaoexportacao: 5368.05,
  valFaltacombustivel: 5915.51,
};

export const sampleWithFullData: IOnsDadosValoresProgramacaoDiaria = {
  id: 13899,
  dinProgramacaodia: dayjs('2025-03-29'),
  numPatamar: 14318,
  codExibicaousina: 'captain mid mallard',
  nomUsina: 'welcome hearten likewise',
  tipGeracao: 'quick-witted with',
  nomModalidadeoperacao: 'quickly deflate',
  idSubsistema: 'shudder oh',
  nomSubsistema: 'incidentally meanwhile',
  idEstado: 'pfft charter foolhardy',
  nomEstado: 'fooey thoroughly cafe',
  valGeracaoprogramada: 13192.03,
  valDisponibilidade: 5122.57,
  valOrdemmerito: 26764.08,
  valInflexibilidade: 17281.02,
  valUc: 5900.89,
  valRazaoeletrica: 18899.99,
  valGeracaoenergetica: 22.55,
  valGesubgsub: 12174.39,
  valExportacao: 2186.68,
  valReposicaoexportacao: 11060.92,
  valFaltacombustivel: 17032.7,
};

export const sampleWithNewData: NewOnsDadosValoresProgramacaoDiaria = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
