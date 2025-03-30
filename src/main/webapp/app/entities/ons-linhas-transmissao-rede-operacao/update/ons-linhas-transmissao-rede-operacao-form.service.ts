import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsLinhasTransmissaoRedeOperacao, NewOnsLinhasTransmissaoRedeOperacao } from '../ons-linhas-transmissao-rede-operacao.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsLinhasTransmissaoRedeOperacao for edit and NewOnsLinhasTransmissaoRedeOperacaoFormGroupInput for create.
 */
type OnsLinhasTransmissaoRedeOperacaoFormGroupInput =
  | IOnsLinhasTransmissaoRedeOperacao
  | PartialWithRequiredKeyOf<NewOnsLinhasTransmissaoRedeOperacao>;

type OnsLinhasTransmissaoRedeOperacaoFormDefaults = Pick<NewOnsLinhasTransmissaoRedeOperacao, 'id'>;

type OnsLinhasTransmissaoRedeOperacaoFormGroupContent = {
  id: FormControl<IOnsLinhasTransmissaoRedeOperacao['id'] | NewOnsLinhasTransmissaoRedeOperacao['id']>;
  idSubsistemaTerminalde: FormControl<IOnsLinhasTransmissaoRedeOperacao['idSubsistemaTerminalde']>;
  nomSubsistemaTerminalde: FormControl<IOnsLinhasTransmissaoRedeOperacao['nomSubsistemaTerminalde']>;
  idSubsistemaTerminalpara: FormControl<IOnsLinhasTransmissaoRedeOperacao['idSubsistemaTerminalpara']>;
  nomSubsistemaTerminalpara: FormControl<IOnsLinhasTransmissaoRedeOperacao['nomSubsistemaTerminalpara']>;
  idEstadoTerminalde: FormControl<IOnsLinhasTransmissaoRedeOperacao['idEstadoTerminalde']>;
  nomEstadoDe: FormControl<IOnsLinhasTransmissaoRedeOperacao['nomEstadoDe']>;
  idEstadoTerminalpara: FormControl<IOnsLinhasTransmissaoRedeOperacao['idEstadoTerminalpara']>;
  nomEstadoPara: FormControl<IOnsLinhasTransmissaoRedeOperacao['nomEstadoPara']>;
  nomSubestacaoDe: FormControl<IOnsLinhasTransmissaoRedeOperacao['nomSubestacaoDe']>;
  nomSubestacaoPara: FormControl<IOnsLinhasTransmissaoRedeOperacao['nomSubestacaoPara']>;
  valNiveltensaoKv: FormControl<IOnsLinhasTransmissaoRedeOperacao['valNiveltensaoKv']>;
  nomTipoderede: FormControl<IOnsLinhasTransmissaoRedeOperacao['nomTipoderede']>;
  nomTipolinha: FormControl<IOnsLinhasTransmissaoRedeOperacao['nomTipolinha']>;
  nomAgenteproprietario: FormControl<IOnsLinhasTransmissaoRedeOperacao['nomAgenteproprietario']>;
  nomLinhadetransmissao: FormControl<IOnsLinhasTransmissaoRedeOperacao['nomLinhadetransmissao']>;
  codEquipamento: FormControl<IOnsLinhasTransmissaoRedeOperacao['codEquipamento']>;
  datEntradaoperacao: FormControl<IOnsLinhasTransmissaoRedeOperacao['datEntradaoperacao']>;
  datDesativacao: FormControl<IOnsLinhasTransmissaoRedeOperacao['datDesativacao']>;
  datPrevista: FormControl<IOnsLinhasTransmissaoRedeOperacao['datPrevista']>;
  valComprimento: FormControl<IOnsLinhasTransmissaoRedeOperacao['valComprimento']>;
  valResistencia: FormControl<IOnsLinhasTransmissaoRedeOperacao['valResistencia']>;
  valReatancia: FormControl<IOnsLinhasTransmissaoRedeOperacao['valReatancia']>;
  valShunt: FormControl<IOnsLinhasTransmissaoRedeOperacao['valShunt']>;
  valCapacoperlongasemlimit: FormControl<IOnsLinhasTransmissaoRedeOperacao['valCapacoperlongasemlimit']>;
  valCapacoperlongacomlimit: FormControl<IOnsLinhasTransmissaoRedeOperacao['valCapacoperlongacomlimit']>;
  valCapacopercurtasemlimit: FormControl<IOnsLinhasTransmissaoRedeOperacao['valCapacopercurtasemlimit']>;
  valCapacopercurtacomlimit: FormControl<IOnsLinhasTransmissaoRedeOperacao['valCapacopercurtacomlimit']>;
  valCapacidadeoperveraodialonga: FormControl<IOnsLinhasTransmissaoRedeOperacao['valCapacidadeoperveraodialonga']>;
  valCapacoperinvernodialonga: FormControl<IOnsLinhasTransmissaoRedeOperacao['valCapacoperinvernodialonga']>;
  valCapacoperinvernonoitelonga: FormControl<IOnsLinhasTransmissaoRedeOperacao['valCapacoperinvernonoitelonga']>;
  valCapacoperveradiacurta: FormControl<IOnsLinhasTransmissaoRedeOperacao['valCapacoperveradiacurta']>;
  valCapacoperveraonoitecurta: FormControl<IOnsLinhasTransmissaoRedeOperacao['valCapacoperveraonoitecurta']>;
  valCapacoperinvernodiacurta: FormControl<IOnsLinhasTransmissaoRedeOperacao['valCapacoperinvernodiacurta']>;
  valCapacoperinvernonoitecurta: FormControl<IOnsLinhasTransmissaoRedeOperacao['valCapacoperinvernonoitecurta']>;
};

