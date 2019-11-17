package kalev.gps

import grails.converters.JSON

class GpsDataController {

    static allowedMethods = [get: 'GET',
                             getGeoJson: 'GET',
                             save: 'POST',
                             delete: 'DELETE']

    GpsDataService gpsDataService

    def get() {
        def object
        if (params.id == '0' || params.id == '-1') {
            object = formatObject(null)
        } else {
            GpsData gpsData = GpsData.findById(UUID.fromString(params?.id?.toString()))//GpsData.findById(params?.id?.toInteger())
            object = formatObject((GpsData) gpsData.collect()[0])
        }
        HashMap response = [:]
        response.put('gpsData', object)
        render response as JSON
    }

    def getGeoJson() {
        params.point = Boolean.parseBoolean(params?.point?.toString()) ?: false
        params.lineString = Boolean.parseBoolean(params?.lineString?.toString()) ?: false
        println params
        def geoJsonAsString = gpsDataService.getGeoJson(params)
        render geoJsonAsString
    }

    def save() {
        def jsonObject = request.JSON
        gpsDataService.saveGpsData(jsonObject)
        render 'save called'
    }

    def delete() {

    }

    private static HashMap formatObject(GpsData gpsData) {
        def object = [
                id:     gpsData?.id?.toString() ?: '',
                x:      gpsData?.x,
                y:      gpsData?.y,
                speed:  gpsData?.speed,
                date:   gpsData?.dateTime
        ]
        return object
    }
}
