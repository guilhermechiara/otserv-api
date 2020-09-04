package com.otserv.api.core.usecases.vocation;

import com.otserv.api.core.UseCase;
import com.otserv.api.core.domain.Vocation;
import com.otserv.api.core.repositories.VocationRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetVocationsUseCase implements
        UseCase<GetVocationsUseCase.InputValues, GetVocationsUseCase.OutputValues> {
    private VocationRepository vocationRepository;

    public GetVocationsUseCase(VocationRepository vocationRepository) {
        this.vocationRepository = vocationRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(
                this.vocationRepository.getAll()
        );
    }

    @Value
    public static class InputValues { }

    @Value
    public static class OutputValues {
        private List<Vocation> vocations;
    }
}
