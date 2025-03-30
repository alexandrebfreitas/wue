import dayjs from 'dayjs/esm';

export interface IOnsLinhasTransmissaoRedeOperacao {
  id: number;
  idSubsistemaTerminalde?: string | null;
  nomSubsistemaTerminalde?: string | null;
  idSubsistemaTerminalpara?: string | null;
  nomSubsistemaTerminalpara?: string | null;
  idEstadoTerminalde?: string | null;
  nomEstadoDe?: string | null;
  idEstadoTerminalpara?: string | null;
  nomEstadoPara?: string | null;
  nomSubestacaoDe?: string | null;
  nomSubestacaoPara?: string | null;
  valNiveltensaoKv?: number | null;
  nomTipoderede?: string | null;
  nomTipolinha?: string | null;
  nomAgenteproprietario?: string | null;
  nomLinhadetransmissao?: string | null;
  codEquipamento?: string | null;
  datEntradaoperacao?: dayjs.Dayjs | null;
  datDesativacao?: dayjs.Dayjs | null;
  datPrevista?: dayjs.Dayjs | null;
  valComprimento?: number | null;
  valResistencia?: number | null;
  valReatancia?: number | null;
  valShunt?: number | null;
  valCapacoperlongasemlimit?: number | null;
  valCapacoperlongacomlimit?: number | null;
  valCapacopercurtasemlimit?: number | null;
  valCapacopercurtacomlimit?: number | null;
  valCapacidadeoperveraodialonga?: number | null;
  valCapacoperinvernodialonga?: number | null;
  valCapacoperinvernonoitelonga?: number | null;
  valCapacoperveradiacurta?: number | null;
  valCapacoperveraonoitecurta?: number | null;
  valCapacoperinvernodiacurta?: number | null;
  valCapacoperinvernonoitecurta?: number | null;
}

export type NewOnsLinhasTransmissaoRedeOperacao = Omit<IOnsLinhasTransmissaoRedeOperacao, 'id'> & { id: null };
