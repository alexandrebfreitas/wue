import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsDescontinuadoPrecipitacaoDiariaObservada,
  NewOnsDescontinuadoPrecipitacaoDiariaObservada,
} from '../ons-descontinuado-precipitacao-diaria-observada.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsDescontinuadoPrecipitacaoDiariaObservada for edit and NewOnsDescontinuadoPrecipitacaoDiariaObservadaFormGroupInput for create.
 */
type OnsDescontinuadoPrecipitacaoDiariaObservadaFormGroupInput =
  | IOnsDescontinuadoPrecipitacaoDiariaObservada
  | PartialWithRequiredKeyOf<NewOnsDescontinuadoPrecipitacaoDiariaObservada>;

type OnsDescontinuadoPrecipitacaoDiariaObservadaFormDefaults = Pick<NewOnsDescontinuadoPrecipitacaoDiariaObservada, 'id'>;

type OnsDescontinuadoPrecipitacaoDiariaObservadaFormGroupContent = {
  id: FormControl<IOnsDescontinuadoPrecipitacaoDiariaObservada['id'] | NewOnsDescontinuadoPrecipitacaoDiariaObservada['id']>;
  codEstacao: FormControl<IOnsDescontinuadoPrecipitacaoDiariaObservada['codEstacao']>;
  valLatitude: FormControl<IOnsDescontinuadoPrecipitacaoDiariaObservada['valLatitude']>;
  valLongitude: FormControl<IOnsDescontinuadoPrecipitacaoDiariaObservada['valLongitude']>;
  valMedida: FormControl<IOnsDescontinuadoPrecipitacaoDiariaObservada['valMedida']>;
  datObservada: FormControl<IOnsDescontinuadoPrecipitacaoDiariaObservada['datObservada']>;
};

export type OnsDescontinuadoPrecipitacaoDiariaObservadaFormGroup = FormGroup<OnsDescontinuadoPrecipitacaoDiariaObservadaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsDescontinuadoPrecipitacaoDiariaObservadaFormService {
  createOnsDescontinuadoPrecipitacaoDiariaObservadaFormGroup(
    onsDescontinuadoPrecipitacaoDiariaObservada: OnsDescontinuadoPrecipitacaoDiariaObservadaFormGroupInput = { id: null },
  ): OnsDescontinuadoPrecipitacaoDiariaObservadaFormGroup {
    const onsDescontinuadoPrecipitacaoDiariaObservadaRawValue = {
      ...this.getFormDefaults(),
      ...onsDescontinuadoPrecipitacaoDiariaObservada,
    };
    return new FormGroup<OnsDescontinuadoPrecipitacaoDiariaObservadaFormGroupContent>({
      id: new FormControl(
        { value: onsDescontinuadoPrecipitacaoDiariaObservadaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      codEstacao: new FormControl(onsDescontinuadoPrecipitacaoDiariaObservadaRawValue.codEstacao),
      valLatitude: new FormControl(onsDescontinuadoPrecipitacaoDiariaObservadaRawValue.valLatitude),
      valLongitude: new FormControl(onsDescontinuadoPrecipitacaoDiariaObservadaRawValue.valLongitude),
      valMedida: new FormControl(onsDescontinuadoPrecipitacaoDiariaObservadaRawValue.valMedida),
      datObservada: new FormControl(onsDescontinuadoPrecipitacaoDiariaObservadaRawValue.datObservada),
    });
  }

  getOnsDescontinuadoPrecipitacaoDiariaObservada(
    form: OnsDescontinuadoPrecipitacaoDiariaObservadaFormGroup,
  ): IOnsDescontinuadoPrecipitacaoDiariaObservada | NewOnsDescontinuadoPrecipitacaoDiariaObservada {
    return form.getRawValue() as IOnsDescontinuadoPrecipitacaoDiariaObservada | NewOnsDescontinuadoPrecipitacaoDiariaObservada;
  }

  resetForm(
    form: OnsDescontinuadoPrecipitacaoDiariaObservadaFormGroup,
    onsDescontinuadoPrecipitacaoDiariaObservada: OnsDescontinuadoPrecipitacaoDiariaObservadaFormGroupInput,
  ): void {
    const onsDescontinuadoPrecipitacaoDiariaObservadaRawValue = {
      ...this.getFormDefaults(),
      ...onsDescontinuadoPrecipitacaoDiariaObservada,
    };
    form.reset(
      {
        ...onsDescontinuadoPrecipitacaoDiariaObservadaRawValue,
        id: { value: onsDescontinuadoPrecipitacaoDiariaObservadaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsDescontinuadoPrecipitacaoDiariaObservadaFormDefaults {
    return {
      id: null,
    };
  }
}
