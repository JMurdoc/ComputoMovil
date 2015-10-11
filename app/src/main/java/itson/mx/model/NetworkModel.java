package itson.mx.model;

/**
 * Created by Ulrick on 11/10/2015.
 */
public class NetworkModel {

    private String name;
    private boolean selected;

    public NetworkModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
