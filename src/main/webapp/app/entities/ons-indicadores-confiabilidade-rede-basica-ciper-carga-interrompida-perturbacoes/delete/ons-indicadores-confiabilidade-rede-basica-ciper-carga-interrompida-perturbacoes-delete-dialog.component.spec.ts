jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService } from '../service/ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.service';

import { OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesDeleteDialogComponent } from './ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes-delete-dialog.component';

describe('OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes Management Delete Component', () => {
  let comp: OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesDeleteDialogComponent;
  let fixture: ComponentFixture<OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesDeleteDialogComponent>;
  let service: OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService);
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
