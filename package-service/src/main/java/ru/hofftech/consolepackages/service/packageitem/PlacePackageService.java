package ru.hofftech.consolepackages.service.packageitem;

import org.springframework.web.multipart.MultipartFile;
import ru.hofftech.consolepackages.model.dto.placepackage.PlacePackagesFromFileDto;
import ru.hofftech.consolepackages.model.dto.placepackage.PlacePackagesFromTextDto;
import ru.hofftech.consolepackages.model.dto.placepackage.PlacePackagesResponse;

public interface PlacePackageService {
    PlacePackagesResponse placePackagesFromString(PlacePackagesFromTextDto loadPackagesFromTextDto);

    PlacePackagesResponse placePackagesFromFile(PlacePackagesFromFileDto loadPackagesFromFileDto, MultipartFile file);
}
