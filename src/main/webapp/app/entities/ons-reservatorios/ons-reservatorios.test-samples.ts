import dayjs from 'dayjs/esm';

import { IOnsReservatorios, NewOnsReservatorios } from './ons-reservatorios.model';

export const sampleWithRequiredData: IOnsReservatorios = {
  id: 31193,
};

export const sampleWithPartialData: IOnsReservatorios = {
  id: 24228,
  nomRee: 'writ eek',
  valCotaminima: 23026.46,
  valVolmax: 31394.77,
  valVolutiltot: 9302.95,
  valProdutibilidadeespecifica: 1207.09,
  valLongitude: 10122.67,
};

export const sampleWithFullData: IOnsReservatorios = {
  id: 18661,
  nomRee: 'following',
  datEntrada: dayjs('2025-03-30'),
  valCotamaxima: 19290.35,
  valCotaminima: 30432.73,
  valVolmax: 26248.87,
  valVolmin: 1442.06,
  valVolutiltot: 857.66,
  valProdutibilidadeespecifica: 17234.04,
  valProdutividade65volutil: 25047.63,
  valTipoperda: 'fooey',
  valPerda: 19235.28,
  valLatitude: 7590.25,
  valLongitude: 6316.98,
  idReservatorio: 'boohoo now structure',
};

export const sampleWithNewData: NewOnsReservatorios = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
