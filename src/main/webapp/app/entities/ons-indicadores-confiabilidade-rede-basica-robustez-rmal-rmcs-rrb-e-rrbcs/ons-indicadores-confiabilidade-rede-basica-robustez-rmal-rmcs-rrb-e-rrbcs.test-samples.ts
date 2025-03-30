import dayjs from 'dayjs/esm';

import {
  IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
  NewOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
} from './ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs.model';

export const sampleWithRequiredData: IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs = {
  id: 2281,
};

export const sampleWithPartialData: IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs = {
  id: 7862,
  codIndicador: 'uh-huh limply solvency',
  dscAgregacao: 'geez',
  codCaracteristica: 'mild',
  dinReferencia: dayjs('2025-03-29'),
  valIndicador: 13844.65,
  numPerturbacoes: 7680.38,
  numPerturbacoescortecarga: 22841.19,
  numPerturbacoescortecarga0a50mw: 9221.57,
  numPerturbacoescortecargaMaior100mw: 1653.65,
};

export const sampleWithFullData: IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs = {
  id: 13480,
  codIndicador: 'yowza',
  dscAgregacao: 'or',
  codCaracteristica: 'boohoo',
  dscCaracteristica: 'jubilantly',
  idPeriodicidade: 'though whose',
  dinReferencia: dayjs('2025-03-29'),
  valIndicador: 12285.07,
  numPerturbacoes: 16194.5,
  numPerturbacoescortecarga: 23886.93,
  numPerturbacoescortecarga0a50mw: 23352.64,
  numPerturbacoescortecarga50a100mw: 19585.93,
  numPerturbacoescortecargaMaior100mw: 29707.8,
};

export const sampleWithNewData: NewOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
