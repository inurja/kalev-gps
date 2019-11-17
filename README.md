CREATE TABLE gps.gps_data
(
    id uuid NOT NULL,
    x double precision NOT NULL,
    y double precision NOT NULL,
    speed double precision,
    date_time timestamp with time zone,
    CONSTRAINT gps_pkey_id PRIMARY KEY (id)
)

SAVE:
http://localhost:8080/rest/v1/gps/save

Content-Type: application/json
{
	"x": 57.85,
	"y": 28.98,
	"speed": 92.54
}

GET:
http://localhost:8080/rest/v1/gps/getGeoJson?lineString=true
http://localhost:8080/rest/v1/gps/getGeoJson?point=true