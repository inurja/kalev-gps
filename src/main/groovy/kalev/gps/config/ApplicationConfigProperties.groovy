package kalev.gps.config

enum ApplicationConfigProperties {
    SCHEMA_NAME_MAIN('grails.app.schema_name.main', 'gps')

    private final String propertyName
    private String defaultStringValue

    private ApplicationConfigProperties(String propertyName) {
        this.propertyName = propertyName
    }

    private ApplicationConfigProperties(String propertyName, String defaultStringValue) {
        this(propertyName)
        this.defaultStringValue = defaultStringValue
    }

    String getPropertyName() {
        return propertyName
    }

    Object getDefault(Object defaultValue = null) {
        if (defaultValue != null) {
            return defaultValue
        }
        if (defaultStringValue != null) {
            return defaultStringValue
        }
        return null
    }

    String getValueAsString() {
        return this.getValueAsString(null)
    }

    String getValueAsString(String defaultValue) {
        return ApplicationContextHolder.config.getProperty(propertyName, String, getDefault(defaultValue))
    }
}