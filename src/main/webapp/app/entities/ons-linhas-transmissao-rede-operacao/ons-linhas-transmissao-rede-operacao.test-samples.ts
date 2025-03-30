import dayjs from 'dayjs/esm';

import { IOnsLinhasTransmissaoRedeOperacao, NewOnsLinhasTransmissaoRedeOperacao } from './ons-linhas-transmissao-rede-operacao.model';

export const sampleWithRequiredData: IOnsLinhasTransmissaoRedeOperacao = {
  id: 13606,
};

export const sampleWithPartialData: IOnsLinhasTransmissaoRedeOperacao = {
  id: 6673,
  idEstadoTerminalde: 'for now',
  nomEstadoDe: 'vibrant whenever',
  nomEstadoPara: 'that',
  nomSubestacaoPara: 'filter',
  valNiveltensaoKv: 18787.86,
  nomTipolinha: 'frantically patiently',
  datEntradaoperacao: dayjs('2025-03-29'),
  valComprimento: 29859.02,
  valResistencia: 4881.06,
  valCapacopercurtasemlimit: 8491.31,
  valCapacidadeoperveraodialonga: 27714.01,
  valCapacoperinvernodialonga: 24410.18,
  valCapacoperinvernonoitelonga: 4171.83,
  valCapacoperveraonoitecurta: 21464.29,
};

export const sampleWithFullData: IOnsLinhasTransmissaoRedeOperacao = {
  id: 9591,
  idSubsistemaTerminalde: 'splurge for',
  nomSubsistemaTerminalde: 'off',
  idSubsistemaTerminalpara: 'what duster hassle',
  nomSubsistemaTerminalpara: 'meh verbally',
  idEstadoTerminalde: 'supplier',
  nomEstadoDe: 'substantial',
  idEstadoTerminalpara: 'expense mount',
  nomEstadoPara: 'declaration',
  nomSubestacaoDe: 'overheard fervently legend',
  nomSubestacaoPara: 'outside made-up',
  valNiveltensaoKv: 2320.97,
  nomTipoderede: 'for excepting disk',
  nomTipolinha: 'brr wearily misreport',
  nomAgenteproprietario: 'hm amused amid',
  nomLinhadetransmissao: 'mixed which',
  codEquipamento: 'react affect lively',
  datEntradaoperacao: dayjs('2025-03-29'),
  datDesativacao: dayjs('2025-03-29'),
  datPrevista: dayjs('2025-03-29'),
  valComprimento: 26648.48,
  valResistencia: 29390.51,
  valReatancia: 28392.37,
  valShunt: 6232.57,
  valCapacoperlongasemlimit: 31957.7,
  valCapacoperlongacomlimit: 20245.47,
  valCapacopercurtasemlimit: 23791.36,
  valCapacopercurtacomlimit: 9138.7,
  valCapacidadeoperveraodialonga: 2090.71,
  valCapacoperinvernodialonga: 26559.59,
  valCapacoperinvernonoitelonga: 12408.03,
  valCapacoperveradiacurta: 1104.99,
  valCapacoperveraonoitecurta: 2585.81,
  valCapacoperinvernodiacurta: 6918.41,
  valCapacoperinvernonoitecurta: 5091.93,
};

export const sampleWithNewData: NewOnsLinhasTransmissaoRedeOperacao = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
