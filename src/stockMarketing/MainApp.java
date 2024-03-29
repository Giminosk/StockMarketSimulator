package stockMarketing;


import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import stockMarketing.model.*;
import stockMarketing.view.CommoditiesPanelController;
import stockMarketing.view.ControlPanelController;
import stockMarketing.view.StocksPanelController;
import stockMarketing.view.RootLayoutController;
import stockMarketing.view.StatisticsPanelController;

/**
 *
 * @author Giminosk
 */

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private ObservableList<Currency> currencyData = FXCollections.observableArrayList();
    private ObservableList<StockMarket> stockExchangeData  = FXCollections.observableArrayList();
    private ObservableList<Company> companyData  = FXCollections.observableArrayList();
    private ObservableList<Investor> investorData  = FXCollections.observableArrayList();
    private ObservableList<Commodity> commodityData = FXCollections.observableArrayList();
    private CommodityMarket commodityMarket;
    
    private Set<String> companyAbbreviationSet = new HashSet<String>();
    
    
    public MainApp(){
        this.currencyData.add(new Currency("Dollar", "USD"));
        this.currencyData.add(new Currency("Euro", "EUR"));
        this.currencyData.add(new Currency("Australia", "AUD"));
        this.currencyData.add(new Currency("Pound Sterling", "GBP"));
        this.currencyData.add(new Currency("Canadian Dollar", "CAD"));
        
        this.stockExchangeData.add(new StockMarket("Warsaw Stock Exchange", 0.05, "WSE", this.currencyData.get(0), "Poland", "Warsaw", "Książęca 4" ));
        
        this.commodityMarket = new CommodityMarket("Commodity Market", 0.02);

        this.companyData.add(new Company("Poland Stock Industry", "PSI", "Giminosk", "05.2004", 73.0, 200.5, 223.1, 7.4, 3.3, 3.1, 3.5,
                30000.0, 60000.0, 10000, 230000, 230000, 9.1, stockExchangeData.get(0)));
        companyAbbreviationSet.add("PSI");
        
        this.companyData.add(new Company("Nozki Zhawa Linisk Co", "NZL", "Jah Singh", "05.2009", 0.5, 1.5, 7.1, 7.4, 3.3, 3.1, 3.5,
                30000.0, 60000.0, 10000, 30000, 30000, 9.1, stockExchangeData.get(0)));
        companyAbbreviationSet.add("NZL");

        this.companyData.add(new Company("Competitive Russian Industrial ", "CRI", "Maksym Kuznethov", "07.2008", 23.0, 23.5, 23.1, 3.4, 3.3, 3.1, 3.5,
                30000.0, 60000.0, 10000, 20000, 20000, 9.1, stockExchangeData.get(0)));
        companyAbbreviationSet.add("CRI");

        this.companyData.add(new Company("Great Britain Stock", "GBS", "Alxandra Victor", "03.2006", 6.0, 8.5, 73.1, 7.4, 3.3, 3.1, 3.5,
                30000.0, 60000.0, 10000, 20000, 20000, 9.1, stockExchangeData.get(0)));
        companyAbbreviationSet.add("GBS");
        
        this.companyData.add(new Company("ASSECO Poland", "ASC", "Arckim Sarh", "06.2009", 8.0, 17.5, 13.1, 7.4, 3.3, 3.1, 3.5,
                30000.0, 60000.0, 10000, 50000, 50000, 9.1, stockExchangeData.get(0)));
        companyAbbreviationSet.add("ASC");

        this.companyData.add(new Company("State Machine Lots", "SML", "Jarcov Muhammad", "11.2006", 3.0, 3.5, 3.1, 3.4, 3.3, 3.1, 3.5,
                30000.0, 60000.0, 10000, 20000, 15000, 5.1, stockExchangeData.get(0)));
        companyAbbreviationSet.add("SML");


        this.commodityData.add(new Commodity("Gold", "Ounces", 150.0, 250.0, 255.0, 10000.0, 1000, 1000, 1000, 5.0, commodityMarket));
        this.commodityData.add(new Commodity("Silver", "Ounces", 100.0, 120.0, 105.0, 10000.0, 3000, 3000, 3000, 2.0, commodityMarket));
        this.commodityData.add(new Commodity("Natural gas", "Barrels", 0.5, 2.0, 4.0, 10000.0, 1000, 3000, 3000, 14.1, commodityMarket));
        this.commodityData.add(new Commodity("Lumber", "Cubic meters", 10.0, 88.0, 105.0, 10000.0, 1000, 4000, 3000, 14.1, commodityMarket));
        this.commodityData.add(new Commodity("Lemon", "Pounds", 10.0, 18.0, 15.0, 10000.0, 1000, 52000, 52000, 14.1, commodityMarket));
        this.commodityData.add(new Commodity("Bread", "Tons", 1.0, 10.0, 5.0, 10000.0, 1000, 5000, 5000, 7.0, commodityMarket));
        this.commodityData.add(new Commodity("Coffee", "Tons", 60.0, 110.0, 115.0, 10000.0, 1000, 88000, 88000, 14.1, commodityMarket));
        this.commodityData.add(new Commodity("Crude Oil", "Barrels", 100.0, 160.0, 165.0, 10000.0, 1000, 24000, 24000, -1.0, commodityMarket));
        this.commodityData.add(new Commodity("Rice", "Tons", 3.0, 10.0, 5.0, 10000.0, 1000, 20001, 30001, 14.1, commodityMarket));
        this.commodityData.add(new Commodity("Beans", "Tons", 1.0, 11.0, 19.0, 10000.0, 1000, 52000, 52000, 14.1, commodityMarket));

                       
        this.investorData.add(new Investor("Giminosk", "Belfort", "1", 12000.0, stockExchangeData.get(0)));
        this.investorData.add(new Investor("Mohammad", "Hazard", "2", 5000.0, stockExchangeData.get(0)));
        this.investorData.add(new Investor("Navinad", "Sangh", "3", 60000.0, stockExchangeData.get(0)));
        this.investorData.add(new Investor("Khalid", "Morries", "4", 1000.0, stockExchangeData.get(0)));
        this.investorData.add(new Investor("Alex", "Pan", "5", 5000.0, stockExchangeData.get(0)));
        this.investorData.add(new Investor("Andrei", "Sokolov", "6", 6000.0, stockExchangeData.get(0)));
        this.investorData.add(new Investor("Tony", "Stark", "7", 12000.0, stockExchangeData.get(0)));

        investorData.forEach(investor -> {
            investor.setCommodityMarketBelonging(commodityMarket);
            new Thread(investor).start();
        });

        companyData.forEach(company -> {
            stockExchangeData.get(0).getCompanies().add(company);
            new Thread(company).start();
        });
        
        commodityData.forEach(commodity -> {
            commodityMarket.getCommodities().add(commodity);
            new Thread(commodity).start();
        });

    }
    
    public ObservableList<Currency> getCurrencyData() {
        return currencyData;
    }
    
    public ObservableList<StockMarket> getStockExchangeData() {
        return stockExchangeData;
    }
    
    public ObservableList<Company> getCompanyData() {
        return companyData;
    }
    
    public ObservableList<Investor> getInvestorData() {
        return investorData;
    }
    
    public ObservableList<Commodity> getCommodityData() {
        return commodityData;
    }
    
    public CommodityMarket getCommodityMarket() {
        return commodityMarket;
    }
    
    public Set<String> getCompanyAbbreviationSet() {
        return companyAbbreviationSet;
    }
    

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Stock Marketing Observer");
        this.primaryStage.setOnCloseRequest(e -> System.exit(0));

        initRootLayout();

        showControlPanel();
        showStocksPanel();
        showCommoditiesPanel();   
        showStatisticPanel();
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();            

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            
            //Give the controller access to main app
            RootLayoutController controller = loader.getController();
            controller.setApp(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showControlPanel() {
        try {
            // Load control panel.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ControlPanel.fxml"));
            BorderPane controlPanel= (BorderPane) loader.load();
            
            // Set control panel into the TabPane of root layout.       
            Tab tab = new Tab();
            tab.setText("Control Panel");
            tab.setContent(controlPanel);
            
            VBox vbox = (VBox) rootLayout.getCenter();
            TabPane tabPane = (TabPane) vbox.getChildren().get(1);
            tabPane.getTabs().add(tab);
            
            // Give the controller access to the main app.
            ControlPanelController controller = loader.getController();
            controller.setApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showStocksPanel() {
        try {
            // Load control panel.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/StocksPanel.fxml"));
            BorderPane stocksPanel= (BorderPane) loader.load();
            
            // Set prices panel into the TabPane of root layout.                   
            Tab tab = new Tab();
            tab.setText("Stocks");
            tab.setContent(stocksPanel);
      
            VBox vbox = (VBox) rootLayout.getCenter();
            TabPane tabPane = (TabPane) vbox.getChildren().get(1);
            tabPane.getTabs().add(tab);            
            
            // Give the controller access to the main app.
            StocksPanelController controller = loader.getController();
            controller.setApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showCommoditiesPanel() {
        try {
            // Load control panel.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CommoditiesPanel.fxml"));
            BorderPane commoditiesPanel= (BorderPane) loader.load();
            
            // Set prices panel into the TabPane of root layout.                   
            Tab tab = new Tab();
            tab.setText("Commodities");
            tab.setContent(commoditiesPanel);
      
            VBox vbox = (VBox) rootLayout.getCenter();
            TabPane tabPane = (TabPane) vbox.getChildren().get(1);
            tabPane.getTabs().add(tab);            
            
            // Give the controller access to the main app.
            CommoditiesPanelController controller = loader.getController();
            controller.setApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showStatisticPanel() {
        try {
            // Load control panel.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/StatisticsPanel.fxml"));
            BorderPane statisitcsPanel= (BorderPane) loader.load();
            
            // Set prices panel into the TabPane of root layout.                   
            Tab tab = new Tab();
            tab.setText("Statistics");
            tab.setContent(statisitcsPanel);
      
            VBox vbox = (VBox) rootLayout.getCenter();
            TabPane tabPane = (TabPane) vbox.getChildren().get(1);
            tabPane.getTabs().add(tab);            
            
            // Give the controller access to the main app.
            StatisticsPanelController controller = loader.getController();
            controller.setApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    
    public File getFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */
    public void setFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
            primaryStage.setTitle("Stock Marketing Observer - " + file.getName());
        } else {
            prefs.remove("filePath");
            primaryStage.setTitle("Stock Marketing Observer");
        }
    }


    public void loadDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(XmlDataWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            commodityData.forEach(commodity -> {
                commodity.terminate();
            });
            companyData.forEach(company -> {
                company.terminate();
            });
            investorData.forEach(investor -> {
                investor.terminate();
            });

            // Reading XML from the file and unmarshalling.
            XmlDataWrapper wrapper = (XmlDataWrapper) um.unmarshal(file);

            currencyData.clear();
            currencyData.addAll(wrapper.getCurrencies());

            stockExchangeData.clear();
            stockExchangeData.addAll(wrapper.getStockExchanges());

            companyData.clear();
            companyAbbreviationSet.clear();
            companyData.addAll(wrapper.getCompanies());
            companyData.forEach(company -> {
                companyAbbreviationSet.add(company.getAbbreviation());
                company.setStockExchangeBelonging(this.getStockExchangeData().get(0));
                this.getStockExchangeData().get(0).getCompanies().add(company);

                new Thread(company).start();
            });

            commodityData.clear();
            commodityData.addAll(wrapper.getCommodities());
            commodityData.forEach(commodity -> {
               commodity.setCommodityMarketBelonging(commodityMarket);
               commodityMarket.getCommodities().add(commodity);

               new Thread(commodity).start();
            });

           investorData.clear();
           investorData.addAll(wrapper.getInvestors());
           investorData.forEach(investor -> {
               investor.setStockExchangeBelonging(this.getStockExchangeData().get(0));
               this.getStockExchangeData().get(0).getInvestors().add(investor);
               new Thread(investor).start();
           });

            // Save the file path to the registry.
            setFilePath(file);

        } catch (Exception e) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Could not load data");
        	alert.setContentText("Could not load data from file:\n" + file.getPath());

        	alert.showAndWait();
        }
    }

    public void saveDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(XmlDataWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping data.
            XmlDataWrapper wrapper = new XmlDataWrapper();
            wrapper.setCurrencies(currencyData);
            wrapper.setStockExchanges(stockExchangeData);
            wrapper.setCompanies(companyData);
            wrapper.setCommodities(commodityData);
            wrapper.setInvestors(investorData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setFilePath(file);
        } catch (Exception e) { // catches ANY exception
            e.printStackTrace();
        	Alert alert = new Alert(Alert.AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Could not save data");
        	alert.setContentText("Could not save data to file:\n" + file.getPath());

        	alert.showAndWait();
        }
    }

    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
