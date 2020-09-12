package com.otserv.api.core.usecases.town;

import com.otserv.api.core.UseCase;
import com.otserv.api.core.exceptions.NotFoundException;
import com.otserv.api.core.domain.Town;
import com.otserv.api.core.repositories.TownRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

@Service
public class GetTownByIdUseCase implements
        UseCase<GetTownByIdUseCase.InputValues, GetTownByIdUseCase.OutputValues> {
    private TownRepository townRepository;

    public GetTownByIdUseCase(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(
                this.townRepository
                        .findById(input.getId())
                        .orElseThrow(() -> new NotFoundException("Town with id not found"))
        );
    }

    @Value
    public static class InputValues {
        private Integer id;
    }

    @Value
    public static class OutputValues {
        private Town town;
    }
}
