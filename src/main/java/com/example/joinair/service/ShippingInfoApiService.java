package com.example.joinair.service;

import com.example.joinair.dto.api.ReqDronLocation;
import com.example.joinair.dto.api.ResDronLocation;
import com.example.joinair.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
@Transactional
public class ShippingInfoApiService {




    @Autowired
    private ShippingRepository shippingRepository;


    public ResDronLocation calDronPosition( ReqDronLocation reqDronLocation  ){
// /       System.out.println( "getDronLat => "  + reqDronLocation.getDronLat());
//        System.out.println("getDronLon =>  "  + reqDronLocation.getDronLon());
//
//        System.out.println( "getEndLat => "  + reqDronLocation.getEndLat());
//        System.out.println("getEndLon =>  "  + reqDronLocation.getEndLon());



        shippingRepository.findAll().stream().forEach(shipping -> {
            System.out.println(shipping.getShippingId());
            System.out.println(shipping.getAddress());
        });

        double mfs = 1.0;

        double bearing = getBearing(  reqDronLocation.getDronLat(), reqDronLocation.getDronLon(), reqDronLocation.getEndLat(), reqDronLocation.getEndLon()  );

        LatLon newDronLocation = computeDistancePosition(  reqDronLocation.getDronLat(), reqDronLocation.getDronLon(), bearing, mfs );

        // 도착 했는지 판단하는 로직.
        boolean isDist = false;

        double remainDist =  distanceMeter(reqDronLocation.getDronLat(), reqDronLocation.getDronLon(), reqDronLocation.getEndLat(), reqDronLocation.getEndLon());

        if(   remainDist <= mfs ) {
            isDist = true;
            newDronLocation = new LatLon(  reqDronLocation.getEndLat(), reqDronLocation.getEndLon() );
            // TODO : 배송 완료 로직 to db.

        } else {
            isDist = false;
        }

        // 도착 예정 시간.

        double speedKmh = mfs * 3.6;
        double remainDistKm = remainDist / 1000.0;

       // 시간=거리/속력
        int eteHour = (int) ( remainDistKm / speedKmh );
        int eteMin = (int) ((( remainDistKm / speedKmh) - eteHour) * 60 );

        //남은 시간
        System.out.println(String.format("%02d : %02d", eteHour % 100, eteMin));


        //도착 예정 시간.
        String etaDest = "-- : --";
        if ((eteHour > -1) && (eteMin > -1)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.HOUR, eteHour);
            cal.add(Calendar.MINUTE, eteMin);
            SimpleDateFormat sdformat = new SimpleDateFormat("HH : mm");
            etaDest = sdformat.format(cal.getTime());
        }
        final String finalEtaDest = etaDest;


        ResDronLocation resDronLocation = new ResDronLocation(newDronLocation.getLat(), newDronLocation.getLon(), isDist, finalEtaDest);

        return resDronLocation;
    }


    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private double distanceMeter(double sourceLat, double sourceLon, double destLat, double destLon) {
        double theta = sourceLon - destLon;
        double dist = Math.sin(deg2rad(sourceLat)) * Math.sin(deg2rad(destLat)) + Math.cos(deg2rad(sourceLat)) * Math.cos(deg2rad(destLat)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1609.344;
        return (dist);
    }

    private double getBearing(double lat1, double lon1, double lat2, double lon2) {
        double longitude1 = lon1;
        double longitude2 = lon2;
        double latitude1 = Math.toRadians(lat1);
        double latitude2 = Math.toRadians(lat2);
        double longDiff = Math.toRadians(longitude2 - longitude1);
        double y = Math.sin(longDiff) * Math.cos(latitude2);
        double x = ((Math.cos(latitude1) * Math.sin(latitude2)) - ((Math.sin(latitude1) * Math.cos(latitude2)) * Math.cos(longDiff)));
        return (Math.toDegrees(Math.atan2(y, x)) + 360) % 360;
    }

    private LatLon computeDistancePosition(double lat, double lon, double heading, double distance /*METER*/) {



        lon = Math.toRadians(lon);
        lat =  Math.toRadians(lat);
        heading = Math.toRadians(heading);

        double R = 6378137.0; // METER  radius of Earth 6,378.1370 km
        double lat2 = Math.asin((Math.sin(lat) * Math.cos(distance / R)) + (Math.cos(lat) * Math.sin(distance / R) * Math.cos(heading)));
        double lon2 = lon + Math.atan2((Math.sin(heading) * Math.sin(distance / R) * Math.cos(lat)), (Math.cos(distance / R) - (Math.sin(lat) * Math.sin(lat2))));

       // return Position.fromRadians(lat2, lon2);
        return new LatLon(Math.toDegrees(lat2), Math.toDegrees(lon2));

    }


    public class LatLon {
        double lat;
        double lon;

        public LatLon(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }
    }

}