export type OnsLinhasTransmissaoRedeOperacaoFormGroup = FormGroup<OnsLinhasTransmissaoRedeOperacaoFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsLinhasTransmissaoRedeOperacaoFormService {
  createOnsLinhasTransmissaoRedeOperacaoFormGroup(
    onsLinhasTransmissaoRedeOperacao: OnsLinhasTransmissaoRedeOperacaoFormGroupInput = { id: null },
  ): OnsLinhasTransmissaoRedeOperacaoFormGroup {
    const onsLinhasTransmissaoRedeOperacaoRawValue = {
      ...this.getFormDefaults(),
      ...onsLinhasTransmissaoRedeOperacao,
    };
    return new FormGroup<OnsLinhasTransmissaoRedeOperacaoFormGroupContent>({
      id: new FormControl(
        { value: onsLinhasTransmissaoRedeOperacaoRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistemaTerminalde: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.idSubsistemaTerminalde),
      nomSubsistemaTerminalde: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.nomSubsistemaTerminalde),
      idSubsistemaTerminalpara: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.idSubsistemaTerminalpara),
      nomSubsistemaTerminalpara: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.nomSubsistemaTerminalpara),
      idEstadoTerminalde: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.idEstadoTerminalde),
      nomEstadoDe: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.nomEstadoDe),
      idEstadoTerminalpara: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.idEstadoTerminalpara),
      nomEstadoPara: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.nomEstadoPara),
      nomSubestacaoDe: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.nomSubestacaoDe),
      nomSubestacaoPara: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.nomSubestacaoPara),
      valNiveltensaoKv: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.valNiveltensaoKv),
      nomTipoderede: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.nomTipoderede),
      nomTipolinha: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.nomTipolinha),
      nomAgenteproprietario: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.nomAgenteproprietario),
      nomLinhadetransmissao: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.nomLinhadetransmissao),
      codEquipamento: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.codEquipamento),
      datEntradaoperacao: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.datEntradaoperacao),
      datDesativacao: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.datDesativacao),
      datPrevista: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.datPrevista),
      valComprimento: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.valComprimento),
      valResistencia: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.valResistencia),
      valReatancia: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.valReatancia),
      valShunt: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.valShunt),
      valCapacoperlongasemlimit: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.valCapacoperlongasemlimit),
      valCapacoperlongacomlimit: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.valCapacoperlongacomlimit),
      valCapacopercurtasemlimit: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.valCapacopercurtasemlimit),
      valCapacopercurtacomlimit: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.valCapacopercurtacomlimit),
      valCapacidadeoperveraodialonga: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.valCapacidadeoperveraodialonga),
      valCapacoperinvernodialonga: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.valCapacoperinvernodialonga),
      valCapacoperinvernonoitelonga: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.valCapacoperinvernonoitelonga),
      valCapacoperveradiacurta: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.valCapacoperveradiacurta),
      valCapacoperveraonoitecurta: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.valCapacoperveraonoitecurta),
      valCapacoperinvernodiacurta: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.valCapacoperinvernodiacurta),
      valCapacoperinvernonoitecurta: new FormControl(onsLinhasTransmissaoRedeOperacaoRawValue.valCapacoperinvernonoitecurta),
    });
  }

  getOnsLinhasTransmissaoRedeOperacao(
    form: OnsLinhasTransmissaoRedeOperacaoFormGroup,
  ): IOnsLinhasTransmissaoRedeOperacao | NewOnsLinhasTransmissaoRedeOperacao {
    return form.getRawValue() as IOnsLinhasTransmissaoRedeOperacao | NewOnsLinhasTransmissaoRedeOperacao;
  }

  resetForm(
    form: OnsLinhasTransmissaoRedeOperacaoFormGroup,
    onsLinhasTransmissaoRedeOperacao: OnsLinhasTransmissaoRedeOperacaoFormGroupInput,
  ): void {
    const onsLinhasTransmissaoRedeOperacaoRawValue = { ...this.getFormDefaults(), ...onsLinhasTransmissaoRedeOperacao };
    form.reset(
      {
        ...onsLinhasTransmissaoRedeOperacaoRawValue,
        id: { value: onsLinhasTransmissaoRedeOperacaoRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsLinhasTransmissaoRedeOperacaoFormDefaults {
    return {
      id: null,
    };
  }
}
