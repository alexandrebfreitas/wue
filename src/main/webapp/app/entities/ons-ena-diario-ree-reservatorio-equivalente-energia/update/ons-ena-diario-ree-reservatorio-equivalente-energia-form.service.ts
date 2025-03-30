import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsEnaDiarioReeReservatorioEquivalenteEnergia,
  NewOnsEnaDiarioReeReservatorioEquivalenteEnergia,
} from '../ons-ena-diario-ree-reservatorio-equivalente-energia.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsEnaDiarioReeReservatorioEquivalenteEnergia for edit and NewOnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroupInput for create.
 */
type OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroupInput =
  | IOnsEnaDiarioReeReservatorioEquivalenteEnergia
  | PartialWithRequiredKeyOf<NewOnsEnaDiarioReeReservatorioEquivalenteEnergia>;

type OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormDefaults = Pick<NewOnsEnaDiarioReeReservatorioEquivalenteEnergia, 'id'>;

type OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroupContent = {
  id: FormControl<IOnsEnaDiarioReeReservatorioEquivalenteEnergia['id'] | NewOnsEnaDiarioReeReservatorioEquivalenteEnergia['id']>;
  enaArmazenavelReePercentualmlt: FormControl<IOnsEnaDiarioReeReservatorioEquivalenteEnergia['enaArmazenavelReePercentualmlt']>;
};

export type OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroup =
  FormGroup<OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormService {
  createOnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroup(
    onsEnaDiarioReeReservatorioEquivalenteEnergia: OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroupInput = { id: null },
  ): OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroup {
    const onsEnaDiarioReeReservatorioEquivalenteEnergiaRawValue = {
      ...this.getFormDefaults(),
      ...onsEnaDiarioReeReservatorioEquivalenteEnergia,
    };
    return new FormGroup<OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroupContent>({
      id: new FormControl(
        { value: onsEnaDiarioReeReservatorioEquivalenteEnergiaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      enaArmazenavelReePercentualmlt: new FormControl(onsEnaDiarioReeReservatorioEquivalenteEnergiaRawValue.enaArmazenavelReePercentualmlt),
    });
  }

  getOnsEnaDiarioReeReservatorioEquivalenteEnergia(
    form: OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroup,
  ): IOnsEnaDiarioReeReservatorioEquivalenteEnergia | NewOnsEnaDiarioReeReservatorioEquivalenteEnergia {
    return form.getRawValue() as IOnsEnaDiarioReeReservatorioEquivalenteEnergia | NewOnsEnaDiarioReeReservatorioEquivalenteEnergia;
  }

  resetForm(
    form: OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroup,
    onsEnaDiarioReeReservatorioEquivalenteEnergia: OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroupInput,
  ): void {
    const onsEnaDiarioReeReservatorioEquivalenteEnergiaRawValue = {
      ...this.getFormDefaults(),
      ...onsEnaDiarioReeReservatorioEquivalenteEnergia,
    };
    form.reset(
      {
        ...onsEnaDiarioReeReservatorioEquivalenteEnergiaRawValue,
        id: { value: onsEnaDiarioReeReservatorioEquivalenteEnergiaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormDefaults {
    return {
      id: null,
    };
  }
}
