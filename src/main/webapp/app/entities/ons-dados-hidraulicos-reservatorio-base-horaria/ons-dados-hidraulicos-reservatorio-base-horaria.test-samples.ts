import {
  IOnsDadosHidraulicosReservatorioBaseHoraria,
  NewOnsDadosHidraulicosReservatorioBaseHoraria,
} from './ons-dados-hidraulicos-reservatorio-base-horaria.model';

export const sampleWithRequiredData: IOnsDadosHidraulicosReservatorioBaseHoraria = {
  id: 25426,
};

export const sampleWithPartialData: IOnsDadosHidraulicosReservatorioBaseHoraria = {
  id: 18017,
  valVazaodefluente: 30682.61,
  valVazaooutrasestruturas: 3927.44,
  valVazaovertidanaoturbinavel: 23069.78,
};

export const sampleWithFullData: IOnsDadosHidraulicosReservatorioBaseHoraria = {
  id: 10999,
  valVolumeutil: 3636.88,
  valVazaoafluente: 1483.77,
  valVazaodefluente: 26549.13,
  valVazaoturbinada: 23314.99,
  valVazaovertida: 14281.33,
  valVazaooutrasestruturas: 1945.14,
  valVazaotransferida: 9378.95,
  valVazaovertidanaoturbinavel: 17404.8,
};

export const sampleWithNewData: NewOnsDadosHidraulicosReservatorioBaseHoraria = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
