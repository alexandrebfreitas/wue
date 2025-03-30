import dayjs from 'dayjs/esm';

export interface IOnsEarDiarioReservatorio {
  id: number;
  idSubsistemaJusante?: string | null;
  nomSubsistemaJusante?: string | null;
  earData?: dayjs.Dayjs | null;
  earReservatorioSubsistemaProprioMwmes?: number | null;
  earReservatorioSubsistemaJusanteMwmes?: number | null;
  earmaxReservatorioSubsistemaProprioMwmes?: number | null;
  earmaxReservatorioSubsistemaJusanteMwmes?: number | null;
  earReservatorioPercentual?: number | null;
  earTotalMwmes?: number | null;
  earMaximaTotalMwmes?: number | null;
  valContribearbacia?: number | null;
  valContribearmaxbacia?: number | null;
  valContribearsubsistema?: number | null;
  valContribearmaxsubsistema?: number | null;
  valContribearsubsistemajusante?: number | null;
  valContribearmaxsubsistemajusante?: number | null;
  valContribearsin?: number | null;
  valContribearmaxsin?: number | null;
}

export type NewOnsEarDiarioReservatorio = Omit<IOnsEarDiarioReservatorio, 'id'> & { id: null };
