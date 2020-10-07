package com.pik.sender;

import com.pik.advertisement.command.AddAdvertisementPhotosCommand;
import com.pik.advertisement.command.ChangeAdvertisementCommand;
import com.pik.advertisement.command.CreateAdvertisementCommand;
import com.pik.advertisement.command.DeleteAdvertisementPhotoCommand;
import com.pik.dto.AdvertisementDto;
import com.pik.dto.AdvertisementPhotosDto;
import com.pik.dto.ChangeAdvertisementDto;
import com.pik.dto.DeleteAdvertisementPhotosDto;
import com.pik.mapper.AdvertisementCommandBuilder;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class AdvertisementCommandSender {
    private final CommandGateway commandGateway;
    private final AdvertisementCommandBuilder advertisementCommandBuilder;
    private final Logger logger = LoggerFactory.getLogger(AdvertisementCommandSender.class);

    public CompletableFuture<Object> sendCreateAdvertisementCommand(AdvertisementDto adDto) {
        CreateAdvertisementCommand command = this.advertisementCommandBuilder.buildCreateAdvertisementCommand(adDto);
        this.logger.info("Sent create advertisement command");
        return this.commandGateway.send(command);
    }

    public void sendChangeAdvertisementCommand(ChangeAdvertisementDto changeAdvertisementDto) {
        ChangeAdvertisementCommand command = advertisementCommandBuilder.buildChangeAdvertisementCommand(changeAdvertisementDto);
        this.commandGateway.send(command);
    }


    public void sendAddPhotoCommand(AdvertisementPhotosDto advertisementPhotosDto) {
        AddAdvertisementPhotosCommand command = this.advertisementCommandBuilder.buildAddAdvertisementPhotosCommand(advertisementPhotosDto);
        this.commandGateway.send(command);
    }

    public void sendDeletePhotoCommand(DeleteAdvertisementPhotosDto advertisementPhotosDto) {
        DeleteAdvertisementPhotoCommand command = this.advertisementCommandBuilder.buildDeleteAdvertisementPhotoCommand(advertisementPhotosDto);
        this.commandGateway.send(command);
    }
}
