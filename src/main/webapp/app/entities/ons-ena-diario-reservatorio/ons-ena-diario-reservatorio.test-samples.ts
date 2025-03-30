import { IOnsEnaDiarioReservatorio, NewOnsEnaDiarioReservatorio } from './ons-ena-diario-reservatorio.model';

export const sampleWithRequiredData: IOnsEnaDiarioReservatorio = {
  id: 17399,
};

export const sampleWithPartialData: IOnsEnaDiarioReservatorio = {
  id: 19191,
  enaBrutaResPercentualmlt: 10257.15,
  enaQuedaBruta: 18667.3,
};

export const sampleWithFullData: IOnsEnaDiarioReservatorio = {
  id: 16611,
  enaBrutaResMwmed: 6319.49,
  enaBrutaResPercentualmlt: 18485.09,
  enaArmazenavelResMwmed: 20497.13,
  enaArmazenavelResPercentualmlt: 11664.17,
  enaQuedaBruta: 16944.32,
  mltEna: 5729.88,
};

export const sampleWithNewData: NewOnsEnaDiarioReservatorio = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
