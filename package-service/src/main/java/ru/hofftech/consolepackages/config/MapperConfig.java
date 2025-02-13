package ru.hofftech.consolepackages.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hofftech.consolepackages.mapper.billing.BillingMapper;
import ru.hofftech.consolepackages.mapper.loadpackage.PackageMapper;
import ru.hofftech.consolepackages.mapper.loadpackage.TruckMapper;
import ru.hofftech.consolepackages.mapper.packagetype.PackageTypeMapper;

@Configuration
@RequiredArgsConstructor
public class MapperConfig {

    @Bean
    public TruckMapper truckMapper() {
        return org.mapstruct.factory.Mappers.getMapper(TruckMapper.class);
    }

    @Bean
    public BillingMapper billingMapper() {
        return org.mapstruct.factory.Mappers.getMapper(BillingMapper.class);
    }

    @Bean
    public PackageMapper packageMapper() {
        return org.mapstruct.factory.Mappers.getMapper(PackageMapper.class);
    }

    @Bean
    public PackageTypeMapper packageTypeMapper() {
        return org.mapstruct.factory.Mappers.getMapper(PackageTypeMapper.class);
    }
}
