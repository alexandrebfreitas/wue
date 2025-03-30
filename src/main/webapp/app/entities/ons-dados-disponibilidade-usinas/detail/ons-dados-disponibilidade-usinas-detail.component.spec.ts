import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsDadosDisponibilidadeUsinasDetailComponent } from './ons-dados-disponibilidade-usinas-detail.component';

describe('OnsDadosDisponibilidadeUsinas Management Detail Component', () => {
  let comp: OnsDadosDisponibilidadeUsinasDetailComponent;
  let fixture: ComponentFixture<OnsDadosDisponibilidadeUsinasDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsDadosDisponibilidadeUsinasDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-dados-disponibilidade-usinas-detail.component').then(m => m.OnsDadosDisponibilidadeUsinasDetailComponent),
              resolve: { onsDadosDisponibilidadeUsinas: () => of({ id: 14685 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsDadosDisponibilidadeUsinasDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsDadosDisponibilidadeUsinasDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsDadosDisponibilidadeUsinas on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsDadosDisponibilidadeUsinasDetailComponent);

      // THEN
      expect(instance.onsDadosDisponibilidadeUsinas()).toEqual(expect.objectContaining({ id: 14685 }));
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
