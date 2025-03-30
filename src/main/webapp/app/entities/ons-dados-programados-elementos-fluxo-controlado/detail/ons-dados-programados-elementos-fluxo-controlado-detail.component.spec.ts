import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsDadosProgramadosElementosFluxoControladoDetailComponent } from './ons-dados-programados-elementos-fluxo-controlado-detail.component';

describe('OnsDadosProgramadosElementosFluxoControlado Management Detail Component', () => {
  let comp: OnsDadosProgramadosElementosFluxoControladoDetailComponent;
  let fixture: ComponentFixture<OnsDadosProgramadosElementosFluxoControladoDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsDadosProgramadosElementosFluxoControladoDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-dados-programados-elementos-fluxo-controlado-detail.component').then(
                  m => m.OnsDadosProgramadosElementosFluxoControladoDetailComponent,
                ),
              resolve: { onsDadosProgramadosElementosFluxoControlado: () => of({ id: 17757 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsDadosProgramadosElementosFluxoControladoDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsDadosProgramadosElementosFluxoControladoDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsDadosProgramadosElementosFluxoControlado on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsDadosProgramadosElementosFluxoControladoDetailComponent);

      // THEN
      expect(instance.onsDadosProgramadosElementosFluxoControlado()).toEqual(expect.objectContaining({ id: 17757 }));
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
