import dayjs from 'dayjs/esm';

import { IOnsEquipamentosControleReativos, NewOnsEquipamentosControleReativos } from './ons-equipamentos-controle-reativos.model';

export const sampleWithRequiredData: IOnsEquipamentosControleReativos = {
  id: 3311,
};

export const sampleWithPartialData: IOnsEquipamentosControleReativos = {
  id: 30500,
  nomSubsistema: 'furthermore',
  idEstado: 'along',
  nomSubestacao: 'propound',
  nomTipoequipamento: 'publicize kooky debut',
  valLimitesuperiorMvar: 10223.82,
  datDesativacao: dayjs('2025-03-29'),
  codEquipamento: 'rebuff',
};

export const sampleWithFullData: IOnsEquipamentosControleReativos = {
  id: 32109,
  idSubsistema: 'oh',
  nomSubsistema: 'from blah',
  idEstado: 'cow whoa',
  nomEstado: 'creamy sushi ha',
  nomSubestacao: 'up',
  nomAgenteProprietario: 'ruddy captain ouch',
  nomTipoderede: 'unexpectedly gloss approximate',
  nomTipoequipamento: 'very international gee',
  nomEquipamento: 'scientific amnesty',
  valPotreativanominalMvar: 31788.51,
  valLimiteinferiorMvar: 11767.49,
  valLimitesuperiorMvar: 23198.21,
  datEntradaoperacao: dayjs('2025-03-29'),
  datDesativacao: dayjs('2025-03-29'),
  codEquipamento: 'grandiose bah whoa',
};

export const sampleWithNewData: NewOnsEquipamentosControleReativos = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
