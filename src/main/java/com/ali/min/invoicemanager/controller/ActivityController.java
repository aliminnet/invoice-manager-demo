package com.ali.min.invoicemanager.controller;

import com.ali.min.invoicemanager.dto.ResultDTO;
import com.ali.min.invoicemanager.enums.Status;
import com.ali.min.invoicemanager.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/activity")
@RequiredArgsConstructor
@SecurityRequirement(name = "inv-man-auth")
public class ActivityController {

    private final ActivityService activityService;

    /**
     * Get all activities
     *
     * @return List of activities
     */
    @GetMapping
    @Operation(summary = "Get all activities")
    public ResponseEntity<ResultDTO> getAll() {
        return ResponseEntity.ok(
                ResultDTO.builder()
                        .data(activityService.getActivities())
                        .status(Status.SUCCESS)
                        .message("Activities retrieved successfully").build());
    }

    /**
     * Get activity by id
     *
     * @param id activity id
     * @return activity
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get activity by id")
    public ResponseEntity<ResultDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ResultDTO.builder()
                        .data(activityService.getActivityById(id))
                        .status(Status.SUCCESS)
                        .message("Activity retrieved successfully").build());
    }

    /**
     * Get all activities by status
     *
     * @param status activity status
     * @return List of activities
     */
    @GetMapping("/status/{status}")
    @Operation(summary = "Get all activities by status")
    public ResponseEntity<ResultDTO> getByStatus(@PathVariable Status status) {
        return ResponseEntity.ok(
                ResultDTO.builder()
                        .data(activityService.getActivitiesByStatus(status))
                        .status(Status.SUCCESS)
                        .message("Activities retrieved successfully").build());
    }
}
