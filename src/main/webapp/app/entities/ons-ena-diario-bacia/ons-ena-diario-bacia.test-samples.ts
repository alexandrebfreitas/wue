import { IOnsEnaDiarioBacia, NewOnsEnaDiarioBacia } from './ons-ena-diario-bacia.model';

export const sampleWithRequiredData: IOnsEnaDiarioBacia = {
  id: 14071,
};

export const sampleWithPartialData: IOnsEnaDiarioBacia = {
  id: 19268,
};

export const sampleWithFullData: IOnsEnaDiarioBacia = {
  id: 21491,
  enaArmazenavelBaciaPercentualmlt: 17281.04,
};

export const sampleWithNewData: NewOnsEnaDiarioBacia = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
