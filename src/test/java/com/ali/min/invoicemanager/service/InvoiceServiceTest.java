package com.ali.min.invoicemanager.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ali.min.invoicemanager.dto.InvoiceDTO;
import com.ali.min.invoicemanager.entity.InvoiceEntity;
import com.ali.min.invoicemanager.mapper.InvoiceMapper;
import com.ali.min.invoicemanager.repository.InvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private InvoiceMapper invoiceMapper;

    @InjectMocks
    private InvoiceService invoiceService;

    private InvoiceDTO invoiceDTO;
    private InvoiceEntity invoiceEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        invoiceDTO = new InvoiceDTO();
        invoiceDTO.setAmount(new BigDecimal("150"));
        invoiceDTO.setEmail("test@example.com");

        invoiceEntity = new InvoiceEntity();
        invoiceEntity.setAmount(new BigDecimal("150"));
        invoiceEntity.setEmail("test@example.com");
    }

    @Test
    void saveInvoice_savesInvoiceSuccessfully() {
        when(invoiceRepository.isWithinThreshold(any(BigDecimal.class), any(), anyString())).thenReturn(true);
        when(invoiceMapper.toEntity(any(InvoiceDTO.class))).thenReturn(invoiceEntity);
        when(invoiceRepository.save(any(InvoiceEntity.class))).thenReturn(invoiceEntity);
        when(invoiceMapper.toDTO(any(InvoiceEntity.class))).thenReturn(invoiceDTO);

        InvoiceDTO result = invoiceService.saveInvoice(invoiceDTO);

        assertNotNull(result);
        assertEquals(invoiceDTO.getAmount(), result.getAmount());
        verify(invoiceRepository).save(invoiceEntity);
    }

    @Test
    void saveInvoice_throwsExceptionWhenAmountExceedsLimit() {
        when(invoiceRepository.isWithinThreshold(any(BigDecimal.class), any(BigDecimal.class), anyString())).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> invoiceService.saveInvoice(invoiceDTO));
    }

    @Test
    void getInvoice_returnsInvoiceSuccessfully() {
        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoiceEntity));
        when(invoiceMapper.toDTO(any(InvoiceEntity.class))).thenReturn(invoiceDTO);

        InvoiceDTO result = invoiceService.getInvoice(1L);

        assertNotNull(result);
        assertEquals(invoiceDTO.getAmount(), result.getAmount());
        verify(invoiceRepository).findById(1L);
    }

    @Test
    void getInvoice_throwsExceptionWhenInvoiceNotFound() {
        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> invoiceService.getInvoice(1L));
    }
}