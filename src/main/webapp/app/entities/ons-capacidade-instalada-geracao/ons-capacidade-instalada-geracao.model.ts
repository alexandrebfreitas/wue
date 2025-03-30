import dayjs from 'dayjs/esm';

export interface IOnsCapacidadeInstaladaGeracao {
  id: number;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  idEstado?: string | null;
  nomEstado?: string | null;
  nomModalidadeoperacao?: string | null;
  nomAgenteproprietario?: string | null;
  nomTipousina?: string | null;
  nomUsina?: string | null;
  ceg?: string | null;
  nomUnidadegeradora?: string | null;
  codEquipamento?: string | null;
  numUnidadegeradora?: string | null;
  nomCombustivel?: string | null;
  datEntradateste?: dayjs.Dayjs | null;
  datEntradaoperacao?: dayjs.Dayjs | null;
  datDesativacao?: dayjs.Dayjs | null;
  valPotenciaefetiva?: number | null;
}

export type NewOnsCapacidadeInstaladaGeracao = Omit<IOnsCapacidadeInstaladaGeracao, 'id'> & { id: null };
