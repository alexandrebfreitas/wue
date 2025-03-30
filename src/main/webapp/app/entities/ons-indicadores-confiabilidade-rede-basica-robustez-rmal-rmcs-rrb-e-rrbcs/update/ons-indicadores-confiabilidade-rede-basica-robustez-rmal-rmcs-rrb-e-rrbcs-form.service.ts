import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
  NewOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
} from '../ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs for edit and NewOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroupInput for create.
 */
type OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroupInput =
  | IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs
  | PartialWithRequiredKeyOf<NewOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs>;

type OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormDefaults = Pick<
  NewOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
  'id'
>;

type OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroupContent = {
  id: FormControl<
    | IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs['id']
    | NewOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs['id']
  >;
  codIndicador: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs['codIndicador']>;
  dscAgregacao: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs['dscAgregacao']>;
  codCaracteristica: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs['codCaracteristica']>;
  dscCaracteristica: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs['dscCaracteristica']>;
  idPeriodicidade: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs['idPeriodicidade']>;
  dinReferencia: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs['dinReferencia']>;
  valIndicador: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs['valIndicador']>;
  numPerturbacoes: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs['numPerturbacoes']>;
  numPerturbacoescortecarga: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs['numPerturbacoescortecarga']>;
  numPerturbacoescortecarga0a50mw: FormControl<
    IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs['numPerturbacoescortecarga0a50mw']
  >;
  numPerturbacoescortecarga50a100mw: FormControl<
    IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs['numPerturbacoescortecarga50a100mw']
  >;
  numPerturbacoescortecargaMaior100mw: FormControl<
    IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs['numPerturbacoescortecargaMaior100mw']
  >;
};

export type OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroup =
  FormGroup<OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormService {
  createOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroup(
    onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroupInput = {
      id: null,
    },
  ): OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroup {
    const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRawValue = {
      ...this.getFormDefaults(),
      ...onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
    };
    return new FormGroup<OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroupContent>({
      id: new FormControl(
        { value: onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      codIndicador: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRawValue.codIndicador),
      dscAgregacao: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRawValue.dscAgregacao),
      codCaracteristica: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRawValue.codCaracteristica),
      dscCaracteristica: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRawValue.dscCaracteristica),
      idPeriodicidade: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRawValue.idPeriodicidade),
      dinReferencia: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRawValue.dinReferencia),
      valIndicador: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRawValue.valIndicador),
      numPerturbacoes: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRawValue.numPerturbacoes),
      numPerturbacoescortecarga: new FormControl(
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRawValue.numPerturbacoescortecarga,
      ),
      numPerturbacoescortecarga0a50mw: new FormControl(
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRawValue.numPerturbacoescortecarga0a50mw,
      ),
      numPerturbacoescortecarga50a100mw: new FormControl(
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRawValue.numPerturbacoescortecarga50a100mw,
      ),
      numPerturbacoescortecargaMaior100mw: new FormControl(
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRawValue.numPerturbacoescortecargaMaior100mw,
      ),
    });
  }

  getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs(
    form: OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroup,
  ): IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs | NewOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs {
    return form.getRawValue() as
      | IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs
      | NewOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs;
  }

  resetForm(
    form: OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroup,
    onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroupInput,
  ): void {
    const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRawValue = {
      ...this.getFormDefaults(),
      ...onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
    };
    form.reset(
      {
        ...onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRawValue,
        id: { value: onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormDefaults {
    return {
      id: null,
    };
  }
}
