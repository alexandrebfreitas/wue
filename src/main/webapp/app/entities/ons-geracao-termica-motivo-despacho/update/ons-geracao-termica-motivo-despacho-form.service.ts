import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsGeracaoTermicaMotivoDespacho, NewOnsGeracaoTermicaMotivoDespacho } from '../ons-geracao-termica-motivo-despacho.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsGeracaoTermicaMotivoDespacho for edit and NewOnsGeracaoTermicaMotivoDespachoFormGroupInput for create.
 */
type OnsGeracaoTermicaMotivoDespachoFormGroupInput =
  | IOnsGeracaoTermicaMotivoDespacho
  | PartialWithRequiredKeyOf<NewOnsGeracaoTermicaMotivoDespacho>;

type OnsGeracaoTermicaMotivoDespachoFormDefaults = Pick<NewOnsGeracaoTermicaMotivoDespacho, 'id'>;

type OnsGeracaoTermicaMotivoDespachoFormGroupContent = {
  id: FormControl<IOnsGeracaoTermicaMotivoDespacho['id'] | NewOnsGeracaoTermicaMotivoDespacho['id']>;
  dinInstante: FormControl<IOnsGeracaoTermicaMotivoDespacho['dinInstante']>;
  nomTipopatamar: FormControl<IOnsGeracaoTermicaMotivoDespacho['nomTipopatamar']>;
  idSubsistema: FormControl<IOnsGeracaoTermicaMotivoDespacho['idSubsistema']>;
  nomSubsistema: FormControl<IOnsGeracaoTermicaMotivoDespacho['nomSubsistema']>;
  nomUsina: FormControl<IOnsGeracaoTermicaMotivoDespacho['nomUsina']>;
  codUsinaplanejamento: FormControl<IOnsGeracaoTermicaMotivoDespacho['codUsinaplanejamento']>;
  ceg: FormControl<IOnsGeracaoTermicaMotivoDespacho['ceg']>;
  valProggeracao: FormControl<IOnsGeracaoTermicaMotivoDespacho['valProggeracao']>;
  valProgordemmerito: FormControl<IOnsGeracaoTermicaMotivoDespacho['valProgordemmerito']>;
  valProgordemdemeritoref: FormControl<IOnsGeracaoTermicaMotivoDespacho['valProgordemdemeritoref']>;
  valProgordemdemeritoacimadainflex: FormControl<IOnsGeracaoTermicaMotivoDespacho['valProgordemdemeritoacimadainflex']>;
  valProginflexibilidade: FormControl<IOnsGeracaoTermicaMotivoDespacho['valProginflexibilidade']>;
  valProginflexembutmerito: FormControl<IOnsGeracaoTermicaMotivoDespacho['valProginflexembutmerito']>;
  valProginflexpura: FormControl<IOnsGeracaoTermicaMotivoDespacho['valProginflexpura']>;
  valPrograzaoeletrica: FormControl<IOnsGeracaoTermicaMotivoDespacho['valPrograzaoeletrica']>;
  valProggarantiaenergetica: FormControl<IOnsGeracaoTermicaMotivoDespacho['valProggarantiaenergetica']>;
  valProggfom: FormControl<IOnsGeracaoTermicaMotivoDespacho['valProggfom']>;
  valProgreposicaoperdas: FormControl<IOnsGeracaoTermicaMotivoDespacho['valProgreposicaoperdas']>;
  valProgexportacao: FormControl<IOnsGeracaoTermicaMotivoDespacho['valProgexportacao']>;
  valProgreservapotencia: FormControl<IOnsGeracaoTermicaMotivoDespacho['valProgreservapotencia']>;
  valProggsub: FormControl<IOnsGeracaoTermicaMotivoDespacho['valProggsub']>;
  valProgunitcommitment: FormControl<IOnsGeracaoTermicaMotivoDespacho['valProgunitcommitment']>;
  valProgconstrainedoff: FormControl<IOnsGeracaoTermicaMotivoDespacho['valProgconstrainedoff']>;
  valProginflexibilidadedessem: FormControl<IOnsGeracaoTermicaMotivoDespacho['valProginflexibilidadedessem']>;
  valVerifgeracao: FormControl<IOnsGeracaoTermicaMotivoDespacho['valVerifgeracao']>;
  valVerifordemmerito: FormControl<IOnsGeracaoTermicaMotivoDespacho['valVerifordemmerito']>;
  valVerifordemdemeritoacimadainflex: FormControl<IOnsGeracaoTermicaMotivoDespacho['valVerifordemdemeritoacimadainflex']>;
  valVerifinflexibilidade: FormControl<IOnsGeracaoTermicaMotivoDespacho['valVerifinflexibilidade']>;
  valVerifinflexembutmerito: FormControl<IOnsGeracaoTermicaMotivoDespacho['valVerifinflexembutmerito']>;
  valVerifinflexpura: FormControl<IOnsGeracaoTermicaMotivoDespacho['valVerifinflexpura']>;
  valVerifrazaoeletrica: FormControl<IOnsGeracaoTermicaMotivoDespacho['valVerifrazaoeletrica']>;
  valVerifgarantiaenergetica: FormControl<IOnsGeracaoTermicaMotivoDespacho['valVerifgarantiaenergetica']>;
  valVerifgfom: FormControl<IOnsGeracaoTermicaMotivoDespacho['valVerifgfom']>;
  valVerifreposicaoperdas: FormControl<IOnsGeracaoTermicaMotivoDespacho['valVerifreposicaoperdas']>;
  valVerifexportacao: FormControl<IOnsGeracaoTermicaMotivoDespacho['valVerifexportacao']>;
  valFdexp: FormControl<IOnsGeracaoTermicaMotivoDespacho['valFdexp']>;
  valVerifreservapotencia: FormControl<IOnsGeracaoTermicaMotivoDespacho['valVerifreservapotencia']>;
  valAtendsatisfatoriorpo: FormControl<IOnsGeracaoTermicaMotivoDespacho['valAtendsatisfatoriorpo']>;
  valVerifgsub: FormControl<IOnsGeracaoTermicaMotivoDespacho['valVerifgsub']>;
  valVerifunitcommitment: FormControl<IOnsGeracaoTermicaMotivoDespacho['valVerifunitcommitment']>;
  valVerifconstrainedoff: FormControl<IOnsGeracaoTermicaMotivoDespacho['valVerifconstrainedoff']>;
  tipRestricaoeletrica: FormControl<IOnsGeracaoTermicaMotivoDespacho['tipRestricaoeletrica']>;
};

