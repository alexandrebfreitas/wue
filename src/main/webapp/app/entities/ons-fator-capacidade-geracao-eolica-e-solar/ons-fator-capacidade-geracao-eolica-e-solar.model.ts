import dayjs from 'dayjs/esm';

export interface IOnsFatorCapacidadeGeracaoEolicaESolar {
  id: number;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  idEstado?: string | null;
  nomEstado?: string | null;
  codPontoconexao?: string | null;
  nomPontoconexao?: string | null;
  nomLocalizacao?: string | null;
  valLatitudesecoletora?: number | null;
  valLongitudesecoletora?: number | null;
  valLatitudepontoconexao?: number | null;
  valLongitudepontoconexao?: number | null;
  nomModalidadeoperacao?: string | null;
  nomTipousina?: string | null;
  nomUsinaConjunto?: string | null;
  dinInstante?: dayjs.Dayjs | null;
  idOns?: string | null;
  ceg?: string | null;
  valGeracaoprogramada?: number | null;
  valGeracaoverificada?: number | null;
  valCapacidadeinstalada?: number | null;
  valFatorcapacidade?: number | null;
}

export type NewOnsFatorCapacidadeGeracaoEolicaESolar = Omit<IOnsFatorCapacidadeGeracaoEolicaESolar, 'id'> & { id: null };
