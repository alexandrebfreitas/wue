import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresDetailComponent } from './ons-dados-valores-previsao-versus-programado-eolicas-e-solares-detail.component';

describe('OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares Management Detail Component', () => {
  let comp: OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresDetailComponent;
  let fixture: ComponentFixture<OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-dados-valores-previsao-versus-programado-eolicas-e-solares-detail.component').then(
                  m => m.OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresDetailComponent,
                ),
              resolve: { onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: () => of({ id: 3628 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsDadosValoresPrevisaoVersusProgramadoEolicasESolares on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresDetailComponent);

      // THEN
      expect(instance.onsDadosValoresPrevisaoVersusProgramadoEolicasESolares()).toEqual(expect.objectContaining({ id: 3628 }));
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
