import dayjs from 'dayjs/esm';

import {
  IOnsFatorCapacidadeGeracaoEolicaESolar,
  NewOnsFatorCapacidadeGeracaoEolicaESolar,
} from './ons-fator-capacidade-geracao-eolica-e-solar.model';

export const sampleWithRequiredData: IOnsFatorCapacidadeGeracaoEolicaESolar = {
  id: 5399,
};

export const sampleWithPartialData: IOnsFatorCapacidadeGeracaoEolicaESolar = {
  id: 1854,
  idSubsistema: 'youthfully minister',
  idEstado: 'warped',
  nomEstado: 'apud dependent',
  codPontoconexao: 'sizzle revere',
  nomPontoconexao: 'charlatan furthermore uh-huh',
  valLatitudesecoletora: 1654.8,
  valLatitudepontoconexao: 4321.03,
  nomModalidadeoperacao: 'behind',
  nomUsinaConjunto: 'drab vibrant',
  dinInstante: dayjs('2025-03-29'),
  idOns: 'bug',
  valGeracaoverificada: 4570.09,
  valCapacidadeinstalada: 24181.98,
};

export const sampleWithFullData: IOnsFatorCapacidadeGeracaoEolicaESolar = {
  id: 278,
  idSubsistema: 'opposite',
  nomSubsistema: 'burdensome cellar heartfelt',
  idEstado: 'across deployment',
  nomEstado: 'once clamour scorpion',
  codPontoconexao: 'pop',
  nomPontoconexao: 'however',
  nomLocalizacao: 'amused following even',
  valLatitudesecoletora: 4839.71,
  valLongitudesecoletora: 13163.22,
  valLatitudepontoconexao: 29692.1,
  valLongitudepontoconexao: 7488.8,
  nomModalidadeoperacao: 'neatly summary usefully',
  nomTipousina: 'remorseful',
  nomUsinaConjunto: 'haze drat pfft',
  dinInstante: dayjs('2025-03-29'),
  idOns: 'at phew likable',
  ceg: 'where lox well-groomed',
  valGeracaoprogramada: 4301.94,
  valGeracaoverificada: 7121.65,
  valCapacidadeinstalada: 309.28,
  valFatorcapacidade: 14145.92,
};

export const sampleWithNewData: NewOnsFatorCapacidadeGeracaoEolicaESolar = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
