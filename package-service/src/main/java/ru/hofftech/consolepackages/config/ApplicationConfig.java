package ru.hofftech.consolepackages.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.mapper.packagetype.PackageTypeMapper;
import ru.hofftech.consolepackages.service.packagetype.PackageTypeService;
import ru.hofftech.consolepackages.service.packagetype.PackageTypeServiceImpl;

import java.time.Clock;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final PackageTypeRepository packageTypeRepository;
    private final PackageTypeMapper packageTypeMapper;


    @Bean
    public PackageTypeService packageTypeService() {
        return new PackageTypeServiceImpl(packageTypeRepository, packageTypeMapper);
    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}
