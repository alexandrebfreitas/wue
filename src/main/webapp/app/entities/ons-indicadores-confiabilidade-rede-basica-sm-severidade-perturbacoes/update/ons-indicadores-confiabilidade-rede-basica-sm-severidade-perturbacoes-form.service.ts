import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
  NewOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
} from '../ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes for edit and NewOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroupInput for create.
 */
type OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroupInput =
  | IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes
  | PartialWithRequiredKeyOf<NewOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes>;

type OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormDefaults = Pick<
  NewOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
  'id'
>;

type OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroupContent = {
  id: FormControl<
    | IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes['id']
    | NewOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes['id']
  >;
  dscAgregacao: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes['dscAgregacao']>;
  codCaracteristica: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes['codCaracteristica']>;
  dscCaracteristica: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes['dscCaracteristica']>;
  idPeriodicidade: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes['idPeriodicidade']>;
  dinReferencia: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes['dinReferencia']>;
  valSm1: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes['valSm1']>;
  valSm2: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes['valSm2']>;
  valSm3: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes['valSm3']>;
  valSm4: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes['valSm4']>;
  valSm5: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes['valSm5']>;
};

export type OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroup =
  FormGroup<OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormService {
  createOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroup(
    onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroupInput = {
      id: null,
    },
  ): OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroup {
    const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRawValue = {
      ...this.getFormDefaults(),
      ...onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
    };
    return new FormGroup<OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroupContent>({
      id: new FormControl(
        { value: onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dscAgregacao: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRawValue.dscAgregacao),
      codCaracteristica: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRawValue.codCaracteristica),
      dscCaracteristica: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRawValue.dscCaracteristica),
      idPeriodicidade: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRawValue.idPeriodicidade),
      dinReferencia: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRawValue.dinReferencia),
      valSm1: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRawValue.valSm1),
      valSm2: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRawValue.valSm2),
      valSm3: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRawValue.valSm3),
      valSm4: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRawValue.valSm4),
      valSm5: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRawValue.valSm5),
    });
  }

  getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes(
    form: OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroup,
  ): IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes | NewOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes {
    return form.getRawValue() as
      | IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes
      | NewOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes;
  }

  resetForm(
    form: OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroup,
    onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroupInput,
  ): void {
    const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRawValue = {
      ...this.getFormDefaults(),
      ...onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
    };
    form.reset(
      {
        ...onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRawValue,
        id: { value: onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormDefaults {
    return {
      id: null,
    };
  }
}
