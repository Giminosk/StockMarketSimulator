package stockMarketing.model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;

/**
 *
 * @author Giminosk
 */
public class Currency {
    
    private StringProperty name;
    private StringProperty code;
    
    public Currency(String name, String code){
        this.name = new SimpleStringProperty(name);
        this.code = new SimpleStringProperty(code);
    }

    public Currency() {
        this(null, null);
    }
    
    public String toString(){
        return this.getCode();
    }
    
    public void setName(String name){
        this.name.set(name);
    }
    
    public String getName(){
        return this.name.get();
    }
    
    public StringProperty nameProperty(){
        return name;
    }

    public void setCode(String code){
        this.code.set(code);
    }
    
    public String getCode(){
        return this.code.get();
    }
    
    public StringProperty codeProperty(){
        return code;
    }
    
    
    
}
