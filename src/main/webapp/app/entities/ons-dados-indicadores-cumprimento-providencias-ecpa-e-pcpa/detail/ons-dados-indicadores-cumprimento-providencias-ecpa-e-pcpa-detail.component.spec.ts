import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaDetailComponent } from './ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa-detail.component';

describe('OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa Management Detail Component', () => {
  let comp: OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaDetailComponent;
  let fixture: ComponentFixture<OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa-detail.component').then(
                  m => m.OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaDetailComponent,
                ),
              resolve: { onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: () => of({ id: 30063 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaDetailComponent);

      // THEN
      expect(instance.onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa()).toEqual(expect.objectContaining({ id: 30063 }));
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
