package club.zhisimina.templateapp.customerview.addresspicker.model;

import java.util.List;


/**
 * @author fupengpeng
 * @description
 * @date 2018/3/20 0020 15:20
 */
public class ProvinceModel {

    private String id;
    private String name;
    private List<CityModel> cityList;

    public ProvinceModel() {
        super();
    }

    public ProvinceModel(String name, List<CityModel> cityList) {
        super();
        this.name = name;
        this.cityList = cityList;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityModel> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityModel> cityList) {
        this.cityList = cityList;
    }

    @Override
    public String toString() {
        return "ProvinceModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cityList=" + cityList +
                '}';
    }
}
