import dayjs from 'dayjs/esm';

import {
  IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
  NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
} from './ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores.model';

export const sampleWithRequiredData: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores = {
  id: 12729,
};

export const sampleWithPartialData: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores = {
  id: 4926,
  codTipoagregacao: 'boo recount',
  nomAgregacao: 'extra-large',
  numTransformadoresoperacao: 2549,
  numTransformadoresviolados: 21162,
  valCcat: 13956.24,
};

export const sampleWithFullData: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores = {
  id: 2270,
  codTipoagregacao: 'suspension traditionalism book',
  idPeriodicidade: 'as fishery',
  nomAgregacao: 'once',
  dinReferencia: dayjs('2025-03-29'),
  numTransformadoresoperacao: 26349,
  numTransformadoresviolados: 14606,
  valCcat: 2779.57,
};

export const sampleWithNewData: NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
