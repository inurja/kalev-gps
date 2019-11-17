package kalev.gps

import grails.gorm.transactions.Transactional
import groovy.json.JsonOutput

@Transactional
class GpsDataService {

    def saveGpsData(Object json) {
        log.info 'Saving GPS data to database'
        def gpsData = new GpsData()
        def uuid = UUID.randomUUID()

        gpsData.id = uuid
        gpsData.x = json?.x
        gpsData.y = json?.y
        gpsData.speed = json?.speed
        gpsData.dateTime = new Date()

        println gpsData
        gpsData.save()
    }

    String getGeoJson(Map params) {
        String featureCollection = ''
        def gpsData = GpsData.list()

        if (params?.point || !params?.point && !params?.lineString) {
            def features =  gpsData.collect() {
                [
                        'type':     'Feature',
                        'geometry': ['type': 'Point', 'coordinates': [it?.y, it?.x]],
                        'properties': [
                                'id':       it?.id?.toString(),
                                'speed':    it?.speed,
                                'date':     it?.dateTime
                        ]
                ]
            }
            featureCollection = '{"type": "FeatureCollection","features":' + JsonOutput.toJson(features) + '}'
        } else if (params?.lineString) {
            def coordinates = []
            gpsData.each {
                coordinates.push([it?.y, it?.x])
            }
            def lineStringFeature =
                    [
                            'type':     'Feature',
                            'geometry': ['type': 'LineString', 'coordinates': coordinates]
                    ]
            featureCollection = '{"type": "FeatureCollection","features": [' + JsonOutput.toJson(lineStringFeature) + ']}'
        }
        return featureCollection
    }
}
