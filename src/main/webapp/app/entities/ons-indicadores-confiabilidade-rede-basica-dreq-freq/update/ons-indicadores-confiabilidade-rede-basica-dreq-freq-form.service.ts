import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq,
  NewOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq,
} from '../ons-indicadores-confiabilidade-rede-basica-dreq-freq.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq for edit and NewOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroupInput for create.
 */
type OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroupInput =
  | IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq
  | PartialWithRequiredKeyOf<NewOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq>;

type OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormDefaults = Pick<NewOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq, 'id'>;

type OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroupContent = {
  id: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq['id'] | NewOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq['id']>;
  dscAgregacao: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq['dscAgregacao']>;
  codCaracteristica: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq['codCaracteristica']>;
  dscCaracteristica: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq['dscCaracteristica']>;
  idPeriodicidade: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq['idPeriodicidade']>;
  dinReferencia: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq['dinReferencia']>;
  valDreq: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq['valDreq']>;
  valFreq: FormControl<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq['valFreq']>;
};

export type OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroup =
  FormGroup<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormService {
  createOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroup(
    onsIndicadoresConfiabilidadeRedeBasicaDreqFreq: OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroupInput = { id: null },
  ): OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroup {
    const onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRawValue = {
      ...this.getFormDefaults(),
      ...onsIndicadoresConfiabilidadeRedeBasicaDreqFreq,
    };
    return new FormGroup<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroupContent>({
      id: new FormControl(
        { value: onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dscAgregacao: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRawValue.dscAgregacao),
      codCaracteristica: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRawValue.codCaracteristica),
      dscCaracteristica: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRawValue.dscCaracteristica),
      idPeriodicidade: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRawValue.idPeriodicidade),
      dinReferencia: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRawValue.dinReferencia),
      valDreq: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRawValue.valDreq),
      valFreq: new FormControl(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRawValue.valFreq),
    });
  }

  getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq(
    form: OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroup,
  ): IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq | NewOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq {
    return form.getRawValue() as IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq | NewOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq;
  }

  resetForm(
    form: OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroup,
    onsIndicadoresConfiabilidadeRedeBasicaDreqFreq: OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroupInput,
  ): void {
    const onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRawValue = {
      ...this.getFormDefaults(),
      ...onsIndicadoresConfiabilidadeRedeBasicaDreqFreq,
    };
    form.reset(
      {
        ...onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRawValue,
        id: { value: onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormDefaults {
    return {
      id: null,
    };
  }
}
