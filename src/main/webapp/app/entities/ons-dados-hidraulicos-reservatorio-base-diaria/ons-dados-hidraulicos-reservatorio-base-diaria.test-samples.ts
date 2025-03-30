import {
  IOnsDadosHidraulicosReservatorioBaseDiaria,
  NewOnsDadosHidraulicosReservatorioBaseDiaria,
} from './ons-dados-hidraulicos-reservatorio-base-diaria.model';

export const sampleWithRequiredData: IOnsDadosHidraulicosReservatorioBaseDiaria = {
  id: 4753,
};

export const sampleWithPartialData: IOnsDadosHidraulicosReservatorioBaseDiaria = {
  id: 7848,
  valNivelmontante: 22656.5,
  valVolumeutilcon: 30565.45,
  valVazaoturbinada: 14789.49,
  valVazaovertida: 26420.6,
  valVazaooutrasestruturas: 22543.92,
  valVazaotransferida: 27324.95,
  valVazaoartificial: 22214.37,
  valVazaoincremental: 1500.89,
  valVazaoevaporacaoliquida: 15993.22,
  valVazaousoconsuntivo: 4245.36,
};

export const sampleWithFullData: IOnsDadosHidraulicosReservatorioBaseDiaria = {
  id: 21647,
  valNivelmontante: 24658.04,
  valNiveljusante: 4138.6,
  valVolumeutilcon: 25735.34,
  valVazaoafluente: 18000.06,
  valVazaoturbinada: 15716.65,
  valVazaovertida: 15869.65,
  valVazaooutrasestruturas: 23902.68,
  valVazaodefluente: 23106.23,
  valVazaotransferida: 24897.13,
  valVazaonatural: 27270.53,
  valVazaoartificial: 16179.47,
  valVazaoincremental: 2996.24,
  valVazaoevaporacaoliquida: 13297.82,
  valVazaousoconsuntivo: 16738.77,
};

export const sampleWithNewData: NewOnsDadosHidraulicosReservatorioBaseDiaria = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
