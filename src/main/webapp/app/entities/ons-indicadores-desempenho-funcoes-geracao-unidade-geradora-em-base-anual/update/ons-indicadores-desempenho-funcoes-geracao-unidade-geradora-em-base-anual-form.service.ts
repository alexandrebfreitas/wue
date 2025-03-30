import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
  NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
} from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual for edit and NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroupInput for create.
 */
type OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroupInput =
  | IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual
  | PartialWithRequiredKeyOf<NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual>;

type OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormDefaults = Pick<
  NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
  'id'
>;

type OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroupContent = {
  id: FormControl<
    | IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['id']
    | NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['id']
  >;
  idSubsistema: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['idSubsistema']>;
  nomSubsistema: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['nomSubsistema']>;
  idEstado: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['idEstado']>;
  nomEstado: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['nomEstado']>;
  nomModalidadeoperacao: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['nomModalidadeoperacao']>;
  nomAgenteproprietario: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['nomAgenteproprietario']>;
  idTipousina: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['idTipousina']>;
  idUsina: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['idUsina']>;
  nomUsina: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['nomUsina']>;
  ceg: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['ceg']>;
  codEquipamento: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['codEquipamento']>;
  numUnidadegeradora: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['numUnidadegeradora']>;
  nomUnidadegeradora: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['nomUnidadegeradora']>;
  valPotencia: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['valPotencia']>;
  dinAno: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['dinAno']>;
  valDispf: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['valDispf']>;
  valIndisppf: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['valIndisppf']>;
  valIndispff: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['valIndispff']>;
  valDmdff: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['valDmdff']>;
  valFdff: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['valFdff']>;
  valTdff: FormControl<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual['valTdff']>;
};

export type OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroup =
  FormGroup<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormService {
  createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroup(
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroupInput = {
      id: null,
    },
  ): OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroup {
    const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue = {
      ...this.getFormDefaults(),
      ...onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
    };
    return new FormGroup<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroupContent>({
      id: new FormControl(
        { value: onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.nomSubsistema),
      idEstado: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.idEstado),
      nomEstado: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.nomEstado),
      nomModalidadeoperacao: new FormControl(
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.nomModalidadeoperacao,
      ),
      nomAgenteproprietario: new FormControl(
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.nomAgenteproprietario,
      ),
      idTipousina: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.idTipousina),
      idUsina: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.idUsina),
      nomUsina: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.nomUsina),
      ceg: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.ceg),
      codEquipamento: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.codEquipamento),
      numUnidadegeradora: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.numUnidadegeradora),
      nomUnidadegeradora: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.nomUnidadegeradora),
      valPotencia: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.valPotencia),
      dinAno: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.dinAno),
      valDispf: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.valDispf),
      valIndisppf: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.valIndisppf),
      valIndispff: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.valIndispff),
      valDmdff: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.valDmdff),
      valFdff: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.valFdff),
      valTdff: new FormControl(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.valTdff),
    });
  }

  getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual(
    form: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroup,
  ):
    | IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual
    | NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual {
    return form.getRawValue() as
      | IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual
      | NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual;
  }

  resetForm(
    form: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroup,
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroupInput,
  ): void {
    const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue = {
      ...this.getFormDefaults(),
      ...onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
    };
    form.reset(
      {
        ...onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue,
        id: { value: onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormDefaults {
    return {
      id: null,
    };
  }
}
