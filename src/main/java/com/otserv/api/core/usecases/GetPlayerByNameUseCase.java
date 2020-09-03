package com.otserv.api.core.usecases;

import com.otserv.api.core.UseCase;
import com.otserv.api.core.domain.Player;
import com.otserv.api.core.exceptions.NotFoundException;
import com.otserv.api.core.repositories.PlayerRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

@Service
public class GetPlayerByNameUseCase implements
        UseCase<GetPlayerByNameUseCase.InputValues, GetPlayerByNameUseCase.OutputValues> {
    private PlayerRepository playerRepository;

    public GetPlayerByNameUseCase(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(
                this.playerRepository
                        .getByName(input.getName())
                        .orElseThrow(() -> new NotFoundException("Player with this name not found"))
        );
    }

    @Value
    public static class InputValues {
        private String name;
    }

    @Value
    public static class OutputValues {
        private Player player;
    }
}
