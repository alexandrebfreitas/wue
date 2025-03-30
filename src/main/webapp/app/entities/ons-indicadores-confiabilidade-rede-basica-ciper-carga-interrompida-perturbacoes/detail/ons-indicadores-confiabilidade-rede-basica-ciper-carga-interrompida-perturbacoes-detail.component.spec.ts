import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesDetailComponent } from './ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes-detail.component';

describe('OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes Management Detail Component', () => {
  let comp: OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesDetailComponent;
  let fixture: ComponentFixture<OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes-detail.component').then(
                  m => m.OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesDetailComponent,
                ),
              resolve: { onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: () => of({ id: 511 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl(
        '/',
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesDetailComponent,
      );

      // THEN
      expect(instance.onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes()).toEqual(
        expect.objectContaining({ id: 511 }),
      );
    });
  });

  describe('PreviousState', () => {
    it('Should navigate to previous state', () => {
      jest.spyOn(window.history, 'back');
      comp.previousState();
      expect(window.history.back).toHaveBeenCalled();
    });
  });
});
