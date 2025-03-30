import dayjs from 'dayjs/esm';

import { IOnsCmoSemihorario, NewOnsCmoSemihorario } from './ons-cmo-semihorario.model';

export const sampleWithRequiredData: IOnsCmoSemihorario = {
  id: 31903,
};

export const sampleWithPartialData: IOnsCmoSemihorario = {
  id: 18864,
  idSubsistema: 'ack inside hm',
  dinInstante: dayjs('2025-03-30'),
};

export const sampleWithFullData: IOnsCmoSemihorario = {
  id: 19877,
  idSubsistema: 'ouch',
  nomSubsistema: 'representation brr uh-huh',
  dinInstante: dayjs('2025-03-29'),
  valCmo: 24533.68,
};

export const sampleWithNewData: NewOnsCmoSemihorario = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
