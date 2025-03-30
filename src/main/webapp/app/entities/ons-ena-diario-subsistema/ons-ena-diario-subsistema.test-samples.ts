import { IOnsEnaDiarioSubsistema, NewOnsEnaDiarioSubsistema } from './ons-ena-diario-subsistema.model';

export const sampleWithRequiredData: IOnsEnaDiarioSubsistema = {
  id: 8464,
};

export const sampleWithPartialData: IOnsEnaDiarioSubsistema = {
  id: 17026,
  enaArmazenavelRegiaoPercentualmlt: 18429.57,
};

export const sampleWithFullData: IOnsEnaDiarioSubsistema = {
  id: 19210,
  enaArmazenavelRegiaoPercentualmlt: 13728.44,
};

export const sampleWithNewData: NewOnsEnaDiarioSubsistema = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
