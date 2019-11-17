package kalev.gps

import kalev.gps.config.ApplicationConfigProperties

class GpsData {

    UUID id

    Double x
    Double y
    Double speed
    Date dateTime

    static mapping = {
        table name: 'gps_data', schema: ApplicationConfigProperties.SCHEMA_NAME_MAIN.valueAsString
        //id generator: 'native', column: 'id', params: [sequence: 'test']
        id generator: 'assigned', column: 'id', type: 'pg-uuid'
        version false
    }

    static constraints = {
        x           nullable: false
        y           nullable: false
        speed       nullable: true
        dateTime    nullable: true
    }
}
