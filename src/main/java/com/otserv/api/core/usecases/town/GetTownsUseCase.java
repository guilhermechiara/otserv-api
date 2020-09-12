package com.otserv.api.core.usecases.town;

import com.otserv.api.core.UseCase;
import com.otserv.api.core.repositories.TownRepository;
import com.otserv.api.core.domain.Town;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTownsUseCase implements
        UseCase<GetTownsUseCase.InputValues, GetTownsUseCase.OutputValues> {
    private TownRepository townRepository;

    public GetTownsUseCase(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(
                this.townRepository.findAll()
        );
    }

    @Value
    public static class InputValues { }

    @Value
    public static class OutputValues {
        private List<Town> towns;
    }
}
