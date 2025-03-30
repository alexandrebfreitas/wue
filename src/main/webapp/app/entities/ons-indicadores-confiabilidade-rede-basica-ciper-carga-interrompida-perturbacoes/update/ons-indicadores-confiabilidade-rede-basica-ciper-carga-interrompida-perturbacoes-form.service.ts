import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
  NewOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
} from '../ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes for edit and NewOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroupInput for create.
 */
type OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroupInput =
  | IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes
  | PartialWithRequiredKeyOf<NewOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes>;

type OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormDefaults = Pick<
  NewOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
  'id'
>;

type OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroupContent = {
  id: FormControl<
    | IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes['id']
    | NewOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes['id']
  >;
  dscAgregacao: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes['dscAgregacao']>;
  codCaracteristica: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes['codCaracteristica']>;
  dscCaracteristica: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes['dscCaracteristica']>;
  idPeriodicidade: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes['idPeriodicidade']>;
  dinReferencia: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes['dinReferencia']>;
  valCiper1: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes['valCiper1']>;
  valCiper2: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes['valCiper2']>;
  valCiper3: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes['valCiper3']>;
  valCiper4: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes['valCiper4']>;
  valCiper5: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes['valCiper5']>;
};

export type OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroup =
  FormGroup<OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormService {
  createOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroup(
    onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroupInput = {
      id: null,
    },
  ): OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroup {
    const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRawValue = {
      ...this.getFormDefaults(),
      ...onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
    };
    return new FormGroup<OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroupContent>({
      id: new FormControl(
        { value: onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dscAgregacao: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRawValue.dscAgregacao),
      codCaracteristica: new FormControl(
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRawValue.codCaracteristica,
      ),
      dscCaracteristica: new FormControl(
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRawValue.dscCaracteristica,
      ),
      idPeriodicidade: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRawValue.idPeriodicidade),
      dinReferencia: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRawValue.dinReferencia),
      valCiper1: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRawValue.valCiper1),
      valCiper2: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRawValue.valCiper2),
      valCiper3: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRawValue.valCiper3),
      valCiper4: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRawValue.valCiper4),
      valCiper5: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRawValue.valCiper5),
    });
  }

  getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes(
    form: OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroup,
  ):
    | IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes
    | NewOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes {
    return form.getRawValue() as
      | IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes
      | NewOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes;
  }

  resetForm(
    form: OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroup,
    onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroupInput,
  ): void {
    const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRawValue = {
      ...this.getFormDefaults(),
      ...onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
    };
    form.reset(
      {
        ...onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRawValue,
        id: { value: onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormDefaults {
    return {
      id: null,
    };
  }
}
