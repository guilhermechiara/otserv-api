package com.otserv.api.core.usecases.players;

import com.otserv.api.core.UseCase;
import com.otserv.api.core.domain.Player;
import com.otserv.api.core.exceptions.NotFoundException;
import com.otserv.api.core.repositories.PlayerRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

@Service
public class GetPlayerByIdUseCase implements
        UseCase<GetPlayerByIdUseCase.InputValues, GetPlayerByIdUseCase.OutputValues> {
    private PlayerRepository playerRepository;

    public GetPlayerByIdUseCase(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(
                this.playerRepository
                        .getById(input.getId())
                        .orElseThrow(() -> new NotFoundException("Player with this name not found"))
        );
    }

    @Value
    public static class InputValues {
        private Long id;
    }

    @Value
    public static class OutputValues {
        private Player player;
    }
}
