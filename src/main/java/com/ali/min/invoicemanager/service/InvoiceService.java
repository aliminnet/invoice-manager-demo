package com.ali.min.invoicemanager.service;

import com.ali.min.invoicemanager.dto.InvoiceDTO;
import com.ali.min.invoicemanager.entity.InvoiceEntity;
import com.ali.min.invoicemanager.mapper.InvoiceMapper;
import com.ali.min.invoicemanager.repository.InvoiceRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Service class for Invoice operations
 *
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class InvoiceService {
    @Value("${invoice.limit:200}")
    @Getter
    @Setter
    private BigDecimal limit;

    private final InvoiceRepository invoiceRepository;

    private final InvoiceMapper invoiceMapper;

    /**
     * Save invoice
     *
     * @param invoiceDTO invoice to be saved
     * @return saved invoice
     */
    public InvoiceDTO saveInvoice(InvoiceDTO invoiceDTO) {
        if (!invoiceRepository.isWithinThreshold(invoiceDTO.getAmount(), limit, invoiceDTO.getEmail())){
            throw new IllegalArgumentException("Amount exceeds the limit");
        }
        InvoiceEntity invoiceEntity  = invoiceMapper.toEntity(invoiceDTO);
        return invoiceMapper.toDTO(invoiceRepository.save(invoiceEntity));
    }

    /**
     * Get invoice by id
     *
     * @param id invoice id
     * @return invoice
     */
    public InvoiceDTO getInvoice(Long id) {
        InvoiceEntity invoiceEntity = invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invoice not found"));
        return invoiceMapper.toDTO(invoiceEntity);
    }
}
