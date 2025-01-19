package ru.hofftech.consolepackages.service.command.impl.createpackagetype;

import ch.qos.logback.core.util.StringUtil;
import ru.hofftech.consolepackages.service.command.CommandContext;

public record CreatePackageTypeContext(
        String name,
        String description,
        String form) implements CommandContext {

    public static class Builder {
        private String name;
        private String description;
        private String form;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder form(String form) {
            this.form = form;
            return this;
        }

        public CreatePackageTypeContext build() {
            if (StringUtil.isNullOrEmpty(name)) {
                throw new IllegalArgumentException("name is null or empty");
            }

            if (StringUtil.isNullOrEmpty(description)) {
                throw new IllegalArgumentException("description is null or empty");
            }

            if (StringUtil.isNullOrEmpty(form)) {
                throw new IllegalArgumentException("form is null or empty");
            }

            return new CreatePackageTypeContext(name, description, form);
        }
    }
}
