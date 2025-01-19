package ru.hofftech.consolepackages.service.command.impl.editpackagetype;

import ch.qos.logback.core.util.StringUtil;
import ru.hofftech.consolepackages.service.command.CommandContext;

public record EditPackageTypeContext(
        String name,
        String form,
        String description) implements CommandContext {

    public static class Builder {
        private String name;
        private String form;
        private String description;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder form(String form) {
            this.form = form;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public EditPackageTypeContext build() {
            if (StringUtil.isNullOrEmpty(name)) {
                throw new IllegalArgumentException("name is null or empty");
            }

            if (StringUtil.isNullOrEmpty(form) || StringUtil.isNullOrEmpty(description)) {
                throw new IllegalArgumentException("form and description is null or empty");
            }

            return new EditPackageTypeContext(name, form, description);
        }
    }
}
