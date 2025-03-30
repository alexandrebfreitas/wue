import dayjs from 'dayjs/esm';

import { IOnsGeracaoTermicaMotivoDespacho, NewOnsGeracaoTermicaMotivoDespacho } from './ons-geracao-termica-motivo-despacho.model';

export const sampleWithRequiredData: IOnsGeracaoTermicaMotivoDespacho = {
  id: 15186,
};

export const sampleWithPartialData: IOnsGeracaoTermicaMotivoDespacho = {
  id: 28761,
  dinInstante: dayjs('2025-03-29'),
  nomTipopatamar: 'nor',
  idSubsistema: 'dull seriously boo',
  codUsinaplanejamento: 13851,
  valProgordemdemeritoref: 5570.05,
  valProginflexibilidade: 3801.92,
  valProginflexembutmerito: 26511.14,
  valPrograzaoeletrica: 6985.71,
  valProggarantiaenergetica: 23514.49,
  valProgreposicaoperdas: 29845.55,
  valProgexportacao: 30718.84,
  valProgreservapotencia: 19426.13,
  valProggsub: 26169.53,
  valProgconstrainedoff: 10789.27,
  valProginflexibilidadedessem: 14353.17,
  valVerifgeracao: 16021.45,
  valVerifordemdemeritoacimadainflex: 21441.78,
  valVerifrazaoeletrica: 15960.67,
  valVerifgarantiaenergetica: 15225.6,
  valVerifgfom: 28337.37,
  valVerifreposicaoperdas: 31595.3,
  valFdexp: 2700.55,
  valAtendsatisfatoriorpo: 10973,
  valVerifgsub: 14570.98,
  valVerifunitcommitment: 19298.47,
};

export const sampleWithFullData: IOnsGeracaoTermicaMotivoDespacho = {
  id: 23869,
  dinInstante: dayjs('2025-03-29'),
  nomTipopatamar: 'aw suckle',
  idSubsistema: 'whenever',
  nomSubsistema: 'modulo grok',
  nomUsina: 'futon turret',
  codUsinaplanejamento: 10230,
  ceg: 'amend',
  valProggeracao: 32022.63,
  valProgordemmerito: 24318.52,
  valProgordemdemeritoref: 4457.55,
  valProgordemdemeritoacimadainflex: 16.03,
  valProginflexibilidade: 24465.99,
  valProginflexembutmerito: 17182.8,
  valProginflexpura: 13526.44,
  valPrograzaoeletrica: 5200.83,
  valProggarantiaenergetica: 32087.69,
  valProggfom: 11772.11,
  valProgreposicaoperdas: 2957.91,
  valProgexportacao: 32141.48,
  valProgreservapotencia: 32515.83,
  valProggsub: 2900.69,
  valProgunitcommitment: 23230.01,
  valProgconstrainedoff: 26244.76,
  valProginflexibilidadedessem: 24444.35,
  valVerifgeracao: 21241.98,
  valVerifordemmerito: 17608.11,
  valVerifordemdemeritoacimadainflex: 13917.3,
  valVerifinflexibilidade: 12452.25,
  valVerifinflexembutmerito: 20607.83,
  valVerifinflexpura: 18510.91,
  valVerifrazaoeletrica: 405.33,
  valVerifgarantiaenergetica: 23222.25,
  valVerifgfom: 2860.99,
  valVerifreposicaoperdas: 14879.97,
  valVerifexportacao: 15041.76,
  valFdexp: 5960.89,
  valVerifreservapotencia: 20836.53,
  valAtendsatisfatoriorpo: 27395,
  valVerifgsub: 10345.36,
  valVerifunitcommitment: 6508.18,
  valVerifconstrainedoff: 6362.02,
  tipRestricaoeletrica: 6969,
};

export const sampleWithNewData: NewOnsGeracaoTermicaMotivoDespacho = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
