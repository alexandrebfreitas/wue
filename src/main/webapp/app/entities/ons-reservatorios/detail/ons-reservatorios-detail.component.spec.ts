import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsReservatoriosDetailComponent } from './ons-reservatorios-detail.component';

describe('OnsReservatorios Management Detail Component', () => {
  let comp: OnsReservatoriosDetailComponent;
  let fixture: ComponentFixture<OnsReservatoriosDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsReservatoriosDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./ons-reservatorios-detail.component').then(m => m.OnsReservatoriosDetailComponent),
              resolve: { onsReservatorios: () => of({ id: 25623 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsReservatoriosDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsReservatoriosDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsReservatorios on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsReservatoriosDetailComponent);

      // THEN
      expect(instance.onsReservatorios()).toEqual(expect.objectContaining({ id: 25623 }));
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
