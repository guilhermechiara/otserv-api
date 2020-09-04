package com.otserv.api.core.usecases.vocation;

import com.otserv.api.core.UseCase;
import com.otserv.api.core.domain.Vocation;
import com.otserv.api.core.exceptions.NotFoundException;
import com.otserv.api.core.repositories.VocationRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

@Service
public class GetVocationByIdUseCase implements
        UseCase<GetVocationByIdUseCase.InputValues, GetVocationByIdUseCase.OutputValues> {
    private final VocationRepository vocationRepository;

    public GetVocationByIdUseCase(VocationRepository vocationRepository) {
        this.vocationRepository = vocationRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(
                this.vocationRepository
                        .findById(input.getId())
                        .orElseThrow(() -> new NotFoundException("Vocation with id not found"))
        );
    }

    @Value
    public static class InputValues {
        private Integer id;
    }

    @Value
    public static class OutputValues {
        private Vocation vocation;
    }

}
