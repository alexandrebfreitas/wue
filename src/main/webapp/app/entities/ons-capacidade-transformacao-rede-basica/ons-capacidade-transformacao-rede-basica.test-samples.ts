import dayjs from 'dayjs/esm';

import {
  IOnsCapacidadeTransformacaoRedeBasica,
  NewOnsCapacidadeTransformacaoRedeBasica,
} from './ons-capacidade-transformacao-rede-basica.model';

export const sampleWithRequiredData: IOnsCapacidadeTransformacaoRedeBasica = {
  id: 21733,
};

export const sampleWithPartialData: IOnsCapacidadeTransformacaoRedeBasica = {
  id: 24560,
  idSubsistema: 'shimmering vacation orchid',
  nomSubsistema: 'duh',
  nomEstado: 'too rapidly',
  nomTipotransformador: 'including ack',
  nomAgenteproprietario: 'fedora puritan accentuate',
  nomSubestacao: 'another',
  nomTransformador: 'place',
  valTensaoprimarioKv: 5335.12,
  valTensaoterciarioKv: 11885.78,
  valPotencianominalMva: 8016.09,
  nomTipoderede: 'on favorable and',
  numBarraPrimario: 7941,
};

export const sampleWithFullData: IOnsCapacidadeTransformacaoRedeBasica = {
  id: 26101,
  idSubsistema: 'off',
  nomSubsistema: 'unlike',
  idEstado: 'farm boohoo',
  nomEstado: 'boo about',
  nomTipotransformador: 'though',
  nomAgenteproprietario: 'lest',
  nomSubestacao: 'now',
  nomTransformador: 'on whose cruelly',
  codEquipamento: 'zen plus',
  datEntradaoperacao: dayjs('2025-03-29'),
  datDesativacao: dayjs('2025-03-29'),
  valTensaoprimarioKv: 18798.72,
  valTensaosecundarioKv: 23945.35,
  valTensaoterciarioKv: 23014.62,
  valPotencianominalMva: 19242.12,
  nomTipoderede: 'unlike ape',
  numBarraPrimario: 20394,
  numBarraSecundario: 1040,
};

export const sampleWithNewData: NewOnsCapacidadeTransformacaoRedeBasica = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
