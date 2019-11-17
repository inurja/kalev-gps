package kalev.gps

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/rest/v1/gps/getGeoJson/$id?"(controller: 'gpsData', action: 'get')
        '/rest/v1/gps/getGeoJson'(controller: 'gpsData', action: 'getGeoJson')
        '/rest/v1/gps/save'(controller: 'gpsData', action: 'save')

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
