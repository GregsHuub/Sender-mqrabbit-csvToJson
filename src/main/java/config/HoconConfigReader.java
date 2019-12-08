package config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class HoconConfigReader {

    public Config read(String configFileName){
        try{
        return ConfigFactory.load(configFileName);
        }catch(Exception e) {
            throw new HoconConfigException(configFileName, e);
        }

    }
}
