package pja.edu.pl.s26772.restaurantmanagement.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.Customer;
import pja.edu.pl.s26772.restaurantmanagement.models.Tables;
import pja.edu.pl.s26772.restaurantmanagement.models.Visit;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.VisitRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.MenuItemResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.VisitResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.repositories.VisitRepository;
import pja.edu.pl.s26772.restaurantmanagement.services.mappers.VisitMapper;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VisitService {
    private final VisitRepository visitRepository;
    private final CustomerService customerService;
    private TableService tableService;
    private final VisitMapper visitMapper;

    public VisitService(TableService tableService, CustomerService customerService,VisitRepository visitRepository, VisitMapper visitMapper) {
        this.visitRepository = visitRepository;
        this.visitMapper = visitMapper;
        this.customerService = customerService;
        this.tableService = tableService;
    }

    public List<VisitResponseDto> getVists() {
        List<Visit> visits = visitRepository.getAllVisits();
        List<VisitResponseDto> visitDtos = visits.stream().map(visitMapper::visitToResponseDto).toList();
        return visitDtos;
    }

    public Optional<Visit> getVisitById(Long visitId){
        return visitRepository.findById(visitId);
    }

    public VisitResponseDto getResponseDto(Visit visit){
        return visitMapper.visitToResponseDto(visit);
    }

    public List<VisitResponseDto> getVisitsByCustomerId(Long customerId) throws NoSuchElementException{
        Customer customer = customerService.getCustomer(customerId).orElseThrow();
        List<Visit> visits = customer.getVisits();
        return visits.stream().map(visitMapper::visitToResponseDto).toList();
    }
    @Transactional
    public VisitResponseDto addVisit(VisitRequestDto requestDto) {
        return visitMapper.visitToResponseDto(visitRepository.save(visitMapper.requestDtoToVisit(requestDto)));
    }

    @Transactional
    public void deleteVisit(Visit visit) {
        visitRepository.delete(visit);
    }

    @Transactional
    public void updateVisit(Visit visit, VisitRequestDto patchedDto) {
        Tables table = tableService.getTable(patchedDto.getTableId()).get();
        Customer customer = customerService.getCustomer(patchedDto.getCustomerId()).get();
        visit.setTables(table);
        visit.setDate(patchedDto.getDate());
        visit.setStartTime(patchedDto.getStartAt());
        visit.setEndTime(patchedDto.getFinishAt());
        visit.setCustomer(customer);
        visitRepository.save(visit);
    }

    public List<VisitResponseDto> getCurrentVists() {
        return visitRepository.findOngoingVisits().stream()
                .map(visitMapper::visitToResponseDto).toList();
    }

    public VisitRequestDto getRequestDto(Visit visit){
        return visitMapper.visitToRequestDto(visit);
    }

    public VisitResponseDto generateBill(Long visitId) throws NoSuchElementException{
        Visit visit = getVisitById(visitId).orElseThrow();
        visit.setEndTime(LocalTime.now());
        VisitResponseDto responseDto = visitMapper.visitToResponseDto(visit);
        return responseDto;
    }
}
