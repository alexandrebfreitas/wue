import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsEnaDiarioSubsistemaDetailComponent } from './ons-ena-diario-subsistema-detail.component';

describe('OnsEnaDiarioSubsistema Management Detail Component', () => {
  let comp: OnsEnaDiarioSubsistemaDetailComponent;
  let fixture: ComponentFixture<OnsEnaDiarioSubsistemaDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsEnaDiarioSubsistemaDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-ena-diario-subsistema-detail.component').then(m => m.OnsEnaDiarioSubsistemaDetailComponent),
              resolve: { onsEnaDiarioSubsistema: () => of({ id: 7404 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsEnaDiarioSubsistemaDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsEnaDiarioSubsistemaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsEnaDiarioSubsistema on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsEnaDiarioSubsistemaDetailComponent);

      // THEN
      expect(instance.onsEnaDiarioSubsistema()).toEqual(expect.objectContaining({ id: 7404 }));
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
