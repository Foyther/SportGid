package android.kfu.service.api.response;

import android.kfu.entities.Map;

import java.util.List;

/**
 * Created by Nurislam on 19.05.2018.
 */
public class ListMapResult {

    List<Map> maps;

    public ListMapResult() {
    }

    public ListMapResult(List<Map> maps) {
        this.maps = maps;
    }

    public List<Map> getMaps() {
        return maps;
    }

    public void setMaps(List<Map> maps) {
        this.maps = maps;
    }
}
