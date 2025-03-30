import {
  IOnsEnaDiarioReeReservatorioEquivalenteEnergia,
  NewOnsEnaDiarioReeReservatorioEquivalenteEnergia,
} from './ons-ena-diario-ree-reservatorio-equivalente-energia.model';

export const sampleWithRequiredData: IOnsEnaDiarioReeReservatorioEquivalenteEnergia = {
  id: 3078,
};

export const sampleWithPartialData: IOnsEnaDiarioReeReservatorioEquivalenteEnergia = {
  id: 13691,
};

export const sampleWithFullData: IOnsEnaDiarioReeReservatorioEquivalenteEnergia = {
  id: 19696,
  enaArmazenavelReePercentualmlt: 32042.65,
};

export const sampleWithNewData: NewOnsEnaDiarioReeReservatorioEquivalenteEnergia = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