export type OnsGeracaoTermicaMotivoDespachoFormGroup = FormGroup<OnsGeracaoTermicaMotivoDespachoFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsGeracaoTermicaMotivoDespachoFormService {
  createOnsGeracaoTermicaMotivoDespachoFormGroup(
    onsGeracaoTermicaMotivoDespacho: OnsGeracaoTermicaMotivoDespachoFormGroupInput = { id: null },
  ): OnsGeracaoTermicaMotivoDespachoFormGroup {
    const onsGeracaoTermicaMotivoDespachoRawValue = {
      ...this.getFormDefaults(),
      ...onsGeracaoTermicaMotivoDespacho,
    };
    return new FormGroup<OnsGeracaoTermicaMotivoDespachoFormGroupContent>({
      id: new FormControl(
        { value: onsGeracaoTermicaMotivoDespachoRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dinInstante: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.dinInstante),
      nomTipopatamar: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.nomTipopatamar),
      idSubsistema: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.nomSubsistema),
      nomUsina: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.nomUsina),
      codUsinaplanejamento: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.codUsinaplanejamento),
      ceg: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.ceg),
      valProggeracao: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valProggeracao),
      valProgordemmerito: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valProgordemmerito),
      valProgordemdemeritoref: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valProgordemdemeritoref),
      valProgordemdemeritoacimadainflex: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valProgordemdemeritoacimadainflex),
      valProginflexibilidade: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valProginflexibilidade),
      valProginflexembutmerito: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valProginflexembutmerito),
      valProginflexpura: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valProginflexpura),
      valPrograzaoeletrica: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valPrograzaoeletrica),
      valProggarantiaenergetica: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valProggarantiaenergetica),
      valProggfom: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valProggfom),
      valProgreposicaoperdas: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valProgreposicaoperdas),
      valProgexportacao: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valProgexportacao),
      valProgreservapotencia: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valProgreservapotencia),
      valProggsub: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valProggsub),
      valProgunitcommitment: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valProgunitcommitment),
      valProgconstrainedoff: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valProgconstrainedoff),
      valProginflexibilidadedessem: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valProginflexibilidadedessem),
      valVerifgeracao: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valVerifgeracao),
      valVerifordemmerito: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valVerifordemmerito),
      valVerifordemdemeritoacimadainflex: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valVerifordemdemeritoacimadainflex),
      valVerifinflexibilidade: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valVerifinflexibilidade),
      valVerifinflexembutmerito: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valVerifinflexembutmerito),
      valVerifinflexpura: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valVerifinflexpura),
      valVerifrazaoeletrica: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valVerifrazaoeletrica),
      valVerifgarantiaenergetica: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valVerifgarantiaenergetica),
      valVerifgfom: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valVerifgfom),
      valVerifreposicaoperdas: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valVerifreposicaoperdas),
      valVerifexportacao: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valVerifexportacao),
      valFdexp: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valFdexp),
      valVerifreservapotencia: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valVerifreservapotencia),
      valAtendsatisfatoriorpo: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valAtendsatisfatoriorpo),
      valVerifgsub: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valVerifgsub),
      valVerifunitcommitment: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valVerifunitcommitment),
      valVerifconstrainedoff: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.valVerifconstrainedoff),
      tipRestricaoeletrica: new FormControl(onsGeracaoTermicaMotivoDespachoRawValue.tipRestricaoeletrica),
    });
  }

  getOnsGeracaoTermicaMotivoDespacho(
    form: OnsGeracaoTermicaMotivoDespachoFormGroup,
  ): IOnsGeracaoTermicaMotivoDespacho | NewOnsGeracaoTermicaMotivoDespacho {
    return form.getRawValue() as IOnsGeracaoTermicaMotivoDespacho | NewOnsGeracaoTermicaMotivoDespacho;
  }

  resetForm(
    form: OnsGeracaoTermicaMotivoDespachoFormGroup,
    onsGeracaoTermicaMotivoDespacho: OnsGeracaoTermicaMotivoDespachoFormGroupInput,
  ): void {
    const onsGeracaoTermicaMotivoDespachoRawValue = { ...this.getFormDefaults(), ...onsGeracaoTermicaMotivoDespacho };
    form.reset(
      {
        ...onsGeracaoTermicaMotivoDespachoRawValue,
        id: { value: onsGeracaoTermicaMotivoDespachoRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsGeracaoTermicaMotivoDespachoFormDefaults {
    return {
      id: null,
    };
  }
}
