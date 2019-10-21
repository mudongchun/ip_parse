package com.pt.ip;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Subdivision;
import org.apache.hadoop.hive.ql.exec.UDF;
import java.io.File;
import java.net.InetAddress;

public class IpParse extends UDF {
    public String evaluate(String ip){
        StringBuilder sb = new StringBuilder();
        File database = new File("/root/GeoLite2-City.mmdb");
        try {
            DatabaseReader reader = new DatabaseReader.Builder(database).build();
            InetAddress ipAddress = InetAddress.getByName(ip);
            CityResponse response = reader.city(ipAddress);
            Country country = response.getCountry();
            sb.append(country.getNames().get("zh-CN")).append("\t");

            Subdivision subdivision = response.getMostSpecificSubdivision();
            sb.append(subdivision.getNames().get("zh-CN")).append("\t");

            City city = response.getCity();
            sb.append(city.getNames().get("zh-CN")).append("\t");

            Location location = response.getLocation();
            sb.append(location.getLatitude().toString()).append("\t");
            sb.append(location.getLongitude().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
