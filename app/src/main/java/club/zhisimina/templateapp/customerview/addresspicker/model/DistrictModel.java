package club.zhisimina.templateapp.customerview.addresspicker.model;

/**
 * @author fupengpeng
 * @description
 * @date 2018/3/20 0020 15:20
 */
public class DistrictModel {

    private String id;
    private String name;
    private String zipcode;

    public DistrictModel() {
        super();
    }


    public DistrictModel(String name, String zipcode) {
        super();
        this.name = name;
        this.zipcode = zipcode;
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "DistrictModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
