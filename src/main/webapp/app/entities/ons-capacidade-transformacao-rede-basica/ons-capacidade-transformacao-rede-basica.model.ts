import dayjs from 'dayjs/esm';

export interface IOnsCapacidadeTransformacaoRedeBasica {
  id: number;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  idEstado?: string | null;
  nomEstado?: string | null;
  nomTipotransformador?: string | null;
  nomAgenteproprietario?: string | null;
  nomSubestacao?: string | null;
  nomTransformador?: string | null;
  codEquipamento?: string | null;
  datEntradaoperacao?: dayjs.Dayjs | null;
  datDesativacao?: dayjs.Dayjs | null;
  valTensaoprimarioKv?: number | null;
  valTensaosecundarioKv?: number | null;
  valTensaoterciarioKv?: number | null;
  valPotencianominalMva?: number | null;
  nomTipoderede?: string | null;
  numBarraPrimario?: number | null;
  numBarraSecundario?: number | null;
}

export type NewOnsCapacidadeTransformacaoRedeBasica = Omit<IOnsCapacidadeTransformacaoRedeBasica, 'id'> & { id: null };
