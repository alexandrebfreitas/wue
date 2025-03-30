import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsEarDiarioReeReservatorioEquivalenteEnergia,
  NewOnsEarDiarioReeReservatorioEquivalenteEnergia,
} from '../ons-ear-diario-ree-reservatorio-equivalente-energia.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsEarDiarioReeReservatorioEquivalenteEnergia for edit and NewOnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroupInput for create.
 */
type OnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroupInput =
  | IOnsEarDiarioReeReservatorioEquivalenteEnergia
  | PartialWithRequiredKeyOf<NewOnsEarDiarioReeReservatorioEquivalenteEnergia>;

type OnsEarDiarioReeReservatorioEquivalenteEnergiaFormDefaults = Pick<NewOnsEarDiarioReeReservatorioEquivalenteEnergia, 'id'>;

type OnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroupContent = {
  id: FormControl<IOnsEarDiarioReeReservatorioEquivalenteEnergia['id'] | NewOnsEarDiarioReeReservatorioEquivalenteEnergia['id']>;
  nomRee: FormControl<IOnsEarDiarioReeReservatorioEquivalenteEnergia['nomRee']>;
  earData: FormControl<IOnsEarDiarioReeReservatorioEquivalenteEnergia['earData']>;
  earMaxRee: FormControl<IOnsEarDiarioReeReservatorioEquivalenteEnergia['earMaxRee']>;
  earVerifReeMwmes: FormControl<IOnsEarDiarioReeReservatorioEquivalenteEnergia['earVerifReeMwmes']>;
  earVerifReePercentual: FormControl<IOnsEarDiarioReeReservatorioEquivalenteEnergia['earVerifReePercentual']>;
};

export type OnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroup =
  FormGroup<OnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsEarDiarioReeReservatorioEquivalenteEnergiaFormService {
  createOnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroup(
    onsEarDiarioReeReservatorioEquivalenteEnergia: OnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroupInput = { id: null },
  ): OnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroup {
    const onsEarDiarioReeReservatorioEquivalenteEnergiaRawValue = {
      ...this.getFormDefaults(),
      ...onsEarDiarioReeReservatorioEquivalenteEnergia,
    };
    return new FormGroup<OnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroupContent>({
      id: new FormControl(
        { value: onsEarDiarioReeReservatorioEquivalenteEnergiaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      nomRee: new FormControl(onsEarDiarioReeReservatorioEquivalenteEnergiaRawValue.nomRee),
      earData: new FormControl(onsEarDiarioReeReservatorioEquivalenteEnergiaRawValue.earData),
      earMaxRee: new FormControl(onsEarDiarioReeReservatorioEquivalenteEnergiaRawValue.earMaxRee),
      earVerifReeMwmes: new FormControl(onsEarDiarioReeReservatorioEquivalenteEnergiaRawValue.earVerifReeMwmes),
      earVerifReePercentual: new FormControl(onsEarDiarioReeReservatorioEquivalenteEnergiaRawValue.earVerifReePercentual),
    });
  }

  getOnsEarDiarioReeReservatorioEquivalenteEnergia(
    form: OnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroup,
  ): IOnsEarDiarioReeReservatorioEquivalenteEnergia | NewOnsEarDiarioReeReservatorioEquivalenteEnergia {
    return form.getRawValue() as IOnsEarDiarioReeReservatorioEquivalenteEnergia | NewOnsEarDiarioReeReservatorioEquivalenteEnergia;
  }

  resetForm(
    form: OnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroup,
    onsEarDiarioReeReservatorioEquivalenteEnergia: OnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroupInput,
  ): void {
    const onsEarDiarioReeReservatorioEquivalenteEnergiaRawValue = {
      ...this.getFormDefaults(),
      ...onsEarDiarioReeReservatorioEquivalenteEnergia,
    };
    form.reset(
      {
        ...onsEarDiarioReeReservatorioEquivalenteEnergiaRawValue,
        id: { value: onsEarDiarioReeReservatorioEquivalenteEnergiaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsEarDiarioReeReservatorioEquivalenteEnergiaFormDefaults {
    return {
      id: null,
    };
  }
}
