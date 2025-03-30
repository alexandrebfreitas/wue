jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService } from '../service/ons-dados-valores-previsao-versus-programado-eolicas-e-solares.service';

import { OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresDeleteDialogComponent } from './ons-dados-valores-previsao-versus-programado-eolicas-e-solares-delete-dialog.component';

describe('OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares Management Delete Component', () => {
  let comp: OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresDeleteDialogComponent;
  let fixture: ComponentFixture<OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresDeleteDialogComponent>;
  let service: OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete(123);
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith(123);
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      }),
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
