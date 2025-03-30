import dayjs from 'dayjs/esm';

import {
  IOnsDadosProgramadosElementosFluxoControlado,
  NewOnsDadosProgramadosElementosFluxoControlado,
} from './ons-dados-programados-elementos-fluxo-controlado.model';

export const sampleWithRequiredData: IOnsDadosProgramadosElementosFluxoControlado = {
  id: 3907,
};

export const sampleWithPartialData: IOnsDadosProgramadosElementosFluxoControlado = {
  id: 19828,
  numPatamar: 8032,
  nomElementofluxocontrolado: 'likewise',
  codSubmercado: 'facilitate evenly',
};

export const sampleWithFullData: IOnsDadosProgramadosElementosFluxoControlado = {
  id: 2687,
  dinProgramacaodia: dayjs('2025-03-29'),
  numPatamar: 5576,
  nomElementofluxocontrolado: 'exotic',
  dscElementofluxocontrolado: 'aw',
  tipTerminal: 10129,
  codSubmercado: 'regulate',
  valCarga: 12702.62,
};

export const sampleWithNewData: NewOnsDadosProgramadosElementosFluxoControlado = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
