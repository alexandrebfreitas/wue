import dayjs from 'dayjs/esm';

import { IOnsEarDiarioReservatorio, NewOnsEarDiarioReservatorio } from './ons-ear-diario-reservatorio.model';

export const sampleWithRequiredData: IOnsEarDiarioReservatorio = {
  id: 7964,
};

export const sampleWithPartialData: IOnsEarDiarioReservatorio = {
  id: 12944,
  earmaxReservatorioSubsistemaJusanteMwmes: 24921.67,
  earReservatorioPercentual: 32632.37,
  earTotalMwmes: 22023.68,
  earMaximaTotalMwmes: 922.27,
  valContribearbacia: 23145.52,
  valContribearmaxbacia: 8259.28,
  valContribearsubsistemajusante: 32038.81,
  valContribearmaxsubsistemajusante: 17314.67,
  valContribearsin: 15390.36,
  valContribearmaxsin: 4142.57,
};

export const sampleWithFullData: IOnsEarDiarioReservatorio = {
  id: 17828,
  idSubsistemaJusante: 'over even',
  nomSubsistemaJusante: 'colorful essay longingly',
  earData: dayjs('2025-03-29'),
  earReservatorioSubsistemaProprioMwmes: 27227.59,
  earReservatorioSubsistemaJusanteMwmes: 25069.93,
  earmaxReservatorioSubsistemaProprioMwmes: 14676.26,
  earmaxReservatorioSubsistemaJusanteMwmes: 1701,
  earReservatorioPercentual: 10350.24,
  earTotalMwmes: 15504.17,
  earMaximaTotalMwmes: 25524.53,
  valContribearbacia: 27814.87,
  valContribearmaxbacia: 8106.14,
  valContribearsubsistema: 9925.87,
  valContribearmaxsubsistema: 19970.38,
  valContribearsubsistemajusante: 17364.4,
  valContribearmaxsubsistemajusante: 27026.91,
  valContribearsin: 15188.46,
  valContribearmaxsin: 21313.02,
};

export const sampleWithNewData: NewOnsEarDiarioReservatorio = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
