import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsEarDiarioSubsistemaDetailComponent } from './ons-ear-diario-subsistema-detail.component';

describe('OnsEarDiarioSubsistema Management Detail Component', () => {
  let comp: OnsEarDiarioSubsistemaDetailComponent;
  let fixture: ComponentFixture<OnsEarDiarioSubsistemaDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsEarDiarioSubsistemaDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-ear-diario-subsistema-detail.component').then(m => m.OnsEarDiarioSubsistemaDetailComponent),
              resolve: { onsEarDiarioSubsistema: () => of({ id: 31637 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsEarDiarioSubsistemaDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsEarDiarioSubsistemaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsEarDiarioSubsistema on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsEarDiarioSubsistemaDetailComponent);

      // THEN
      expect(instance.onsEarDiarioSubsistema()).toEqual(expect.objectContaining({ id: 31637 }));
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
