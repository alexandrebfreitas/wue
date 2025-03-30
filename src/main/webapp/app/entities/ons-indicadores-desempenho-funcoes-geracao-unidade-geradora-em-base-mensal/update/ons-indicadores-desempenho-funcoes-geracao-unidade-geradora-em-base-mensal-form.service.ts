import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
  NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
} from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal for edit and NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroupInput for create.
 */
type OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroupInput =
  | IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal
  | PartialWithRequiredKeyOf<NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal>;

type OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormDefaults = Pick<
  NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
  'id'
>;

type OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroupContent = {
  id: FormControl<
    | IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['id']
    | NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['id']
  >;
  idSubsistema: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['idSubsistema']>;
  nomSubsistema: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['nomSubsistema']>;
  idEstado: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['idEstado']>;
  nomEstado: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['nomEstado']>;
  nomModalidadeoperacao: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['nomModalidadeoperacao']>;
  nomAgenteproprietario: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['nomAgenteproprietario']>;
  idTipousina: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['idTipousina']>;
  idUsina: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['idUsina']>;
  nomUsina: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['nomUsina']>;
  ceg: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['ceg']>;
  codEquipamento: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['codEquipamento']>;
  numUnidadegeradora: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['numUnidadegeradora']>;
  nomUnidadegeradora: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['nomUnidadegeradora']>;
  valPotencia: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['valPotencia']>;
  valDispf: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['valDispf']>;
  valIndisppf: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['valIndisppf']>;
  valIndispff: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['valIndispff']>;
  valDmdff: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['valDmdff']>;
  valFdff: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['valFdff']>;
  valTdff: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal['valTdff']>;
};

export type OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroup =
  FormGroup<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormService {
  createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroup(
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroupInput = {
      id: null,
    },
  ): OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroup {
    const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue = {
      ...this.getFormDefaults(),
      ...onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
    };
    return new FormGroup<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroupContent>({
      id: new FormControl(
        { value: onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.nomSubsistema),
      idEstado: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.idEstado),
      nomEstado: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.nomEstado),
      nomModalidadeoperacao: new FormControl(
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.nomModalidadeoperacao,
      ),
      nomAgenteproprietario: new FormControl(
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.nomAgenteproprietario,
      ),
      idTipousina: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.idTipousina),
      idUsina: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.idUsina),
      nomUsina: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.nomUsina),
      ceg: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.ceg),
      codEquipamento: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.codEquipamento),
      numUnidadegeradora: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.numUnidadegeradora),
      nomUnidadegeradora: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.nomUnidadegeradora),
      valPotencia: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.valPotencia),
      valDispf: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.valDispf),
      valIndisppf: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.valIndisppf),
      valIndispff: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.valIndispff),
      valDmdff: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.valDmdff),
      valFdff: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.valFdff),
      valTdff: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.valTdff),
    });
  }

  getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal(
    form: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroup,
  ):
    | IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal
    | NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal {
    return form.getRawValue() as
      | IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal
      | NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal;
  }

  resetForm(
    form: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroup,
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroupInput,
  ): void {
    const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue = {
      ...this.getFormDefaults(),
      ...onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
    };
    form.reset(
      {
        ...onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue,
        id: { value: onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormDefaults {
    return {
      id: null,
    };
  }
}
